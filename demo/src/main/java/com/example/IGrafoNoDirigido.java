package com.example;


import java.util.Collection;
import java.util.LinkedList;

public interface IGrafoNoDirigido {

    @SuppressWarnings("rawtypes")
    public Collection <TVertice> bea();
    @SuppressWarnings("rawtypes")
    public Collection <TVertice> bea(Comparable etiquetaOrigen);
    public TGrafoNoDirigido Prim();

    public TGrafoNoDirigido Kruskal();
    
    @SuppressWarnings("rawtypes")
    public LinkedList<TVertice> puntosArticulacion(Comparable etOrigen);
    
	boolean esConexo();
    
    @SuppressWarnings("rawtypes")
    public boolean conectados(TVertice origen, TVertice destino);
}
