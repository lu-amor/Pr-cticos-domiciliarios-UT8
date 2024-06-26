package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TGrafoDirigido  implements IGrafoDirigido {

    private final Map<Comparable, TVertice> vertices; //lista de vertices del grafo.-
    private final TAristas aristas;

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
    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override
    public boolean insertarVertice(TVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

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
    @Override
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

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

    @Override
    public Collection<TVertice> bpf(TVertice vertice) {
        desvisitarVertices();
        Collection<TVertice> visitados = new ArrayList<>();
        if (!vertice.getVisitado()) {
            vertice.bpf(visitados);
        }
        return visitados;
    }

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

    public Collection<TVertice> bpfCompletoDesde(Comparable etiquetaOrigen) {   
        // Nuevo método para completar la visita de los vértices que aún no han sido visitados
        Collection<TVertice> visitados = bpf(etiquetaOrigen);
        for (TVertice vertice : vertices.values()) {
            if (!vertice.getVisitado()) {
                vertice.bpf(visitados);
            }
        }
        return visitados;
    }

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

    /*@SuppressWarnings("rawtypes")
    public TCaminos todosLosCaminosCasoEspecial(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        desvisitarVertices();
        TCaminos todosLosCaminos = new TCaminos(); 
        TVertice v = buscarVertice(etiquetaOrigen); 
        if(v != null){ 
            TCamino caminoPrevio = new TCamino(v); 
            v.todosLosCaminosCasoEspecial(etiquetaDestino, caminoPrevio, todosLosCaminos, 1.0d); // Contador inicial en 1
            return todosLosCaminos; 
        } 
        return null;
    }*/

    public boolean hayCamino(Comparable origen, Comparable destino) {
        Object[] etiquetasArray = getVertices().keySet().toArray();
        Integer origenIdx = null, destinoIdx = null;

        for (int i = 0; i < etiquetasArray.length; i++) {
            if (etiquetasArray[i].equals(origen)) {
                origenIdx = i;
            }
            if (etiquetasArray[i].equals(destino)) {
                destinoIdx = i;
            }
            if (origenIdx != null && destinoIdx != null) {
                break;
            }
        }

        if (origenIdx == null || destinoIdx == null) {
            return false;
        }

        boolean[][] matrizWarshall = warshall();
        return matrizWarshall[origenIdx][destinoIdx];
    }

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
    
    @Override
    public boolean tieneCiclo(Comparable etiquetaOrigen) {
        desvisitarVertices();
        TVertice verticeOrigen = buscarVertice(etiquetaOrigen);
        if (verticeOrigen != null && !verticeOrigen.getVisitado()) {
            return verticeOrigen.tieneCiclo(new LinkedList<Comparable>());
        }
        return false;
    }

    public boolean esConexo(){
        int cantidadDeComponentesConexos = obtenerComponentesFuertementeConectados().getCaminos().size();
        if (cantidadDeComponentesConexos == 0 || cantidadDeComponentesConexos == 1){
            return true;
        }
        return false;
    }
    
    public boolean esFuertementeConexo() {
        if (vertices.isEmpty()) {
            return false;
        }

        // Realizar DFS 
        Collection<TVertice> visitados = bpf();

        // Verificar si todos los vértices fueron visitados
        if (visitados.size() != vertices.size()) {
            return false;
        }

        // Crear el grafo transpuesto
        TGrafoDirigido grafoTranspuesto = transponerGrafo();

        // Realizar DFS en el grafo transpuesto 
        Collection<TVertice> visitadosTranspuesto = grafoTranspuesto.bpf();

        // Verificar si todos los vértices fueron visitados en el grafo transpuesto
        return visitadosTranspuesto.size() == vertices.size();
    }

    private TGrafoDirigido transponerGrafo() {
        
        Collection<TArista> aristas = new ArrayList<TArista>();

        for (TVertice vertice : vertices.values()) {
            for (TAdyacencia adyacente : (Collection<TAdyacencia>) vertice.getAdyacentes()) {
                TArista arista = new TArista(adyacente.getDestino().getEtiqueta(), vertice.getEtiqueta(), adyacente.getCosto());
                aristas.add(arista);
            }
        }

        TGrafoDirigido grafoTranspuesto = new TGrafoDirigido(vertices.values(),aristas);
        
        return grafoTranspuesto;
    }

    public TCaminos obtenerComponentesFuertementeConectados() {
        Stack<TVertice> pila = new Stack<>();
        TCaminos componentes = new TCaminos();
        desvisitarVertices();

        // Paso 1: Realizar DFS en el grafo original y registrar el orden de finalización en una pila
        for (TVertice vertice : vertices.values()) {
            if (!vertice.getVisitado()) {
                bpf(vertice, pila);
            }
        }

        // Paso 2: Transponer el grafo
        TGrafoDirigido grafoTranspuesto = transponerGrafo();

        // Paso 3: Realizar DFS en el grafo transpuesto
        grafoTranspuesto.desvisitarVertices();

        while (!pila.isEmpty()) {
            TVertice vertice = grafoTranspuesto.buscarVertice(pila.pop().getEtiqueta());

            if (!vertice.getVisitado()) {
                TCamino componenteActual = new TCamino(vertice);
                grafoTranspuesto.bpfTranspuesto(vertice, componenteActual);
                componentes.getCaminos().add(componenteActual);
            }
        }
        return componentes;
    }

    private void bpf(TVertice vertice, Stack<TVertice> pila) {
        vertice.setVisitado(true);
        for (TAdyacencia adyacente :(Collection<TAdyacencia>) vertice.getAdyacentes()) {
            TVertice destino = adyacente.getDestino();
            if (!destino.getVisitado()) {
                bpf(destino, pila);
            }
        }
        pila.push(vertice);
    }

    private void bpfTranspuesto(TVertice vertice, TCamino  componenteActual) {
        vertice.setVisitado(true);

        // Agregar el vértice actual al camino
        //componenteActual.getOtrosVertices().add(vertice.getEtiqueta()); 
        componenteActual.setOtrosVertices(vertice.getEtiqueta());

        for (TAdyacencia adyacente : (Collection<TAdyacencia>) vertice.getAdyacentes()) {
            TVertice destino = adyacente.getDestino();
            if (!destino.getVisitado()) {
                bpfTranspuesto(destino, componenteActual);
            }
        }
    }

    public List<TVertice> sortTopologico() {
        desvisitarVertices();
        Stack<TVertice> stack = new Stack<>();
        for (TVertice vertice : vertices.values()) {
            if (!vertice.getVisitado()) {
                vertice.sortTopologico(stack);
            }
        }
        List<TVertice> ordenTopologico = new ArrayList<>();
        while (!stack.isEmpty()) {
            ordenTopologico.add(stack.pop());
        }
        return ordenTopologico;
    }

    public LinkedList<TVertice> obtenerOrdenParcial() {
        
        desvisitarVertices();
        LinkedList<TVertice> orden = new LinkedList();
        
        if (!tieneCiclo()){
            return orden;
        }

        TGrafoDirigido grafoTranspuesto = transponerGrafo();
        Map<Comparable,TVertice> verticesGrafoTranspuesto = grafoTranspuesto.getVertices();

        for (TVertice vertice : verticesGrafoTranspuesto.values()) {
            if (!vertice.getVisitado()) {
                vertice.obtenerOrdenParcial(orden);
            }
        }
        //Collections.reverse(orden);
        return orden;
    }

    public TCamino caminoCritico() {
        TVertice origen = null;
        Set<TVertice> verticesConEntradas = new HashSet<>();
        for (TVertice vertice : vertices.values()) {
            for (TAdyacencia adyacente : (Collection<TAdyacencia>) vertice.getAdyacentes()) {
                verticesConEntradas.add(adyacente.getDestino());
            }
        }
        for (TVertice vertice : vertices.values()) {
            if (!verticesConEntradas.contains(vertice)) {
                origen = vertice;
                break;
            }
        }
        if (origen == null) {
            return null;
        }
    
        // Identificar el destino final (sin adyacencias)
        TVertice destino = null;
        for (TVertice vertice : vertices.values()) {
            if (vertice.getAdyacentes().isEmpty()) {
                destino = vertice;
                break;
            }
        }
        if (destino == null) {
            return null;
        }
    
        // Obtener todos los caminos desde el origen al destino
        TCaminos todosLosCaminos = origen.todosLosCaminos(destino.getEtiqueta(), new TCamino(origen), new TCaminos());
    
        // Encontrar el camino con el mayor costo (camino crítico)
        TCamino caminoCritico = null;
        double maxCosto = Double.NEGATIVE_INFINITY;
        for (TCamino camino : todosLosCaminos.getCaminos()) {
            if (camino.getCostoTotal() > maxCosto) {
                maxCosto = camino.getCostoTotal();
                caminoCritico = camino;
                caminoCritico.setCostoTotal(maxCosto);
            }
        }
        return caminoCritico;
    }
    
    /*public TCamino caminoCriticoCasoEspecial() {
        TVertice origen = null;
        Set<TVertice> verticesConEntradas = new HashSet<>();
        for (TVertice vertice : vertices.values()) {
            for (TAdyacencia adyacente : (Collection<TAdyacencia>) vertice.getAdyacentes()) {
                verticesConEntradas.add(adyacente.getDestino());
            }
        }
        for (TVertice vertice : vertices.values()) {
            if (!verticesConEntradas.contains(vertice)) {
                origen = vertice;
                break;
            }
        }
        if (origen == null) {
            return null;
        }
    
        // Identificar el destino final (sin adyacencias)
        TVertice destino = null;
        for (TVertice vertice : vertices.values()) {
            if (vertice.getAdyacentes().isEmpty()) {
                destino = vertice;
                break;
            }
        }
        if (destino == null) {
            return null;
        }
    
        // Obtener todos los caminos desde el origen al destino usando el nuevo método
        TCaminos todosLosCaminos = todosLosCaminosCasoEspecial(origen.getEtiqueta(), destino.getEtiqueta());
    
        // Encontrar el camino con el mayor costo (camino crítico)
        TCamino caminoCritico = null;
        double maxCosto = Double.NEGATIVE_INFINITY;
        for (TCamino camino : todosLosCaminos.getCaminos()) {
            if (camino.getCostoTotal() > maxCosto) {
                maxCosto = camino.getCostoTotal();
                caminoCritico = camino;
            }
        }
    
        if (caminoCritico != null) {
            caminoCritico.setCostoTotal(maxCosto);
        }
    
        return caminoCritico;
    }*/
    

    public TCaminos obtenerCaminosNoCriticos() {
        TCamino caminoCritico = this.caminoCritico();
        if (caminoCritico == null) {
            return new TCaminos();
        }
        double costoCaminoCritico = caminoCritico.getCostoTotal();
        TCaminos caminosNoCriticos = new TCaminos();

        TCaminos todosLosCaminos = this.todosLosCaminos(caminoCritico.getOrigen().getEtiqueta(), caminoCritico.getDestino());
        for (TCamino camino : todosLosCaminos.getCaminos()) {
            if (!camino.equals(caminoCritico)) {
                double holgura = costoCaminoCritico - camino.getCostoTotal();
                camino.setHolgura(holgura);
                caminosNoCriticos.getCaminos().add(camino);
            }
        }
        return caminosNoCriticos;
    }

    /*public TCaminos obtenerCaminosNoCriticosCasoEspecial() {
        TCamino caminoCritico = this.caminoCriticoCasoEspecial();
        if (caminoCritico == null) {
            return new TCaminos();
        }
        double costoCaminoCritico = caminoCriticoCasoEspecial().getCostoTotal();
        TCaminos caminosNoCriticos = new TCaminos();
    
        TCaminos todosLosCaminos = this.todosLosCaminosCasoEspecial(caminoCritico.getOrigen().getEtiqueta(), caminoCritico.getDestino());
        for (TCamino camino : todosLosCaminos.getCaminos()) {
            if (!camino.equals(caminoCritico)) {
                double holgura = costoCaminoCritico - camino.getCostoTotal();
                camino.setHolgura(holgura);
                caminosNoCriticos.getCaminos().add(camino);
            }
        }
        return caminosNoCriticos;
    }*/

    public void clasificarArcos(TVertice verticeOrigen, ListaArcos arcosArbol, ListaArcos arcosRetroceso, ListaArcos arcosAvance, ListaArcos arcosCruzados) {
        desvisitarVertices();
        int[] tiempo = {0}; // Array para mantener el tiempo de forma mutable
        if (verticeOrigen != null) {
            verticeOrigen.clasificarArcosGrafoDirigido(arcosArbol, arcosRetroceso, arcosAvance, arcosCruzados, tiempo);
        }
    }

    public void listarTareas(LinkedList<TVertice> orden) {
        if (!orden.isEmpty()){
            for (TVertice vertice : orden) {
                System.out.println(vertice.getEtiqueta());
            }
        } else {
            System.out.println("se ha ingresado una lista vacía");
        }
    }
}

