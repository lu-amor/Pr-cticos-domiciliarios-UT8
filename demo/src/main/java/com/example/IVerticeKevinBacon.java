package com.example;

import java.util.Collection;
import java.util.Set;

/**
 * @author ernesto
 */
public interface IVerticeKevinBacon {

    int getBacon();
    void setBacon(int numBacon);
    @SuppressWarnings("rawtypes")
    void listarContactos (Collection<TVertice> visitados, int maxSaltos);

}