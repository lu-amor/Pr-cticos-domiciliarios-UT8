package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author javie
 */
public class App {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //PD1();
        //PD2();
        //PD3();
        //PD4(); 
        //TA5(); 
        //TA6(); 
        //Parcial2018();
        //Parcial2019(); // falta testing 
        //Parcial2020(); // implementar como lo sugiere la letra del problema - main - testing
        //Parcial2020Personalizado(); // falta testing
        //Parcial2021(); //falta testing
    }

    public static void PD1(){
        String cc = "demo/src/main/java/com/example/cc.txt";
        String conexiones = "demo/src/main/java/com/example/conexiones.txt";
        TGrafoNoDirigido gnd = (TGrafoNoDirigido) UtilGrafos.cargarGrafo(cc, conexiones, false, TGrafoNoDirigido.class);

        TGrafoNoDirigido prim = gnd.Prim();
        TAristas aristas2 = prim.getLasAristas();
        System.out.println("Aristas del árbol de expansión mínimo de Prim:");
        String aristasStr2 = aristas2.imprimirEtiquetas();
        System.out.println(aristasStr2);

    }

    public static void PD2(){
        String cc = "demo/src/main/java/com/example/cc.txt";
        String conexiones = "demo/src/main/java/com/example/conexiones.txt";
        TGrafoNoDirigido gnd = (TGrafoNoDirigido) UtilGrafos.cargarGrafo(cc, conexiones, false, TGrafoNoDirigido.class);

        TGrafoNoDirigido kruskal = gnd.Kruskal();
        TAristas aristas = kruskal.getLasAristas();
        System.out.println("Aristas del árbol de expansión mínimo de Kruskal:");
        String aristasStr = aristas.imprimirEtiquetas();
        System.out.println(aristasStr);
    }

    public static void PD3(){
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
    public static void PD4(){
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
    public static void TA5(){ 
        String actores = "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/TA5archs/actores.csv";
        String en_pelicula = "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/TA5archs/en_pelicula.csv";
        TGrafoNoDirigido gd = (TGrafoNoDirigido) UtilGrafos.cargarGrafo(actores, en_pelicula, false, TGrafoNoDirigido.class);

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

        Set<TVertice> actoresConectados = gd.obtenerVerticesConMaxEnlaces("Kevin_Bacon", 6);
        for (TVertice actor : actoresConectados) {
            System.out.println(actor.getEtiqueta());
        }  
    }

    public static void TA6(){
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
    public static void Parcial2018(){
        try {
            String actores = "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/Parcial2018archs/actores.txt";
            String en_pelicula = "UT8_PD1 - copia/mavenproject1/src/main/java/com/mycompany/mavenproject1/Parcial2018archs/en_pelicula.txt";
            TGrafoNoDirigido gkb = (TGrafoNoDirigido) UtilGrafos.cargarGrafo(actores, en_pelicula, false, TGrafoNoDirigido.class);

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
        // CREAR EL GRAFO CON PERSONAS.TXT y CONTACTOS.TXT
        
        TGrafoContagios grafoTrazabilidad = (TGrafoContagios) UtilGrafos.cargarGrafo(
                "src/personas.txt",
                "src/contactos.txt",
                false, TGrafoContagios.class);
        
        String personaCOVID = "SE INDICARA EN EL PIZARRON";
        //int maxDistancia = SE INDICARA EN EL PIZARRON
        
        // invocar al método "anillosDeProbablesContagiados" con estos parámetros;

        // emitir por consola la cantidad de contactos que se encuentran a la distancia
        // de la personaCOVID indicada EN EL PIZARRÓN: distAnilloBuscar
        //int distAnilloBuscar = SE INDICARA EN EL PIZARRÓN
         // EMITIR EL ARCHIVO "ANILLOS.TXT", CON EL FORMATO INDICADO EN LA LETRA, 
         // CON LOS CONJUNTOS DE CONTACTOS HASTA UNA DISTANCIA MENOR O IGUAL A LA INDICADA
         // EN EL PIZARRÓN: distMaxParaArchivoListado
        
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
