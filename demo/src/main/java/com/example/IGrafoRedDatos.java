package com.example;

import java.util.LinkedList;

/**
 * @author ernesto
 */
@SuppressWarnings("rawtypes")
public interface IGrafoRedDatos {
  LinkedList <TVertice> rutaMenosSaltos (Comparable origen, Comparable destino);    
}
