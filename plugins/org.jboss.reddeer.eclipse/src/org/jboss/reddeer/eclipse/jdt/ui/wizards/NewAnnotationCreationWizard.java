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
package org.jboss.reddeer.eclipse.jdt.ui.wizards;

import org.jboss.reddeer.eclipse.topmenu.NewMenuWizard;

/**
 * Wizard dialog for creating an annotation.
 */
public class NewAnnotationCreationWizard extends NewMenuWizard {

	/**
	 * Constructs the wizard with "Java" &gt; "Annotation".
	 */
	public NewAnnotationCreationWizard() {
		super("New Annotation Type","Java", "Annotation");
	}
}
