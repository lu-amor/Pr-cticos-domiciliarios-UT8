package com.example;

/**
 * @author ernesto
 */
public interface IGrafoContagio {

    public TAnillosContagio anillosDeProbablesContagiados(String personaCOVID, int maxDistancia); 
}