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
package org.jboss.reddeer.core.handler;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Item;
import org.jboss.reddeer.common.util.Display;
import org.jboss.reddeer.common.util.ResultRunnable;

/**
 * Contains methods for handling UI operations on {@link Item} widget
 * @author rawagner
 *
 */
public class ItemHandler extends WidgetHandler{
	
	
	private static ItemHandler instance;
	
	/**
	 * Gets instance of ItemHandler.
	 * 
	 * @return instance of ItemHandler
	 */
	public static ItemHandler getInstance(){
		if(instance == null){
			instance = new ItemHandler();
		}
		return instance;
	}
	
	/**
	 * Gets image of item
	 * @param swtItem to handle
	 * @return image of specified item
	 */
	public Image getImage(Item swtItem){
		return Display.syncExec(new ResultRunnable<Image>() {

			@Override
			public Image run() {
				return swtItem.getImage();
			}
		});
	}
	
	/**
	 * Gets text of item
	 * @param swtItem item to handle
	 * @return text of specified item
	 */
	public String getText(Item swtItem){
		return Display.syncExec(new ResultRunnable<String>() {

			@Override
			public String run() {
				return swtItem.getText();
			}
		});
	}
	
	

}
