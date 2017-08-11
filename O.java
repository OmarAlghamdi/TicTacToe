import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class O extends XO {
	
	public O(Coordinate coord) {
		super(coord);
	}

	@Override
	public void draw(Graphics2D g2d) {
		super.draw(g2d);
		g2d.setColor(Color.BLUE);
		g2d.draw(new Ellipse2D.Float(getCoord().getX(), getCoord().getY(), 50, 50));
	}

}
