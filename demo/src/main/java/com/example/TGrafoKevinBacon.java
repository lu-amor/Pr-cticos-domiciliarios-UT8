package com.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("rawtypes")
public class TGrafoKevinBacon extends TGrafoNoDirigido implements IGrafoKevinBacon {

    public TGrafoKevinBacon(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }

    @Override
    public TVertice buscarVertice(Comparable etiqueta) {
        return super.buscarVertice(etiqueta);
    }


    /*
     * @param nombreActor representa el vertice al partir del cual se hayan los enlaces
     * @param maxSaltos simboliza los enlaces
     */
    @SuppressWarnings("unchecked")
    @Override
    public Set<TVertice> listarContactos (String nombreActor, int maxSaltos) { // listarContactos parcial 2018 (Kevin Bacon)
        desvisitarVertices();

        if (!existeVertice(nombreActor)) {
            return new HashSet<>();
        }
        
        TVertice actor = buscarVertice(nombreActor); 
        Set<TVertice> visitados = new HashSet<>();
        visitados.add(actor);
        actor.listarContactos(visitados, maxSaltos);
        return visitados;
    }
}
