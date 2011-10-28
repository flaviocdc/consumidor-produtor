package br.ufrj.dcc.so.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class ThreadsPanel extends JPanel {

	private JList consumidoresList;
	private JList produtoresList;
	
	public ThreadsPanel() {
		super();
		
		init();
	}
	
	public void init() {
		consumidoresList = new JList(new String[] { "Teste1", "Teste2" });
		consumidoresList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		consumidoresList.setLayoutOrientation(JList.VERTICAL);
		consumidoresList.setVisibleRowCount(-1);
		consumidoresList.setBorder(BorderFactory.createTitledBorder("Consumidores"));
		
		/*JScrollPane consumidoresListScroller = new JScrollPane(consumidoresList);
		consumidoresListScroller.setSize(100, 100);
		*/
		produtoresList = new JList(new String[] { "Teste3", "Teste4" });
		produtoresList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		produtoresList.setLayoutOrientation(JList.VERTICAL);
		produtoresList.setVisibleRowCount(-1);
		produtoresList.setBorder(BorderFactory.createTitledBorder("Produtores"));
	/*	
		JScrollPane produtoresListScroller = new JScrollPane(produtoresList);
		produtoresListScroller.setPreferredSize(new Dimension(100, 100));
	*/	
		setLayout(new BorderLayout(10, 10));
		
		add(consumidoresList, BorderLayout.WEST);
		add(produtoresList, BorderLayout.EAST);
	}

}
