package br.ufrj.dcc.so.filas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FilaMultiplosLeitores<T> extends Fila<T> {

	private int readcount, writecount;
	private Semaphore readcountSem = new Semaphore(1), writecountSem = new Semaphore(1), z = new Semaphore(1);
	private Semaphore wsem = new Semaphore(1), rsem = new Semaphore(1);
	
	private Integer posicaoCorrente = -1;
	private Lock posicaoCorrenteLock = new ReentrantLock();
	
	private LinkedList<T> backend = new LinkedList<T>();
	private ArrayList<Integer> toRemove = new ArrayList<Integer>(); 
	
	@Override
	public void inserir(T element) {
		try {
			writecountSem.acquire();
			writecount++;
			if (writecount == 1)
				rsem.acquire();
			writecountSem.release();
			
			wsem.acquire();
			
			posicaoCorrenteLock.lock();

			posicaoCorrente++;
			System.out.println("Incrementei posicao corrente: " + posicaoCorrente);
			
			backend.add(0, element);
			
			posicaoCorrenteLock.unlock();
			
			wsem.release();
			writecountSem.acquire();
			writecount--;
			if (writecount == 0)
				rsem.release();
			writecountSem.release();
		} catch (InterruptedException e) {}
	}

	@Override
	public T consome() {
		T val = null;
		
		try {
			z.acquire();
			rsem.acquire();
			
			readcountSem.acquire();
			readcount++;
			if (readcount == 1) {
				toRemove.clear();
				wsem.acquire();
			}
			readcountSem.release();
			
			rsem.release();
			z.release();

			posicaoCorrenteLock.lock();
			
			val = backend.get(posicaoCorrente);
			toRemove.add(posicaoCorrente);
			posicaoCorrente--;
			System.out.println("Decrementei posicao corrente: " + posicaoCorrente);
			
			posicaoCorrenteLock.unlock();

			readcountSem.acquire();
			readcount--;
			if (readcount == 0) {
				Collections.sort(toRemove, Collections.reverseOrder());
				for (Integer remove : toRemove) {
					backend.remove(remove);
				}
				wsem.release();
			}
			readcountSem.release();

		} catch (InterruptedException e) {}
		
		return val;
	}

}
