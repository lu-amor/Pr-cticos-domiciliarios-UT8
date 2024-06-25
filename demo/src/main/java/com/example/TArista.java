package com.example;

public class TArista implements IArista {

    @SuppressWarnings("rawtypes")
    protected Comparable etiquetaOrigen;
    @SuppressWarnings("rawtypes")
    protected Comparable etiquetaDestino;
    protected double costo;

    @SuppressWarnings("rawtypes")
    public TArista(Comparable etiquetaOrigen, Comparable etiquetaDestino, double costo) {
        this.etiquetaOrigen = etiquetaOrigen;
        this.etiquetaDestino = etiquetaDestino;
        this.costo = costo;
    }

    
    
    @SuppressWarnings("rawtypes")
    @Override
    public Comparable getEtiquetaOrigen() {
        return etiquetaOrigen;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void setEtiquetaOrigen(Comparable etiquetaOrigen) {
        this.etiquetaOrigen = etiquetaOrigen;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Comparable getEtiquetaDestino() {
        return etiquetaDestino;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void setEtiquetaDestino(Comparable etiquetaDestino) {
        this.etiquetaDestino = etiquetaDestino;
    }

    @Override
    public double getCosto() {
        return costo;
    }

    @Override
    public void setCosto(double costo) {
        this.costo = costo;
    }
    
    @Override
    public TArista aristaInversa (){
        return new TArista(this.getEtiquetaDestino(), this.getEtiquetaOrigen(),this.getCosto());
    }

    
}
