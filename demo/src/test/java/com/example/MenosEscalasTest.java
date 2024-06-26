package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class MenosEscalasTest {
    private TGrafoAerolinea grafo;
    @SuppressWarnings("rawtypes")
    private LinkedList<TVertice> rutaConMenosEscalas;

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
    private TArista a8;

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
        a8 = new TArista(v4.getEtiqueta(), v1.getEtiqueta(), 5);

        // Crear colecciones de vértices y aristas
        Collection<TVertice> vertices = Arrays.asList(v1, v2, v3, v4, v5);
        Collection<TArista> aristas = Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8);

        
        grafo = new TGrafoAerolinea(vertices, aristas);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void unaConexionDirectaTest(){
        rutaConMenosEscalas = grafo.menosEscalas(v1.getEtiqueta(), v5.getEtiqueta());

        TVertice [] esperado = new TVertice[rutaConMenosEscalas.size()];
        esperado[0] = v1;
        esperado[1] = v5;

        int index = 0;

        assertEquals(esperado.length,rutaConMenosEscalas.size());

        for (TVertice vertice : rutaConMenosEscalas){
            assertEquals(esperado[index].getEtiqueta(), vertice.getEtiqueta());
            index++;
        }
        
    }

    @Test
    public void noConexionTest(){
        rutaConMenosEscalas = grafo.menosEscalas(v1.getEtiqueta(), v2.getEtiqueta());
        assertTrue(rutaConMenosEscalas.isEmpty());
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void unaConexionIndirectaTest(){
       rutaConMenosEscalas = grafo.menosEscalas(v2.getEtiqueta(), v1.getEtiqueta());
        
        TVertice [] esperado = new TVertice[rutaConMenosEscalas.size()];
        esperado[0] = v2;
        esperado[1] = v4;
        esperado[2] = v1;

        int index = 0;

        for (TVertice vertice : rutaConMenosEscalas){
            assertEquals(esperado[index].getEtiqueta(), vertice.getEtiqueta());
            index++;
        } 
        assertEquals(esperado.length,rutaConMenosEscalas.size());
    }

}
