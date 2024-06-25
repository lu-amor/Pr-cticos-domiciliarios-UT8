package com.example;

import java.util.Set;

/**
 * @author ernesto
 */
public interface IVerticeKevinBacon {

    int getBacon();
    void setBacon(int numBacon);
    //void listarContactos (Collection<TVertice> visitados, int maxSaltos);
    Set<TVertice> buscarMaxEnlacesDesdeVertice(int maxEnlaces, Set<TVertice> visitados);
}

