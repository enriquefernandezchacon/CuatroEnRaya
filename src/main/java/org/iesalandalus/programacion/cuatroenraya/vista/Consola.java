package org.iesalandalus.programacion.cuatroenraya.vista;

import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.utilidades.Entrada;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;

public class Consola {
	
	static Jugador jugadorUno;
	static Jugador jugadorDos;

	private Consola() {
		
	}
	
	public static String leerNombre() {
		String nombre;
		do {
			System.out.print("\nINTRODUZCA EL NOMBRE DEL JUGADOR:");
			nombre = Entrada.cadena();
		} while (nombre.matches("\\s+") || nombre.matches(""));

		return nombre;
	}
	
	public static Ficha elegirColorFicha() {
		int eleccion;
		do {
			System.out.println("\nINTRODUZCA EL COLOR DE LA FICHA DESEADA:\n");
			System.out.println("1 - AZUL");
			System.out.println("2 - VERDE");
			System.out.print("\nCOLOR ELEGIDO: ");
			eleccion = Entrada.entero();
		} while (eleccion != 1 && eleccion != 2);
		System.out.println();
		Ficha colorFicha;
		if (eleccion == 1) {
			colorFicha = Ficha.AZUL;
		} else {
			colorFicha = Ficha.VERDE;
		}
		return colorFicha;
	}
	
	public static Jugador leerJugador() {
		String nombreUno = leerNombre();
		Ficha fichaUno = elegirColorFicha();
		jugadorUno = new Jugador(nombreUno, fichaUno);
		return jugadorUno;
	}
	
	public static Jugador leerJugador(Ficha ficha) {
		String nombreDos = leerNombre();
		jugadorDos = new Jugador(nombreDos, ficha);
		return jugadorDos;
	}
	
	public static int leerColumna(Jugador jugador) {
		String nombre = jugador.getNombre();
		System.out.println("TURNO DE: "+nombre);
		System.out.print("ELIJA LA COLUMNA (1-7): ");
		int columna = Entrada.entero()-1;
		return columna;
	}
	
}
