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
package org.jboss.reddeer.eclipse.ui.dialogs;

import org.jboss.reddeer.workbench.topmenu.TopMenuWizardDialog;

/**
 * Represents Eclipse NewWizard that can be found in File, New, Other... menu
 * 
 * @author vpakan
 * @since 0.6
 *
 */
public class NewWizard extends TopMenuWizardDialog{
	
	public static final String DIALOG_TITLE = "New";
	
	public NewWizard() {
		super(DIALOG_TITLE,"File","New","Other...");
	}

	
	
	
}
