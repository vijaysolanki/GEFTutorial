package com.qualityeclipse.geneology.event;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.UpdateManager;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class FigureMover implements MouseListener, MouseMotionListener {

	private IFigure figure;
	private Point location;

	public FigureMover(IFigure figure) {
		this.figure = figure;
		this.figure.addMouseListener(this);
		this.figure.addMouseMotionListener(this);
	}

	@Override
	public void mouseDragged(MouseEvent me) {

		if (null == this.location) {
			return;
		}

		// 1. get the new location and different between new and old location.
		Point newLocation = me.getLocation();
		if (null == newLocation) {
			return;
		}

		// get the difference between old and new location.
		Dimension offset = newLocation.getDifference(location);
		if (offset.width == 0 && offset.height == 0) {
			return;
		}

		// 2. initialize old location with new location.
		location = newLocation;

		// 3. make the old location as dirty so that update manger redraw it
		// again.
		UpdateManager updateManager = figure.getUpdateManager();
		Rectangle bounds = figure.getBounds();
		updateManager.addDirtyRegion(figure.getParent(), bounds);
		
		// 4. add x and y points to bounds and return new bounds
		bounds = bounds.getCopy().translate(offset.width, offset.height);

		// 5. move figure with x and y points
		figure.translate(offset.width, offset.height);

		// 6. update layout of figure.
		LayoutManager layoutManager = figure.getParent().getLayoutManager();
		layoutManager.setConstraint(figure, bounds);


		// 7. make the new location as dirty so that update manger redraw it
		// again.
		updateManager.addDirtyRegion(figure.getParent(), bounds);

		// 8. make the event is already consumed, no other figure should not
		// listen further.
		me.consume();
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseHover(MouseEvent me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent me) {
		this.location = me.getLocation();
		me.consume();
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		if (null == location) {
			return;
		}

		location = null;
		me.consume();
	}

	@Override
	public void mouseDoubleClicked(MouseEvent me) {
		// TODO Auto-generated method stub

	}

}
