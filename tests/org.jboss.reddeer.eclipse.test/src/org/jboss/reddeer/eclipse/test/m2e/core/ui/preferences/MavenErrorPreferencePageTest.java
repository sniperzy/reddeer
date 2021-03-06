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
package org.jboss.reddeer.eclipse.test.m2e.core.ui.preferences;

import static org.junit.Assert.assertTrue;

import org.jboss.reddeer.eclipse.m2e.core.ui.preferences.WarningsPreferencePage;
import org.jboss.reddeer.eclipse.m2e.core.ui.preferences.WarningsPreferencePage.MavenErrorSeverity;
import org.jboss.reddeer.junit.runner.RedDeerSuite;
import org.jboss.reddeer.swt.exception.SWTLayerException;
import org.jboss.reddeer.workbench.ui.dialogs.WorkbenchPreferenceDialog;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test for MavenErrorPreferencePage
 * 
 * @author ldimaggi
 *
 */

@RunWith(RedDeerSuite.class)
public class MavenErrorPreferencePageTest {

	private WorkbenchPreferenceDialog preferencesDialog = new WorkbenchPreferenceDialog();	
	private WarningsPreferencePage mavenPreferencePage = new WarningsPreferencePage();

	@Test
	public void checkAllPreferences() {
		preferencesDialog.open();
		preferencesDialog.select(mavenPreferencePage);
				
		/* Change values - verify changes are made */
		mavenPreferencePage.setGroupId(MavenErrorSeverity.IGNORE);
		mavenPreferencePage.setVersion(MavenErrorSeverity.IGNORE);
		mavenPreferencePage.setProjectConfig(MavenErrorSeverity.IGNORE);
		mavenPreferencePage.setPluginExecution(MavenErrorSeverity.IGNORE);
		mavenPreferencePage.setManagedVersion(MavenErrorSeverity.IGNORE);
		
		assertTrue(mavenPreferencePage.getGroupId().equals(MavenErrorSeverity.IGNORE.getValue()));
		assertTrue(mavenPreferencePage.getVersion().equals(MavenErrorSeverity.IGNORE.getValue()));
		assertTrue(mavenPreferencePage.getProjectConfig().equals(MavenErrorSeverity.IGNORE.getValue()));
		assertTrue(mavenPreferencePage.getPluginExecution().equals(MavenErrorSeverity.IGNORE.getValue()));
		assertTrue(mavenPreferencePage.getManagedVersion().equals(MavenErrorSeverity.IGNORE.getValue()));
		
		preferencesDialog.cancel();
	}

	@After
	public void tearDown(){
		if(preferencesDialog.isOpen()){
			preferencesDialog.cancel();
		}
	}
}

