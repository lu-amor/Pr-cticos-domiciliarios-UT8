package com.example;


public class TAdyacencia implements IAdyacencia {

    @SuppressWarnings("rawtypes")
    private Comparable etiqueta;
    private double costo;
    @SuppressWarnings("rawtypes")
    private TVertice destino;
    
    @SuppressWarnings("rawtypes")
    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public double getCosto() {
        return costo;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public TVertice getDestino() {
        return destino;
    }

    @SuppressWarnings("rawtypes")
    public TAdyacencia(double costo, TVertice destino) {
        this.etiqueta = destino.getEtiqueta();
        this.costo = costo;
        this.destino = destino;
    }
}
