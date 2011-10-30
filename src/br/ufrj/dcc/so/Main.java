package br.ufrj.dcc.so;

import java.util.ArrayList;
import java.util.List;

import br.ufrj.dcc.so.filas.Fila;
import br.ufrj.dcc.so.filas.FilaCaseiraLimitada;

public class Main {

	private static final int NUM_PRODUTORES = 3;
	private static final int NUM_CONSUMIDORES = 5;
	public static final int NUM_PRODUTOS = 1000;
	
	private static final Object mutexProduzidos = new Object();
	private static int NUM_PRODUZIDOS = 0;
	
	private static final Object mutexConsumidos = new Object();
	private static int NUM_CONSUMIDOS = 0;
	
	private static Fila<String> fila = new FilaCaseiraLimitada<String>(50); 
	//private static Fila<String> fila = new FilaBuiltin<String>(50);

	private List<Thread> produtores = new ArrayList<Thread>(NUM_PRODUTORES);
	private List<Thread> consumidores = new ArrayList<Thread>(NUM_CONSUMIDORES);
	
	public static void main(String[] args) {
		new Main().exec();
	}
	
	public static Fila<String> getFila() {
		return fila;
	}
	
	public static int incrementarNumProduzidos() {
		synchronized (mutexProduzidos) {
			return ++NUM_PRODUZIDOS;
		}
	}
	
	public static int getNumProduzidos() {
		synchronized (mutexProduzidos) {
			return NUM_PRODUZIDOS;
		}
	}
	
	public static int incrementarNumConsumidos() {
		synchronized (mutexConsumidos) {
			return ++NUM_CONSUMIDOS;
		}
	}
	
	public static int getNumConsumidos() {
		synchronized (mutexConsumidos) {
			return NUM_CONSUMIDOS;
		}
	}
	
	public void exec() {
		for (int i = 0; i < NUM_PRODUTORES; i++) {
			Thread t = new Produtor();
			t.setName("Produtor-" + i);
			t.setDaemon(false);
			
			produtores.add(t);
			
			t.start();
		}
		
		for (int i = 0; i < NUM_CONSUMIDORES; i++) {
			Thread t = new Consumidor();
			t.setName("Consumidor-" + i);
			t.setDaemon(false);
			
			consumidores.add(t);
			
			t.start();
		}
	}
	
}