package com.qualityeclipse.geneology.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GroupBoxBorder;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.SimpleEtchedBorder;
import org.eclipse.draw2d.SimpleLoweredBorder;
import org.eclipse.draw2d.SimpleRaisedBorder;
import org.eclipse.draw2d.TitleBarBorder;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.qualityeclipse.geneology.utilities.WindowHandler;

public class BasicBorders {
	public static void main(String... v) {
		new BasicBorders().run();
	}

	public void run() {
		Display display = new Display();
		Shell shell = WindowHandler.constractShell(display);

		Figure root = WindowHandler.constractCanvasNRootFigure(shell);

		addCompoundBorder(root);

		addFrameBorder(root);

		addGroupBoxBorder(root);

		addLineBorder(root);

		addSimpleEtchedBorder(root);

		addSimpleLowerBorder(root);

		addSimpleRaiseBorder(root);

		addTitleBorder(root);

		WindowHandler.handleWindowOperation(display, shell);
	}

	private void addTitleBorder(Figure root) {
		Label label = new Label("TitleBorder");
		label.setBorder(new TitleBarBorder("MyTitle"));
		root.add(label, new Rectangle(340, 80, 150, 60));
	}

	private void addSimpleRaiseBorder(Figure root) {
		Label label = new Label("SimpleRaiseBorder");
		label.setBorder(new SimpleRaisedBorder(3));
		root.add(label, new Rectangle(160, 80, 150, 60));
	}

	private void addSimpleLowerBorder(Figure root) {
		Label label = new Label("SimpleLowerBorder");
		label.setBorder(new SimpleLoweredBorder(3));
		root.add(label, new Rectangle(10, 70, 150, 60));
	}

	private void addSimpleEtchedBorder(Figure root) {
		Label label = new Label("SimpleEtchedBorder");
		label.setBorder(SimpleEtchedBorder.singleton);
		root.add(label, new Rectangle(120, 10, 140, 50));
	}

	private void addLineBorder(Figure root) {
		Label label = new Label("Lineborder");
		label.setBorder(new LineBorder(ColorConstants.blue, 3, Graphics.LINE_DASH));
		root.add(label, new Rectangle(10, 10, 100, 50));
	}

	private void addGroupBoxBorder(Figure root) {
		Label label = new Label("GroupBoxBorder");
		label.setBorder(new GroupBoxBorder("MyGroup"));
		root.add(label, new Rectangle(new Point(400, 10), label.getPreferredSize()));
	}

	private void addFrameBorder(Figure root) {
		Label label = new Label("Frameborder");
		label.setBorder(new FrameBorder("My Title"));
		root.add(label, new Rectangle(270, 10, 100, 60));
	}

	private void addCompoundBorder(Figure root) {
		Label label = new Label("CompoundBorder\n with Compound and Margin Border");
		label.setBorder(new CompoundBorder(new GroupBoxBorder("MyGroup"), new MarginBorder(10)));
		root.add(label, new Rectangle(10, 150, 350, 120));
	}

}
