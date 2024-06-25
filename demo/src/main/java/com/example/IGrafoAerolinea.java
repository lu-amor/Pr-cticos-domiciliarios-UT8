package com.example;

import java.util.LinkedList;

/**
 * @author ernesto
 */

@SuppressWarnings("rawtypes")
public interface IGrafoAerolinea {
  LinkedList <TVertice> menosEscalas (Comparable origen, Comparable destino);
}
