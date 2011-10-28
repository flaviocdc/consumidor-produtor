package br.ufrj.dcc.so.filas;

import java.util.concurrent.LinkedBlockingDeque;

public class FilaBuiltin<T> extends Fila<T> {

	private LinkedBlockingDeque<T> fila;
	private int capacidade;
	
	public FilaBuiltin(int paramCapacidade) {
		capacidade = paramCapacidade;
		
		fila = new LinkedBlockingDeque<T>(capacidade);
	}
	
	@Override
	public void inserir(T element) {
		try {
			fila.putLast(element);
		} catch (InterruptedException e) {
			throw new FilaException("Interrompido enquanto estava esperando para adquirir");
		}
	}

	@Override
	public T consome() {
		try {
			return fila.takeFirst();
		} catch (InterruptedException e) {
			throw new FilaException("Erro adquirindo recurso para consumir");
		}
	}

}
