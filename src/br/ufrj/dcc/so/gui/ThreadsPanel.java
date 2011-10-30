package br.ufrj.dcc.so.gui;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import br.ufrj.dcc.so.Main;

public class ThreadsPanel extends JPanel {

	private TabelaEstados tabelaConsumidores;
	private TabelaEstados tabelaProdutores;
	
	public ThreadsPanel() {
		super();
		
		init();
	}
	
	public void init() {
		tabelaConsumidores = new TabelaEstados(Main.instance().getConsumidores());
		tabelaConsumidores.setBorder(BorderFactory.createTitledBorder("Consumidores"));

		tabelaProdutores = new TabelaEstados(Main.instance().getProdutores());
		tabelaProdutores.setBorder(BorderFactory.createTitledBorder("Produtores"));
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		add(tabelaConsumidores);
		add(tabelaProdutores);
	}

}
