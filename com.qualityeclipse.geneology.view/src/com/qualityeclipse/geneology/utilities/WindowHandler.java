package com.qualityeclipse.geneology.utilities;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.ImageUtilities;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class WindowHandler {

	public static Shell constractShell(Display display) {
		Shell shell = new Shell(display);
		shell.setText("Basic Draw2d Borders");
		shell.setLayout(new GridLayout());
		return shell;
	}

	public static Figure constractCanvasNRootFigure(Shell shell) {

		Canvas canvas = new Canvas(shell, SWT.DOUBLE_BUFFERED);
		canvas.setBackground(ColorConstants.tooltipForeground);
		canvas.setLayoutData(new GridData(GridData.FILL_BOTH));
		LightweightSystem lws = new LightweightSystem(canvas);

		Figure root = new Figure();
		root.setLayoutManager(new XYLayout());
		lws.setContents(root);
		return root;
	}

	public static Figure constractCanvasNRootFigureWithoutLayout(Shell shell) {

		Canvas canvas = new Canvas(shell, SWT.DOUBLE_BUFFERED);
		canvas.setBackground(ColorConstants.lightGreen);
		canvas.setLayoutData(new GridData(GridData.FILL_BOTH));
		LightweightSystem lws = new LightweightSystem(canvas);
		Figure root = new Figure();
		// root.setBackgroundColor(ColorConstants.blue);
		// root.setLayoutManager(new XYLayout());
		lws.setContents(root);
		return root;
	}

	public static void handleWindowOperation(Display display, Shell shell) {
		shell.open();
		while (!shell.isDisposed()) {
			while (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public static ImageFigure getImage(IFigure root, String name) {
		FontData fontData = root.getFont().getFontData()[0];
		Image createRotatedImageOfString = ImageUtilities.createRotatedImageOfString(name,
				new Font(Display.getCurrent(), fontData), ColorConstants.red, ColorConstants.lightGray);

		ImageFigure imageFigure = new ImageFigure(createRotatedImageOfString);
		return imageFigure;
	}
}
