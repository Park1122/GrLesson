package global;

import shape.GShape;
import shape.GRectangle;
import shape.GPolygon;

public class Constants {
	
	public enum EMainFrame {
		x(200),
		y(100),
		w(400),
		h(600),
		;
		
		private int value;
		private EMainFrame(int value) {
			this.value = value;
		}
		public int getValue() {
			return this.value;
		}
	}
	public enum EToolBar {
		rectangle("네모", new GRectangle()),
		rectangle2("네모", new GRectangle()),
		polygon("폴리곤", new GPolygon()),
		;
		private String text;
		private GShape shape;
		private EToolBar(String text, GShape shape) {
			this.text = text;
			this.shape = shape;
		}
		public String getText() {
			return this.text;
		}
		public GShape getShape() {
			return this.shape;
		}
	}
	
	public enum EMenu {
		fileMenu("파일"),
		editMenu("Edit"),
		;
		private String text;
		private EMenu(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
	}
	public enum EFileMenu {
		newItem("new"),
		openItem("open"),
		;
		private String text;
		private EFileMenu(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
	}
}
