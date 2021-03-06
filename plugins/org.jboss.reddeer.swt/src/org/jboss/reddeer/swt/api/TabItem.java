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
package org.jboss.reddeer.swt.api;

import org.jboss.reddeer.core.reference.ReferencedComposite;

/**
 * API for tab item manipulation.
 * 
 * @author apodhrad
 * 
 */
public interface TabItem extends Item<org.eclipse.swt.widgets.TabItem>, ReferencedComposite {

	/**
	 * Activates the tab item.
	 */
	void activate();
	
	/**
	 * Gets tooltip text of tab item
	 * @return tooltip text of tab item
	 */
	String getToolTipText();
	
	/**
	 * Gets parent of tab item
	 * @return TabFolder which is parent of tab item
	 */
	TabFolder getTabFolder();
}
