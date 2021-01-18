package org.iesalandalus.programacion.cuatroenraya;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.cuatroenraya.modelo.Tablero;
import org.iesalandalus.programacion.cuatroenraya.vista.Consola;

public class CuatroEnRaya {

	// ATRIBUTOS
	
	private Jugador[] jugador;
	private Tablero tablero;
	
	int NUMERO_JUGADORES = 2;
	
	// METODOS
	
	public CuatroEnRaya (Jugador jugadorUno, Jugador jugadorDos) {
		jugador = new Jugador[2];
		tablero = new Tablero();
		jugador[0] = jugadorUno;
		jugador[1] = jugadorDos;
	}
	
	public void jugar() throws OperationNotSupportedException {
		boolean victoriaJugadorUno = false;
		boolean victoriaJugadorDos = false;
		while (!tablero.estaLleno() && !victoriaJugadorUno && !victoriaJugadorDos) {
			System.out.println(tablero.toString());
			victoriaJugadorUno = tirar(jugador[0]);
			if (!victoriaJugadorUno) {
				System.out.println(tablero.toString());
				victoriaJugadorDos = tirar(jugador[1]);
			}
		}
		if (victoriaJugadorUno) {
			System.out.println("FELICIDADES "+jugador[0].getNombre()+", ¡HAS GANADO!");
		} else if (victoriaJugadorDos) {
			System.out.println("FELICIDADES "+jugador[1].getNombre()+", ¡HAS GANADO!");
		} else {
			System.out.println("EL JUEGO HA QUEDADO EN EMPATE");
		}
	}
	
	private boolean tirar(Jugador jugador) throws OperationNotSupportedException {
		boolean victoria = false;
		int columna = Consola.leerColumna(jugador);
		victoria = tablero.introducirFicha(columna, jugador.getColorFichas());
		return victoria;
	}
}
