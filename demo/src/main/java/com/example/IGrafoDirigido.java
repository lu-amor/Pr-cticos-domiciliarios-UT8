package com.example;


import java.util.Collection;
import java.util.Map;

public interface IGrafoDirigido {

    @SuppressWarnings("rawtypes")
    Collection<TVertice> bpf();
    @SuppressWarnings("rawtypes")
    Collection <TVertice> bea();
    @SuppressWarnings("rawtypes")
    Collection<TVertice> bpf(TVertice vertice);

    @SuppressWarnings("rawtypes")
    Collection<TVertice> bpf(Comparable etiquetaOrigen);

    /*Collection<Comparable> bpf();

    Collection<Comparable> bpf(TVertice vertice);

    Collection<Comparable> bpf(Comparable etiquetaOrigen);*/

    /**
     * @return Etiqueta del centro del grafo
     */
    @SuppressWarnings("rawtypes")
    Comparable centroDelGrafo();

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la arista, retorna falso. En caso de que las etiquetas
     * sean invalidas (no existe el vertice origen o el destino), retorna falso.
     */
    @SuppressWarnings("rawtypes")
    boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino);

    /**
     * Metodo encargado de eliminar un vertice en el grafo. En caso de no
     * existir el vertice, retorna falso. En caso de que la etiqueta sea
     * invalida, retorna false.
     *
     */
    @SuppressWarnings("rawtypes")
    boolean eliminarVertice(Comparable nombreVertice);

    /**
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por parametro deben ser validas (o sea, los vértices origen y
     * destino deben existir en el grafo).
     *
     * @return True si existe la arista, false en caso contrario
     */
    @SuppressWarnings("rawtypes")
    boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino);

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    @SuppressWarnings("rawtypes")
    boolean existeVertice(Comparable unaEtiqueta);

    /**
     * ejecuta el algoritmo de Floyd en el grafo, para hallar los caminos
     * mínimos entre todos los pares de vértices.
     *
     * @return una matriz de n x n (n = cantidad de vértices del grafo) con los
     * costos de los caminos mínimos.
     */
    Double[][] floyd();

    /**
     * Metododouble encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea valida,
     * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
     * parametros son validas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @param arista
     * @param etiquetaOrigen
     * @return True si se pudo insertar la arista, false en caso contrario
     */
    boolean insertarArista(TArista arista);

    /**
     * Metodo encargado de insertar un vertice en el grafo.
     *
     * No pueden ingresarse vertices con la misma etiqueta. La etiqueta
     * especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del vertice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    @SuppressWarnings("rawtypes")
    boolean insertarVertice(TVertice vertice);

    @SuppressWarnings("rawtypes")
    Comparable obtenerExcentricidad(Comparable etiquetaVertice);

    /**
     * ejecuta el algoritmo de Warshall para halla la cerradura transitiva del
     * grafo.
     *
     * @return una matriz de n x n (n = cantidad de vértices del grafo) en la
     * que sus celdas indican si hay (TRUE) o no (FALSE) conectividad entre cada
     * par de vértices.
     */
    boolean[][] warshall();

    @SuppressWarnings("rawtypes")
    public Map<Comparable, TVertice> getVertices();


    public void desvisitarVertices();

    @SuppressWarnings("rawtypes")
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino);

    public boolean tieneCiclo(TCamino camino);

    @SuppressWarnings("rawtypes")
    public boolean tieneCiclo(Comparable etiquetaOrigen);

    public boolean tieneCiclo();

}
