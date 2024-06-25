package com.example;

import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author Ernesto
 */
public interface IVertice {

    @SuppressWarnings("rawtypes")
    TAdyacencia buscarAdyacencia(TVertice verticeDestino);

    @SuppressWarnings("rawtypes")
    TAdyacencia buscarAdyacencia(Comparable etiquetaDestino);

    @SuppressWarnings("rawtypes")
    boolean eliminarAdyacencia(Comparable nomVerticeDestino);

    LinkedList<TAdyacencia> getAdyacentes();
    
    @SuppressWarnings("rawtypes")
    boolean insertarAdyacencia(Double costo, TVertice verticeDestino);

    @SuppressWarnings("rawtypes")
    Double obtenerCostoAdyacencia(TVertice verticeDestino);

    @SuppressWarnings("rawtypes")
    TVertice primerAdyacente();

    @SuppressWarnings("rawtypes")
    TVertice siguienteAdyacente(TVertice w);

    @SuppressWarnings("rawtypes")
    public void bpf(Collection<TVertice> visitados);

    @SuppressWarnings("rawtypes")
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos);
    
    @SuppressWarnings("rawtypes")
    public boolean tieneCiclo(LinkedList<Comparable> camino);
    
    @SuppressWarnings("rawtypes")
    public void bea(Collection<TVertice> visitados);

    @SuppressWarnings("rawtypes")
    public boolean conectadoCon(TVertice destino);
}
