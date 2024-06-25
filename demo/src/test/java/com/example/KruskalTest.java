package com.example;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class KruskalTest {
    @Test
    public void testGrafoConexo() {
        TVertice v1 = new TVertice("1");
        TVertice v2 = new TVertice("2");
        TVertice v3 = new TVertice("3");
        TVertice v4 = new TVertice("4");
        TVertice v5 = new TVertice("5");
        TArista a1 = new TArista("1", "2", 1);
        TArista a2 = new TArista("2", "3", 4);
        TArista a3 = new TArista("2", "4", 5);
        TArista a4 = new TArista("3", "5", 8);
        TArista a5 = new TArista("2", "5", 9);
        ArrayList<TVertice> vertices = new ArrayList<>();
        vertices.add(v1);   
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);
        ArrayList<TArista> aristas = new ArrayList<>();
        aristas.add(a1);
        aristas.add(a2);
        aristas.add(a3);
        aristas.add(a4);
        aristas.add(a5);
        TGrafoNoDirigido grafo = new TGrafoNoDirigido(vertices, aristas);
        TGrafoNoDirigido kruskal = grafo.Kruskal();
        assertEquals(5, kruskal.getVertices().size());
        assertEquals(8, kruskal.getLasAristas().size());
    }

    @Test
    public void testGrafoNoConexo() {
        TVertice v1 = new TVertice("1");
        TVertice v2 = new TVertice("2");
        TVertice v3 = new TVertice("3");
        TVertice v4 = new TVertice("4");
        TVertice v5 = new TVertice("5");
        TArista a2 = new TArista("2", "3", 4);
        TArista a3 = new TArista("2", "4", 5);
        TArista a4 = new TArista("3", "5", 8);
        TArista a5 = new TArista("2", "5", 9);
        ArrayList<TVertice> vertices = new ArrayList<>();
        vertices.add(v1);   
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);
        ArrayList<TArista> aristas = new ArrayList<>();
        aristas.add(a2);
        aristas.add(a3);
        aristas.add(a4);
        aristas.add(a5);
        TGrafoNoDirigido grafo = new TGrafoNoDirigido(vertices, aristas);
        TGrafoNoDirigido kruskal = grafo.Kruskal();
        assertEquals(5, kruskal.getVertices().size());
        assertEquals(6, kruskal.getLasAristas().size()); // 6 porque es una de ida y una de vuelta por cada arista
    }

    @Test
    public void testGrafoSinAristas() {
        TVertice v1 = new TVertice("1");
        TVertice v2 = new TVertice("2");
        TVertice v3 = new TVertice("3");
        TVertice v4 = new TVertice("4");
        TVertice v5 = new TVertice("5");
        ArrayList<TVertice> vertices = new ArrayList<>();
        vertices.add(v1);   
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);
        ArrayList<TArista> aristas = new ArrayList<>();
        TGrafoNoDirigido grafo = new TGrafoNoDirigido(vertices, aristas);
        TGrafoNoDirigido kruskal = grafo.Kruskal();
        assertEquals(5, kruskal.getVertices().size());
        assertEquals(0, kruskal.getLasAristas().size());
    }
}
