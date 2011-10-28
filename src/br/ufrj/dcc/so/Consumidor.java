package br.ufrj.dcc.so;

public class Consumidor implements Runnable {

	@Override
	public void run() {
		while (Main.getNumConsumidos() != Main.NUM_PRODUTOS) {
			Main.incrementarNumConsumidos();
			String produto = Main.getFila().consome();
			
			System.out.println("Consumido: " + produto);
		}
	}

}
