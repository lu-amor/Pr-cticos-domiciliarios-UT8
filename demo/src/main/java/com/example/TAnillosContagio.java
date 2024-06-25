package com.example;

import java.util.HashMap;
import java.util.TreeSet;

public class TAnillosContagio extends HashMap<Integer, TreeSet<String>> {

    public void agregarContagio(int distancia, String nombrePersona){
        TreeSet<String> elAnillo = this.get(distancia);
        if (elAnillo == null){
            elAnillo = new TreeSet<>();
            this.put(distancia, elAnillo);
        }
        elAnillo.add(nombrePersona);
    }
    
    public TreeSet<String> obtenerAnillo(int distancia){
        return this.get(distancia);
    }
}