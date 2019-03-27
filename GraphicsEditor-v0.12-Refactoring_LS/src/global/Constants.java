package global;

import shape.Rectangle;
import shape.Shape;

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
		rectangle("네모", new Rectangle()),
		rectangle2("네모", new Rectangle()),
		rectangle3("네모", new Rectangle()),
		;
		private String text;
		private Shape shape;
		private EToolBar(String text, Shape shape) {
			this.text = text;
			this.shape = shape;
		}
		public String getText() {
			return this.text;
		}
		public Shape getShape() {
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
