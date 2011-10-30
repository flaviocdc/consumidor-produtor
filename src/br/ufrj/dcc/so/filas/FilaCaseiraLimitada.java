package br.ufrj.dcc.so.filas;

import java.util.ArrayDeque;
import java.util.concurrent.Semaphore;

import br.ufrj.dcc.so.estados.Estado;
import br.ufrj.dcc.so.estados.GerenciadorEstados;

public class FilaCaseiraLimitada<T> extends FilaCaseira<T> {

	private Semaphore semaforoCapacidade; 
	
	public FilaCaseiraLimitada(int capacidade) {
		semaforoCapacidade = new Semaphore(capacidade);
		
		fila = new ArrayDeque<T>();
	}
	
	public void inserir(T element) {
		try {
			GerenciadorEstados.mudarEstado(Estado.SLEEPING_SYNC);
			semaforoCapacidade.acquire();

			GerenciadorEstados.mudarEstado(Estado.EXECUTANDO);
			super.inserir(element);
		} catch (InterruptedException e) {
			throw new FilaException("Interrompido enquanto estava esperando para adquirir");
		}
	}
	
	@Override
	public T consome() {
		semaforoCapacidade.release();
		
		GerenciadorEstados.mudarEstado(Estado.EXECUTANDO);
		return super.consome();
	}
	
}
