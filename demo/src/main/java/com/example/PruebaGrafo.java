package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author javie
 */
public class PruebaGrafo{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("src\\aeropuertos_1.txt", "src\\conexiones_1.txt",
                false, TGrafoDirigido.class);
        
        Object[] etiquetasarray = gd.getEtiquetasOrdenado();*/

        

        //UT7_mainDado(gd,etiquetasarray);

        //UT7_TA3(gd, etiquetasarray);
        //UT7_TA4(gd);
        //UT7_TA5();
        //UT7_TA6_1();
        //UT7_TA6_2();
        //UT7_TA6_3();

        //UT7_PD2();
        //UT7_PD3();
        //UT7_PD4();
        //UT7_PD5_3();
        //UT7_PD7();
        //UT7_PD8();
        
        //UT8_PD1();
        //UT8_PD2();
        //UT8_PD3();
        //UT8_PD4(); 
        //UT8_TA5(); 
        //UT8_TA6(); 

        //Parcial2018enGrafoKevinBacon(); 
        //Parcial2018enGrafoNoDirigido();
        //Parcial2019(); 
        Parcial2020(); //  no funca el main - testing
        //Parcial2020Personalizado(); 
        //Parcial2021(); 
    }

    @SuppressWarnings({ "rawtypes"})
    public static void UT7_mainDado(TGrafoDirigido gd, Object[] etiquetasarray){
        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");
        Double[][] mfloyd = gd.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd, gd.getVertices(), "Matriz luego de FLOYD");
        for (int i = 0; i < etiquetasarray.length; i++) {
            System.out.println("excentricidad de " + etiquetasarray[i] + " : " + gd.obtenerExcentricidad((Comparable) etiquetasarray[i]));
        }
        System.out.println();
        System.out.println("Centro del grafo: " + gd.centroDelGrafo());
    }

    public static void UT7_TA3(TGrafoDirigido gd, Object[] etiquetasarray){
        /* ----------- EJERCICIO 2 DEL TA3-----------
         * WARSHALL: En muchas oportunidades interesa simplemente conocer si existe algún itinerario que nos permita viajar desde una cierta ciudad a otra.  
         * Implemente un algoritmo que permita conocer la conectividad entre cualquier par de ciudades. 
         * El programa Java resultante deberá permitir: 
         *    • contestar interactivamente preguntas del tipo “indique  si es posible volar desde la ciudad x a la ciudad y” 
         */

        // Llamar al método warshall para obtener la matriz de conectividad
        boolean[][] conectividad = gd.warshall();

        // Interacción con el usuario para preguntar sobre la conectividad entre ciudades
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Indique la ciudad de origen (o 'salir' para terminar):");
            String origen = scanner.nextLine();
            if (origen.equalsIgnoreCase("salir")) {
                break;
            }

            System.out.println("Indique la ciudad de destino:");
            String destino = scanner.nextLine();

            if (origen.isEmpty() || destino.isEmpty() || !gd.getVertices().containsKey(origen) || !gd.getVertices().containsKey(destino)) {
                System.out.println("Ciudad no válida. Intente nuevamente.");
                continue;
            }

            int indiceOrigen = -1;
            int indiceDestino = -1;

            // Obtener los índices de las ciudades en la matriz de conectividad
            for (int i = 0; i < etiquetasarray.length; i++) {
                if (etiquetasarray[i].equals(origen)) {
                    indiceOrigen = i;
                }
                if (etiquetasarray[i].equals(destino)) {
                    indiceDestino = i;
                }
            }

            // Verificar si existe conectividad entre las ciudades
            if (indiceOrigen != -1 && indiceDestino != -1) {
                if (conectividad[indiceOrigen][indiceDestino]) {
                    System.out.println("Es posible volar desde " + origen + " a " + destino);
                } else {
                    System.out.println("No es posible volar desde " + origen + " a " + destino);
                }
            } else {
                System.out.println("No se encontraron índices válidos para las ciudades especificadas.");
            }
        }
        scanner.close();
    }

    @SuppressWarnings({ "rawtypes"})
    public static void UT7_TA4(TGrafoDirigido gd){
        //  ----------- EJERCICIO 2 DEL TA4-----------
        Collection<TVertice> recorrido = gd.bpf();
        // imprimir etiquetas del bpf de todo el grafo....
        System.out.println("\nRecorrido BPF de todo el grafo:");
            for (TVertice vertice : recorrido) {
                System.out.println(vertice.getEtiqueta());
            }

        Collection<TVertice> recorrido_Asuncion = gd.bpf("Asuncion");
        // imprimir etiquetas del bpf desde Asunción....
        System.out.println("\nRecorrido BPF desde Asunción:");
            for (TVertice vertice : recorrido_Asuncion) {
                System.out.println(vertice.getEtiqueta());
            }
        
    }

    public static void UT7_TA5(){
        //----------- EJERCICIO 2 y 3 DEL TA5-----------
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("src\\aeropuertos_2.txt", "src\\conexiones_2.txt",
                false, TGrafoDirigido.class);
        System.out.println("\nCaminos posibles desde Santos a Curitiba : ");
        TCaminos caminos = gd.todosLosCaminos("Santos", "Curitiba");
        caminos.imprimirCaminosConsola(); // NO HAY CAMINO
        System.out.println("\nCaminos posibles desde Asunción a Curitiba : ");
        TCaminos caminos2 = gd.todosLosCaminos("Asuncion", "Curitiba");
        caminos2.imprimirCaminosConsola();
    }

    public static void UT7_TA6_1(){
        //----------- EJERCICIO 1 DEL TA6-----------
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("src\\aeropuertos_2.txt", "src\\conexiones_2.txt",
                false, TGrafoDirigido.class);
        gd.tieneCiclo();
    }
    
    /*@SuppressWarnings("rawtypes") 
    //comentado porque los metodos en si estan comentados en la clase TGrafoDirigido y en TVertice, los metodos funcionan bien
    public static void UT7_TA6_2(){
        //----------- EJERCICIO 2 DEL TA6-----------
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("PD8/src/tareas.txt", "PD8\\src\\precedencias.txt",
                false, TGrafoDirigido.class);

        System.out.println("\nCamino critico : ");
        TCamino caminoCritico = gd.caminoCriticoCasoEspecial();
        caminoCritico.imprimirEtiquetasConsola();


        System.out.println("\nCaminos no criticos: ");
        TCaminos caminosNoCriticos = gd.obtenerCaminosNoCriticosCasoEspecial();
        caminosNoCriticos.imprimirCaminosNoCriticosConsola();

    }*/

    @SuppressWarnings({ "rawtypes" })
    public static void UT7_TA6_3(){
        //----------- EJERCICIO 3 DEL TA6-----------
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("src\\tareas.txt", "src\\precedencias.txt",
                false, TGrafoDirigido.class);
        LinkedList<TVertice> ordenParcial = gd.obtenerOrdenParcial();
        System.out.println("Orden parcial del grafo: ");
        for (TVertice vertice : ordenParcial){
            System.out.println(vertice.getEtiqueta());
        }
    }

    @SuppressWarnings({ "rawtypes"})
    public static void  UT7_PD2(){
        //----------- PD2-----------
        // Creación de la instancia de TGrafoDirigido
        TGrafoDirigido gd = new TGrafoDirigido(new LinkedList<>(), new LinkedList<>());

        // Inserción de los vértices
        gd.insertarVertice("Artigas");
        gd.insertarVertice("Canelones");
        gd.insertarVertice("Durazno");
        gd.insertarVertice("Florida");
        gd.insertarVertice("Montevideo");
        gd.insertarVertice("Punta del Este");
        gd.insertarVertice("Rocha");

        // Inserción de las aristas
        gd.insertarArista(new TArista("Artigas", "Rocha", 400));
        gd.insertarArista(new TArista("Canelones", "Artigas", 500));
        gd.insertarArista(new TArista("Canelones", "Colonia", 200));
        gd.insertarArista(new TArista("Canelones", "Durazno", 170));
        gd.insertarArista(new TArista("Canelones", "Punta del Este", 90));
        gd.insertarArista(new TArista("Colonia", "Montevideo", 180));
        gd.insertarArista(new TArista("Florida", "Durazno", 60));
        gd.insertarArista(new TArista("Montevideo", "Artigas", 700));
        gd.insertarArista(new TArista("Montevideo", "Canelones", 30));
        gd.insertarArista(new TArista("Montevideo", "Punta del Este", 130));
        gd.insertarArista(new TArista("Punta del Este", "Rocha", 90));
        gd.insertarArista(new TArista("Rocha", "Montevideo", 270));
        gd.insertarArista(new TArista("Florida", "Durazno", 60));

        Object[] etiquetasarray = gd.getEtiquetasOrdenado();

        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());

        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");
        
        Double[][] mfloyd= gd.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd, gd.getVertices(), "Matriz luego de FLOYD");
        
        for (int i = 0; i < etiquetasarray.length; i++) {
            System.out.println("excentricidad de " + etiquetasarray[i] + " : " + gd.obtenerExcentricidad((Comparable) etiquetasarray[i]));
        }
        System.out.println();
        System.out.println("Centro del grafo: " + gd.centroDelGrafo());
    }

    @SuppressWarnings("rawtypes")
    public static void UT7_PD3(){
        // ----------- EJERCICIO 1 DEL PD3-----------
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("src\\aeropuertos.txt", "src\\conexiones.txt",
                false, TGrafoDirigido.class);
        
        Object[] etiquetasarray = gd.getEtiquetasOrdenado();

        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");
        Double[][] mfloyd = gd.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd, gd.getVertices(), "Matriz luego de FLOYD");
        for (int i = 0; i < etiquetasarray.length; i++) {
            System.out.println("excentricidad de " + etiquetasarray[i] + " : " + gd.obtenerExcentricidad((Comparable) etiquetasarray[i]));
        }
        System.out.println();
        System.out.println("Centro del grafo: " + gd.centroDelGrafo() + "\n");

        // ----------- EJERCICIO 2 DEL PD3-----------
        System.out.println("\nCaminos posibles desde Montevideo a Curitiba : ");
        TCaminos caminos = gd.todosLosCaminos("Montevideo", "Curitiba");
        caminos.imprimirCaminosConsola(); 
        System.out.println("\nCaminos posibles desde Porto Alegre a Santos : ");
        TCaminos caminos2 = gd.todosLosCaminos("Porto_Alegre", "Santos");
        caminos2.imprimirCaminosConsola();
        
        // ----------- EJERCICIO 3 PARTE 1 DEL PD3-----------
        System.out.println("\nRecorrido BPF desde todos los vértices:");
        Collection<TVertice> recorridoBPF = gd.bpf();
        for (TVertice vertice : recorridoBPF) {
            System.out.println(vertice.getEtiqueta());
        }
        
        // ----------- EJERCICIO 3 PARTE 3 DEL PD3-----------
        System.out.println("\nRecorrido BPF desde Montevideo:");
        Collection<TVertice> recorridoBPFDesdeMVD = gd.bpf("Montevideo");
        for (TVertice vertice : recorridoBPFDesdeMVD) {
            System.out.println(vertice.getEtiqueta()); // no visita todos los vertices del grafo 
        }

        System.out.println("\nRecorrido BPF completo (todos los vertices del grafo visitados) desde Montevideo:");
        Collection<TVertice> recorridoBPFCompletoDesdeMVD = gd.bpfCompletoDesde("Montevideo");
        for (TVertice vertice : recorridoBPFCompletoDesdeMVD) {
            System.out.println(vertice.getEtiqueta()); 
        }

        // ----------- EJERCICIO 3 PARTE 4 DEL PD3-----------
        System.out.println("\nCaminos posibles desde Montevideo a Rio de Janeiro : ");
        TCaminos caminos3 = gd.todosLosCaminos("Montevideo", "Rio_de_Janeiro");
        caminos3.imprimirCaminosConsola(); 

    }

    public static void UT7_PD4(){
        // ----------- EJECUCIÓN DEL PD4-----------
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("src\\aeropuertos_2.txt", "src\\conexiones_2.txt",
                false, TGrafoDirigido.class);
        System.out.println("\nCaminos posibles desde Asunción a Montevideo : ");
        TCaminos caminos = gd.todosLosCaminos("Asuncion", "Montevideo");
        caminos.imprimirCaminosConsola();

    }

    public static void UT7_PD5_3(){
        // ----------- PD5 EJERCICIO 3 PARTE 1-----------
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("src\\aeropuertos_1.txt", "src\\conexiones_1.txt",
                false, TGrafoDirigido.class);

        //boolean esFuertementeConexo = gd.esFuertementeConexo(); no funciona siempre bien
        boolean esConexo = gd.esConexo();
        boolean esConexoAcordeACantidadComponentes = gd.esConexoAcordeACantidadComponentes();

        // ambos casos deben de retornar la misma respuesta
        System.out.println("El grafo es conexo: " + esConexo);
        System.out.println("El grafo es conexo acorde a su cantidad de componentes conexos?" + esConexoAcordeACantidadComponentes + "\n");
        
        // ----------- PD5 EJERCICIO 3 PARTE 2-----------
        TCaminos componentesConexos = gd.obtenerComponentesFuertementeConectados();
        componentesConexos.imprimirComponentesConexosConsola();
    }

    @SuppressWarnings({"rawtypes" })
    public static void UT7_PD7(){
        //----------- EJERCICIOS 1 Y 2 DEL PD7-----------
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("src\\tareas.txt", "src\\precedencias.txt",
                false, TGrafoDirigido.class);
        
        LinkedList<TVertice> ordenParcial = gd.obtenerOrdenParcial();
        System.out.println("Orden parcial del grafo: ");
        gd.listarTareas(ordenParcial);

        String[] lineasOrden = new String[ordenParcial.size()];
        for (int i = 0; i < ordenParcial.size(); i++) {
            lineasOrden[i] = (String) ordenParcial.get(i).getEtiqueta();
        }
        ManejadorArchivosGenerico.escribirArchivo("orden.txt", lineasOrden);

         // Otra alternativa de como hacer la parte inicial (sin la emision del archivo)
         // Cargar una linked list para los vértices que representan las tareas
        /*String[] lineasTareas = ManejadorArchivosGenerico.leerArchivoRutaRelativa("tareas.txt", false);
        LinkedList<TVertice> tareas = new LinkedList<>();
        for (String linea : lineasTareas) {
             tareas.add(new TVertice<>(linea)); // Suponiendo que la clase Tarea tiene un constructor que recibe el nombre de la tarea
        }

         // Cargar una lista de aristas (TAristas) que representan las precedencias
        String[] lineasPrecedencias = ManejadorArchivosGenerico.leerArchivoRutaRelativa("precedencias.txt", false);
        LinkedList<TArista> precedencias = new LinkedList<>();
        for (String linea : lineasPrecedencias) {
            String[] partes = linea.split(",");
            Comparable etiquetaOrigen = partes[0];
            Comparable etiquetaDestino = partes[1];
            double costo = Double.parseDouble(partes[2]);
            precedencias.add(new TArista(etiquetaOrigen, etiquetaDestino, costo));
        }

        // Instanciar un grafo dirigido que represente el proyecto
        TGrafoDirigido grafoProyecto = new TGrafoDirigido(tareas, precedencias);

        // Invocar al método “ordenParcial” para todo el proyecto
        LinkedList<TVertice> ordenParcial = grafoProyecto.obtenerOrdenParcial();

        // Invocar al método “listarTareas” para mostrar por consola el resultado obtenido
        System.out.println("Orden parcial del proyecto: ");
        grafoProyecto.listarTareas(ordenParcial);*/

        // Emitir un archivo “orden.txt” que contenga la secuencia de tareas
    }

    @SuppressWarnings("rawtypes")
    public static void UT7_PD8(){
        //----------- EJERCICIO 2 DEL PD8-----------
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("src\\aeropuertos_3.txt", "src\\conexiones_2.txt",
                false, TGrafoDirigido.class);
        ListasDeListaArcos listasDeListaArcos = new ListasDeListaArcos();
        Comparable florianopolis = (Comparable)"Florianopolis";
        TVertice verticeOrigen = gd.buscarVertice(florianopolis);
        gd.clasificarArcos(verticeOrigen, listasDeListaArcos.getArcosArbol(), listasDeListaArcos.getArcosRetroceso(), listasDeListaArcos.getArcosAvance(), listasDeListaArcos.getArcosCruzados());
        listasDeListaArcos.imprimirListasDeArcosConsola();
    }

    public static void UT8_PD1(){
        String cc = "demo/src/main/java/com/example/cc.txt";
        String conexiones = "demo/src/main/java/com/example/conexiones.txt";
        TGrafoNoDirigido gnd = (TGrafoNoDirigido) UtilGrafos.cargarGrafo(cc, conexiones, false, TGrafoNoDirigido.class);

        TGrafoNoDirigido prim = gnd.Prim();
        TAristas aristas2 = prim.getLasAristas();
        System.out.println("Aristas del árbol de expansión mínimo de Prim:");
        String aristasStr2 = aristas2.imprimirEtiquetas();
        System.out.println(aristasStr2);

    }

    public static void UT8_PD2(){
        String cc = "demo/src/main/java/com/example/cc.txt";
        String conexiones = "demo/src/main/java/com/example/conexiones.txt";
        TGrafoNoDirigido gnd = (TGrafoNoDirigido) UtilGrafos.cargarGrafo(cc, conexiones, false, TGrafoNoDirigido.class);

        TGrafoNoDirigido kruskal = gnd.Kruskal();
        TAristas aristas = kruskal.getLasAristas();
        System.out.println("Aristas del árbol de expansión mínimo de Kruskal:");
        String aristasStr = aristas.imprimirEtiquetas();
        System.out.println(aristasStr);
    }

    public static void UT8_PD3(){
        // cargar grafo con casas y distancias
        TGrafoRedElectrica laRed =  (TGrafoRedElectrica) UtilGrafos.cargarGrafo(
                "UT8\\mavenproject1\\src\\main\\java\\com\\mycompany\\mavenproject1\\PD3archs\\barrio.txt",
                "UT8\\mavenproject1\\src\\main\\java\\com\\mycompany\\mavenproject1\\PD3archs\\distancias.txt",
                false, TGrafoRedElectrica.class);
        
        //calcular la mejor red electrica
        TAristas mejorRedElectrica = laRed.mejorRedElectrica();
        /*listar en el archivo "redelectrica.txt" el costo total del cableado
        y las conexiones establecidas, una por linea (origen, destino, costo)
        */
        System.err.println(mejorRedElectrica.imprimirEtiquetas());
        ManejadorArchivosGenerico.escribirArchivo("UT8\\mavenproject1\\src\\main\\java\\com\\mycompany\\mavenproject1\\PD3archs\\redelectrica.txt", mejorRedElectrica.ordenadoPorCosto());
    }

    @SuppressWarnings("rawtypes")
    public static void UT8_PD4(){
        String cc = "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/PD4archs/cc.txt";
        String conexiones = "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/PD4archs/conexiones.txt";
        TGrafoNoDirigido gnd = (TGrafoNoDirigido) UtilGrafos.cargarGrafo(cc, conexiones, false, TGrafoNoDirigido.class);

        TGrafoNoDirigido kruskal = gnd.Kruskal();
        TAristas aristas = kruskal.getLasAristas();
        System.out.println("Aristas del árbol de expansión mínimo de Kruskal:");
        String aristasStr = aristas.imprimirEtiquetas();
        System.out.println(aristasStr);
        TVertice v1 = gnd.buscarVertice("1");
        TVertice v2 = gnd.buscarVertice("2");
        System.out.println("los vertices  1 y 2 estas conectados ? : " + gnd.conectados(v1, v2));
    }

    @SuppressWarnings("rawtypes")
    public static void UT8_TA5(){ 
        String actores = "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/TA5archs/actores.csv";
        String en_pelicula = "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/TA5archs/en_pelicula.csv";
        TGrafoKevinBacon gd = (TGrafoKevinBacon) UtilGrafos.cargarGrafo(actores, en_pelicula, false, TGrafoKevinBacon.class);

        int distancia = gd.numBea( "John_Goodman", "Kevin_Bacon");
        int distancia2 = gd.numBea("Tom_Cruise", "Kevin_Bacon");
        int distancia3 = gd.numBea("Jason_Statham", "Kevin_Bacon");
        int distancia4 = gd.numBea("Lukas_Haas", "Kevin_Bacon");
        int distancia5 = gd.numBea("Dijimon_Hounsou", "Kevin_Bacon");
        System.out.println("Distancia de Uma_Thurman a Kevin_Bacon: " + distancia);
        System.out.println("Distancia de Tom_Cruise a Kevin_Bacon: " + distancia2);
        System.out.println("Distancia de Jason_Statham a Kevin_Bacon: " + distancia3);
        System.out.println("Distancia de Lukas_Haas a Kevin_Bacon: " + distancia4);
        System.out.println("Distancia de Dijimon_Hounsou a Kevin_Bacon: " + distancia5);
        String[] res = new String[5];
        res[0] = "Distancia de Uma_Thurman a Kevin_Bacon: " + distancia;
        res[1] = "Distancia de Tom_Cruise a Kevin_Bacon: " + distancia2;
        res[2] = "Distancia de Jason_Statham a Kevin_Bacon: " + distancia3;
        res[3] = "Distancia de Lukas_Haas a Kevin_Bacon: " + distancia4;
        res[4] = "Distancia de Dijimon_Hounsou a Kevin_Bacon: " + distancia5;
        ManejadorArchivosGenerico.escribirArchivo("UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/TA5archs/salida.txt", res);

        Set<TVertice> actoresConectados = gd.listarContactos("Kevin_Bacon", 6);
        for (TVertice actor : actoresConectados) {
            System.out.println(actor.getEtiqueta());
        }  
    }

    public static void UT8_TA6(){
        TGrafoNoDirigido gnd = (TGrafoNoDirigido) UtilGrafos.cargarGrafo(
            "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/TA6archs/vert2.txt",
            "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/TA6archs/ari2.txt",
            false, TGrafoNoDirigido.class);



        TGrafoNoDirigido grafoPrim = gnd.Prim();
        System.out.println("cantidad de vertices del grafo por prim:"  + grafoPrim.getVertices().size());
        /*
            mostrar las aristas del AAM por Prim y el costo total
        */
        TAristas AAM = grafoPrim.getLasAristas();
        String AAMStr = AAM.imprimirEtiquetas();
        System.out.println(AAMStr);
        
            

        TGrafoNoDirigido grafoKruskal = gnd.Kruskal();
        System.out.println("cantidad de vertices del grafo por kruskal:"  + grafoKruskal.getVertices().size());
        /*
            mostrar las aristas del AAM por Kruskal y el costo total
        */  
        TAristas aristas = grafoKruskal.getLasAristas();
        String aristasStr = aristas.imprimirEtiquetas();
        System.out.println(aristasStr);
    }

    @SuppressWarnings("rawtypes")
    public static void Parcial2018enGrafoKevinBacon(){
        try {
            String actores = "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/Parcial2018archs/actores.txt";
            String en_pelicula = "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/Parcial2018archs/en_pelicula.txt";
            TGrafoKevinBacon gkb = (TGrafoKevinBacon) UtilGrafos.cargarGrafo(actores, en_pelicula, false, TGrafoKevinBacon.class);

            String actorZZ1 = "Tom_Cruise"; // se indicará en el pizarrón
            Collection<TVertice> contactos1 = gkb.listarContactos(actorZZ1, 1);

            String actorZZ2 = "Kevin_Bacon"; // se indicará en el pizarrón
            Collection<TVertice> contactos2 = gkb.listarContactos(actorZZ2, 2);

            String[] salida1 = new String[contactos1.size()];
            int cont = 0;

            System.out.println("Contactos de Tom_Cruise:\n");
            for (TVertice vertice : contactos1) {
                System.out.println(vertice.getEtiqueta());
                salida1[cont++] = vertice.getEtiqueta().toString();
            }
            System.out.println("\n");

            String[] salida2 = new String[contactos2.size()];
            cont = 0;
            
            System.out.println("Contactos de Kevin_Bacon:\n");
            for (TVertice vertice : contactos2) {
                System.out.println(vertice.getEtiqueta());
                salida2[cont++] = vertice.getEtiqueta().toString();
            }

            // emitir un archivo de salida, "salida.txt" con la lista de contactos obtenida
            String archSalida1 = "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/Parcial2018archs/salida1.txt";
            ManejadorArchivosGenerico.escribirArchivo(archSalida1, salida1);
            String archSalida2 = "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/Parcial2018archs/salida2.txt";
            ManejadorArchivosGenerico.escribirArchivo(archSalida2, salida2);

        } catch (Exception e) {
            System.err.println("Error durante la ejecución del programa: " + e.getMessage());
            e.printStackTrace();
        }
    }
   
    @SuppressWarnings("rawtypes")
    public static void Parcial2018enGrafoNoDirigido(){
        try {
            String actores = "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/Parcial2018archs/actores.txt";
            String en_pelicula = "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/Parcial2018archs/en_pelicula.txt";
            TGrafoNoDirigido gkb = UtilGrafos.cargarGrafo(actores, en_pelicula, false, TGrafoNoDirigido.class);

            String actorZZ1 = "Tom_Cruise"; // se indicará en el pizarrón
            Collection<TVertice> contactos1 = gkb.obtenerVerticesConMaxEnlaces(actorZZ1, 1);

            String actorZZ2 = "Kevin_Bacon"; // se indicará en el pizarrón
            Collection<TVertice> contactos2 = gkb.obtenerVerticesConMaxEnlaces(actorZZ2, 2);

            String[] salida1 = new String[contactos1.size()];
            int cont = 0;

            System.out.println("Contactos de Tom_Cruise:\n");
            for (TVertice vertice : contactos1) {
                System.out.println(vertice.getEtiqueta());
                salida1[cont++] = vertice.getEtiqueta().toString();
            }
            System.out.println("\n");

            String[] salida2 = new String[contactos2.size()];
            cont = 0;
            
            System.out.println("Contactos de Kevin_Bacon:\n");
            for (TVertice vertice : contactos2) {
                System.out.println(vertice.getEtiqueta());
                salida2[cont++] = vertice.getEtiqueta().toString();
            }

            // emitir un archivo de salida, "salida.txt" con la lista de contactos obtenida
            String archSalida1 = "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/Parcial2018archs/salida1personalizada.txt";
            ManejadorArchivosGenerico.escribirArchivo(archSalida1, salida1);
            String archSalida2 = "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/Parcial2018archs/salida2personalizada.txt";
            ManejadorArchivosGenerico.escribirArchivo(archSalida2, salida2);

        } catch (Exception e) {
            System.err.println("Error durante la ejecución del programa: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("rawtypes")
    public static void Parcial2019(){
        String aeropuertos = "UT8\\mavenproject1\\src\\main\\java\\com\\mycompany\\mavenproject1\\Parcial2019archs\\aeropuertos.txt";
        String vuelos = "UT8\\mavenproject1\\src\\main\\java\\com\\mycompany\\mavenproject1\\Parcial2019archs\\vuelos.txt";
        // cargar grafo aerolínea con aeropuertos y vuelos
        TGrafoAerolinea gd = (TGrafoAerolinea) UtilGrafos.cargarGrafo(aeropuertos, vuelos, false, TGrafoAerolinea.class);

        
        TGrafoAerolinea aerolinea = gd;
            
        String elOrigen = "B";
        String elDestino = "A";
        
        // obtener el itinerario con menos escalas entre elOrigen y elDestino;
        LinkedList<TVertice> lasEscalas = aerolinea.menosEscalas(elOrigen, elDestino);
        
        /*
        mostrar las escalas y listar en archivo de salida 
       */

        System.out.println("itinerario mejor para vuelo entre "+ elOrigen+ " y "+ elDestino +":\n ");

        String[] escalas = new String[lasEscalas.size()];
        int contador = 0;

        for (TVertice v : lasEscalas) {      
            System.out.println(v.getEtiqueta());
            escalas[contador] = v.getEtiqueta().toString();
            contador++;

        }
        String escalasPath = "UT8\\mavenproject1\\src\\main\\java\\com\\mycompany\\mavenproject1\\Parcial2019archs\\escalas.txt";
        ManejadorArchivosGenerico.escribirArchivo(escalasPath, escalas);
    }

    @SuppressWarnings("rawtypes")
    public static void Parcial2020Personalizado(){
        try{
            String personas = "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/Parcial2020archs/personas.txt";
            String contactos = "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/Parcial2020archs/contactos.txt";
            TGrafoNoDirigido gd = (TGrafoNoDirigido) UtilGrafos.cargarGrafo(personas, contactos, false, TGrafoNoDirigido.class);
            List<Map.Entry<TVertice, Integer>> anillos = gd.obtenerDistanciasDesdeVertice("Kevin_Bacon");
            for (Map.Entry<TVertice, Integer> entry : anillos) {
                System.out.println(entry.getKey().getEtiqueta() + " " + entry.getValue());
            }

            // Crear un TreeMap para agrupar por distancia
                TreeMap<Integer, List<String>> agrupacionPorDistancia = new TreeMap<>();

                for (Map.Entry<TVertice, Integer> entry : anillos) {
                    String nombrePersona = entry.getKey().getEtiqueta().toString();
                    Integer distancia = entry.getValue();

                    agrupacionPorDistancia.putIfAbsent(distancia, new ArrayList<>());
                    agrupacionPorDistancia.get(distancia).add(nombrePersona);
                }

                // Crear la lista de contenido de salida
                List<String> contenidoSalida = new ArrayList<>();

                for (Map.Entry<Integer, List<String>> entry : agrupacionPorDistancia.entrySet()) {
                    Integer distancia = entry.getKey();
                    List<String> personasEnDistancia = entry.getValue();
                    
                    // Ordenar alfabéticamente
                    Collections.sort(personasEnDistancia);

                    // Agregar encabezado de distancia
                    contenidoSalida.add("DISTANCIA de CONTACTO: " + distancia + ":");

                    // Agregar personas
                    for (String persona : personasEnDistancia) {
                        contenidoSalida.add("  " + persona);
                    }
                }

                // Convertir la lista a un arreglo
                String[] contenidoArray = contenidoSalida.toArray(new String[0]);

                // Escribir en el archivo
                String anillosPath = "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/Parcial2020archs/anillos.txt";
                ManejadorArchivosGenerico.escribirArchivo(anillosPath, contenidoArray);

        } catch (Exception e) {
            System.err.println("Error durante la ejecución del programa: " + e.getMessage());
            e.printStackTrace();
        }    
    }

    public static void Parcial2020(){
        try {
            // CREAR EL GRAFO CON PERSONAS.TXT y CONTACTOS.TXT
            String personas = "demo\\src\\main\\java\\com\\example\\UT8archs\\Parcial2020archs\\personas.txt";
            String contactos = "demo\\src\\main\\java\\com\\example\\UT8archs\\Parcial2020archs\\contactos.txt";
            TGrafoContagios grafoTrazabilidad = (TGrafoContagios) UtilGrafos.cargarGrafo(
                    personas,
                    contactos,
                    false, TGrafoContagios.class);
            
            
            String personaCOVID = "Keira_Knightley";
            int maxDistancia = 5;

            // invocar al método "anillosDeProbablesContagiados" con estos parámetros
            TAnillosContagio anillosContagios = grafoTrazabilidad.anillosDeProbablesContagiados(personaCOVID, maxDistancia);

            // emitir por consola la cantidad de contactos que se encuentran a la distancia
            TreeSet<String> anillo = anillosContagios.obtenerAnillo(maxDistancia);

            System.out.println("la cantidad de contactos de" + personaCOVID + "a la distancia de " + maxDistancia + "es " + anillo.size() );
            System.out.println("dichos contactos son : ");
            for (String contacto : anillo){
                System.out.println(contacto);
            }

            // de la personaCOVID indicada EN EL PIZARRÓN: distAnilloBuscar
            //int distAnilloBuscar = SE INDICARA EN EL PIZARRÓN
            // EMITIR EL ARCHIVO "ANILLOS.TXT", CON EL FORMATO INDICADO EN LA LETRA, 
            // CON LOS CONJUNTOS DE CONTACTOS HASTA UNA DISTANCIA MENOR O IGUAL A LA INDICADA
            // EN EL PIZARRÓN: distMaxParaArchivoListado
        } catch (Exception e) {
            System.err.println("Error durante la ejecución del programa: " + e.getMessage());
            e.printStackTrace();
        }
        
    }

    @SuppressWarnings("rawtypes")
    public static void Parcial2021(){
        try {
        // cargar grafo con SERVIDORES y ENLACES
        String servidores = "UT8/demo/src/main/java/com/example/Parcial2021archs/servidores.txt";
        String enlaces = "UT8/demo/src/main/java/com/example/Parcial2021archs/enlaces.txt";
        TGrafoRedDatos redDatos = (TGrafoRedDatos) UtilGrafos.cargarGrafo(servidores, 
                                                                        enlaces, 
                                                                        false,
                                                                        TGrafoRedDatos.class );
       
        // EJECUTAR PARA servidor1 = BUF y servidor2 = DFW
        String servidor1 = "BUF";
        String [] servidores2  = {"DFW" , "LAS", "MIA" }; //BUF BWI PIT DFW
        for (String servidor2 : servidores2){
            
            LinkedList<TVertice> ruta = redDatos.rutaMenosSaltos(servidor1, servidor2);

            // ESCRIBIR RUTA EN rutas.txt SEGUIDO DE 2 LINEAS EN BLANCO
            System.out.println("\nRuta con menor cantidad de enlaces entre "+ servidor1 + " y "+ servidor2 +":");

            String[] rutaSalida = new String[ruta.size() + 1];
            
            int contador = 0;

            for (TVertice v : ruta) { 
                System.out.println(v.getEtiqueta());
                rutaSalida[contador] = v.getEtiqueta().toString();
                contador++;
            }

            rutaSalida[contador] = "\n" ;
            String rutaPath = "UT8/demo/src/main/java/com/example/Parcial2021archs/rutas.txt";
            ManejadorArchivosGenerico.escribirArchivo(rutaPath, rutaSalida);
        }
           
        } catch (Exception e) {
            System.err.println("Error durante la ejecución del programa: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
