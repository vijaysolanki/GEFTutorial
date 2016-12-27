package com.qualityeclipse.geneology.figures;


import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.ImageUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolygonShape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.qualityeclipse.geneology.event.FigureMover;

public class MarriageFigure extends PolygonShape {

	public MarriageFigure(IFigure root, int marriageyear) {
		Rectangle rectangle = new Rectangle(0, 0, 50, 50);
		setStart(rectangle.getTop());
		addPoint(rectangle.getTop());
		addPoint(rectangle.getLeft());
		addPoint(rectangle.getBottom());
		addPoint(rectangle.getRight());
		addPoint(rectangle.getTop());
		setEnd(rectangle.getTop());
		setFill(true);
		setBackgroundColor(ColorConstants.lightGray);
		setPreferredSize(rectangle.getSize().expand(1, 1));

		setLayoutManager(new StackLayout());

		add(new Label(Integer.toString(marriageyear)));

		// addVerticalLabel(root);
		new FigureMover(this);
	}

	private void addVerticalLabel(IFigure root) {
		// setLayoutManager(new XYLayout());
		GridLayout manager = new GridLayout();
		setLayoutManager(manager);
		FontData fontData = root.getFont().getFontData()[0];
		fontData.setHeight(5);
		Image createRotatedImageOfString = ImageUtilities.createRotatedImageOfString("Marriage",
				new Font(Display.getCurrent(), fontData), ColorConstants.red, ColorConstants.lightGray);

		ImageFigure imageFigure = new ImageFigure(createRotatedImageOfString);
		// this.add(imageFigure, new Rectangle(imageFigure.getLocation(),
		// this.getPreferredSize()));
		this.add(imageFigure, new GridData(GridData.FILL_BOTH));
	}
}
