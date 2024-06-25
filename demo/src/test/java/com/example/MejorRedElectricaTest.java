package com.example;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class MejorRedElectricaTest {
    private TGrafoRedElectrica grafo;
    private TAristas mejorRedElectrica;

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
    private TArista a6;
    private TArista a7;

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
        a1 = new TArista(v1.getEtiqueta(), v3.getEtiqueta(), 1);
        a2 = new TArista(v1.getEtiqueta(), v4.getEtiqueta(), 2);
        a3 = new TArista(v1.getEtiqueta(), v5.getEtiqueta(), 6);
        a4 = new TArista(v2.getEtiqueta(), v5.getEtiqueta(), 3);
        a5 = new TArista(v2.getEtiqueta(), v4.getEtiqueta(), 1);
        a6 = new TArista(v2.getEtiqueta(), v3.getEtiqueta(), 8);
        a7 = new TArista(v3.getEtiqueta(), v5.getEtiqueta(), 4);

        // Crear colecciones de vértices y aristas
        Collection<TVertice> vertices = Arrays.asList(v1, v2, v3, v4, v5);
        Collection<TArista> aristas = Arrays.asList(a1, a2, a3, a4, a5, a6, a7);

        
        grafo = new TGrafoRedElectrica(vertices, aristas);
        mejorRedElectrica = grafo.mejorRedElectrica();
    }


    @Test
    public void testMejorRedElectricaTamano(){
        assertEquals(4, mejorRedElectrica.size());
    }

    @Test
    public void testMejorRedElectricaContiene(){
        assertTrue(mejorRedElectrica.contains(a1));
        assertTrue(mejorRedElectrica.contains(a2));
        assertTrue(mejorRedElectrica.contains(a4));
    }

    @Test
    public void testMejorRedElectricaNoContiene(){
        assertFalse(mejorRedElectrica.contains(a3));
        assertFalse(mejorRedElectrica.contains(a5));
        assertFalse(mejorRedElectrica.contains(a6));
        assertFalse(mejorRedElectrica.contains(a7));
    }
}
