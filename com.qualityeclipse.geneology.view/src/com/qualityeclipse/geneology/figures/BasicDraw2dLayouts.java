package com.qualityeclipse.geneology.figures;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.DelegatingLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.SimpleEtchedBorder;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.qualityeclipse.geneology.utilities.WindowHandler;

public class BasicDraw2dLayouts {
	public static void main(String[] args) {
		new BasicDraw2dLayouts().run();
	}

	private void run() {
		Display display = new Display();
		Shell shell = WindowHandler.constractShell(display);
		Figure root = WindowHandler.constractCanvasNRootFigureWithoutLayout(shell);

		// addBorderLayout(root);

		// addDelegatingLayout(root);

		// addFlowLayout(root);

		// addGridLayout(root);
		
		// /addStackLayout(root);

		// addToolBarLayout(root);

		// addXYLayout(root);

		WindowHandler.handleWindowOperation(display, shell);

	}

	private void addFlowLayout(Figure root) {
		// FlowLayout flowLayout = new FlowLayout();

		FlowLayout flowLayout = new FlowLayout(false);
		flowLayout.setMajorAlignment(FlowLayout.ALIGN_CENTER);
		flowLayout.setMajorSpacing(5);
		flowLayout.setMinorAlignment(flowLayout.ALIGN_CENTER);
		flowLayout.setMinorSpacing(20);

		root.setLayoutManager(flowLayout);
		Label one = new Label("one");
		one.setBorder(new LineBorder(2));
		Label two = new Label("two");
		two.setBorder(new LineBorder(2));
		Label three = new Label("three");
		three.setBorder(new LineBorder(2));
		Label four = new Label("four");
		four.setBorder(new LineBorder(2));
		root.add(one);
		root.add(two);
		root.add(three);
		root.add(four);
	}

	private void addDelegatingLayout(Figure root) {
		DelegatingLayout delegatingLayout = new DelegatingLayout();

		root.setLayoutManager(delegatingLayout);

		Label one = new Label("one");
		one.setBorder(SimpleEtchedBorder.singleton);
		root.add(one);
		delegatingLayout.setConstraint(one, new Locator() {
			@Override
			public void relocate(IFigure target) {
				target.setBounds(new Rectangle(0, 0, 100, 25));
			}
		});
		
		Label two = new Label("two");
		two.setBorder(SimpleEtchedBorder.singleton);
		root.add(two);
		root.setConstraint(two, new Locator() {
			
			@Override
			public void relocate(IFigure target) {
				target.setBounds(new Rectangle(25, 25, 100, 50));
			}
		});

		Label three = new Label("three");
		three.setBorder(SimpleEtchedBorder.singleton);
		root.add(three);
		root.setConstraint(three, new Locator() {

			@Override
			public void relocate(IFigure target) {
				target.setBounds(new Rectangle(50, 75, 100, 100));
			}
		});

		Label four = new Label("four");
		four.setBorder(SimpleEtchedBorder.singleton);
		root.add(four);
		root.setConstraint(four, new Locator() {

			@Override
			public void relocate(IFigure target) {
				target.setBounds(new Rectangle(75, 175, 125, 125));
			}
		});
	}

	private void addBorderLayout(Figure root) {
		BorderLayout borderLayout = new BorderLayout();
		root.setLayoutManager(borderLayout);

		Label top = new Label("top");
		root.add(top);
		borderLayout.setConstraint(top, BorderLayout.TOP);

		Label left = new Label("left");
		root.add(left);
		borderLayout.setConstraint(left, BorderLayout.LEFT);

		Label bottom = new Label("bottom");
		root.add(bottom);
		borderLayout.setConstraint(bottom, BorderLayout.BOTTOM);

		Label right = new Label("right");
		root.add(right);
		borderLayout.setConstraint(right, BorderLayout.RIGHT);
		borderLayout.setHorizontalSpacing(10);
		borderLayout.setVerticalSpacing(5);
		System.out.println("testing is going on");
	}

}
