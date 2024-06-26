package com.example;

import java.util.Collection;

/**
 * @author ernesto
 */
@SuppressWarnings("rawtypes")
public class TGrafoContagios extends TGrafoNoDirigido implements IGrafoContagio{
    
    public TGrafoContagios(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }

    @Override
    public TAnillosContagio anillosDeProbablesContagiados(String personaCOVID, int maxDistancia) {
        desvisitarVertices();
        TVertice persona =  buscarVertice(personaCOVID);
        if (persona == null) {
            return null;
        }
        TAnillosContagio anillos = new TAnillosContagio();
        persona.obtenerAnillos(anillos, maxDistancia);
        return anillos;
    }
}
