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
        if (caminos.isEmpty())
        {
            sb.append("No se hallaron caminos\n");
        } else {
            for (TCamino camino : caminos){
                sb.append(camino.imprimirEtiquetas());
                sb.append(" (Costo: ");
                sb.append(camino.getCostoTotal());
                sb.append(")\n");
            }
        }
        
        return sb.toString();
    }

    public void imprimirCaminosConsola(){
        System.out.println(imprimirCaminos());
    }

    public String imprimirCaminosNoCriticos(){
        StringBuilder sb = new StringBuilder();
        if (caminos.isEmpty())
        {
            sb.append("No se hallaron caminos\n");
        } else {
            for (TCamino camino : caminos){
                sb.append(camino.imprimirEtiquetas());
                sb.append(" (Costo: ");
                sb.append(camino.getCostoTotal());
                sb.append(")");
                sb.append(" (Holgura: ");
                sb.append(camino.getHolgura());
                sb.append(")\n");
            }
        }
        
        return sb.toString();
    }

    public void imprimirCaminosNoCriticosConsola(){
        System.out.println(imprimirCaminosNoCriticos());
    }

    public String imprimirComponentesConexos(){
        StringBuilder sb = new StringBuilder();
        if (caminos.isEmpty())
        {
            sb.append("No se hallaron caminos\n");
        } else {
            sb.append("Los componentes conexos de este grafo son :\n");
            for (TCamino camino : caminos){
                sb.append(camino.imprimirEtiquetas()+"\n");
            }
        }
        return sb.toString();
    }

    public void imprimirComponentesConexosConsola(){
        System.out.println(imprimirComponentesConexos());
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
