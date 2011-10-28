package br.ufrj.dcc.so.filas;

import java.util.ArrayDeque;
import java.util.concurrent.Semaphore;

public class FilaCaseiraLimitada<T> extends FilaCaseira<T> {

	private Semaphore semaforoCapacidade; 
	
	public FilaCaseiraLimitada(int capacidade) {
		semaforoCapacidade = new Semaphore(capacidade);
		
		fila = new ArrayDeque<T>();
	}
	
	public void inserir(T element) {
		try {
			semaforoCapacidade.acquire();

			super.inserir(element);
		} catch (InterruptedException e) {
			throw new FilaException("Interrompido enquanto estava esperando para adquirir");
		}
	}
	
	@Override
	public T consome() {
		semaforoCapacidade.release();
		
		return super.consome();
	}
	
}
