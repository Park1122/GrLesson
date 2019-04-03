package shape;

import java.awt.Graphics;

public class Polygon extends Shape {
	private final static int nMaxPoints = 20;
	private int[] xPoints;
	private int[] yPoints;
	private int nPoints;
	public Polygon() {
		this.nPoints = 0;
		this.xPoints = new int[nMaxPoints];
		this.yPoints = new int[nMaxPoints];	
	}
	public void setOrigin(int x, int y) {
		this.xPoints[this.nPoints] = x; //0번째 점
		this.yPoints[this.nPoints] = y;
		this.nPoints = this.nPoints + 1;
		
		this.xPoints[this.nPoints] = x; //1번째 점
		this.yPoints[this.nPoints] = y;
		this.nPoints = this.nPoints + 1;
	}

	public void setPoint(int x, int y) {
		this.xPoints[this.nPoints-1] = x;
		this.yPoints[this.nPoints-1] = y;
	}
	public void addPoint(int x, int y) {
		this.xPoints[this.nPoints] = x;
		this.yPoints[this.nPoints] = y;
		this.nPoints = this.nPoints + 1;
	}
	public void draw(Graphics graphics) {
		graphics.drawPolygon(xPoints, yPoints, nPoints);
	}
}
