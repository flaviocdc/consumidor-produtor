package br.ufrj.dcc.so;

public class Produtor extends ThreadComEstados {

	@Override
	public void run() {
		while (Main.getNumProduzidos() < Main.NUM_PRODUTOS) {
			int i = Main.incrementarNumProduzidos();
			
			System.out.println("Produzi: " + i);
			
			Main.getFila().inserir("Produto-" + i);
		}
	}

}
