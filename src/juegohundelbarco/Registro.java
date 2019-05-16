package juegohundelbarco;

import java.util.Comparator;
import java.util.Date;

/*
    Autor: Nora Quesada
    Fecha: 20/03/2019
    Actividad: Clase Registro
 */
public class Registro implements java.io.Serializable {

    //Variables privadas partida, fila, columna y efecto
    private int partida;
    private int fila;
    private int columna;
    private String efecto;
    private String puntos;
    private String fecha;

    //Constructor Registro
    public Registro(int partida, int fila, int columna, String efecto, String puntos, String fecha) {

        this.partida = partida;
        this.fila = fila;
        this.columna = columna;
        this.efecto = efecto;
        this.puntos = puntos;
        this.fecha = fecha;

    }

    //Set y Get de partida, fila, columna y efecto
    void setPartida(int partida) {
        this.partida = partida;
    }

    int getPartida() {
        return this.partida;
    }

    void setFila(int fila) {
        this.fila = fila;
    }

    int getFila() {
        return this.fila;
    }

    void setColumna(int columna) {
        this.columna = columna;
    }

    int getColumna() {
        return this.columna;
    }

    void setEfecto(String efecto) {
        this.efecto = efecto;
    }

    String getEfecto() {
        return this.efecto;
    }

    void setPuntos(String puntos) {
        this.puntos = puntos;
    }

    String getPuntos() {
        return this.puntos;
    }

    void setFecha(String fecha) {
        this.fecha = fecha;
    }

    String getFecha() {
        return this.fecha;
    }

    //Metodo mostrardatos
    public void MostrarDatos() {

        System.out.println("Partida: " + partida + " Fila: " + fila + " Columna: " + columna + " Efecto: " + efecto + " Puntos: " + puntos + " Fecha: " + fecha);
    }

}
