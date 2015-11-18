package org.jboss.reddeer.eclipse.rse.ui.wizard;

import org.jboss.reddeer.jface.wizard.WizardPage;
import org.jboss.reddeer.swt.impl.tree.DefaultTreeItem;

/**
 * This class represents first wizard page of the New Connection wizard 
 * and it let user choose type of the remote system.
 * @author Pavol Srna
 *
 */
public class NewConnectionWizardSelectionPage extends WizardPage{
	
	
	/**
	 * Select remote system type in tree.
	 *
	 * @param type the type
	 */
	public void selectSystemType(SystemType type){
		new DefaultTreeItem("General", type.getLabel()).select(); 
	}
	
	/**
	 * Enumeration of supported remote system types 
	 */
	public enum SystemType {
		FTP_ONLY("FTP Only"), LINUX("Linux"), LOCAL("Local"), SSH_ONLY("SSH Only"), 
		TELNET_ONLY("Telnet Only (Experimental)"), UNIX("Unix"), WINDOWS("Windows");
		
		private String label;
		
		/**
		 * Instantiates a new system type.
		 *
		 * @param label the label
		 */
		SystemType(String label) {
			this.label = label;
		}
		
		/**
		 * Gets the label.
		 *
		 * @return the label
		 */
		public String getLabel() {
			return label;
		}
	}

}
