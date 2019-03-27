package drawingPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import global.Constants.EToolBar;
import shape.Shape;

public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private MouseHandler mouseHandler;
	
	private Shape currentTool;
	public void setCurrentTool(EToolBar currentTool) {
		this.currentTool = currentTool.getShape();
	}
	
	public DrawingPanel() {
		this.setBackground(Color.WHITE);
		
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(this.mouseHandler);
		
		currentTool = EToolBar.rectangle.getShape();
	}
	
	private void drawShape() {
		Graphics graphics = this.getGraphics();
		graphics.setXORMode(getBackground());
		this.currentTool.draw(graphics);
	}
	
	private void initDrawing(int x, int y) {
		this.currentTool.setOrigin(x, y);
		this.drawShape();
	}
	
	private void keepDrawing(int x, int y, int clickcount) { // 마우스 움직일때만 작동.
		this.drawShape();
		this.currentTool.setPoint(x, y);
		this.drawShape();
	}
	private void continueDrawing(int x, int y) { //움직이다가 점 클릭.
		this.currentTool.addPoint(x, y);
		
	}
	private void finishDrawing(int x, int y) { //더블클릭하면 끝. 그러나 폴리곤은 마지막점이 원점 연결.
		this.drawShape();
		this.currentTool.setPoint(x, y);
		this.drawShape();
	}
	
	private class MouseHandler implements MouseListener, MouseMotionListener{
		@Override
		public void mouseClicked(MouseEvent e) { //1번 
			//polygon - 처음점이면 init 아니면 continue
			if(e.getClickCount() == 1) {
				mouse1Click(e);
			} else if(e.getClickCount() == 2) {
				mouse2Click(e);
			}
		}
		
		public void mouse1Click(MouseEvent e) {
			initDrawing(e.getX(), e.getY());
			finishDrawing(e.getX(), e.getY());
		}
		
		public void mouse2Click(MouseEvent e) {
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			

		}
		@Override
		public void mouseMoved(MouseEvent e) {
			keepDrawing(e.getX(), e.getY(), e.getClickCount());
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
}
