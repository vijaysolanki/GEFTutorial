package com.qualityeclipse.geneology.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;

public class NoteFigure extends Label {
	public NoteFigure(String note) {
		super(note);
		// setOpaque(true);
		// setBackgroundColor(ColorConstants.white);
		setBorder(new NoteBorder());
	}

	@Override
	protected void paintFigure(Graphics graphics) {
		graphics.setBackgroundColor(ColorConstants.white);
		Rectangle r = getBounds();
		int fold = NoteBorder.FOLD;
		graphics.fillRectangle(r.x + fold, r.y, r.width - fold, fold);
		graphics.fillRectangle(r.x, r.y + fold, r.width, r.height - fold);

		super.paintFigure(graphics);
	}
}
