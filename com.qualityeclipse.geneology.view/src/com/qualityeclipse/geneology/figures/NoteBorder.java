package com.qualityeclipse.geneology.figures;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

public class NoteBorder extends AbstractBorder {

	public static final int FOLD = 10;

	@Override
	public Insets getInsets(IFigure figure) {
		return new Insets(1, 2 + FOLD, 2, 2);
	}

	@Override
	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		Rectangle r = figure.getBounds().getCopy();
		r.shrink(insets);
		graphics.setLineWidth(1);

		// // top
		graphics.drawLine(r.x + FOLD, r.y, r.x + r.width - 1, r.y);
		// // left
		graphics.drawLine(r.x, r.y + FOLD, r.x, r.y + r.height - 1);
		// // right
		graphics.drawLine(r.x + r.width - 1, r.y, r.x + r.width - 1, r.y + r.height - 1);
		// // bottom
		graphics.drawLine(r.x, r.y + r.height - 1, r.x + r.width - 1, r.y + r.height - 1);
		//
		// // solid short edges
		graphics.drawLine(r.x + FOLD, r.y, r.x + FOLD, r.y + FOLD);
		graphics.drawLine(r.x, r.y + FOLD, r.x + FOLD, r.y + FOLD);

		// gray small triangle
		graphics.setBackgroundColor(ColorConstants.lightGray);
		// graphics.drawLine(r.x, r.y + FOLD, r.x + FOLD, r.y);
		graphics.fillPolygon(new int[] { r.x, r.y + FOLD, r.x + FOLD, r.y, r.x + FOLD, r.y + FOLD });

		// dotted short diagonal line
		graphics.setLineStyle(SWT.LINE_DOT);
		graphics.drawLine(r.x, r.y + FOLD, r.x + FOLD, r.y);

	}

}
