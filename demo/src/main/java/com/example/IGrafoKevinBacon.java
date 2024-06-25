package com.example;

import java.util.Collection;

/**
 * @author ernesto
 */
@SuppressWarnings("rawtypes")
public interface IGrafoKevinBacon {
    //Collection<TVertice> listarContactos (String nombreActor, int maxSaltos);
    Collection <TVertice> obtenerVerticesConMaxEnlaces(String verticeInicio, int maxEnlaces);
}
