import java.awt.BasicStroke;
import java.awt.Graphics2D;

public abstract class XO {
	private Coordinate coord;
	
	public XO(Coordinate coord) {
		super();
		this.coord = coord;
	}
	
	public Coordinate getCoord() {
		return coord;
	}

	public void draw(Graphics2D g2d){
		g2d.setStroke(new BasicStroke(6.0f));
	}
}
