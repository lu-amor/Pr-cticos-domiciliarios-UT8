package com.example;

/**
 * @author Ernesto
 */
@SuppressWarnings("rawtypes")
public interface IArista {

    double getCosto();
    Comparable getEtiquetaDestino();
    Comparable getEtiquetaOrigen();
    void setCosto(double costo);
    void setEtiquetaDestino(Comparable etiquetaDestino);
    void setEtiquetaOrigen(Comparable etiquetaOrigen);
    TArista aristaInversa ();
}
