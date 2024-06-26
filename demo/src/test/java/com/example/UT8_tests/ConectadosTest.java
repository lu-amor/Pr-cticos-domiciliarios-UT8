package com.example.UT8_tests;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.example.TArista;
import com.example.TGrafoNoDirigido;
import com.example.TVertice;

public class ConectadosTest {

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
    public void testEstanConectados() {
        assertTrue(grafo.conectados(v1, v5));
        assertTrue(grafo.conectados(v2, v3));
        assertTrue(grafo.conectados(v3, v4));
        assertTrue(grafo.conectados(v2, v4)); // conexion indirecta
    
    }

    @Test 
    public void testNoEstanConectados(){
        assertFalse(grafo.conectados(v1, v4));
    }
}
