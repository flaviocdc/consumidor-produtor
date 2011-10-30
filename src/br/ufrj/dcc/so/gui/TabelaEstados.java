package br.ufrj.dcc.so.gui;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import br.ufrj.dcc.so.ThreadComEstados;

public class TabelaEstados extends JTable {

	private final static String[] NOME_COLUNAS = { "Thread", "Estado" };
	
	private DefaultTableModel model = new DefaultTableModel();
	private List<? extends ThreadComEstados> threads;
	
	public TabelaEstados(List<? extends ThreadComEstados> paramThreads) {
		super();
		
		threads = paramThreads;

		for (String column : NOME_COLUNAS)
			model.addColumn(column);
		
		setModel(model);
		
		initModelData();
		
		setPreferredSize(new Dimension(200, 200));
		
		new Thread(new Atualizador(), "Atualizador de tabelas").start();
	}

	private void initModelData() {
		for (ThreadComEstados t : threads) {
			model.addRow(new Object[] { t.getName(), t.getEstado() });
		}
	}
	
	public void atualizarTabela() {
		while (model.getRowCount() != 0)
			model.removeRow(0);
		
		initModelData();
	}
	
	private class Atualizador implements Runnable {

		@Override
		public void run() {
			while (true) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						atualizarTabela();
					}
				});
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
		}
		
	}
}
