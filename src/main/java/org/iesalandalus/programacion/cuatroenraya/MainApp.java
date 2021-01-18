package org.iesalandalus.programacion.cuatroenraya;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.cuatroenraya.vista.Consola;

public class MainApp {

	static CuatroEnRaya juego;
	static Jugador jugadorUno, jugadorDos;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("BIENVENIDO AL CUATRO EN RAYA");
		System.out.println("----------------------------");
		Ficha fichaJugadorDos;
		jugadorUno = Consola.leerJugador();
		if (jugadorUno.getColorFichas()==Ficha.AZUL) {
			fichaJugadorDos = Ficha.VERDE;
		} else {
			fichaJugadorDos = Ficha.AZUL;
		}
		jugadorDos = Consola.leerJugador(fichaJugadorDos);
		juego = new CuatroEnRaya(jugadorUno, jugadorDos);
		try {
			juego.jugar();
		} catch (OperationNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
