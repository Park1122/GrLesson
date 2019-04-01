package drawingPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import global.Constants.EToolBar;
import shape.Shape;

public class DrawingPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private enum EAcitonState {
		eReady, e2PDrawing, eNPDrawing
	}; // 상태

	private EAcitonState eAcitonState;
	private MouseHandler mouseHandler;

	private Vector<Shape> shapeVector;
	private Shape currentShape;
	private Shape currentTool;

	public void setCurrentTool(EToolBar currentTool) {
		this.currentTool = currentTool.getShape();
	}

	public DrawingPanel() {
		this.eAcitonState = EAcitonState.eReady;

		this.setBackground(Color.WHITE);

		this.mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(this.mouseHandler);

		this.shapeVector = new Vector<Shape>();
		this.currentTool = EToolBar.rectangle.getShape();
	}

	public void paint(Graphics graphics) {
		super.paint(graphics);

		for (Shape shape : this.shapeVector) {
			shape.draw(graphics);
		}
	}

	private void drawShape() {
		Graphics graphics = this.getGraphics();
		graphics.setXORMode(getBackground());
		this.currentShape.draw(graphics);
	}

	private void initDrawing(int x, int y) {
		this.currentShape = this.currentTool.clone();
		this.currentShape.setOrigin(x, y);
		this.drawShape();
	}

	private void keepDrawing(int x, int y) { // 마우스 움직일때만 작동.
		this.drawShape();
		this.currentShape.setPoint(x, y); // 무빙의 경우에는 원점이 움직이도록, 리사이즈 할때는 가로 세로 길이가 바뀌도록.
		this.drawShape();
	}

	private void continueDrawing(int x, int y) { // 움직이다가 점 클릭.
		this.currentShape.addPoint(x, y);
	}

	private void finishDrawing(int x, int y) { // 더블클릭하면 끝. 그러나 폴리곤은 마지막점이 원점 연결.
		this.shapeVector.add(this.currentShape);
	}

	private class MouseHandler implements MouseListener, MouseMotionListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				mouse1Clicked(e);
			} else if (e.getClickCount() == 2) {
				mouse2Clicked(e);
			}
		}

		public void mouse2Clicked(MouseEvent e) {
			if (eAcitonState == EAcitonState.eNPDrawing) {
				finishDrawing(e.getX(), e.getY());
				eAcitonState = EAcitonState.eReady;
			}
		}

		public void mouse1Clicked(MouseEvent e) {
			if (eAcitonState == EAcitonState.eReady) {
				initDrawing(e.getX(), e.getY());
				eAcitonState = EAcitonState.eNPDrawing;
			} else if (eAcitonState == EAcitonState.eNPDrawing) {
				continueDrawing(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (eAcitonState == EAcitonState.eNPDrawing) {
				keepDrawing(e.getX(), e.getY());
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (eAcitonState == EAcitonState.eReady) {
				initDrawing(e.getX(), e.getY());
				eAcitonState = EAcitonState.e2PDrawing;
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (eAcitonState == EAcitonState.e2PDrawing) {
				finishDrawing(e.getX(), e.getY());
				eAcitonState = EAcitonState.eReady;
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (eAcitonState == EAcitonState.e2PDrawing) {
				keepDrawing(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
}
