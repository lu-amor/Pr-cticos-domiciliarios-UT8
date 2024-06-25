package com.example;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ernesto
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TGrafoAerolinea extends TGrafoDirigido implements IGrafoAerolinea {

    public TGrafoAerolinea(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }

    @Override
    public LinkedList<TVertice> menosEscalas(Comparable origen, Comparable destino) {
        desvisitarVertices();
        /*if (tieneCiclo()) {
            throw new IllegalStateException("El grafo contiene ciclos.");
        }*/
        Queue<LinkedList<TVertice>> cola = new LinkedList<>();
        TVertice verticeOrigen = buscarVertice(origen);
        TVertice verticeDestino = buscarVertice(destino);
        if (verticeOrigen == null || verticeDestino == null) {
            return new LinkedList<>(); // Retorna una lista vacía si alguno de los vértices no existe
        }
        LinkedList<TVertice> caminoInicial = new LinkedList<>();
        caminoInicial.add(verticeOrigen);
        cola.add(caminoInicial);
        verticeOrigen.setVisitado(true);

        while (!cola.isEmpty()) {
            LinkedList<TVertice> caminoActual = cola.poll();
            TVertice ultimoVertice = caminoActual.getLast();
            if (ultimoVertice.equals(verticeDestino)) {
                return caminoActual;
            }
            for (TAdyacencia adyacente : (Collection<TAdyacencia>) ultimoVertice.getAdyacentes()) {
                if (!adyacente.getDestino().getVisitado()) {
                    adyacente.getDestino().setVisitado(true);
                    LinkedList<TVertice> nuevoCamino = new LinkedList<>(caminoActual);
                    nuevoCamino.add(adyacente.getDestino());
                    cola.add(nuevoCamino);
                }
            }
        }
        return new LinkedList<>(); // Retorna una lista vacía si no hay camino
    }
}
