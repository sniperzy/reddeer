/******************************************************************************* 
 * Copyright (c) 2017 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.jboss.reddeer.workbench.topmenu;

import org.hamcrest.Matcher;
import org.jboss.reddeer.core.matcher.WithTextMatcher;
import org.jboss.reddeer.jface.wizard.WizardDialog;
import org.jboss.reddeer.swt.api.Shell;
import org.jboss.reddeer.workbench.api.TopMenuOpenable;

/**
 * Represents all WizardDialog dialogs openable from workbench shell menu
 * @author rawagner
 *
 */
public abstract class TopMenuWizardDialog extends WizardDialog implements TopMenuOpenable{
	
	private Matcher<String> matcher;
	private String[] path;
	
	/**
	 * 
	 * @param text of WizardDialog
	 * @param path workbench shell menu path to open dialog
	 */
	public TopMenuWizardDialog(String text, String... path){
		this(new WithTextMatcher(text),path);
	}
	
	/**
	 * 
	 * @param matcher to match WizardDialog
	 * @param path workbench shell menu path to open dialog
	 */
	public TopMenuWizardDialog(Matcher<String> matcher, String... path){
		super((Shell)null);
		this.matcher = matcher;
		this.path = path;
		isOpen();
	}
	
	@Override
	public Matcher<String> getShellMatcher() {
		return matcher;
	}
	
	@Override
	public String[] getMenuPath() {
		return path;
	}
	
}
