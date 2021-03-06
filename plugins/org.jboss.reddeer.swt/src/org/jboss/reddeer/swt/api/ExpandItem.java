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
import org.jboss.reddeer.common.wait.TimePeriod;

/**
 * API for expand bar item manipulation.
 * 
 * @author Vlado Pakan
 *
 */
public interface ExpandItem extends ReferencedComposite, Item<org.eclipse.swt.widgets.ExpandItem> {

	/**
	 * Expands the expand bar item.
	 */
	void expand();

	/**
	 * Expands expand bar item and wait for specific time period.
	 * 
	 * @param timePeriod time period to wait
	 */
	void expand(TimePeriod timePeriod);

	/**
	 * Collapses expand bar item.
	 */
	void collapse();

	/**
	 * Returns parent expand bar.
	 * 
	 * @return parent RedDeer expand bar
	 */
	ExpandBar getParent();

	/**
	 * Finds out whether expand bar item is collapsed or not.
	 * 
	 * @return true if expand bar item is collapsed, false otherwise
	 */
	boolean isExpanded();
}
