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
package org.jboss.reddeer.jface.preference;

import org.eclipse.swt.custom.CLabel;
import org.hamcrest.Matcher;
import org.jboss.reddeer.common.wait.TimePeriod;
import org.jboss.reddeer.common.wait.WaitUntil;
import org.jboss.reddeer.common.wait.WaitWhile;
import org.jboss.reddeer.core.condition.WidgetIsFound;
import org.jboss.reddeer.core.lookup.ShellLookup;
import org.jboss.reddeer.core.matcher.WithTextMatcher;
import org.jboss.reddeer.jface.window.AbstractWindow;
import org.jboss.reddeer.swt.api.Shell;
import org.jboss.reddeer.swt.api.TreeItem;
import org.jboss.reddeer.swt.condition.ShellIsAvailable;
import org.jboss.reddeer.swt.impl.button.CancelButton;
import org.jboss.reddeer.swt.impl.button.OkButton;
import org.jboss.reddeer.swt.impl.clabel.DefaultCLabel;
import org.jboss.reddeer.swt.impl.shell.DefaultShell;
import org.jboss.reddeer.swt.impl.tree.DefaultTreeItem;

/**
 * Preference dialog implementation. 
 * 
 * @author Lucia Jelinkova
 *
 */
public class PreferenceDialog extends AbstractWindow{
	
	public PreferenceDialog(String text) {
		super(text);
	}

	public PreferenceDialog(Shell shell) {
		super(shell);
	}


	public PreferenceDialog(Matcher<?>...matchers) {
		super(matchers);
	}
	
	/**
	 * Selects the specified preference page <var>page</var>.
	 * @param page preference page to be opened
	 */
	public void select(PreferencePage page) {
		if (page == null) {
			throw new IllegalArgumentException("page can't be null");
		}
		select(page.getPath());
	}

	/**
	 * Selects preference page with the specified <var>path</var>.
	 * @param path path in preference shell tree to specific preference page
	 */
	public void select(String... path) {
		if (path == null) {
			throw new IllegalArgumentException("path can't be null");
		}
		if (path.length == 0) {
			throw new IllegalArgumentException("path can't be empty");
		}
		TreeItem t = new DefaultTreeItem(path);
		t.select();
		
		new WaitUntil(new WidgetIsFound(CLabel.class, new WithTextMatcher(path[path.length-1])), TimePeriod.SHORT, false);
	}
	
	/**
	 * Get name of the current preference page.
	 * 
	 * @return name of preference page
	 */
	public String getPageName() {
		DefaultCLabel cl = new DefaultCLabel();
		return cl.getText();
	}
	
	/**
	 * Presses Ok button on Property Dialog. 
	 */
	public void ok() {
		org.eclipse.swt.widgets.Shell parentShell = ShellLookup.getInstance().getParentShell(getShell().getSWTWidget());
		
		OkButton ok = new OkButton();
		ok.click();
		new WaitWhile(new ShellIsAvailable(getShell())); 
		new DefaultShell(parentShell);
	}
	
	/**
	 * Presses Cancel button on Property Dialog. 
	 */
	public void cancel() {
		org.eclipse.swt.widgets.Shell parentShell = ShellLookup.getInstance().getParentShell(getShell().getSWTWidget());
		
		CancelButton cancel = new CancelButton();
		cancel.click();
		new WaitWhile(new ShellIsAvailable(getShell())); 
		new DefaultShell(parentShell);
	}

	@Override
	public Class<?> getEclipseClass() {
		return org.eclipse.jface.preference.PreferenceDialog.class;
	}
}
