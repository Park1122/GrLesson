package shape;

import java.awt.Graphics2D;

import shape.GAnchors.EAnchors;

public abstract class GShape implements Cloneable{
	
	public enum EOnState {eOnShape, eOnResize, eOnRotate};
	
	protected java.awt.Shape shape;
	protected int px;
	protected int py;
	private GAnchors anchors;
	
	private boolean selected;
	public boolean isSelected() { return selected; }
	public void setSelected(boolean selected) {
		this.selected = selected;
		if(this.selected) {
			this.anchors.setBoundingRect(this.shape.getBounds());
		}
	}
	
	public GShape() {
		this.selected = false;
		this.anchors = new GAnchors();
	}
	
	abstract public void setOrigin(int x, int y);
	abstract public void setPoint(int x, int y);
	abstract public void addPoint(int x, int y);
	
	public void initMoving(Graphics2D graphics2d, int x, int y) {
		this.px = x;
		this.py = y;
		if(!this.selected) {
			this.selected = true;
			this.anchors.setBoundingRect(this.shape.getBounds());
			this.anchors.draw(graphics2d);
		}
	}
	abstract public void keepMoving(int x, int y);
	abstract public void finishMoving(int x, int y);
	
	public GShape clone() { // 자기와 똑같이 생긴 메모리를 그대로 복제하는 능력
		try {
			return (GShape)this.getClass().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void draw(Graphics2D graphics2d) {
		graphics2d.draw(this.shape);
		if(this.selected) {
			this.anchors.setBoundingRect(this.shape.getBounds());
			this.anchors.draw(graphics2d);
		}
	}
	
	public EOnState onShape(int x, int y) {
		if(this.selected) {
			EAnchors eAnchor = this.anchors.onShape(x, y);
			if(eAnchor == EAnchors.RR) {
				return EOnState.eOnRotate;
			} else if(eAnchor == null) {
				if(this.shape.contains(x,y)) {
					return EOnState.eOnShape;
				}
			} else {
				return EOnState.eOnResize;
			}
		} else {
			if(this.shape.contains(x,y)) {
				return EOnState.eOnShape;
			}
		}
		return null;
	}

}
