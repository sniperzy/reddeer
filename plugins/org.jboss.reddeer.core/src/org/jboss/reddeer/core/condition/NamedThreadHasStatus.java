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
package org.jboss.reddeer.core.condition;

import java.util.Iterator;
import java.util.Set;

import org.hamcrest.Matcher;
import org.jboss.reddeer.common.condition.AbstractWaitCondition;

/**
 * Condition is met when thread with matching name has required state
 * 
 * @author vlado pakan
 * @contributor jkopriva@redhat.com
 * 
 */
public class NamedThreadHasStatus extends AbstractWaitCondition {

	private Matcher<String> nameMatcher;
	private Thread.State state;
	private boolean returnTrueIfDoesNotExist;
	private Set<Thread> currentThreads = Thread.getAllStackTraces().keySet();
	/**
	 * Condition is met when thread with name matching nameMatcher has state equals to state
	 * In case returnTrueIfDoesNotExist parameter is set to true condition is fulfilled also
	 * when matching thread does not exists 
	 * @param nameMatcher
	 * @param state
	 * @param returnTrueIfDoesNotExist
	 * 
	 */
	public NamedThreadHasStatus(Matcher<String> nameMatcher , Thread.State state, boolean returnTrueIfDoesNotExist) {
		this.nameMatcher = nameMatcher;
		this.state = state;
		this.returnTrueIfDoesNotExist = returnTrueIfDoesNotExist;
	}

	/* (non-Javadoc)
	 * @see org.jboss.reddeer.common.condition.WaitCondition#test()
	 */
	@Override
	public boolean test() {
		currentThreads = Thread.getAllStackTraces().keySet();
		Iterator<Thread> itThread = currentThreads.iterator();
		boolean threadNotFound = true;
		boolean hasState = false;
		while(threadNotFound && itThread.hasNext()){
			Thread thread = itThread.next();
			if (nameMatcher.matches(thread.getName())){
				threadNotFound = false;
				hasState = thread.getState().equals(state);
			}
		}
		return hasState || (threadNotFound && returnTrueIfDoesNotExist);
	}

	/* (non-Javadoc)
	 * @see org.jboss.reddeer.common.condition.AbstractWaitCondition#description()
	 */
	@Override
	public String description() {
		return "thread with name matching" + this.nameMatcher + " has state " + this.state;
	}

	/* (non-Javadoc)
	 * @see org.jboss.reddeer.common.condition.AbstractWaitCondition#errorMessageWhile()
	 */
	@Override
	public String errorMessageWhile() {
		return createErrorMessageWithThreadList("The following threads are still available:\n");
	}
	
	/* (non-Javadoc)
	 * @see org.jboss.reddeer.common.condition.AbstractWaitCondition#errorMessageUntil()
	 */
	@Override
	public String errorMessageUntil() {
		return createErrorMessageWithThreadList("The threads have not been found.The following threads are available:\n");
	}
	
	/**
	 * Creates error message for methods errorMessageWhile() and errorMessageUntil() with job names.
	 * 
	 * @param messageStart start of the error message with job list
	 */
	private String createErrorMessageWithThreadList(String messageStart){
		StringBuilder message = new StringBuilder(messageStart);
		for (Thread thread : currentThreads){
			if (thread != null){
				message.append(thread.getName());
				message.append(":");
				message.append(thread.getState());
				message.append("\n");
			}
		}
		return message.toString();
	}
}
