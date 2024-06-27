package com.example.UT7_tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.example.TArista;
import com.example.TGrafoDirigido;
import com.example.TVertice;

public class BeaTest {
    Collection<TVertice> vertices;
    Collection<TArista> aristas;
    TGrafoDirigido g;
    Collection<TVertice> bea;

    @Before
    public void setUp(){
        // crear aristas
        TVertice v1 = new TVertice("1");
        TVertice v2 = new TVertice("2");
        TVertice v3 = new TVertice("3");
        TVertice v4 = new TVertice("4");
        TVertice v5 = new TVertice("5");
        TVertice v6 = new TVertice("6");

        //crear aristas
        TArista a1 = new TArista(v1.getEtiqueta(), v2.getEtiqueta(), 1);
        TArista a2 = new TArista(v2.getEtiqueta(), v3.getEtiqueta(), 1);
        TArista a3 = new TArista(v2.getEtiqueta(), v5.getEtiqueta(), 1);
        TArista a4 = new TArista(v3.getEtiqueta(), v2.getEtiqueta(), 1);
        TArista a5 = new TArista(v3.getEtiqueta(), v4.getEtiqueta(), 1);
        TArista a6 = new TArista(v4.getEtiqueta(), v2.getEtiqueta(), 1);

        // Crear colecciones de vértices y aristas
        vertices = Arrays.asList(v1, v2, v3, v4, v5, v6);
        aristas = Arrays.asList(a1, a2, a3, a4, a5, a6);


        g = new TGrafoDirigido(vertices, aristas);
        bea = g.bea();
    }

    @Test
    public void testBea() {
        assertTrue(bea.size() == 6);

        for (TVertice vertice : vertices){
            TVertice v = g.getVertices().get(vertice.getEtiqueta());
            assertTrue(bea.contains(v));
            assertTrue(v.getVisitado());
        }
    }
}
