package com.example;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Ernesto
 */
@SuppressWarnings("rawtypes")
public interface IVertice {

    TAdyacencia buscarAdyacencia(TVertice verticeDestino);
    TAdyacencia buscarAdyacencia(Comparable etiquetaDestino);
    boolean eliminarAdyacencia(Comparable nomVerticeDestino);
    LinkedList<TAdyacencia> getAdyacentes();
    boolean insertarAdyacencia(Double costo, TVertice verticeDestino);
    Double obtenerCostoAdyacencia(TVertice verticeDestino);
    TVertice primerAdyacente();
    TVertice siguienteAdyacente(TVertice w);
    void bpf(Collection<TVertice> visitados);
    TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos);
    boolean tieneCiclo(LinkedList<Comparable> camino);
    void bea(Collection<TVertice> visitados);
    boolean conectadoCon(TVertice destino);
}
