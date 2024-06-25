package com.example;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author diego
 */
public class TCaminos {
    
    private final Collection<TCamino> caminos; // caminos no estaba puesto como final

    public TCaminos() {
        this.caminos = new LinkedList<>();
    }
    
    public String imprimirCaminos(){
        StringBuilder sb = new StringBuilder();
        for (TCamino camino : caminos){
            sb.append(camino.imprimirEtiquetas())
                .append("\n");
        }
        return sb.toString();
    }

    public void imprimirCaminosConsola(){
        System.out.println(imprimirCaminos());
    }

    public Collection<TCamino> getCaminos() {
        return caminos;
    }
    
    public int costoMin() { // no estaba seteada la visibilidad
        int costoMenor = Integer.MAX_VALUE;
        int tempCosto;
        for (TCamino elcamino:caminos){
            tempCosto =elcamino.getOtrosVertices().size();
            if ( tempCosto < costoMenor) costoMenor = tempCosto;
        }
        return costoMenor;
    }
}
