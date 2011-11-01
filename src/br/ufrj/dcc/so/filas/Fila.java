package br.ufrj.dcc.so.filas;

public abstract class Fila<T> {

	public abstract void inserir(T element);
	
	public abstract T consome();
	
}