package com.qualityeclipse.geneology.view;


import org.eclipse.draw2d.ChopboxAnchor;

//import java.awt.Point;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.ImageUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.PolygonShape;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.qualityeclipse.geneology.event.FigureMover;
import com.qualityeclipse.geneology.figures.MarriageFigure;
import com.qualityeclipse.geneology.figures.NoteFigure;
import com.qualityeclipse.geneology.figures.PersonFigure;
import com.qualityeclipse.geneology.utilities.WindowHandler;

public class View extends ViewPart {
	public static final String ID = "com.qualityeclipse.geneology.view.view";

	private TableViewer viewer;


	class ViewLabelProvider extends LabelProvider  {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}

		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(
					ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		// viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
		// | SWT.V_SCROLL);
		// viewer.setContentProvider(ArrayContentProvider.getInstance());
		// viewer.setLabelProvider(new ViewLabelProvider());
		// // Provide the input to the ContentProvider
		// viewer.setInput(new String[] {"One", "Two", "Three"});
		createDiagram(parent);

	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		// viewer.getControl().setFocus();
	}
	
	
	/**
	 * Use main/normal thread to test your Draw2d code if it does not use any Eclipse Library. 
	 * @param args
	 */
	public static void main(String[] args) {
		new View().run();
	}
	
	public void run()
	{
		 Display display = new Display();
		 Shell shell = new Shell(display);
		 shell.setBackground(new Color(null, 255, 255, 255));
		 shell.setText("Genealogy View");
		 shell.setLayout(new GridLayout()); 
	
		 
		 Canvas diagram = createDiagram(shell);
		 diagram.setLayoutData(new GridData(GridData.FILL_BOTH));
		 
		 shell.open();
		 while(!shell.isDisposed())
		 {
			 while(!display.readAndDispatch())
			 {
				 display.sleep();
			 }
		 }
	}

	private Canvas createDiagram(Composite parent) {
		
		Figure root = new Figure();
		root.setFont(parent.getFont());
		XYLayout xyLayout = new XYLayout();
		root.setLayoutManager(xyLayout);
		
		// RectangleFigure father = createPersonFigure("Andy");
		PersonFigure father = new PersonFigure("Andy", 1960, 2050, root, WindowHandler.getImage(root, "Father"));
		father.add(new NoteFigure("Andy is \ngood Man"));
		root.add(father);
		xyLayout.setConstraint(father, new Rectangle(new Point(10, 10), father.getPreferredSize()));
		
		
		// RectangleFigure mother = createPersonFigure("Betty");
		PersonFigure mother = new PersonFigure("Betty", 1962, 2050, root, WindowHandler.getImage(root, "Mother"));
		mother.add(new NoteFigure("Betty is a \nnice lady"));
		// root.add(mother);
//		xyLayout.setConstraint(mother, new Rectangle(new Point(230, 10), mother.getPreferredSize()));
		root.add(mother, new Rectangle(new Point(230, 10), mother.getPreferredSize()));
		
		// RectangleFigure child = createPersonFigure("Carl");
		PersonFigure child = new PersonFigure("Carl", 2000, -1, root, WindowHandler.getImage(root, "Child"));
		child.add(new NoteFigure("Carl is \nsweety boy"));
		child.add(new NoteFigure("He lives in \nMA."));
		// root.add(child);
		// xyLayout.setConstraint(child, new Rectangle(new Point(120, 120),
		// mother.getPreferredSize()));
		root.add(child, new Rectangle(new Point(120, 120), child.getPreferredSize()));
		
		
		// PolygonShape marriage = createMarriageFigure(root);
		MarriageFigure marriage = new MarriageFigure(root, 1986);
		root.add(marriage, new Rectangle( new Point(145, 35), marriage.getPreferredSize()));
		
		root.add(connect(father, marriage));
		root.add(connect(mother, marriage));
		root.add(connect(child, marriage));
		
		NoteFigure note1 = new NoteFigure("Smith Family");
		// note1.setFont(root.getFont());
		Dimension size1 = note1.getPreferredSize();
		root.add(note1, new Rectangle(new Point(120, 250), size1));

		// NoteFigure note2 = new NoteFigure("Smith Family");
		// note2.setFont(root.getFont());
		// Dimension size2 = note2.getPreferredSize();
		// root.add(note2, new Rectangle(new Point(120, 350), size2));

		Canvas canvas = new Canvas(parent, SWT.DOUBLE_BUFFERED);
		canvas.setBackground(ColorConstants.white);
		
		LightweightSystem lightweightSystem = new LightweightSystem(canvas);
		lightweightSystem.setContents(root);
		
		return canvas;
		
		
	}

	private PolylineConnection connect(IFigure father, IFigure marriage) {
		PolylineConnection polylineConnection = new PolylineConnection();
		polylineConnection.setSourceAnchor(new ChopboxAnchor(father));
		polylineConnection.setTargetAnchor(new ChopboxAnchor(marriage));
		return polylineConnection;
		
	}

	private PolygonShape createMarriageFigure(Figure root) {

		Rectangle rectangle = new Rectangle(0, 0, 50, 50);
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setStart(rectangle.getTop());
		polygonShape.addPoint(rectangle.getTop());
		polygonShape.addPoint(rectangle.getLeft());
		polygonShape.addPoint(rectangle.getBottom());
		polygonShape.addPoint(rectangle.getRight());
		polygonShape.addPoint(rectangle.getTop());
		polygonShape.setEnd(rectangle.getTop());
		polygonShape.setFill(true);
		polygonShape.setPreferredSize(rectangle.getSize());
		polygonShape.setBackgroundColor(ColorConstants.lightGray);
		polygonShape.setLayoutManager(new XYLayout());

//		polygonShape.add(new Label("M"), new Rectangle(polygonShape.getBounds().x+10 , polygonShape.getBounds().y , 30, 30));
//		root.add(new Label("Marriage"), new Rectangle(new Point(145, 0), polygonShape.getPreferredSize()));

		Font font = root.getFont();
		FontData fontData = font.getFontData()[0];
		fontData.setHeight(6);

		Image verticalImage = ImageUtilities.createRotatedImageOfString("Marriage",
				new Font(Display.getCurrent(), fontData), ColorConstants.black,
				polygonShape.getBackgroundColor());
		// verticalImage.setBackground(ColorConstants.white);
		ImageFigure imageFigure = new ImageFigure(verticalImage);
		// imageFigure.setBackgroundColor(ColorConstants.white);
		// Label label = new Label();
		// label.setOpaque(false);
		// polygonShape.add(label);
		polygonShape.add(imageFigure, new Rectangle(polygonShape.getLocation(), polygonShape.getPreferredSize()));

		new FigureMover(polygonShape);
		return polygonShape;
		
	}

	private RectangleFigure createPersonFigure(String string) {
		RectangleFigure rectangleFigure = new RectangleFigure();
		rectangleFigure.setPreferredSize(100, 100);
		rectangleFigure.setLayoutManager(new ToolbarLayout());
		rectangleFigure.setBackgroundColor(ColorConstants.lightGray);
		
		rectangleFigure.add(new Label(string));
		
		new FigureMover(rectangleFigure);
		return rectangleFigure;
	}
	
	
	
}