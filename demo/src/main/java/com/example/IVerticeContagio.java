package com.example;

/**
 * @author Ernesto
 */
@SuppressWarnings("rawtypes")
public interface IVerticeContagio {

    TAdyacencia buscarAdyacencia(TVertice verticeDestino);
    TAdyacencia buscarAdyacencia(Comparable etiquetaDestino);
    boolean eliminarAdyacencia(Comparable nomVerticeDestino);
    boolean insertarAdyacencia(Double costo, TVertice verticeDestino);
    Double obtenerCostoAdyacencia(TVertice verticeDestino);
    TVertice primerAdyacente();
    TVertice siguienteAdyacente(TVertice w);
    void obtenerAnillos(TAnillosContagio losAnillos , int maxDistancia);
}
