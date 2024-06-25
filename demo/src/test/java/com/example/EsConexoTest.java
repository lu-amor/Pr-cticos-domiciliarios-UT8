package com.example;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class EsConexoTest {

    private TGrafoNoDirigido grafoConexo;
    private TGrafoNoDirigido grafoNoConexo;

    private TVertice v1;
    private TVertice v2;
    private TVertice v3;
    private TVertice v4;
    private TVertice v5;
    
    private TArista a1;
    private TArista a2;
    private TArista a3;
    private TArista a4;

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
        a4 = new TArista(v4.getEtiqueta(), v5.getEtiqueta(), 3);

        // Crear colecciones de vértices y aristas
        Collection<TVertice> vertices = Arrays.asList(v1, v2, v3, v4, v5);
        Collection<TArista> aristasConexo = Arrays.asList(a1, a2, a3, a4);
        Collection<TArista> aristasNoConexo = Arrays.asList(a1, a2, a3);

        // Inicializar el grafo
        grafoConexo = new TGrafoNoDirigido(vertices, aristasConexo);
        grafoNoConexo = new TGrafoNoDirigido(vertices, aristasNoConexo);
    }
    
    @Test
    public void testGrafoConexo() {
        assertTrue(grafoConexo.esConexo());
        assertFalse(!grafoConexo.esConexo());
    }

    @Test 
    public void testGrafoNoConexo() {
        assertTrue(grafoNoConexo.esConexo());
        assertFalse(!grafoNoConexo.esConexo());
    }
}
