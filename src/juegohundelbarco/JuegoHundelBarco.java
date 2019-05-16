package juegohundelbarco;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeMap;

/*
    Autor: Nora Quesada
    Fecha: 20/03/2019
    Actividad: Main Juego Hunde El Barco 
    ESto son cambios para ver el efecto que tiene
 */
public class JuegoHundelBarco implements java.io.Serializable {

    public static void main(String[] args) {

        //--------------------DECLARACIONES DE VARIABLES A UTILIZAR--------------------
        //Para controlar los menus
        int menu = 0;
        int menu2 = 0;
        int menu3 = 0;

        TreeMap<Integer, ArrayList> ListaGuardar = new TreeMap<>(); //Inicializamos un TreeMap vacio
        ListaGuardar = Tablero.CargarTreeMap("GuardarPartida.txt", ListaGuardar); //Cargamos lo que contenga el fichero, en ese TreeMap

        //Para que partida sume 1 al tamaño de la cantidad de objetos, esto es cuanto cerramos el programa.
        //es decir si hay 2 objetos dos partidas, le sumamos 1 para que la varible partida sea 3
        int partida = ListaGuardar.size() + 1;

        do {
            Scanner sc = new Scanner(System.in);

            //Usamos dos matrices uno para administrador y otra para el usuario o jugador
            String matrizadm[][] = new String[10][10];
            String matrizusu[][] = new String[10][10];

            //Creamos los objetos barcos con sus variables o atributos
            Barco portaviones = new Barco("Portaaviones", 5, 'P');
            Barco submarinos = new Barco("Submarinos", 3, 'S');
            Barco destructores = new Barco("Destructores", 2, 'D');

            //Creamos el objeto Tablero
            Tablero t = new Tablero();

            //Controlar las respuestas de verdadero o falso
            boolean resp;
            boolean resp2 = true;

            //Variables tipo int para filas, columnas y sentido
            int fila = -1;
            int columna = -1;
            int sentido = 0;

            //Variables tipo String, vacio es para que no me de error cuando slte el catch
            String letra_sentido;
            String vacio;

            //----------------------FIN DECLARACIONES DE VARIABLES---------------------
            //---------------------INICIO DE MENU PRINCIPAL----------------------------
            do {
                //Menu2 controla que no salga del menu hasta que escoja una de esas 4 opciones
                try {
                    System.out.println("1 -> Jugar Partida");
                    System.out.println("2 -> Leer Partida");
                    System.out.println("3 -> Ver Cantidad de Movimientos X Partida");
                    System.out.println("4 -> Salir");
                    System.out.print("Escoja una opcion: ");
                    menu2 = sc.nextInt();

                } catch (InputMismatchException e) {
                    System.out.println("No se permiten letras, intentelo de nuevo");
                }

            } while (menu2 < 0 && menu2 > 4);

            //Segun lo que se haya escojido en el menu2 hay 4 opciones
            switch (menu2) {

                //Case 1, nos permite jugar
                case 1:
                    //--------------------ZONA INICIAL ADMINISTRADOR--------------------
                    System.out.println("\n");
                    System.out.println("BIENVENIDO AL JUEGO HUNDE LA FLOTA");
                    System.out.println("\n");
                    System.out.println("EL ADMINISTRADOR DEBE INSERTAR LAS FLOTAS");
                    System.out.println("-----------------------------------------");
                    System.out.println("\n");
                    t.MostrarInicio(matrizadm); //Muestra la matriz del administrador sin barcos

                    //-------------------------ZONA MENU INICIAL-------------------------
                    //----------------------INSERTAR PORTAVIONES------------------------
                    do {
                        //Mientras sentido no sea 1 o 2, me aparecera este menu
                        try {
                            System.out.println("INSERTE EL PORTAVIONES");
                            System.out.println("1 -> Horizontal");
                            System.out.println("2 -> Vertical");
                            System.out.print("Orientacion(1-2): ");
                            sentido = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("No se permiten letras, intentelo de nuevo");
                            vacio = sc.next();
                        }

                        //Metodo para convertir sentido de int a String
                        letra_sentido = Sentido(sentido);

                    } while ((sentido != 1) && (sentido != 2));

                    do {
                        //Nos informa de que dependiendo del sentido tenemos unos limites para el portaviones
                        try {
                            if (sentido == 1) {
                                System.out.print("Fila (0-9): ");       //Si es horizontal la fila es de 0-9
                                fila = sc.nextInt();
                                System.out.print("Columna (0-5): ");    //Y la columna de 0 a 5
                                columna = sc.nextInt();

                            } else {
                                System.out.print("Fila (0-5): ");       //Si es vertical la fila es de 0-5
                                fila = sc.nextInt();
                                System.out.print("Columna (0-9): ");    //Y la columna de 0-9
                                columna = sc.nextInt();
                            }
                        } catch (InputMismatchException e) { //Excepcion si inserta letras nos sale este mensaje
                            System.out.println("No se permiten letras, intentelo de nuevo");
                            vacio = sc.next();

                        }

                        //Metodo para controlar las filas y las columnas segun el barco
                        resp = ControlFilasColumnas("p", sentido, fila, columna);

                    } while (!resp); //Mientras no sea correctas las filas y columnas, me volvera a preguntar por ellas

                    //Si son correctas carga el barco en la matriz del administrador
                    System.out.println("\n");
                    t.CargarBarco(matrizadm, portaviones, fila, columna, letra_sentido);
                    t.MostrarBarco(matrizadm); //Y la muestra

                    //----------------------FIN INSERTAR PORTAVIONES------------------------
                    //--------------------------INSERTAR SUBMARINOS-------------------------
                    do {
                        try {
                            System.out.println("INSERTE EL SUBMARINO");
                            System.out.println("1 -> Horizontal");
                            System.out.println("2 -> Vertical");
                            System.out.print("Orientacion(1-2): ");
                            sentido = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("No se permiten letras, intentelo de nuevo");
                            vacio = sc.next();
                        }
                        letra_sentido = Sentido(sentido);

                    } while ((sentido != 1) && (sentido != 2));

                    do {
                        //Nos informa de que dependiendo del sentido tenemos unos limites para el submarinos
                        try {
                            if (sentido == 1) {
                                System.out.print("Fila (0-9): ");       //Si es horizontal la fila es de 0-9
                                fila = sc.nextInt();
                                System.out.print("Columna (0-7): ");    //Y la columna de 0-7
                                columna = sc.nextInt();
                            } else {
                                System.out.print("Fila (0-7): ");       //Si es vertical la fila es de 0-7
                                fila = sc.nextInt();
                                System.out.print("Columna (0-9): ");    //Y la columna de 0-9
                                columna = sc.nextInt();
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("No se permiten letras, intentelo de nuevo");
                            vacio = sc.next();
                            columna = -1;                   //Para que no me coja el valor que tenia en portaviones
                        }

                        //Metodo para controlar las filas y las columnas segun el barco
                        resp = ControlFilasColumnas("s", sentido, fila, columna);

                        if (resp == true) { //Si las filas y columnas son correctas
                            resp2 = ControlOcupado(matrizadm, submarinos, sentido, fila, columna); //Compruebo con este metodo si esta ocupado por el portaviones
                        }

                        if (resp2 == false) { //Si la respuesta es negativa nos manda un mensaje
                            System.out.println("Posicion ocupada");
                        }

                    } while (!resp2 || !resp); //Mientras las filas y columnas sean incorrectas o la posicion este ocupada se repite

                    //Carga y muestra el submarino en la matriz del administrador
                    System.out.println("\n");
                    t.CargarBarco(matrizadm, submarinos, fila, columna, letra_sentido);
                    t.MostrarBarco(matrizadm);

                    //-----------------------FIN INSERTAR SUBMARINOS------------------------
                    //------------------------INSERTAR DESTRUCTORES-------------------------
                    do {
                        try {
                            System.out.println("INSERTE EL DESTRUCTOR");
                            System.out.println("1 -> Horizontal");
                            System.out.println("2 -> Vertical");
                            System.out.print("Orientacion(1-2): ");
                            sentido = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("No se permiten letras, intentelo de nuevo");
                            vacio = sc.next();
                        }
                        letra_sentido = Sentido(sentido);

                    } while ((sentido != 1) && (sentido != 2));

                    do {
                        try {
                            if (sentido == 1) {
                                System.out.print("Fila (0-9): ");        //Si es horizontal la fila es de 0-9
                                fila = sc.nextInt();
                                System.out.print("Columna (0-8): ");     //Y la columna es de 0-8
                                columna = sc.nextInt();
                            } else {
                                System.out.print("Fila (0-8): ");        //Si es vertical la fila es de 0-8
                                fila = sc.nextInt();
                                System.out.print("Columna (0-9): ");     //Y la columna es de 0-9
                                columna = sc.nextInt();

                            }
                        } catch (InputMismatchException e) {
                            System.out.println("No se permiten letras, intentelo de nuevo");
                            vacio = sc.next();
                            columna = -1;                   //Para que no me coja el valor que tenia en submarinos
                        }

                        resp = ControlFilasColumnas("d", sentido, fila, columna);

                        if (resp == true) {
                            resp2 = ControlOcupado(matrizadm, destructores, sentido, fila, columna);
                        }

                        if (resp2 == false) { //Si la respuesta es negativa nos manda un mensaje
                            System.out.println("Posicion ocupada");
                        }

                    } while (!resp || !resp2);

                    //Carga y muestra el submarino en la matriz del administrador
                    System.out.println("\n");
                    t.CargarBarco(matrizadm, destructores, fila, columna, letra_sentido);
                    t.MostrarBarco(matrizadm);

                    //----------------------FIN INSERTAR DESTRUCTORES-----------------------
                    //------------------------ZONA INICIAL JUGADOR--------------------------
                    System.out.println("\n");
                    System.out.println("EL JUGADOR DEBE INSERTAR LOS DISPAROS");
                    System.out.println("---------------------------------------");
                    System.out.println("\n");
                    t.MostrarInicio(matrizusu); //Muestra la matriz del usuario inicial

                    //---------------------FIN ZONA INICIAL JUGADOR-------------------------
                    //------------------------ZONA PARA DISPARAR----------------------------
                    int contador = 0; //Inicializar contador que hara que pare el juego cuando gane
                    do {
                        do {
                            try {
                                System.out.println("Inserte disparo");
                                System.out.print("Fila (0-9): ");  //La fila de 0-9
                                fila = sc.nextInt();
                                System.out.print("Columna (0-9):"); //Columna de 0-9
                                columna = sc.nextInt();
                            } catch (InputMismatchException e) { //Excepcion si hay letras manda mensaje
                                System.out.println("No se permiten letras, intentelo de nuevo");
                                vacio = sc.nextLine();
                                fila = -1;                  //Para que no me coja el valor fila anterior de destructor
                                columna = -1;               //Para que no me coja el valor columna anterior de destructor
                            }
                            //Metodo Para Comprobar si sale del rango 0-9
                            resp = ControlFilasColumnas("disparo", 0, fila, columna);

                        } while (!resp); //Mientras el rango no sea correcto, lo repite

                        //Contador almacena un numero que retorna el metodo InsertarDisparo cuando el disparo es tocado o hundido
                        contador = t.InsertarDisparo(matrizadm, matrizusu, fila, columna, partida, ListaGuardar);
                        t.MostrarPartida(matrizusu); //Muestra la matriz del usuario, con la letra que haya sido A, T o H

                    } while (contador != 10); //Mientras el contador sea 10 (Numero total para ganar) vuelve a pedir disparo

                    //-------------------------FIN ZONA DISPARAR----------------------------
                    //--------------------------MENSAJE GANADOR-----------------------------
                    if (contador == 10) { //Si contador es 10 ganador

                        //Manda mensaje informando de los movimientos totales en esta partida, ejemplo para utilizar el metodo ContabilizarPartida
                        System.out.println("Total de Movimientos Usados Para Ganar La Partida " + partida + ": " + t.ContabilizarPartida());
                        System.out.println("Total de Puntos En La Partida " + t.ContabilizarPuntos());
                        System.out.println("\n");
                        System.out.println("************************");
                        System.out.println("* HA GANADO LA PARTIDA *");
                        System.out.println("************************");
                        System.out.println("\n");
                    }

                    //Suma 1 a partida por si quiere seguir jugando
                    partida++;
                    break;

                //Case 2, nos permite leer las partidas  
                case 2:

                    do {
                        try {
                            System.out.println("1 -> Leer Todas Las Partidas");
                            System.out.println("2 -> Leer Partida Especifica");
                            System.out.println("3 -> Leer Cantidad de Agua en Partida Especifica");
                            System.out.print("Escoja una opcion: ");
                            menu3 = sc.nextInt();

                            if (menu3 == 1) { //Si es 1 nos lee todas las partidas por orden de partida
                                System.out.println("Leyendo fichero GuardarPartida.txt..");
                                System.out.println("-----------------------------------------------");
                                t.LeerPartidaGuardada("GuardarPartida.txt", ListaGuardar);  //Mandamos nombre de fichero y el treeMap

                            } else if (menu3 == 2) {//Si es 2 lee la partida que le pedi el usuario
                                System.out.print("Numero de Partida a Leer: ");
                                int pleer = sc.nextInt();
                                System.out.println("-----------------------------------------------");
                                t.LeerPartidaElegida("GuardarPartida.txt", ListaGuardar, pleer); //Mandamos nombre de fichero, treeMap, y el nº de la partida

                            } else if (menu3 == 3) {
                                System.out.print("Numero de Partida a Leer: ");
                                int pleer = sc.nextInt();
                                System.out.println("-----------------------------------------------");
                                t.LeerPartidaElegidaAgua("GuardarPartida.txt", ListaGuardar, pleer); //Mandamos nombre de fichero, treeMap, y el nº de la partida
                                menu3=1;

                            } else { //Si no es ni 1 ni 2, nos manda mensaje de error
                                System.out.println("Opcion no valida, intentelo de nuevo");
                            }
                        } catch (InputMismatchException e) { //Si se introduce letras manda mensaje
                            System.out.println("No se permiten letras, intentelo de nuevo");
                            vacio = sc.next();
                        }

                    } while (menu3 != 1 && menu3 != 2 && menu != 3); //Mientras menu3 no sea 1 o 2 me repite el menu

                    break;

                //Case 3, nos muestra la cantidad de movimientos por partida ordenados demenor a mayor
                case 3:
                    System.out.println("Mostrando Cantidad de Movimientos Por Partida Ordenados de menor a mayor..");
                    t.OrdenarPartida(ListaGuardar); //Mandamos el treeMap
                    break;

                //Case 4, Salimos cambiando el valor de menu a 1
                case 4:
                    menu = 1;
                    break;
            }

        } while (menu != 1); //Mientras menu no sea 1, se repite el menu principal
    }

//-----------------CONTROLAR RANGO FILAS Y COLUMNAS---------------------
    public static boolean ControlFilasColumnas(String letra, int sentido, int fila, int columna) {

        boolean resp = false;

        switch (letra) {
            case "p": //Si es un portaviones, segun su sentido se podra un rango y otro
                if (sentido == 1) {
                    if (fila >= 0 && fila <= 9 && columna >= 0 && columna <= 5) {
                        resp = true;
                    } else {
                        resp = false;
                        System.out.println("Posicion fuera del rango, intentelo de nuevo");
                    }

                } else {
                    if (fila >= 0 && fila <= 5 && columna >= 0 && columna <= 9) {
                        resp = true;
                    } else {
                        resp = false;
                        System.out.println("Posicion fuera del rango, intentelo de nuevo");
                    }
                }

                break;

            case "s": //Si es un submarino, segun su sentido se podra un rango y otro
                if (sentido == 1) {
                    if (fila >= 0 && fila <= 9 && columna >= 0 && columna <= 7) {
                        resp = true;
                    } else {
                        resp = false;
                        System.out.println("Posicion fuera del rango, intentelo de nuevo");
                    }

                } else {
                    if (fila >= 0 && fila <= 7 && columna >= 0 && columna <= 9) {
                        resp = true;
                    } else {
                        resp = false;
                        System.out.println("Posicion fuera del rango, intentelo de nuevo");
                    }
                }
                break;

            case "d": //Si es un destructor, segun su sentido se podra un rango y otro

                if (sentido == 1) {
                    if (fila >= 0 && fila <= 9 && columna >= 0 && columna <= 8) {
                        resp = true;
                    } else {
                        resp = false;
                        System.out.println("Posicion fuera del rango, intentelo de nuevo");
                    }

                } else {
                    if (fila >= 0 && fila <= 8 && columna >= 0 && columna <= 9) {
                        resp = true;
                    } else {
                        resp = false;
                        System.out.println("Posicion fuera del rango, intentelo de nuevo");
                    }
                }
                break;

            case "disparo": //Si es un disparo, segun su sentido se podra un rango y otro

                if (fila >= 0 && fila <= 9 && columna >= 0 && columna <= 9) {
                    resp = true;
                } else {
                    resp = false;
                    System.out.println("Posicion fuera del rango, intentelo de nuevo");
                }
                break;
        }

        return resp; //Si retorna falso, es que esta fuera de rango y vuelve a pedir filas y columnas
    }

    //--------------------------CONTROLAR POSICION OCUPADA----------------------------  
    public static boolean ControlOcupado(String matriz[][], Barco b, int sentido, int fila, int columna) {

        boolean resp2 = true;

        if (fila >= 0 && fila <= 9 && columna >= 0 && columna <= 9) { //Si fila y columna esta en el rango general correcto 0-9

            if (sentido == 1) { //Si el sentido es horizontal

                //Hace un bucle para ver si las columnas son distintas a "*"
                for (int i = columna; i <= (columna + b.getTam()) - 1; i++) {
                    if (matriz[fila][i] != "*") { //Si es distinto
                        resp2 = false;  //Entonces hay algun barco y resp2 se pone en falso
                    }
                }

            } else { //Si es vertical

                //Hace un bucle para ver si las filas son distintas a "*"
                for (int i = fila; i <= (fila + b.getTam()) - 1; i++) {
                    if (matriz[i][columna] != "*") { //Si es distinto
                        resp2 = false;  //Entonces hay algun barco y resp2 se pone en falso
                    }
                }
            }

        } else { //Si fila y columna no estan en el ragno correcto tambien se pone en falso
            resp2 = false;
        }
        return resp2; //Si retorna falso, vuelve a pedir posicion del barco, si retorna verdadero continua el programa
    }

    //----------------------ENVIAR EL SENTIDO EN STRING---------------------
    //Para mandar o guardar en tipo String la orientacion o sentido 
    public static String Sentido(int orientacion) {
        String letra_sentido;
        if (orientacion == 1) {
            letra_sentido = "Horizontal";
        } else {
            letra_sentido = "Vertical";
        }
        return letra_sentido;

    }
}
