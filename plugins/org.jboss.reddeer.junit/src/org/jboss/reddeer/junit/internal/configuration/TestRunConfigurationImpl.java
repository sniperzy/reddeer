package org.jboss.reddeer.junit.internal.configuration;

import java.io.File;

import org.jboss.reddeer.junit.internal.configuration.reader.XMLReader;

/**
 * Configuration associated with one configuration file representing one run of the tests. 
 * 
 * @author Lucia Jelinkova
 *
 */
public class TestRunConfigurationImpl implements TestRunConfiguration {

	private File file;
	
	private String id;
	
	private XMLReader configurationReader;
	
	private RequirementsConfiguration requirementsConfiguration;
	
	/**
	 * Instantiates a new test run configuration impl.
	 *
	 * @param file the file
	 */
	public TestRunConfigurationImpl(File file) {
		this.file = file;
	}
	
	/* (non-Javadoc)
	 * @see org.jboss.reddeer.junit.internal.configuration.TestRunConfiguration#getId()
	 */
	public String getId() {
		if (id == null){
			id = file.getName();
		}
		return id;
	}
	
	/* (non-Javadoc)
	 * @see org.jboss.reddeer.junit.internal.configuration.TestRunConfiguration#getRequirementConfiguration()
	 */
	public RequirementsConfiguration getRequirementConfiguration(){
		if (requirementsConfiguration == null){
			requirementsConfiguration = new RequirementsConfigurationImpl(getConfigurationReader());
		}
		return requirementsConfiguration;
	}
	
	private XMLReader getConfigurationReader() {
		if (configurationReader == null){
			configurationReader = new XMLReader(file);
		}
		return configurationReader;
	}
}
