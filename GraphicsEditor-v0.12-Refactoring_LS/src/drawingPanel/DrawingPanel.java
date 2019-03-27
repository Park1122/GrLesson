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
	
	private void keepDrawing(int x, int y, int clickcount) { // ���콺 �����϶��� �۵�.
		this.drawShape();
		this.currentTool.setPoint(x, y);
		this.drawShape();
	}
	private void continueDrawing(int x, int y) { //�����̴ٰ� �� Ŭ��.
		this.currentTool.addPoint(x, y);
		
	}
	private void finishDrawing(int x, int y) { //����Ŭ���ϸ� ��. �׷��� �������� ���������� ���� ����.
		this.drawShape();
		this.currentTool.setPoint(x, y);
		this.drawShape();
	}
	
	private class MouseHandler implements MouseListener, MouseMotionListener{
		@Override
		public void mouseClicked(MouseEvent e) { //1�� 
			//polygon - ó�����̸� init �ƴϸ� continue
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
