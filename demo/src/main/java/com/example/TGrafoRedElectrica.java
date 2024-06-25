package com.example;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ocamp
 */
@SuppressWarnings({"rawtypes", "unused"})
public class TGrafoRedElectrica extends TGrafoNoDirigido implements IGrafoRedElectrica{
    
    public TGrafoRedElectrica(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }

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
