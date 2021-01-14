package org.iesalandalus.programacion.cuatroenraya.modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.OperationNotSupportedException;

public class Jugador {

	// ATRIBUTOS

	private String nombre;
	private Ficha ficha;

	// METODOS

	public Jugador(String nombre, Ficha ficha) {
		setNombre(nombre);
		setColorFichas(ficha);
	}

	public Ficha getColorFichas() {
		return ficha;
	}

	private void setColorFichas(Ficha ficha) {
		if (ficha == null) {
			throw new NullPointerException("ERROR: El color de las fichas no puede ser nulo.");
		} else if (ficha != Ficha.AZUL && ficha != Ficha.VERDE) {
			throw new IllegalArgumentException("ERROR: El nombre no puede estar vacÃ­o.");
		} else {
			this.ficha = ficha;
		}
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		} else if (nombre.matches("\\s+") || nombre.matches("")) {
			throw new IllegalArgumentException("ERROR: El nombre no puede estar vacío.");
		} else {
			this.nombre = nombre;
		}
	}

	@Override
	public String toString() {
		return nombre + " (" + ficha + ")";
	}

}
