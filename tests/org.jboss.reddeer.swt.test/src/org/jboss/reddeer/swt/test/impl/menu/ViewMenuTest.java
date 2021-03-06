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
package org.jboss.reddeer.swt.test.impl.menu;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.jboss.reddeer.common.condition.AbstractWaitCondition;
import org.jboss.reddeer.common.wait.WaitUntil;
import org.jboss.reddeer.common.wait.WaitWhile;
import org.jboss.reddeer.core.exception.CoreLayerException;
import org.jboss.reddeer.junit.runner.RedDeerSuite;
import org.jboss.reddeer.swt.condition.ShellIsAvailable;
import org.jboss.reddeer.swt.impl.button.PushButton;
import org.jboss.reddeer.swt.impl.shell.DefaultShell;
import org.jboss.reddeer.swt.impl.text.DefaultText;
import org.jboss.reddeer.swt.test.handler.ParameterizedHandler;
import org.jboss.reddeer.swt.test.handler.ViewActionWithId;
import org.jboss.reddeer.workbench.impl.menu.ViewMenu;
import org.jboss.reddeer.workbench.impl.view.WorkbenchView;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RedDeerSuite.class)
public class ViewMenuTest {

	@Test
	public void testErrorLogMenu() {
		new WorkbenchView("Error Log").open();
		new ViewMenu("Filters...").select();
		new DefaultShell("Log Filters");
		new PushButton("OK").click();
		new WaitWhile(new ShellIsAvailable("Log Filters"));
		new WaitWhile(new ShellIsAvailable("Progress Information"));
	}

	@Test
	public void testCheckStyledMenus() {
		new WorkbenchView("Error Log").open();
		ViewMenu filter = new ViewMenu("Show text filter");
		boolean selected = filter.isSelected();
		if (selected) {
			filter.select();
			assertFalse(filter.isSelected() == selected);
			try {
				new DefaultText();
				fail();
			} catch (CoreLayerException ex) {
				// search text field is hidden = ok
			}
		} else {
			filter.select();
			assertFalse(filter.isSelected() == selected);
			try {
				new DefaultText();
			} catch (CoreLayerException ex) {
				fail();
			}
		}
	}

	@Test
	public void parameterizedViewMenuItemTest() {
		// open View
		new WorkbenchView("RedDeer SWT").open();

		// click menu item A
		new ViewMenu("submenu", "parameterizedMenuA").select();
		assertTrue(ParameterizedHandler.isToggledA());
		assertFalse(ParameterizedHandler.isToggledB());

		// click menu item B
		new ViewMenu("submenu", "parameterizedMenuB").select();
		assertTrue(ParameterizedHandler.isToggledA());
		assertTrue(ParameterizedHandler.isToggledB());
	}

	@Test
	public void actionWithIdViewMenuTest() {
		// open View
		new WorkbenchView("RedDeer SWT").open();
		
		// click Action With Id Menu
		assertFalse(ViewActionWithId.isToggled());
		new ViewMenu("View Action with ID").select();
		new WaitUntil(new ViewWithActionIdIsToggled());
		assertTrue(ViewActionWithId.isToggled());
	}

	private class ViewWithActionIdIsToggled extends AbstractWaitCondition {

		@Override
		public boolean test() {
			return ViewActionWithId.isToggled();
		}

		@Override
		public String description() {
			return "ViewWithActionIsToggled.";
		}

	}

}
