package juegohundelbarco;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeMap;

/*
    Autor: Nora Quesada
    Fecha: 20/03/2019
    Actividad: Clase Tablero
 */
public class Tablero implements java.io.Serializable {

    //Inicializamos el ArrayList Lista que usaremos
    ArrayList<Registro> Lista = new ArrayList();

    //Inicializamos las variables que usaremos como contadores
    int total = 0;  //Contador total para metodo ContabilizarPartida
    //CONTADOR DE PUNTOS
    int totalpuntos = 0;
    int cpp = 0;
    int cps = 0;
    int cpd = 0;
    int cpa = 0;
    int ct = 0;     //Contador total disparos acertados
    int cp = 0;     //Contador tocado portaviones
    int cs = 0;     //Contador tocado submarino
    int cd = 0;     //Contador tocado destructor
    int ch = 0;     //contador hundidos

    //Metodo CargarBarco, cargara en la matriz del administrador los 3 barcos
    public void CargarBarco(String matrizadm[][], Barco b, int fila, int columna, String sentido) {

        switch (b.getTam()) { //Segun el tamaño del barco

            case 5: //Si es 5, sabemos que es el portaviones

                if (sentido.equalsIgnoreCase("Horizontal")) { //Si es horizontal la y suma
                    for (int y = columna; y <= (columna + 4); y++) {
                        matrizadm[fila][y] = "P";
                    }
                } else {
                    for (int x = fila; x <= (fila + 4); x++) { //Si es vertical la x suma
                        matrizadm[x][columna] = "P";
                    }
                }
                break;

            case 3: //Si es 3, sabemos que es un submarino

                if (sentido.equalsIgnoreCase("Horizontal")) { //Si es horizontal la y suma
                    for (int y = columna; y <= (columna + 2); y++) {
                        matrizadm[fila][y] = "S";
                    }
                } else {
                    for (int x = fila; x <= (fila + 2); x++) { //Si es vertical la x suma
                        matrizadm[x][columna] = "S";
                    }
                }
                break;

            case 2: //Si es 2, sabemos que es un destructor

                if (sentido.equalsIgnoreCase("Horizontal")) {
                    for (int y = columna; y <= (columna + 1); y++) { //Si es horizontal la y suma
                        matrizadm[fila][y] = "D";
                    }
                } else {
                    for (int x = fila; x <= (fila + 1); x++) { //Si es vertical la x suma
                        matrizadm[x][columna] = "D";
                    }
                }
                break;
        }
    }

