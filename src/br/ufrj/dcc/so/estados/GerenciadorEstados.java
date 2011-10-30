package br.ufrj.dcc.so.estados;

import br.ufrj.dcc.so.ThreadComEstados;

public class GerenciadorEstados {

	private static ThreadComEstados get() {
		Thread t = Thread.currentThread();
		
		if (t instanceof ThreadComEstados) {
			return (ThreadComEstados) t;
		}
		
		throw new RuntimeException("Impossivel usar GerenciadorEstados em uma thread sem estados");
	}

	public static void mudarEstado(Estado estado) {
		ThreadComEstados t = get();
		
		System.out.println(t.getName() + " - mudando estado de " + t.getEstado() + " para " + estado);
		
		t.setEstado(estado);
	}
	
}