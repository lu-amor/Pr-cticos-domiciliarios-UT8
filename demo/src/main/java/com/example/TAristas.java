package com.example;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

@SuppressWarnings("rawtypes")
public class TAristas extends LinkedList<TArista> {

    private final static String SEPARADOR_ELEMENTOS_IMPRESOS = "-";
    private final Collection<TArista> aristas  = new LinkedList<TArista>();
    // aristas no estaban puestas como final 
    
    /**
     * Busca dentro de la lista de aristas una arista que conecte a etOrigen con
     * etDestino
     *
     * @param etOrigen
     * @param etDestino
     * @return
     */
    public TArista buscar(Comparable etOrigen, Comparable etDestino) {
        for (TArista arista : this) {
            if ((arista.getEtiquetaOrigen().equals(etOrigen)) && arista.getEtiquetaDestino().equals(etDestino)) {
                return arista;
            }
        }
        return null;
    }

    /**
     * Busca la arista de menor costo que conecte a cualquier nodo de VerticesU
     * con cualquier otro de VerticesV y cuyo costo sea el minimo
     *
     * @param VerticesU - Lista de vertices U
     * @param VerticesV - Lista de vertices V
     * @return
     */
    public TArista buscarMin(Collection<Comparable> VerticesU, Collection<Comparable> VerticesV) {
        TArista tempArista;
        TArista tAMin = null;
        Double costoMin = Double.POSITIVE_INFINITY;
        for (Comparable u : VerticesU) {
            for (Comparable v : VerticesV) {
                tempArista = buscar(u, v);
                if (tempArista != null && tempArista.getCosto() < costoMin) {
                    tAMin = tempArista;
                    costoMin = tempArista.getCosto();
                }
            }
        }
        return tAMin;
    }

    public String imprimirEtiquetas() {
        if (this.isEmpty()) {
            return null;
        }
        StringBuilder salida = new StringBuilder();
        for (TArista arista : this) {
            salida.append(arista.getEtiquetaOrigen())
                .append(" ")
                .append(SEPARADOR_ELEMENTOS_IMPRESOS)
                .append(arista.getEtiquetaDestino())
                .append(" ")
                .append(SEPARADOR_ELEMENTOS_IMPRESOS)
                .append(" ")
                .append(arista.getCosto())
                .append("\n");
        }
        return salida.toString();
    }

    void insertarAmbosSentidos(Collection<TArista> aristas) {
        if (aristas == null) return;
        for (TArista ta : aristas) {
            this.add(ta);
            this.add(ta.aristaInversa());
        }
    }

    /**
     * @return the aristas
     */
    public Collection<TArista> getLasAristas() {
        return aristas;
    }

    public void ordenarPorCosto() {
        Collections.sort(this, new Comparator<TArista>() {
            @Override
            public int compare(TArista arista1, TArista arista2) {
                return Double.compare(arista1.getCosto(), arista2.getCosto());
            }
        });
    }

    public String[] ordenadoPorCosto() {
        TAristas aristasOrdenadas = this;
        aristasOrdenadas.ordenarPorCosto();
        
        // Crear un arreglo de strings con el tamaño de aristasOrdenadas
        String[] aristasArray = new String[aristasOrdenadas.size()];
        
        // Convertir cada arista en string y guardarla en el arreglo
        int index = 0;
        for (TArista arista : aristasOrdenadas) {
            aristasArray[index++] = arista.getEtiquetaOrigen() + " - " + arista.getEtiquetaDestino() + " - " + arista.getCosto();
        }
        return aristasArray;
    }
}
