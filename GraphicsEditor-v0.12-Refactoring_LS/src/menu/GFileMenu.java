package menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import global.Constants.EFileMenu;

public class GFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	
	public GFileMenu(String text) {
		super(text);
		
		ActionHandler actionHandler = new ActionHandler();
		
		for(EFileMenu eMenuItem: EFileMenu.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getText());
			menuItem.setActionCommand(eMenuItem.getMethod());
			menuItem.addActionListener(actionHandler);
			add(menuItem);
		}
	}
	
	public void initialize() {
	}
	
	public void nnew() {
	}
	
	public void open() {
	}
	
	public void save() {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				new BufferedOutputStream(
						new FileOutputStream(file)));
		objectOutputStream.writeObject(this.getDrawingPanel().getShapeVector());
		objectOutputStream.close();
	}
	
	public void saveAs() {
	}
	
	public void close() {
	}
	
	private void invokeMethod(String name) {
		try {
			this.getClass().getMethod(name).invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException 
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
	}
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			invokeMethod(event.getActionCommand());
		}
	}
}
