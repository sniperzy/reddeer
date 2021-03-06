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
package org.jboss.reddeer.swt.impl.scale;

import org.hamcrest.Matcher;
import org.jboss.reddeer.swt.api.Scale;
import org.jboss.reddeer.core.handler.ScaleHandler;
import org.jboss.reddeer.core.reference.ReferencedComposite;
import org.jboss.reddeer.swt.widgets.AbstractControl;

/**
 * Abstract class for each Scale implementation
 * @author Vlado Pakan
 *
 */
public abstract class AbstractScale extends AbstractControl<org.eclipse.swt.widgets.Scale> implements Scale {

	protected AbstractScale(ReferencedComposite refComposite, int index, Matcher<?>... matchers) {
		super(org.eclipse.swt.widgets.Scale.class, refComposite, index, matchers);
	}
	
	protected AbstractScale(org.eclipse.swt.widgets.Scale widget){
		super(widget);
	}
	
	/**
	 * See {@link Scale}.
	 *
	 * @return the minimum
	 */
	@Override
	public int getMinimum() {
		return ScaleHandler.getInstance().getMinimum(this.getSWTWidget());
	}
	
	/**
	 * See {@link Scale}.
	 *
	 * @return the maximum
	 */
	@Override
	public int getMaximum() {
		return ScaleHandler.getInstance().getMaximum(this.getSWTWidget());
	}
	
	/**
	 * See {@link Scale}.
	 *
	 * @return the selection
	 */
	@Override
	public int getSelection() {
		return ScaleHandler.getInstance().getSelection(this.getSWTWidget());
	}
	
	/**
	 * See {@link Scale}.
	 *
	 * @param value the new selection
	 */
	@Override
	public void setSelection(int value) {
		ScaleHandler.getInstance().setSelection(this.getSWTWidget(), value);		
	}
}
