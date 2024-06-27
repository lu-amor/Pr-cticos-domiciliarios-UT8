package com.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ernesto
 */
@SuppressWarnings("rawtypes")
public class TGrafoContagios extends TGrafoNoDirigido implements IGrafoContagio{

    //private final Map<String, TVerticeContagio> contagios;
    
    public TGrafoContagios(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
        /*this.contagios = new HashMap<>();
        for (TVertice vertice : vertices){
            contagios.put(vertice.getEtiqueta().toString(), (TVerticeContagio)vertice);
        }*/
    }

    /*public Map<String, TVerticeContagio> getContagios(){
        return contagios;
    }
    
    public TVerticeContagio buscarContagio(String etiqueta){
        return getContagios().get(etiqueta);
    }*/
    
    @Override
    public TAnillosContagio anillosDeProbablesContagiados(String personaCOVID, int maxDistancia) {
        desvisitarVertices();

        //TVerticeContagio persona = buscarContagio(personaCOVID);
        TVerticeContagio persona = (TVerticeContagio) buscarVertice(personaCOVID);

        if (!existeVertice(personaCOVID)) {
            return null;
        }
        
        TAnillosContagio anillos = new TAnillosContagio();
        persona.obtenerAnillos(anillos, maxDistancia);
        return anillos;
    }
}
