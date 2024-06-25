package com.example;

import java.util.Collection;

/**
 * @author ernesto
 */
@SuppressWarnings("rawtypes")
public interface IAristas {

    /**
     * Busca dentro de la lista de aristas una arista que conecte a etOrigen con
     * etDestino
     *
     * @param etOrigen
     * @param etDestino
     * @return
     */

    TArista buscar(Comparable etOrigen, Comparable etDestino);
    boolean buscarAmbosSentidos(Comparable etOrigen, Comparable etDestino);

    /**
     * Busca la arista de menor costo que conecte a cualquier nodo de VerticesU
     * con cualquier otro de VerticesV y cuyo costo sea el minimo
     *
     * @param VerticesU - Lista de vertices U
     * @param VerticesV - Lista de vertices V
     * @return
     */
    TArista buscarMin(Collection<Comparable> VerticesU, Collection<Comparable> VerticesV);
    String imprimirEtiquetas();
    void ordenar();
}

