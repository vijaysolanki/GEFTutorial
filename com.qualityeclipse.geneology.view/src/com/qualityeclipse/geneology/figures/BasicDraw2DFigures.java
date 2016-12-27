package com.qualityeclipse.geneology.figures;

import org.eclipse.draw2d.ArrowButton;
import org.eclipse.draw2d.Button;
import org.eclipse.draw2d.CheckBox;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.ImageUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.Polygon;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.Triangle;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class BasicDraw2DFigures {

	public static void main(String[] args) {
		new BasicDraw2DFigures().drawBasicFigures();
	}

	public void drawBasicFigures() {

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Basic Draw2d Figures");
		shell.setLayout(new GridLayout());
		// shell.setSize(200, 200);

		Figure root = constractCanvasNRootFigure(shell);

		addEllipseFigure(root);

		addPolygon(root);

		addPolyLine(root);

		addRectangeFigure(root);

		addRoundedRectange(root);

		addTriangle(root);

		addArrowButton(root);

		addButton(root);

		addCheckBox(root);

		addLabel(root);

		addRotatedText(shell, root);

		handleWindow(display, shell);
	}

	private void addRotatedText(Shell shell, Figure root) {
		Image createRotatedImageOfString = ImageUtilities.createRotatedImageOfString("Rotated text", shell.getFont(),
				ColorConstants.white, ColorConstants.orange);
		ImageFigure imageFigure = new ImageFigure(createRotatedImageOfString);

		root.add(imageFigure, new Rectangle(new Point(10, 120), imageFigure.getPreferredSize()));
	}

	private void addLabel(Figure root) {
		Label label = new Label("this is label");
		label.setBorder(new LineBorder(1));
		root.add(label, new Rectangle(new Point(300, 60), label.getPreferredSize()));
	}

	private void addCheckBox(Figure root) {
		CheckBox checkBox = new CheckBox("this is check box");
		root.add(checkBox, new Rectangle(150, 60, 150, 20));
	}

	private void addButton(Figure root) {
		Button button = new Button("this is button");
		root.add(button, new Rectangle(40, 60, 100, 20));
	}

	private void addArrowButton(Figure root) {
		ArrowButton arrowButton = new ArrowButton();
		arrowButton.setDirection(PositionConstants.EAST);
		root.add(arrowButton, new Rectangle(10, 60, 20, 20));
	}

	private void addTriangle(Figure root) {
		Triangle triangle = new Triangle();
		root.add(triangle, new Rectangle(220, 10, 60, 40));
	}

	private void addRoundedRectange(Figure root) {
		RoundedRectangle roundedRectangle = new RoundedRectangle();
		roundedRectangle.setPreferredSize(60, 40);
		roundedRectangle.setBackgroundColor(ColorConstants.cyan);
		roundedRectangle.setCornerDimensions(new Dimension(10, 10));
		root.add(roundedRectangle, new Rectangle(new Point(80, 10), roundedRectangle.getPreferredSize()));
	}

	private void addRectangeFigure(Figure root) {
		RectangleFigure rectangleFigure = new RectangleFigure();
		rectangleFigure.setBackgroundColor(ColorConstants.lightGreen);
		rectangleFigure.setFill(true);
		rectangleFigure.setPreferredSize(60, 40);
		root.add(rectangleFigure, new Rectangle(new Point(10, 10), rectangleFigure.getPreferredSize()));
	}

	private void addPolyLine(Figure root) {
		Polyline polyLine = new Polyline();
		polyLine.addPoint(new Point(400, 10));
		polyLine.addPoint(new Point(460, 10));
		polyLine.addPoint(new Point(440, 50));
		polyLine.addPoint(new Point(500, 50));
		root.add(polyLine, new Rectangle(polyLine.getStart(), polyLine.getPreferredSize()));
	}

	private void addPolygon(Figure root) {
		Polygon polygon = new Polygon();
		polygon.addPoint(new Point(290, 10));
		polygon.addPoint(new Point(350, 10));
		polygon.addPoint(new Point(390, 50));
		polygon.addPoint(new Point(330, 50));
		polygon.setFill(true);
		polygon.setBackgroundColor(ColorConstants.lightBlue);
		root.add(polygon, new Rectangle(polygon.getStart(), polygon.getPreferredSize()));
	}

	private void handleWindow(Display display, Shell shell) {
		shell.open();
		while (!shell.isDisposed()) {
			while (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private void addEllipseFigure(Figure root) {
		Ellipse ellipse = new Ellipse();
		ellipse.setBackgroundColor(ColorConstants.lightGray);
		ellipse.setPreferredSize(60, 40);
		root.add(ellipse, new Rectangle(new Point(150, 10), ellipse.getPreferredSize()));
	}

	private Figure constractCanvasNRootFigure(Shell shell) {

		Canvas canvas = new Canvas(shell, SWT.DOUBLE_BUFFERED);
		canvas.setBackground(ColorConstants.white);
		canvas.setLayoutData(new GridData(GridData.FILL_BOTH));
		LightweightSystem lws = new LightweightSystem(canvas);

		Figure root = new Figure();
		root.setLayoutManager(new XYLayout());
		lws.setContents(root);
		return root;
	}
}
