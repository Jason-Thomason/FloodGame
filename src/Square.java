import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Square {

	public double width, height, x, y;
	public Color color;
	public boolean owned = false, checkable = true;

	public Square() {

	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);
		g2.setColor(color);
		g2.fill(rect);
	}

}
