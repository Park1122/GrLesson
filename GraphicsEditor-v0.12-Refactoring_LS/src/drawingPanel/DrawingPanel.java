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
	}; // ����

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

	private void keepDrawing(int x, int y) { // ���콺 �����϶��� �۵�.
		this.drawShape();
		this.currentShape.setPoint(x, y); // ������ ��쿡�� ������ �����̵���, �������� �Ҷ��� ���� ���� ���̰� �ٲ��.
		this.drawShape();
	}

	private void continueDrawing(int x, int y) { // �����̴ٰ� �� Ŭ��.
		this.currentShape.addPoint(x, y);
	}

	private void finishDrawing(int x, int y) { // ����Ŭ���ϸ� ��. �׷��� �������� ���������� ���� ����.
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
