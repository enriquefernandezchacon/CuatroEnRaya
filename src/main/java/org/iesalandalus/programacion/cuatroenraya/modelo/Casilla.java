package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

public class Casilla {

	// ATRIBUTOS

	private Ficha ficha;

	// METODOS

	// CONSTRUCTOR POR DEFECTO

	public Casilla() {
		this.ficha = null;
	}

	public Ficha getFicha() {
		return ficha;
	}

	public void setFicha(Ficha ficha) throws OperationNotSupportedException {
		
			if (ficha == null) {
				throw new NullPointerException("ERROR: No se puede poner una ficha nula.");
			}
			if (estaOcupada()) {
				throw new OperationNotSupportedException("ERROR: Ya contengo una ficha.");
			}
			
			this.ficha = ficha;
		
	}
		
	public boolean estaOcupada() {
		return ficha != null;
	}

	@Override
	public String toString() {
		if (ficha == null) {
			return " ";
		} else {
			return String.format("%.1s", ficha);
		}
	}

}
