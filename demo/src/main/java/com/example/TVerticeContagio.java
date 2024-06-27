package com.example;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("rawtypes")
public class TVerticeContagio extends TVertice implements IVerticeContagio {
    
    public TVerticeContagio(Comparable unaEtiqueta) {
        super(unaEtiqueta);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void obtenerAnillos(TAnillosContagio losAnillos, int maxDistancia) {
        Queue<TVertice> cola = new LinkedList<>();
        Queue<Integer> distancias = new LinkedList<>();

        cola.add(this);
        distancias.add(0);
        setVisitado(true);

        while (!cola.isEmpty()) {
            TVertice actual = cola.poll();
            int distancia = distancias.poll();

            if (distancia > maxDistancia && maxDistancia > 0) {
                continue;
            }

            losAnillos.agregarContagio(distancia, actual.getEtiqueta().toString());

            for (TAdyacencia adyacencia : (Collection<TAdyacencia>)actual.getAdyacentes()) {
                TVertice adyacente = adyacencia.getDestino();
                if (!adyacente.getVisitado()) {
                    cola.add(adyacente);
                    distancias.add(distancia + 1);
                    adyacente.setVisitado(true);
                }
            }
        }
        
    }
}
