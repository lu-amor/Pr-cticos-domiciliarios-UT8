package com.example;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class NumBeaTest {

    private TGrafoNoDirigido grafo;

    private TVertice v1;
    private TVertice v2;
    private TVertice v3;
    private TVertice v4;
    private TVertice v5;
    
    private TArista a1;
    private TArista a2;
    private TArista a3;

    @SuppressWarnings("rawtypes")
    @Before 
    public void setUp(){
        // crear vertices 
        v1 = new TVertice("1");
        v2 = new TVertice("2");
        v3 = new TVertice("3");
        v4 = new TVertice("4");
        v5 = new TVertice("5");

        // crear aristas
        a1 = new TArista(v1.getEtiqueta(), v5.getEtiqueta(), 1);
        a2 = new TArista(v2.getEtiqueta(), v3.getEtiqueta(), 2);
        a3 = new TArista(v3.getEtiqueta(), v4.getEtiqueta(), 6);

        // Crear colecciones de vértices y aristas
        Collection<TVertice> vertices = Arrays.asList(v1, v2, v3, v4, v5);
        Collection<TArista> aristas = Arrays.asList(a1, a2, a3);

        // Inicializar el grafo
        grafo = new TGrafoNoDirigido(vertices, aristas);
    }

    @Test
    public void numBeaDirectConnectionTest(){
        int numBea = grafo.numBea(v1.getEtiqueta(),v5.getEtiqueta());
        assertEquals(1, numBea);

    }

    @Test
    public void numBeaNoConnectionTest(){
        int numBea = grafo.numBea(v3.getEtiqueta(),v5.getEtiqueta());
        assertEquals(-1, numBea);

    }

    @Test
    public void numBeaIndirectConnectionTest(){
        int numBea = grafo.numBea(v2.getEtiqueta(),v4.getEtiqueta());
        assertEquals(2, numBea);

    }
}
