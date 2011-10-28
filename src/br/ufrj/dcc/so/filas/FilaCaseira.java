package br.ufrj.dcc.so.filas;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
		lockFila.lock();
		
		fila.addLast(element);
		semaforoProduzidos.release();
		
		lockFila.unlock();
	}

	@Override
	public T consome() {
		T element = null;
		
		try {
			semaforoProduzidos.acquire();
		} catch (InterruptedException e) {
			throw new FilaException("Erro adquirindo recurso para consumir");
		}
		
		lockFila.lock();
		
		element = fila.removeFirst();
		
		lockFila.unlock();
		
		return element;
	}

}