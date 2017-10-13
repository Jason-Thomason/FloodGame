import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
//import ColumnRowID.java;

public class Square {

	public double width, height, x, y;
	public Color color;
	public boolean owned = false, checkable = true;
	public ColumnRowID columnRowID;

	public Square(double width, double height, double x, double y, Color c) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y =y;
		this.color = c;
	}
	
	public void setColumnRowID(int columnNumber, int rowNumber) {
		columnRowID.columnNumber = columnNumber;
		columnRowID.rowNumber = rowNumber;
	}
	
	public int getColumn() {
		return columnRowID.columnNumber;
	}
	
	public int getRow() {
		return columnRowID.rowNumber;
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);
		g2.setColor(color);
		g2.fill(rect);
	}

}
