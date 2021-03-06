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
package org.jboss.reddeer.junit.internal.runner;

import org.junit.runner.Runner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.parameterized.ParametersRunnerFactory;
import org.junit.runners.parameterized.TestWithParameters;

/**
 * ParametersRunnerFactory for creating ParameterizedRequirementsRunners.
 * 
 * @author rhopp
 *
 */
public class ParameterizedRequirementsRunnerFactory implements ParametersRunnerFactory {

	/* (non-Javadoc)
	 * @see org.junit.runners.parameterized.ParametersRunnerFactory#createRunnerForTestWithParameters(org.junit.runners.parameterized.TestWithParameters)
	 */
	@Override
	public Runner createRunnerForTestWithParameters(TestWithParameters test) throws InitializationError {
		return new ParameterizedRequirementsRunner(test);
	}

}
