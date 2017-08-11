import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class X extends XO {
	
	public X(Coordinate coord) {
		super(coord);
	}

	@Override
	public void draw(Graphics2D g2d) {
		super.draw(g2d);
		g2d.setColor(Color.RED);
		g2d.draw(new Line2D.Float(getCoord().getX(), getCoord().getY(), getCoord().getX()+50, getCoord().getY()+50));
		g2d.draw(new Line2D.Float(getCoord().getX()+50, getCoord().getY(), getCoord().getX(), getCoord().getY()+50));
	}

}
