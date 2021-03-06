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
package org.jboss.reddeer.eclipse.test.jdt.ui.dialogs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jboss.reddeer.eclipse.core.resources.DefaultProject;
import org.jboss.reddeer.eclipse.exception.EclipseLayerException;
import org.jboss.reddeer.eclipse.jdt.ui.dialogs.GenerateHashCodeEqualsDialog;
import org.jboss.reddeer.eclipse.jdt.ui.packageview.PackageExplorerPart;
import org.jboss.reddeer.eclipse.jdt.ui.wizards.JavaProjectWizard;
import org.jboss.reddeer.eclipse.jdt.ui.wizards.NewClassCreationWizard;
import org.jboss.reddeer.eclipse.jdt.ui.wizards.NewClassWizardPage;
import org.jboss.reddeer.eclipse.jdt.ui.wizards.NewJavaProjectWizardPageOne;
import org.jboss.reddeer.eclipse.utils.DeleteUtils;
import org.jboss.reddeer.junit.runner.RedDeerSuite;
import org.jboss.reddeer.requirements.cleanworkspace.CleanWorkspaceRequirement.CleanWorkspace;
import org.jboss.reddeer.workbench.impl.shell.WorkbenchShell;
import org.jboss.reddeer.workbench.impl.editor.TextEditor;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@CleanWorkspace
@RunWith(RedDeerSuite.class)
public class GenerateHashAndEqualsTest {
	
	@AfterClass
	public static void deleteProject(){
		PackageExplorerPart pe = new PackageExplorerPart();
		pe.open();
		try{
			DefaultProject testProject = pe.getProject("GenHashProject");
			DeleteUtils.forceProjectDeletion(testProject,true);
		} catch (EclipseLayerException ele){
			// do nothing
		}
	}
	
	@Test
	public void generateHashAndEquals(){
		new WorkbenchShell().maximize();
		JavaProjectWizard jp = new JavaProjectWizard();
		jp.open();
		new NewJavaProjectWizardPageOne().setProjectName("GenHashProject");
		jp.finish();
		
		NewClassCreationWizard jc = new NewClassCreationWizard();
		jc.open();
		NewClassWizardPage jpp = new NewClassWizardPage();
		jpp.setName("GenHash");
		jc.finish();
		
		TextEditor te = new TextEditor("GenHash.java");
		te.insertText(3, 0, "private String text;");
		te.save();
		GenerateHashCodeEqualsDialog gd = new GenerateHashCodeEqualsDialog();
		gd.open(false);
		assertEquals(1,gd.getFields().size());
		assertEquals("text",gd.getFields().get(0).getFieldName());
		gd.selectAll();
		gd.ok();
		String text = te.getText();
		te.close();
		assertTrue(text.contains("equals"));
		assertTrue(text.contains("hashCode"));
	}

}
