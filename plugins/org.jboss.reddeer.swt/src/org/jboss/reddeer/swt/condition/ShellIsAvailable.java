package org.jboss.reddeer.swt.condition;

import org.hamcrest.Matcher;
import org.jboss.reddeer.core.condition.ShellMatchingMatcherIsAvailable;
import org.jboss.reddeer.core.lookup.ShellLookup;
import org.jboss.reddeer.core.matcher.WithTextMatcher;
import org.jboss.reddeer.core.util.InstanceValidator;
import org.jboss.reddeer.swt.api.Shell;

/**
 * Wait condition for shells checking whether specified shell is available
 * 
 * @author rawagner
 */
public class ShellIsAvailable extends ShellMatchingMatcherIsAvailable {
	
	private Shell shell;
	
	/**
	 * Fulfilled, when available shell is equal to given shell.
	 * 
	 * @param shell Shell to compare to.
	 */
	public ShellIsAvailable(Shell shell){
		InstanceValidator.checkNotNull(shell, "shell");
		this.shell = shell;
	}
	
	/**
	 * Creates new ShellIsAvailable wait condition with specified matcher.
	 * @param matchers matchers to match shell
	 */
	public ShellIsAvailable(Matcher<?>... matchers) {
		super(matchers);
	}
	
	/**
	 * Creates new ShellIsAvailable wait condition with specified shell text.
	 * @param shellText shell text
	 */
	public ShellIsAvailable(String shellText) {
		super(new WithTextMatcher(shellText));
	}

	@Override
	public boolean test() {
		if(shell != null){
			for(org.eclipse.swt.widgets.Shell s: ShellLookup.getInstance().getShells()){
				if(shell.getSWTWidget().equals(s)){
					return true;
				}
			}
			return false;
		} 
		return super.test();
	}
	
	@Override
	public String description() {
		if(shell != null){
			return "shell is available";
		} 
		return super.description();
	}
	
	@Override
	public String errorMessageWhile() {
		if(shell != null){
			return "shell is still available";
		}
		return super.errorMessageWhile();
	}
	
	@Override
	public String errorMessageUntil() {
		if(shell != null){
			return "shell is not available";
		}
		return super.errorMessageUntil();
	}

}
