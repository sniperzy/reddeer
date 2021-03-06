/******************************************************************************* 
 * Copyright (c) 2016 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.jboss.reddeer.eclipse.test.jdt.ui.junit;

import static org.junit.Assert.assertEquals;

import org.jboss.reddeer.common.wait.WaitWhile;
import org.jboss.reddeer.eclipse.jdt.junit.ui.TestRunnerViewPart;
import org.jboss.reddeer.eclipse.jdt.ui.packageview.PackageExplorerPart;
import org.jboss.reddeer.eclipse.jdt.ui.wizards.JavaProjectWizard;
import org.jboss.reddeer.eclipse.jdt.ui.wizards.NewClassCreationWizard;
import org.jboss.reddeer.eclipse.jdt.ui.wizards.NewClassWizardPage;
import org.jboss.reddeer.eclipse.jdt.ui.wizards.NewJavaProjectWizardPageOne;
import org.jboss.reddeer.eclipse.utils.DeleteUtils;
import org.jboss.reddeer.junit.runner.RedDeerSuite;
import org.jboss.reddeer.swt.condition.ShellIsAvailable;
import org.jboss.reddeer.swt.impl.button.FinishButton;
import org.jboss.reddeer.swt.impl.button.NextButton;
import org.jboss.reddeer.swt.impl.list.DefaultList;
import org.jboss.reddeer.swt.impl.menu.ContextMenu;
import org.jboss.reddeer.swt.impl.shell.DefaultShell;
import org.jboss.reddeer.workbench.impl.editor.TextEditor;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * 
 * @author apodhrad
 *
 */
@RunWith(RedDeerSuite.class)
public class JUnitRunTest {

	private static final String PROJECT_NAME = "hellotest";

	@BeforeClass
	public static void createTestProject() {
		JavaProjectWizard projectWizard = new JavaProjectWizard();
		projectWizard.open();
		new NewJavaProjectWizardPageOne().setProjectName(PROJECT_NAME);
		projectWizard.finish();

		NewClassCreationWizard classWizard = new NewClassCreationWizard();
		classWizard.open();
		new NewClassWizardPage().setName("HelloTest");
		classWizard.finish();

		PackageExplorerPart explorer = new PackageExplorerPart();
		explorer.open();

		explorer.getProject(PROJECT_NAME).select();
		new ContextMenu("Build Path", "Add Libraries...").select();
		new DefaultShell("Add Library");
		new DefaultList().select("JUnit");
		new NextButton().click();
		new FinishButton().click();
		new WaitWhile(new ShellIsAvailable("Add Library"));

		explorer.getProject("hellotest").getProjectItem("src", "hellotest", "HelloTest.java").open();
		TextEditor editor = new TextEditor("HelloTest.java");
		editor.setText("package hellotest;\n\n"
				+ "import org.junit.After;\nimport org.junit.Assert;\nimport org.junit.Test;\n\n"
				+ "public class HelloTest {\n\n"
				+ "\t@Test\n\tpublic void badTest() {\n\t\tAssert.assertEquals(5, 2 + 2);\n\t}\n\n"
				+ "\t@Test\n\tpublic void veryBadTest() {\n\t\tthrow new RuntimeException();\n\t}\n\n"
				+ "\t@After\n\tpublic void waitAfter() throws Exception {\n\t\tThread.sleep(5000);\n\t}" + "\n}");
		editor.save();
		editor.close();
	}

	@AfterClass
	public static void deleteTestProject() {
		PackageExplorerPart explorer = new PackageExplorerPart();
		explorer.open();
		DeleteUtils.forceProjectDeletion(explorer.getProject(PROJECT_NAME), true);
	}

	@Test
	public void junitRunTest() {
		PackageExplorerPart explorer = new PackageExplorerPart();
		explorer.open();
		explorer.getProject(PROJECT_NAME).getProjectItem("src", "hellotest", "HelloTest.java").runAsJUnitTest();

		TestRunnerViewPart junitView = new TestRunnerViewPart();
		junitView.open();

		assertEquals("2/2", junitView.getRunStatus());
		assertEquals(1, junitView.getNumberOfErrors());
		assertEquals(1, junitView.getNumberOfFailures());
	}
}
