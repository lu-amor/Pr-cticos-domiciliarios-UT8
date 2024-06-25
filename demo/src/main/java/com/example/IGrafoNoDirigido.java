package com.example;

import java.util.Collection;
import java.util.LinkedList;

@SuppressWarnings("rawtypes")
public interface IGrafoNoDirigido {

    Collection <TVertice> bea();
    Collection <TVertice> bea(Comparable etiquetaOrigen);
    TGrafoNoDirigido Prim();
    TGrafoNoDirigido Kruskal();
    LinkedList<TVertice> puntosArticulacion(Comparable etOrigen);
	boolean esConexo();
    boolean conectados(TVertice origen, TVertice destino);
}
