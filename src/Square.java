import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
//import ColumnRowID.java;

public class Square {

	public double width, height, x, y;
	public Color color;
	public boolean owned = false, checkable = false;
	public ColumnRowID columnRowID;
	public ArrayList <Square> neighbors = new ArrayList<Square>();

	public Square(double width, double height, int columnNumber, int rowNumber, Color c) {
		this.width = width;
		this.height = height;
		columnRowID = new ColumnRowID(columnNumber, rowNumber);
		setCoordinates();
		this.color = c;
	}
	
	private void setCoordinates(){
		this.x = columnRowID.columnNumber * width;
		this.y = columnRowID.rowNumber * height;
	}
	
	public int getColumn() {
		return columnRowID.columnNumber;
	}
	
	public int getRow() {
		return columnRowID.rowNumber;
	}
	
	public void addNeighbor(Square s){
		neighbors.add(s);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);
		g2.setColor(color);
		g2.fill(rect);
	}

}
