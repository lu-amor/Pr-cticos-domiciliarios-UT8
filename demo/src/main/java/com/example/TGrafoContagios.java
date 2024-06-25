package com.example;

import java.util.Collection;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ernesto
 */
public class TGrafoContagios extends TGrafoNoDirigido implements IGrafoContagio{
    
    public TGrafoContagios(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }

    @Override
    public TAnillosContagio anillosDeProbablesContagiados(String personaCOVID, int maxDistancia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // debe realizarse invocando el metodo de TVertice "obtenerAnillos"...
    }
}
