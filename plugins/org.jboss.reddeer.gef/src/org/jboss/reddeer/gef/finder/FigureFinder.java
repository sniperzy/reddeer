package org.jboss.reddeer.gef.finder;

import java.util.List;

import org.eclipse.draw2d.IFigure;

/**
 * 
 * @author apodhrad
 * 
 */
public class FigureFinder extends Finder<IFigure> {

	/* (non-Javadoc)
	 * @see org.jboss.reddeer.gef.finder.Finder#getChildren(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<IFigure> getChildren(IFigure child) {
		return child.getChildren();
	}

}
