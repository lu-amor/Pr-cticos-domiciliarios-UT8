package com.example;

import java.util.LinkedList;
import java.util.List;

public class ListaArcos {
    private List<TArista> arcos;

    public ListaArcos() {
        this.arcos = new LinkedList<TArista>();
    }

    public void add(TArista arista) {
        arcos.add(arista);
    }

    public List<TArista> getArcos() {
        return arcos;
    }

    public String imprimirArcos() {
        StringBuilder sb = new StringBuilder();

        if (getArcos().isEmpty()){
            sb.append("No hay arcos en la lista.");
        } else {
            for (TArista arcos : getArcos()){
                sb.append(arcos.getEtiquetaOrigen());
                sb.append(" -> ");
                sb.append(arcos.getEtiquetaDestino());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public void imprimirArcosConsola() {
        System.out.println(imprimirArcos());
    }

    public boolean isEmpty() {
        return arcos.isEmpty();
    }
}
