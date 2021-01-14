package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

public class Tablero {

	// ATRIBUTOS

	public static int FILAS = 6;
	public static int COLUMNAS = 7;
	public static int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;
	private Casilla[][] casillas;

	// METODOS

	public Tablero() {

		casillas = new Casilla[FILAS][COLUMNAS];
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				casillas[i][j] = new Casilla();
			}
		}

	}

	public boolean estaVacio() {
		boolean tableroVacio = true;
		for (int i = 0; i < COLUMNAS; i++) {

			boolean columnaVacia = columnaVacia(i);
			if (!columnaVacia) {
				tableroVacio = false;
				return false;
			}
		}
		return tableroVacio;
	}

	private boolean columnaVacia(int columna) {
		boolean casillaVacia = true;
		if (casillas[0][columna].estaOcupada()) {
			casillaVacia = false;
		}
		return casillaVacia;
	}

	public boolean estaLleno() throws OperationNotSupportedException {
		boolean tableroLleno = true;
		for (int i = 0; i < COLUMNAS; i++) {
			boolean columnaLlena = columnaLlena(i);
			if (!columnaLlena) {
				tableroLleno = false;
				return tableroLleno;
			}
		}
		return tableroLleno;
	}

	private boolean columnaLlena(int columna) throws OperationNotSupportedException {
		boolean columnaLlena = true;
		for(int i=0;i<FILAS;i++) {
			if(!casillas[i][columna].estaOcupada()) {
				columnaLlena = false;
				return columnaLlena;
			}
		}
		return columnaLlena;
	}

	public boolean introducirFicha(int columna, Ficha ficha) throws OperationNotSupportedException {
		boolean victoria = false;
		comprobarFicha(ficha);
		comprobarColumna(columna);
		if (columnaLlena(columna)) {
			throw new OperationNotSupportedException("ERROR: Columna llena.");
		}else {
			int fila = getPrimeraFilaVacia(columna);
			casillas[fila][columna].setFicha(ficha);
			victoria = comprobarTirada(fila, columna, ficha);
			return victoria;
		}
		
		
	}
	
	private void comprobarFicha(Ficha ficha) {
		if (ficha == null) {
			throw new NullPointerException("ERROR: La ficha no puede ser nula.");
		} else if (ficha != Ficha.VERDE && ficha != Ficha.AZUL) {
			throw new IllegalArgumentException("ERROR: La ficha");
		}
	}

	private void comprobarColumna(int columna) {
		if (columna < 0 || columna >= COLUMNAS) {
			throw new IllegalArgumentException("ERROR: Columna incorrecta.");
		}
	}

	private int getPrimeraFilaVacia(int columna) {
		int filaVacia = 0;
		for (int i = 0; i < FILAS; i++) {
			if (!casillas[i][columna].estaOcupada()) {
				filaVacia = i;
				return filaVacia;
			}
		}
		return filaVacia;
	}

	private boolean comprobarTirada(int fila, int columna, Ficha ficha) {
		boolean victoria = false;
		if(comprobarHorizontal(fila, ficha)) {
			victoria = true;
			System.out.println("Victoria Horizontal:"+victoria);
		}
		if(comprobarVertical(columna, ficha)) {
			victoria = true;
			System.out.println("Victoria Vertical:"+victoria);
		}
		if(comprobarDiagonalNE(fila, columna, ficha)) {
			victoria = true;
			System.out.println("Victoria NE:"+victoria);
		}
		if(comprobarDiagonalNO(fila, columna, ficha)) {
			victoria = true;
			System.out.println("Victoria NO:"+victoria);
		}
		return victoria;
	}
	
	private boolean objetivoAlcanzado(int fichasConsecutivas) {
		boolean conseguido = false;
		if (fichasConsecutivas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS) {
			conseguido = true;
		}
		return conseguido;
	}

	private boolean comprobarHorizontal(int fila, Ficha ficha) {
		boolean victoria = false;
		int contador = 0;
		for (int j = 0; j < COLUMNAS; j++) {
			if (casillas[fila][j].getFicha() == ficha) {
				contador++;
				if (objetivoAlcanzado(contador))
					victoria = true;
			} else {
				contador = 0;
			}
		}
		return victoria;

	}

	private boolean comprobarVertical(int columna, Ficha ficha) {
		boolean victoria = false;
		int contador = 0;
		for (int i = 0; i < FILAS; i++) {
			if (casillas[i][columna].getFicha() == ficha) {
				contador++;
				if (objetivoAlcanzado(contador)) {
					victoria = true;
				} 
			}else {
				contador = 0;
			}
		}
		return victoria;
	}

	private boolean comprobarDiagonalNE(int fila, int columna, Ficha ficha) {
		boolean victoria = false;
		int contador = 0;
		int desplazamientoInicial = menor(fila, columna);
		int filaInicial = fila - desplazamientoInicial;
		int columnaInicial = columna - desplazamientoInicial;
		/*for (int i = filaInicial; i < FILAS; i++) {
			for (int j = columnaInicial; j < COLUMNAS; j++) {
				if (casillas[i][j].getFicha() == ficha) {
					contador++;
					if (objetivoAlcanzado(contador)) {
						victoria = true;
					} 
				} else {
					contador = 0;
				}
			}
		}*/
		while (filaInicial < FILAS && columnaInicial < COLUMNAS) {
			if(casillas[filaInicial][columnaInicial].getFicha()==ficha) {
				contador++;
				if (objetivoAlcanzado(contador)) {
					victoria = true;
					return victoria;
				}
			} else {
				contador = 0;
			}
			filaInicial++;
			columnaInicial++;
		}
		
		return victoria;
	}
	
	private boolean comprobarDiagonalNO(int fila, int columna, Ficha ficha) {
		boolean victoria = false;
		int contador = 0;
		int desplazamientoInicial = menor(fila, COLUMNAS-1-columna);
		int filaInicial = fila - desplazamientoInicial;
		int columnaInicial = columna + desplazamientoInicial;
		/*for (int i = filaInicial; i < FILAS; i++) {
			for (int j = columnaInicial; j >= 0; j--) {
				if (casillas[i][j].getFicha() == ficha) {
					contador++;
					if (objetivoAlcanzado(contador)) {
						victoria = true;
					} 
				} else {
					contador = 0;
				}
			}
		}*/
		while (filaInicial < FILAS && columnaInicial >= 0) {
			if(casillas[filaInicial][columnaInicial].getFicha()==ficha) {
				contador++;
				if (objetivoAlcanzado(contador)) {
					victoria = true;
					return victoria;
				}
			} else {
				contador = 0;
			}
			filaInicial++;
			columnaInicial--;
		}
		
		return victoria;
	}
	
	private int menor(int fila, int columna) {
		int menor;
		if (fila <= columna) {
			menor = fila;
		} else {
			menor = columna;
		}
		return menor;
	}

	@Override
	public String toString() {
		StringBuilder representacionGrafica = new StringBuilder();
		for(int i=FILAS-1;i>=0;i--){
			representacionGrafica.append("|");
			for(int j=0;j<COLUMNAS;j++) {
				representacionGrafica.append(casillas[i][j]);
			}
			representacionGrafica.append("|\n");
		}
		representacionGrafica.append(" -------\n");
		String representacionFinal = representacionGrafica.toString();
		return representacionFinal;
	}
	
	
}
