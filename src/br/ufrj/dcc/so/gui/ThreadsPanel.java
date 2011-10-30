package br.ufrj.dcc.so.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
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
		consumidoresList.setPreferredSize(new Dimension(150, 150));

		JScrollPane consumidoresListScroller = new JScrollPane(consumidoresList);
		consumidoresListScroller.setSize(100, 100);

		produtoresList = new JList(new String[] { "Teste3", "Teste4" });
		produtoresList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		produtoresList.setLayoutOrientation(JList.VERTICAL);
		produtoresList.setVisibleRowCount(-1);
		produtoresList.setBorder(BorderFactory.createTitledBorder("Produtores"));
		produtoresList.setPreferredSize(new Dimension(150, 150));
		
		JScrollPane produtoresListScroller = new JScrollPane(produtoresList);
		produtoresListScroller.setPreferredSize(new Dimension(100, 100));
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		add(consumidoresList);
		add(produtoresList);
	}

}