    //Metodo MostrarInicio, mostramos las matrices por primera vez vacias, solo con "*"
    public void MostrarInicio(String matriz[][]) {

        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7\t8\t9\n"); //Para ver el numero de las filas

        for (int i = 0; i < matriz.length; i++) {
            System.out.print(i + "\t");                    //Para ver el numero de las columnas
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] = "*");      //Imprimimos "*" oculto
                if (j != matriz[i].length - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println("\n");
        }
    }

    //MetodoBarco, mostramos solo al administrador, para que vea donde pone los barcos
    public void MostrarBarco(String matrizadmn[][]) {

        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7\t8\t9\n"); //Para ver el numero de las filas

        for (int i = 0; i < matrizadmn.length; i++) {
            System.out.print(i + "\t");                        //Para ver el numero de las columnas
            for (int j = 0; j < matrizadmn[i].length; j++) {
                System.out.print(matrizadmn[i][j]);          //Imprimimos la matrizadm, que deberia estar cargada con algun barco
                if (j != matrizadmn[i].length - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println("\n");
        }
    }

    //MostrarPartida, mostramos al usuario los disparos que esta realizando, y como esta quedando la partida
    public void MostrarPartida(String matrizusu[][]) {

        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7\t8\t9\n"); //Para ver el numero de las filas

        for (int i = 0; i < matrizusu.length; i++) {
            System.out.print(i + "\t");                     //Para ver el numero de las columnas
            for (int j = 0; j < matrizusu[i].length; j++) {
                System.out.print(matrizusu[i][j]);          //Imprimimos la matrizusu, que deberia estar cargada con los disparos del usuario
                if (j != matrizusu[i].length - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println("\n");
        }
    }

    //METODO PARA LA FECHA ACTUAL
    public static String fechaActual() {

        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");

        return formatoFecha.format(fecha);

    }

    //Metodo InsertarDisparo, inserta el disparo comparando la matrizadm con la matrizusu
    public int InsertarDisparo(String matrizadm[][], String matrizusu[][], int fila, int columna, int partida, TreeMap ListaGuardar) {

        Registro r; //Inicializamos r
        String efecto = null;   //Inicializamos efecto
        boolean enviar = false; //Inicializmos enviar
        String puntos = null;   //Inicializamos puntos
        String fecha = null;

        //Si en la matrizadm, la posicion que ha enviado el usuario es igual a "*" es que no hay barco, es decir es agua
        if (matrizadm[fila][columna] == "*") {

            if (matrizusu[fila][columna] != "A") { //Si no hay todavia una "A" en la matrizusu

                matrizusu[fila][columna] = "A";    //Guardamos "A" en esa posicion en la matrizusu
                efecto = "Agua";                   //La variable efecto sera "Agua"
                puntos = "-1";                     //La variable puntos sera -1
                cpa++;
                fecha = fechaActual();
                enviar = true;                     //Y se podra enviar
                ContabilizarPartida();             //Metodo ContabilizarPartida, para sumar el total
                System.out.println("AGUA\n");      //E informamos al usuario que ha disparado al agua

            } else {//Si ya hay una "A", le decimos al usuario que ya a disparado en esa posicion
                System.out.println("Ya ha disparado en esta posicion, intentelo de nuevo\n");
            }

        } else if (matrizadm[fila][columna] == "P") {  //Si en la matrizadm, la posicion es igual a "P" es que hay un portaaviones

            if (matrizusu[fila][columna] != "T") { //Si no hay todavia una "T" en la matrizusu

                if (cp != 4) {                                   //Si el contador de tocadoportaviones no es 4
                    matrizusu[fila][columna] = "T";              //Guardamos "T" en esa posicion en la matrizusu
                    cp++;                                        //Sumamos 1 al contador de tocados
                    cpp = cpp + 15;
                    efecto = "Tocado";                           //La variable efecto sera "Tocado"
                    puntos = "15";                              //La variable putnos sera 15
                    fecha = fechaActual();
                    enviar = true;
                    ContabilizarPartida();                       //Metodo ContabilizarPartida, para sumar el total         
                    System.out.println("TOCADO PORTAVIONES\n");    //E informamos al usuario que ha tocado el portaviones

                } else if (matrizusu[fila][columna] != "H") {    //Si el contador de tocadoportaviones es 4
                    matrizusu[fila][columna] = "H";              //Guardamos "H" en esa posicion en la matrizusu
                    ch++;                                        //Sumamos 1 al contador de hundidos
                    cpp = cpp + 15;
                    efecto = "Hundido";                          //La variable efecto sera "Hundido"
                    puntos = "15";                              //La variable putnos sera 15
                    fecha = fechaActual();
                    enviar = true;
                    ContabilizarPartida();
                    System.out.println("HUNDIDO PORTAVIONES\n");  //E informamos al usuario que ha hundido el portaviones

                } else {  //Si vuelve a disparar le informamos que ya a hundido el portaaviones
                    System.out.println("Ya ha disparado y hundido el portaviones\n");
                }

            } else { //Si ya hay una "T", le decimos al usuario que ya a disparado en esa posicion
                System.out.println("Ya ha disparado en esta posicion, intentelo de nuevo\n");
            }

        } else if (matrizadm[fila][columna] == "S") { //Si en la matrizadm, la posicion es igual a "S" es que hay un submarino

            if (matrizusu[fila][columna] != "T") {

                if (cs != 2) {
                    matrizusu[fila][columna] = "T";
                    cs++;
                    cps = cps + 10;
                    efecto = "Tocado";
                    puntos = "10";                              //La variable punyos sera 10
                    fecha = fechaActual();
                    enviar = true;
                    ContabilizarPartida();
                    System.out.println("TOCADO SUBMARINO\n");

                } else if (matrizusu[fila][columna] != "H") {
                    matrizusu[fila][columna] = "H";
                    ch++;
                    cps = cps + 10;
                    efecto = "Hundido";
                    puntos = "10";                              //La variable punyos sera 10
                    fecha = fechaActual();
                    enviar = true;
                    ContabilizarPartida();
                    System.out.println("HUNDIDO SUBMARINO\n");
                } else {
                    System.out.println("Ya ha disparado y hundido el submarino\n");
                }

            } else {
                System.out.println("Ya ha disparado en esta posicion, intentelo de nuevo\n");
            }

        } else if (matrizadm[fila][columna] == "D") { //Si en la matrizadm, la posicion es igual a "D" es que hay un destructor

            if (matrizusu[fila][columna] != "T") {

                if (cd != 1) {
                    matrizusu[fila][columna] = "T";
                    cd++;
                    cpd = cpd + 5;
                    efecto = "Tocado";
                    puntos = "5";                              //La variable punyos sera 5
                    fecha = fechaActual();
                    enviar = true;
                    ContabilizarPartida();
                    System.out.println("TOCADO DESTRUCTOR\n");
                } else if (matrizusu[fila][columna] != "H") {
                    matrizusu[fila][columna] = "H";
                    ch++;
                    cpd = cpd + 5;
                    efecto = "Hundido";
                    puntos = "5";                              //La variable punyos sera 5
                    fecha = fechaActual();
                    enviar = true;
                    ContabilizarPartida();
                    System.out.println("HUNDIDO DESTRUCTOR\n");

                } else {
                    System.out.println("Ya ha disparado y hundido el destructor\n");
                }

            } else {
                System.out.println("Ya ha disparado en esta posicion, intentelo de nuevo\n");
            }
        } else {
            System.out.println("Posicion no valida, introduzca en fila y columna numeros del 0-9\n");
        }

        if (enviar == true) { //Si enviar es igual a true
            r = new Registro(partida, fila, columna, efecto, puntos, fecha); //Creamos r //METEMOS LOS PUNTOS EN EL REGISTRO
            Lista.add(r); //Y lo añadimos a la lista del ArrayList
        }
        ct = cp + cs + cd + ch; //sumamos los contadores para tener el total de aciertos

        if (ct == 10) { //Si el total es igual a 10, acabara la partida
            ListaGuardar.put(partida, Lista); //Por lo que guardamos en el treeMap la Lista con partida como key
            GuardarDisparo(ListaGuardar);     //Mandamos el treeMap al metodo GuardarPartida
            //SUMAMOS EL TOTAL DE PUNTOS CUANDO ACABA LA PARTIDA
            totalpuntos = cpp + cps + cpd - cpa;
        }
        return ct; //Retornamos contador total para terminar la partida en el main

    }

    //Metodo ContabiizarPartida, suma uno cada vez que hay un disparo
    public int ContabilizarPartida() {
        total++;
        return total - 1; //Devolvemos el total-1, ya que la ultima vez que se le llama es en el main
    }

    //ENVIAR PUNTOS
    public int ContabilizarPuntos() {
        return totalpuntos; //Devolvemos el -1, ya que la ultima vez que se le llama es en el main
    }

    //Metodo GuardarDisparo, guardara en un fichero como objeto una lista de los disparos
    public void GuardarDisparo(TreeMap ListaGuardar) {

        try {
            FileOutputStream fos = new FileOutputStream("GuardarPartida.txt", false); //Lo debemos poner en falso para no se sobreescriba
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ListaGuardar);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Error en lectura del fichero");
        }

    }

    //Metodo CargarTreeMap, para cuando el programa se detenga, lo primero que haga sea cargar el contenido del fichero y tener las partidas anteriores
    public static TreeMap CargarTreeMap(String fichero, TreeMap CargarLista) {

        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            f = new File(fichero);
            if (f.exists()) {       //Si existe el fichero "GuardarPartida.txt"
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                CargarLista = (TreeMap) ois.readObject();   //Nos lo guarda en CargarLista
            }

            if (ois != null) {
                ois.close();
                fis.close();
            }
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
            System.out.println("Error en lectura");
        }

        return CargarLista;  //Y retornamos CargarLista, para en el main guardarlo en ListaGuardar y usarlo
    }

    //Metodo LeerPartida, nos leera el fichero donde guardamos la informacion
    public void LeerPartidaGuardada(String fichero, TreeMap LeerLista) {
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            f = new File(fichero);
            if (f.exists()) {
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);

                LeerLista = (TreeMap) ois.readObject();
                //Iterador TreeMap
                Iterator MostrarPartida = LeerLista.keySet().iterator();
                while (MostrarPartida.hasNext()) {
                    int key = (int) MostrarPartida.next();
                    ArrayList r = (ArrayList) LeerLista.get(key);
                    //Iterador Arraylist
                    Iterator<Registro> MostrarPartida2 = r.iterator();
                    while (MostrarPartida2.hasNext()) {
                        Registro r2 = MostrarPartida2.next();
                        r2.MostrarDatos();
                    }
                    System.out.println("-----------------------------------------------");
                }
            }
            if (ois
                    != null) {
                ois.close();
                fis.close();
            }
        } catch (IOException e) {
            System.out.println("Fin Fichero");
        } catch (ClassNotFoundException e) {
            System.out.println("Error en lectura");
        }

    }

    //Metodo LeerPartidaElegida, nos leera la partida que quiera el usuario
    public void LeerPartidaElegida(String fichero, TreeMap LeerListaElegida, int elegir) {
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            f = new File(fichero);
            if (f.exists()) {
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);

                LeerListaElegida = (TreeMap) ois.readObject();
                //Iterador TreeMap
                Iterator MostrarPartida = LeerListaElegida.keySet().iterator();
                while (MostrarPartida.hasNext()) {
                    int key = (int) MostrarPartida.next();
                    ArrayList r = (ArrayList) LeerListaElegida.get(key);
                    //Iterador Arraylist
                    Iterator<Registro> MostrarPartida2 = r.iterator();
                    while (MostrarPartida2.hasNext()) {
                        Registro r2 = MostrarPartida2.next();
                        int partida = r2.getPartida();      //Guardamos el valor de partida en partida

                        if (partida == elegir) {    //Si partida es igual al numero de partida que queria leer el usuario
                            r2.MostrarDatos();      //Nos lo muestra usando el metodo MostrarDatos de Registro 
                        }                           //Si no se encuentra la partida no muestra nada
                    }
                }
                System.out.println("---------------------------------------");
            }
            if (ois
                    != null) {
                ois.close();
                fis.close();
            }
        } catch (IOException e) {
            System.out.println("Fin Fichero");
        } catch (ClassNotFoundException e) {
            System.out.println("Error en lectura");
        }

    }

    public void LeerPartidaElegidaAgua(String fichero, TreeMap LeerListaElegida, int elegir) {

        int contador = 0;

        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            f = new File(fichero);
            if (f.exists()) {
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);

                LeerListaElegida = (TreeMap) ois.readObject();
                //Iterador TreeMap
                Iterator MostrarPartida = LeerListaElegida.keySet().iterator();
                while (MostrarPartida.hasNext()) {
                    int key = (int) MostrarPartida.next();
                    ArrayList r = (ArrayList) LeerListaElegida.get(key);
                    //Iterador Arraylist
                    Iterator<Registro> MostrarPartida2 = r.iterator();
                    while (MostrarPartida2.hasNext()) {
                        Registro r2 = MostrarPartida2.next();
                        int partida = r2.getPartida();      //Guardamos el valor de partida en partida

                        if (partida == elegir) {    //Si partida es igual al numero de partida que queria leer el usuario
                            if (r2.getEfecto().equalsIgnoreCase("Agua")) {
                                contador++;

                            }

                        }                           //Si no se encuentra la partida no muestra nada
                    }
                }

                System.out.println("La Partida " + elegir + " Ha tocado Agua " + contador + " Veces");
                System.out.println("-----------------------------------------------");
            }
            if (ois
                    != null) {
                ois.close();
                fis.close();
            }
        } catch (IOException e) {
            System.out.println("Fin Fichero");
        } catch (ClassNotFoundException e) {
            System.out.println("Error en lectura");
        }
    }

    //Metodo OrdenarPartida, use una clase aparte y el mismo reglamento que en los otros metodos
    public void OrdenarPartida(TreeMap ListaOrdenar) {
        //ArrayList<OrdenarPartida> ListaO = new ArrayList(); //Inicializamos ListaO
        //OrdenarPartida = obj = null;                       //Inicializamos el obj de OrdenPartida
        // Registro obj = null;                          //Inicializamos el obj de Registro
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            f = new File("GuardarPartida.txt");
            if (f.exists()) {
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                ListaOrdenar = (TreeMap) ois.readObject();
                //Iterador TreeMap
                Iterator MostrarPartida = ListaOrdenar.keySet().iterator();
                while (MostrarPartida.hasNext()) {
                    int key = (int) MostrarPartida.next();
                    ArrayList r = (ArrayList) ListaOrdenar.get(key);
                    //Iterador Arraylist
                    Iterator<Registro> MostrarPartida2 = r.iterator();
                    while (MostrarPartida2.hasNext()) {
                        MostrarPartida2.next();
                    }
                    System.out.println("Partida: " + key + " -> " + "Movimientos: " + r.size());
                    //int contador = r.size();            //Contador guardara r.size, el tamaño de r que seria igual a la cantidad de movimiento totales
                    //  int partida = key;                  //Partida guardara Key que seria el valor de partida
                    //obj = new OrdenarPartida(partida, contador); //Creamos el obj
                    //ListaO.add(obj);                    //Lo añadimos a la lista
                }

                System.out.println("-----------------------------------------------");

                /*Ordenamos con sort la lista por contador
                ListaO.sort(Comparator.comparing(OrdenarPartida::getContador));
                //Mediante un iterador mostramos la lista ListaO ordenada por contador
                Iterator Ordenacont = ListaO.iterator();
                while (Ordenacont.hasNext()) {
                    OrdenarPartida obj2 = (OrdenarPartida) Ordenacont.next();
                    System.out.println("Partida: " + obj2.getPartida() + " -> " + "Movimientos: " + obj2.getContador());
                }*/
                //  System.out.println("-----------------------------------------------");
            }

            if (ois != null) {
                ois.close();
                fis.close();
            }
        } catch (IOException e) {
            System.out.println("Fin Fichero");
        } catch (ClassNotFoundException e) {
            System.out.println("Error en lectura");
        }
    }
}
