package main;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import drawingPanel.DrawingPanel;
import global.Constants.EMainFrame;
import menu.MenuBar;
import toolbar.ToolBar;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// components
	private MenuBar menuBar;
	private ToolBar toolBar;
	private DrawingPanel drawingPanel;
	
	public MainFrame() {
		// attribute
		this.setLocation(EMainFrame.x.getValue(), EMainFrame.y.getValue());
		this.setSize(EMainFrame.w.getValue(), EMainFrame.h.getValue());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		// components
		this.menuBar = new MenuBar();
		this.setJMenuBar(this.menuBar);
		
		this.toolBar = new ToolBar();
		this.add(this.toolBar, BorderLayout.NORTH);
		
		this.drawingPanel = new DrawingPanel();
		this.add(this.drawingPanel, BorderLayout.CENTER);
		
		//associations
		this.menuBar.associate(this.drawingPanel);
		this.toolBar.associate(this.drawingPanel);
	}
}
