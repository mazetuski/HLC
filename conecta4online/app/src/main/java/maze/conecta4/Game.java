package maze.conecta4;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by maze on 14/11/2016.
 */

public class Game {
    private int[][] tablero;
    private static final int FILAS = 6;
    private static final int COL = 7;
    static final int JUGADOR1 = 1;
    static final int JUGADOR2 = 2;
    private static final int VACIO = 0;
    private static final String JUGADOR1GANA = ".*1{4,}.*";
    private static final String JUGADOR1PUEDEGANAR = ".*1{2}.*";
    private static final String JUGADOR2CASIGANA = ".*2{3}.*";
    private static final String JUGADOR1CASIGANA = ".*1{3}.*";
    private static final String JUGADOR2GANA = ".*2{4,}.*";
    private int turno;
    private String estado = "";
    private boolean terminar = false;

    public Game(int jugador) {
        this.tablero = new int[FILAS][COL];
        setTurno(jugador);

        reiniciarTablero();
    }

    public void reiniciarTablero(){
        for (int i = 0; i < FILAS; i++)
            for (int j = 0; j < COL; j++)
                this.tablero[i][j] = VACIO;
    }

    public int getTurno() {
        return this.turno;
    }

    public void setTurno(int jugador) {
        this.turno = jugador;
    }

    public int getJugador1() {
        return JUGADOR1;
    }

    public int getJugador2() {
        return JUGADOR2;
    }

    public void cambiarTurno() {
        if (getTurno() == getJugador1())
            setTurno(getJugador2());
        else
            setTurno(getJugador1());
    }

    public void cambiarTurno(int turno){
        if (turno == getJugador1())
            setTurno(getJugador2());
        else
            setTurno(getJugador1());
    }

    public void setFicha(int fila, int columna, int jugador) {
        this.tablero[fila][columna] = jugador;
    }

    public boolean isVacio(int fila, int columna) {
        return this.tablero[fila][columna] == this.VACIO;
    }

    public boolean comprobarJugadaGanadora(String fila, String columna, String oblicuaIzq, String oblicuaDer) {
        if (Pattern.matches(JUGADOR1GANA, fila) || Pattern.matches(JUGADOR2GANA, fila)) {
            terminar = true;
            return true;
        }
        if (Pattern.matches(JUGADOR1GANA, columna) || Pattern.matches(JUGADOR2GANA, columna)) {
            terminar = true;
            return true;
        }
        if (Pattern.matches(JUGADOR1GANA, oblicuaIzq) || Pattern.matches(JUGADOR2GANA, oblicuaIzq)) {
            terminar = true;
            return true;
        }
        if (Pattern.matches(JUGADOR1GANA, oblicuaDer) || Pattern.matches(JUGADOR2GANA, oblicuaDer)) {
            terminar = true;
            return true;
        }
        return false;
    }

    public String getFila(int fila) {
        String filaCompleta = "";
        for (int i = 0; i < COL; i++)
            filaCompleta += tablero[fila][i];
        return filaCompleta;

    }

    public String getColumna(int columna) {
        String columnaCompleta = "";
        for (int i = 0; i < FILAS; i++)
            columnaCompleta += tablero[i][columna];
        return columnaCompleta;
    }

    public String getOblicuaIzq(int fila, int columna) {
        String filaOblicuaIzq = "";
        for (int i = fila, j = columna; i < FILAS && j < COL; i++, j++)
            filaOblicuaIzq += tablero[i][j];
        for (int i = fila - 1, j = columna - 1; j >= 0 && i >= 0; i--, j--)
            filaOblicuaIzq = tablero[i][j] + filaOblicuaIzq;
        return filaOblicuaIzq;
    }

    public String getOblicuaDer(int fila, int columna) {
        String filaOblicuaDer = "";
        for (int i = fila, j = columna; i < FILAS && j >= 0; i++, j--)
            filaOblicuaDer += tablero[i][j];
        for (int i = fila - 1, j = columna + 1; j < COL && i >= 0; i--, j++)
            filaOblicuaDer = tablero[i][j] + filaOblicuaDer;
        return filaOblicuaDer;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isTerminado() {
        if (comprobarTableroLleno())
            terminar = true;
        return terminar;
    }

    public void setTerminado(boolean valor) {
        this.terminar = valor;
    }

    public boolean comprobarTableroLleno() {
        for (int i = 0; i < COL; i++)
            if (tablero[0][i] == 0)
                return false;
        return true;
    }

    public int[][] getTablero() {
        return tablero;
    }

    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }

