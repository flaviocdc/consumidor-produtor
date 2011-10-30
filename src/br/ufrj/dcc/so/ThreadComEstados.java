package br.ufrj.dcc.so;

import br.ufrj.dcc.so.estados.Estado;

public class ThreadComEstados extends Thread {

	private Estado estado;

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}