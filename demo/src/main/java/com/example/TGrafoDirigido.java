package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class TGrafoDirigido  implements IGrafoDirigido {

    @SuppressWarnings("rawtypes")
    private final Map<Comparable, TVertice> vertices; //lista de vertices del grafo.-
    private final TAristas aristas;
    

    @SuppressWarnings("rawtypes")
    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta()); // metodo que no fue dado como privado
        }
        
        this.aristas = new TAristas();
        for (TArista arista : aristas) {
            insertarArista(arista); 
        }
    }

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean invalidas, retorna falso.
     *
     * @param nomVerticeOrigen
     * @param nomVerticeDestino
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de eliminar un vertice en el grafo. En caso de no
     * existir el v�rtice, retorna falso. En caso de que la etiqueta sea
     * inv�lida, retorna false.
     *
     * @param nombreVertice
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        if (nombreVertice != null) {
            getVertices().remove(nombreVertice);
            return getVertices().containsKey(nombreVertice);
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por par�metro deben ser v�lidas.
     *
     * @param etiquetaOrigen
     * @param etiquetaDestino
     * @return True si existe la adyacencia, false en caso contrario
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     *
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de verificar buscar un vertice dentro del grafo.-
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    @SuppressWarnings("rawtypes")
    protected TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Matodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea valida,
     * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
     * parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @param arista
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            tempbool = (vertOrigen != null) && (vertDestino != null);
            if (tempbool) {
                aristas.getLasAristas().add(arista);
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }

        }
        return false;
    }

    /**
     * Metodo encargado de insertar un vertice en el grafo.
     *
     * No pueden ingresarse v�rtices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del v�rtice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    @SuppressWarnings("rawtypes")
    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean insertarVertice(TVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }


    @SuppressWarnings("rawtypes")
    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void desvisitarVertices() {
        for (TVertice vertice : vertices.values()) {
            vertice.setVisitado(false);
            /*vertice.setNumeroBP(-1);
            vertice.setNumeroBEA(-1);
            vertice.setTiempoDeFinalizacion(-1);
            vertice.setNumeroBajo(-1);
            vertice.setTiempoDeFinalizacion(-1);*/
        }
    }

    /**
     * @return the vertices
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }



    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Collection<TVertice> bpf(TVertice vertice) {
        desvisitarVertices();
        Collection<TVertice> visitados = new ArrayList<>();
        if (!vertice.getVisitado()) {
            vertice.bpf(visitados);
        }
        return visitados;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Collection<TVertice> bpf() {
        desvisitarVertices();
        Collection<TVertice> visitados = new ArrayList<>();
        for (TVertice vertice : vertices.values()) {
        if (!vertice.getVisitado()) {
            vertice.bpf(visitados);
        }
    }
    return visitados;
    }

    @SuppressWarnings({ "rawtypes"})
    @Override
    public Collection<TVertice> bpf(Comparable etiquetaOrigen) {
        desvisitarVertices();
        Collection<TVertice> visitados = new ArrayList<>();
        TVertice verticeOrigen = buscarVertice(etiquetaOrigen);
        if (verticeOrigen != null) {
            /*if (!verticeOrigen.getVisitado()){
                verticeOrigen.bpf(visitados);
            }*/
            return bpf(verticeOrigen);
        }
        return visitados; // Devolver una lista vacía si no se encuentra el vértice
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Comparable centroDelGrafo() {
        // Inicializar la excentricidad mínima con el valor más alto posible
        Comparable minExcentricidad = Double.POSITIVE_INFINITY;

        // Inicializar la variable para el centro del grafo
        Comparable centro = null;

        // Iterar sobre todos los vértices en el grafo
        for (TVertice vertice : vertices.values()) {
            // Obtener la excentricidad del vértice actual usando su etiqueta
            Comparable excentricidad = obtenerExcentricidad(vertice.getEtiqueta());

            // Comparar la excentricidad del vértice actual con la excentricidad mínima encontrada hasta ahora
            if (excentricidad.compareTo(minExcentricidad) < 0) {
                // Actualizar la excentricidad mínima
                minExcentricidad = excentricidad;
                // Actualizar el vértice que es el centro del grafo
                centro = vertice.getEtiqueta();
            }
        }
    // Retornar el vértice que es el centro del grafo
    return centro;
    }

    @Override
    public Double[][] floyd() {
        Double [][] matrizDistancias = UtilGrafos.obtenerMatrizCostos(getVertices());
        int cantidadVertices = vertices.size();
        Integer [][] predecesores = new Integer[cantidadVertices][cantidadVertices];

    // Algoritmo de Floyd
    for (int k = 0; k < cantidadVertices; k++) {
        for (int i = 0; i < cantidadVertices; i++) {
            /*if (A[i][k] == Double.POSITIVE_INFINITY) {
                continue; // Cortocircuito si no hay conexión directa i -> k
            }*/
            for (int j = 0; j < cantidadVertices; j++) {
                if (matrizDistancias[i][k] != null && matrizDistancias[k][j] != null && matrizDistancias[i][j] != null && matrizDistancias[i][k] + matrizDistancias[k][j] < matrizDistancias[i][j]) {
                    matrizDistancias[i][j] = matrizDistancias[i][k] + matrizDistancias[k][j]; // Actualizar distancia
                    predecesores[i][j] = k; // Actualizar predecesor
                }
            }
        }
    }

    return matrizDistancias;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        // Ejecutar el algoritmo de Floyd-Warshall para obtener la matriz de distancias
        Double[][] distancias = floyd();
    
        // Buscar el vértice con la etiqueta dada
        TVertice verticeBuscado = buscarVertice(etiquetaVertice);
        if (verticeBuscado == null) {
            return null; // El vértice no se encontró en el grafo
        }
    
        // Obtener el índice del vértice en la matriz de distancias
        int index = -1;
        int i = 0;
        for (TVertice vertice : vertices.values()) {
            if (vertice.equals(verticeBuscado)) {
                index = i;
                break;
            }
            i++;
        }
    
        // Inicializar la excentricidad al valor mínimo posible
        Double excentricidad = Double.NEGATIVE_INFINITY;
        // Iterar sobre todas las distancias desde el vértice encontrado a otros vértices
        for (int j = 0; j < distancias.length; j++) {
            // Actualizar la excentricidad si se encuentra una mayor distancia
            if (distancias[index][j] != null && distancias[index][j] > excentricidad) {
                excentricidad = distancias[index][j];
            }
        }
    
        // Retornar la excentricidad calculada como Comparable
        return excentricidad;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean[][] warshall() {
        int n = vertices.size(); // Se obtiene el número de vértices en el grafo (`n`).
        boolean[][] alcance = new boolean[n][n]; // Se crea una matriz de booleanos `alcance` de tamaño `n x n`, inicializada con `false`.
        Map<Comparable, Integer> indices = new HashMap<>(); // Se crea un mapa `indices` para almacenar la asociación entre las etiquetas de los vértices y sus índices correspondientes en la matriz.

        int i = 0; // Inicializa un índice `i` en `0`.
        for (Comparable etiqueta : vertices.keySet()) { // Se itera sobre las etiquetas de los vértices en el grafo.
            indices.put(etiqueta, i++); // Asocia cada etiqueta de vértice con un índice único en el mapa `indices` e incrementa `i`.
        }

        for (i = 0; i < n; i++) { // Se itera sobre los índices `i` desde `0` hasta `n-1`.
            for (int j = 0; j < n; j++) { // Se itera sobre los índices `j` desde `0` hasta `n-1`.
                alcance[i][j] = false; // Inicializa todas las posiciones de la matriz `alcance` con `false`.
            }
        }

        for (TVertice vertice : vertices.values()) { // Se itera sobre todos los vértices del grafo.
            int origenIndex = indices.get(vertice.getEtiqueta()); // Obtiene el índice del vértice origen del mapa `indices`.
            TVertice primerAdyacente = vertice.primerAdyacente(); // Obtiene el primer adyacente del vértice actual.
            while (primerAdyacente != null) { // Mientras haya un adyacente.
                int destinoIndex = indices.get(primerAdyacente.getEtiqueta()); // Obtiene el índice del vértice destino del mapa `indices`.
                alcance[origenIndex][destinoIndex] = true; // Establece `true` en la posición correspondiente de la matriz `alcance`, indicando que hay un alcance directo del vértice origen al destino.
                primerAdyacente = vertice.siguienteAdyacente(primerAdyacente); // Pasa al siguiente adyacente.
            }
        }

        for (int k = 0; k < n; k++) { // Se itera sobre todos los posibles vértices intermediarios `k`.
            for (int u = 0; u < n; u++) { // Se itera sobre todos los posibles vértices origen `u`.
                for (int v = 0; v < n; v++) { // Se itera sobre todos los posibles vértices destino `v`.
                    alcance[u][v] = alcance[u][v] || (alcance[u][k] && alcance[k][v]); // Aplica la relación de transitividad de Warshall: si existe un camino de `u` a `k` y un camino de `k` a `v`, entonces también debe existir un camino de `u` a `v`. Se utiliza una operación OR lógico para actualizar la matriz `alcance`.
                }
            }
        }

        return alcance; // Devuelve la matriz `alcance` que ahora indica si existe un camino entre cualquier par de vértices del grafo.
    }

    @SuppressWarnings("rawtypes")
    @Override
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        desvisitarVertices();
        TCaminos todosLosCaminos = new TCaminos(); 
        TVertice v = buscarVertice(etiquetaOrigen); 
        if(v != null){ 
            TCamino caminoPrevio = new TCamino(v); 
            v.todosLosCaminos(etiquetaDestino, caminoPrevio, todosLosCaminos); 
            return todosLosCaminos; 
        } 
        return null;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public boolean tieneCiclo(Comparable etiquetaOrigen) {
        desvisitarVertices();
        TVertice verticeOrigen = buscarVertice(etiquetaOrigen);
        if (verticeOrigen != null && !verticeOrigen.getVisitado()) {
            return verticeOrigen.tieneCiclo(new LinkedList<Comparable>());
        }
        return false;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public boolean tieneCiclo() {
        desvisitarVertices();
        for (TVertice vertice : vertices.values()) {
            if (!vertice.getVisitado()) {
                if (vertice.tieneCiclo(new LinkedList<Comparable>())) {
                    return true;
                }
            }
        }
        return false;

    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean tieneCiclo(TCamino camino) {
        desvisitarVertices();
        for (TVertice vertice : vertices.values()) {
            if (!vertice.getVisitado()) {
                if (vertice.tieneCiclo(camino)) {
                    System.out.println("El grafo tiene ciclo. Vértices involucrados: " + camino.imprimirEtiquetas());
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Collection<TVertice> bea() {
        this.desvisitarVertices();
        Collection<TVertice> visitados = new ArrayList<>();
        for (TVertice vertice : vertices.values()) {
            if (!vertice.getVisitado()) {
                vertice.bea(visitados);
            }
        }
        return visitados;
    }
}
