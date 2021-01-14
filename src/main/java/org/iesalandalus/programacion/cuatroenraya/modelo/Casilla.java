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
		if (estaOcupada() == false) {
			if (ficha == null) {
				throw new NullPointerException("ERROR: No se puede poner una ficha nula.");
			} else if (ficha == Ficha.VERDE) {
				this.ficha = Ficha.VERDE;
			} else if (ficha == Ficha.AZUL) {
				this.ficha = Ficha.AZUL;
			} else {
				throw new IllegalArgumentException("ERROR: Color incorrecto");
			}
		} else {
			throw new OperationNotSupportedException("ERROR: Ya contengo una ficha.");
		}
	}

	public boolean estaOcupada() {
		boolean casillaOcupada = false;
		if (this.ficha == Ficha.AZUL || this.ficha == Ficha.VERDE) {
			casillaOcupada = true;
		}
		return casillaOcupada;
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
