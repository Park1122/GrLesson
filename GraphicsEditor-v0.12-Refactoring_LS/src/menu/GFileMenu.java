package menu;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import global.Constants.EFileMenu;

public class GFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;

	private JMenuItem newItem;
	public GFileMenu(String text) {
		super(text);
		
		this.newItem = new JMenuItem(EFileMenu.newItem.getText());
		this.add(this.newItem);
	}
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
}
