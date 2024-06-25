package com.example;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class PrimTest {
    private TGrafoNoDirigido grafo;
    private TGrafoNoDirigido prim;

    private TGrafoNoDirigido grafo2;
    private TGrafoNoDirigido prim2;

    private TVertice v1;
    private TVertice v2;
    private TVertice v3;
    private TVertice v4;
    private TVertice v5;

    private TArista a1;
    private TArista a2;
    private TArista a3;
    private TArista a4;
    private TArista a5;
    

    @SuppressWarnings("rawtypes")
    @Before
    public void setUp() {
        // crear vertices
        v1 = new TVertice("1");
        v2 = new TVertice("2");
        v3 = new TVertice("3");
        v4 = new TVertice("4");
        v5 = new TVertice("5");

        // crear aristas
        a1 = new TArista(v1.getEtiqueta(), v2.getEtiqueta(), 1);
        a2 = new TArista(v2.getEtiqueta(), v3.getEtiqueta(), 4);
        a3 = new TArista(v2.getEtiqueta(), v4.getEtiqueta(), 5);
        a4 = new TArista(v3.getEtiqueta(), v5.getEtiqueta(), 8);
        a5 = new TArista(v2.getEtiqueta(), v5.getEtiqueta(), 9);

        // Crear colecciones de vértices y aristas
        Collection<TVertice> vertices = Arrays.asList(v1, v2, v3, v4, v5);
        Collection<TArista> aristas = Arrays.asList(a1, a2, a3, a4, a5);

        // Crear colecciones de aristas
        Collection<TArista> aristas2 = Arrays.asList(a2, a3, a4);


        // Inicializar el grafo
        grafo = new TGrafoNoDirigido(vertices, aristas);
        prim = grafo.Prim();

        // Inicializar el grafo
        grafo2 = new TGrafoNoDirigido(vertices, aristas2);
        prim2 = grafo2.Prim();
        

    }

    @Test
    public void testGrafoConexo() {
        assertEquals(5, prim.getVertices().size());
        assertEquals(10, prim.getLasAristas().size());
    }

    @Test
    public void testGrafoNoConexo() {
        assertEquals(5, prim2.getVertices().size());
        assertEquals(6, prim2.getLasAristas().size()); // 6 porque es una de ida y una de vuelta por cada arista
    }
}
