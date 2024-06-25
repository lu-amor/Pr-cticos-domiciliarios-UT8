package com.example;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ernesto
 */
public interface IArista {

    double getCosto();

    @SuppressWarnings("rawtypes")
    Comparable getEtiquetaDestino();

    @SuppressWarnings("rawtypes")
    Comparable getEtiquetaOrigen();

    void setCosto(double costo);

    @SuppressWarnings("rawtypes")
    void setEtiquetaDestino(Comparable etiquetaDestino);

    @SuppressWarnings("rawtypes")
    void setEtiquetaOrigen(Comparable etiquetaOrigen);
    
    public TArista aristaInversa ();
}
