package com.qualityeclipse.geneology.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.widgets.Display;

import com.qualityeclipse.geneology.event.FigureMover;
/**
 * 
 * @author ashokv
 *
 */
public class PersonFigure extends Figure {

	public PersonFigure(String name, int dob, int dod, Figure root, ImageFigure image) {
		// setBackgroundColor(ColorConstants.lightGray);
		ToolbarLayout manager = new ToolbarLayout();
		manager.setSpacing(1);
		setLayoutManager(manager);

		setPreferredSize(110, 100);
//		setBorder(new LineBorder(1));
		 setBorder(new CompoundBorder(new LineBorder(1), new
		MarginBorder(2)));

		Figure imagenamedate = new Figure();
		GridLayout grid = new GridLayout(2, false);
		grid.marginHeight = 0;
		grid.marginWidth = 0;
		grid.horizontalSpacing = 1;
		imagenamedate.setLayoutManager(grid);
		add(imagenamedate);

		imagenamedate.add(image);

		Figure namedate = new Figure();
		namedate.setLayoutManager(new ToolbarLayout());
		imagenamedate.add(namedate, new GridData(GridData.FILL_HORIZONTAL));
		namedate.add(new Label(name));

		String date = dob + "-";
		if (dod != -1) {
			date += " " + dod;
		}
		namedate.add(new Label(date));
		
		// add(new NoteFigure(notes));

		/*
		 * add(new Label(date)); Label label = new Label(notes);
		 * label.setBorder(new NoteBorder()); add(label);
		 */

		/*
		 * add(new Label(notes) {
		 * 
		 * @Override protected void paintBorder(Graphics graphics) { Rectangle r
		 * = getBounds(); graphics.drawLine(r.x, r.y, r.x + r.width, r.y); }
		 * 
		 * @Override public Insets getInsets() { return new Insets(2, 0, 0, 0);
		 * } });
		 */
		new FigureMover(this);

	}

	@Override
	public void paintFigure(Graphics graphics) {

		Rectangle bound = getBounds();
		graphics.setBackgroundPattern(new Pattern(Display.getCurrent(), bound.x, bound.y, bound.x + bound.width,
				bound.y + bound.height, ColorConstants.white, ColorConstants.lightGray));
		graphics.fillRectangle(bound);
		super.paintFigure(graphics);
	}

}
