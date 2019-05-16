package juegohundelbarco;

/*
    Autor: Nora Quesada
    Fecha: 20/03/2019
    Actividad: Clase Barco
*/

public class Barco implements java.io.Serializable{

    //Varibles privadas tipo, tam y letra
    private String tipo;
    private int tam;
    private char letra;

    //Constructor Barco
    public Barco(String tipo, int tam, char letra) {

        this.tipo = tipo;
        this.tam = tam;
        this.letra = letra;

    }

    //Set y Get de tipo, tam y letra
    void setTipo(String tipo) {
        this.tipo = tipo;
    }

    String getTipo() {
        return this.tipo;
    }

    void setTam(int tam) {
        this.tam = tam;
    }

    int getTam() {
        return this.tam;
    }

    void setLetra(char letra) {
        this.letra = letra;
    }

    char getLetra() {
        return this.letra;
    }

    //Metodo mostrardatos
    public void MostrarDatos() {
        
        System.out.println("Tipo: " + tipo + " Tamaño: " + tam + " Letra: " + letra);
    }
  
}