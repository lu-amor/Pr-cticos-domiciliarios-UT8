package com.example.UT8_tests;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.example.TArista;
import com.example.TGrafoNoDirigido;
import com.example.TVertice;

public class ObtenerVerticesConMaxEnlacesTest {

    private TGrafoNoDirigido grafo;

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
        // Crear vértices
        v1 = new TVertice("A");
        v2 = new TVertice("B");
        v3 = new TVertice("C");
        v4 = new TVertice("D");
        v5 = new TVertice("E");

        // Crear aristas
        a1 = new TArista(v1.getEtiqueta(), v2.getEtiqueta(), 0.0d);
        a2 = new TArista(v1.getEtiqueta(), v3.getEtiqueta(), 0.0d);
        a3 = new TArista(v2.getEtiqueta(), v4.getEtiqueta(), 0.0d);
        a4 = new TArista(v3.getEtiqueta(), v4.getEtiqueta(), 0.0d);
        a5 = new TArista(v4.getEtiqueta(), v5.getEtiqueta(), 0.0d);

        // Crear colecciones de vértices y aristas
        Collection<TVertice> vertices = Arrays.asList(v1, v2, v3, v4, v5);
        Collection<TArista> aristas = Arrays.asList(a1, a2, a3, a4, a5);

        // Inicializar el grafo
        grafo = new TGrafoNoDirigido(vertices, aristas);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testObtenerVerticesConMaxEnlaces_Existente() {
        Set<TVertice> resultado = grafo.obtenerVerticesConMaxEnlaces("A", 2);

        Set<Comparable> esperado = new HashSet<>();
        esperado.add(v1.getEtiqueta());
        esperado.add(v3.getEtiqueta());
        esperado.add(v4.getEtiqueta());
        esperado.add(v2.getEtiqueta());

        for (TVertice vertice : resultado){
            assertTrue(esperado.contains(vertice.getEtiqueta()));
        }
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testObtenerVerticesConMaxEnlaces_Inexistente() {
        Set<TVertice> resultado = grafo.obtenerVerticesConMaxEnlaces("Z", 2);

        assertTrue(resultado.isEmpty());
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testObtenerVerticesConMaxEnlaces_ZeroEnlaces() {
        Set<TVertice> resultado = grafo.obtenerVerticesConMaxEnlaces("A", 0);

        TVertice [] esperadoArray = new TVertice[resultado.size()];
        esperadoArray [0] = v1;
        int contador = 0;

        for (TVertice vertice : resultado){
            assertEquals(vertice.getEtiqueta(), esperadoArray[contador].getEtiqueta());
            contador++;
        }
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testObtenerVerticesConMaxEnlaces_TodosLosVertices() {
        Set<TVertice> resultado = grafo.obtenerVerticesConMaxEnlaces("A", 4);

        Set<Comparable> esperado = new HashSet<>();
        esperado.add(v1.getEtiqueta());
        esperado.add(v2.getEtiqueta());
        esperado.add(v3.getEtiqueta());
        esperado.add(v4.getEtiqueta());
        esperado.add(v5.getEtiqueta());

        for (TVertice vertice : resultado){
            assertTrue(esperado.contains(vertice.getEtiqueta()));
        }

    }
}

