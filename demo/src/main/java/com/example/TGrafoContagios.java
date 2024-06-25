package com.example;

import java.util.Collection;

/**
 * @author ernesto
 */
public class TGrafoContagios extends TGrafoNoDirigido implements IGrafoContagio{
    
    public TGrafoContagios(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }

    @Override
    public TAnillosContagio anillosDeProbablesContagiados(String personaCOVID, int maxDistancia) {
        throw new UnsupportedOperationException("Not supported yet.");
        // debe realizarse invocando el metodo de TVertice "obtenerAnillos"...
    }
}
