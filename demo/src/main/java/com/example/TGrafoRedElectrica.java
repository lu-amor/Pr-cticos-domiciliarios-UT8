package com.example;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ocamp
 */
public class TGrafoRedElectrica extends TGrafoNoDirigido implements IGrafoRedElectrica{
    
    @SuppressWarnings("rawtypes")
    public TGrafoRedElectrica(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public TAristas mejorRedElectrica() {
        Set<Comparable> U = new HashSet<>();
        Set<Comparable> V = new HashSet<>(getVertices().keySet());
        TAristas aristasAAM = new TAristas();
        double costoPrim = 0;

        Comparable primerVertice = V.iterator().next();
        V.remove(primerVertice);
        U.add(primerVertice);

        while (!V.isEmpty()) {
            TArista tempArista = getLasAristas().buscarMin(U, V);
            if (tempArista != null) {
                aristasAAM.add(tempArista);
                V.remove(tempArista.getEtiquetaDestino());
                U.add(tempArista.getEtiquetaDestino());
                costoPrim += tempArista.getCosto();
            }
        }

        return aristasAAM;
        //return Prim().getLasAristas();
    }

}