    public String tableroToString() {
        String mensaje = "";
        for (int i = 0; i < FILAS; i++)
            for (int j = 0; j < COL; j++)
                mensaje += tablero[i][j];
        return mensaje;
    }

    public void stringToTablero(String string) {
        int contador =0;
        for (int i = 0; i < FILAS; i++)
            for (int j = 0; j < COL; j++)
                tablero[i][j]=Integer.parseInt(string.substring(contador, ++contador));
    }


    public int maquinaCol(int colUsuario) {
        String fila;
        String comprobarColumna = "";
        int col;
        int filaMaquina;

        for (int i = 0; i < FILAS; i++) {
            fila = "";
            for (int j = 0; j < COL; j++) {
                fila += tablero[i][j];
                col = j;
                if (Pattern.matches(JUGADOR2CASIGANA, fila) && col != (COL - 1) && tablero[i][col + 1] == VACIO)
                    return col + 1;
                if (Pattern.matches(JUGADOR2CASIGANA, fila) && (col - 3) >= 0 && tablero[i][col - 3] == VACIO)
                    return col - 3;
            }
        }

        for (int i = 0; i < FILAS; i++) {
            fila = "";
            for (int j = 0; j < COL; j++) {
                fila += tablero[i][j];
                col = j;
                if (Pattern.matches(JUGADOR1CASIGANA, fila) && col != (COL - 1) && tablero[i][col + 1] == VACIO && i + 1 <= (FILAS - 1) && tablero[i + 1][col + 1] == VACIO)
                    return (int) (Math.random() * COL);
                if (Pattern.matches(JUGADOR1CASIGANA, fila) && col != (COL - 1) && tablero[i][col + 1] == VACIO)
                    return col + 1;

                if (Pattern.matches(JUGADOR1CASIGANA, fila) && (col - 3) >= 0 && tablero[i][col - 3] == VACIO && i + 1 <= (FILAS - 1) && tablero[i + 1][col - 3] == VACIO)
                    return (int) (Math.random() * COL);
                if (Pattern.matches(JUGADOR1CASIGANA, fila) && (col - 3) >= 0 && tablero[i][col - 3] == VACIO)
                    return col - 3;
            }
        }

        for (int k = 0; k < FILAS; k++) {
            comprobarColumna += tablero[k][colUsuario];
            filaMaquina = k;
            if (Pattern.matches(JUGADOR1CASIGANA, comprobarColumna) && (filaMaquina - 3) >= 0 && tablero[filaMaquina - 3][colUsuario] == VACIO)
                return colUsuario;
        }

        for (int i = 0; i < FILAS; i++) {
            fila = "";
            for (int j = 0; j < COL; j++) {
                fila += tablero[i][j];
                col = j;
                if (Pattern.matches(JUGADOR1PUEDEGANAR, fila) && col != (COL - 1) && tablero[i][col + 1] == VACIO && i + 1 <= (FILAS - 1) && tablero[i + 1][col + 1] == VACIO)
                    return (int) (Math.random() * COL);
                if (Pattern.matches(JUGADOR1PUEDEGANAR, fila) && col != (COL - 1) && tablero[i][col + 1] == VACIO)
                    return col + 1;

                if (Pattern.matches(JUGADOR1PUEDEGANAR, fila) && (col - 2) >= 0 && tablero[i][col - 2] == VACIO && i + 1 <= (FILAS - 1) && tablero[i + 1][col - 2] == VACIO)
                    return (int) (Math.random() * COL);
                if (Pattern.matches(JUGADOR1PUEDEGANAR, fila) && (col - 2) >= 0 && tablero[i][col - 2] == VACIO)
                    return col - 2;
            }
        }
        col = (int) (Math.random() * COL);
        return col;
    }

    /*public void vaciarTablero(){
        for (int i = 0; i < FILAS; i++)
            for (int j = 0; j < COL; j++)
                this.tablero[i][j] = VACIO;
    }*/
}
