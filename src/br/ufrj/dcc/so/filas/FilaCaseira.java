package br.ufrj.dcc.so.filas;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import br.ufrj.dcc.so.estados.Estado;
import br.ufrj.dcc.so.estados.GerenciadorEstados;

public class FilaCaseira<T> extends Fila<T> {

	protected Deque<T> fila;
	
	private Lock lockFila;
	private Semaphore semaforoProduzidos;

	public FilaCaseira() {
		fila = new ArrayDeque<T>();
		
		lockFila =  new ReentrantLock();
		semaforoProduzidos = new Semaphore(0);
	}
	
	@Override
	public void inserir(T element) {
		GerenciadorEstados.mudarEstado(Estado.SLEEPING_MUTEX);
		lockFila.lock();
		
		GerenciadorEstados.mudarEstado(Estado.EXECUTANDO);
		fila.addLast(element);
		semaforoProduzidos.release();
		
		lockFila.unlock();
		
		GerenciadorEstados.mudarEstado(Estado.PRONTO);
	}

	@Override
	public T consome() {
		T element = null;
		
		try {
			GerenciadorEstados.mudarEstado(Estado.SLEEPING_SYNC);
			semaforoProduzidos.acquire();
		} catch (InterruptedException e) {
			throw new FilaException("Erro adquirindo recurso para consumir");
		}
		
		GerenciadorEstados.mudarEstado(Estado.SLEEPING_MUTEX);
		lockFila.lock();
		
		GerenciadorEstados.mudarEstado(Estado.EXECUTANDO);
		element = fila.removeFirst();
		
		lockFila.unlock();
		
		GerenciadorEstados.mudarEstado(Estado.PRONTO);
		return element;
	}

}