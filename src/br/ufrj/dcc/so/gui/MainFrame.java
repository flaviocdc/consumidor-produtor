package br.ufrj.dcc.so.gui;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	
	private JPanel threadsPanel;
	private JPanel mainPanel;
	
	public MainFrame() {
		super("Prog2");
	}
	
	public void init() {
		mainPanel = new JPanel();
		threadsPanel = new ThreadsPanel();

		mainPanel.setOpaque(true);
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		setContentPane(mainPanel);
		
		add(threadsPanel);
		
		setSize(500, 500);
		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
