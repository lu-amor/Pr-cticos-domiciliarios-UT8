package com.example.UT7_tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.example.TArista;
import com.example.TCamino;
import com.example.TGrafoDirigido;
import com.example.TVertice;

public class CaminoCriticoTest {
    @Test
    public void testCaminoCritico() {
        TVertice v1 = new TVertice("1");
        TVertice v2 = new TVertice("2");
        TVertice v3 = new TVertice("3");
        TVertice v4 = new TVertice("4");
        TVertice v5 = new TVertice("5");
        TVertice v6 = new TVertice("6");
        TArista a1 = new TArista(v1.getEtiqueta(), v2.getEtiqueta(), 1);
        TArista a2 = new TArista(v2.getEtiqueta(), v3.getEtiqueta(), 1);
        TArista a3 = new TArista(v2.getEtiqueta(), v5.getEtiqueta(), 1);
        TArista a5 = new TArista(v3.getEtiqueta(), v4.getEtiqueta(), 1);
        Collection<TVertice> vertices = new ArrayList<>();
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);
        vertices.add(v6);
        Collection<TArista> aristas = new ArrayList<>();
        aristas.add(a1);
        aristas.add(a2);
        aristas.add(a3);
        aristas.add(a5);

        TGrafoDirigido g = new TGrafoDirigido(vertices, aristas);
        TCamino caminoCritico = g.caminoCritico();
        assertTrue(caminoCritico.getOrigen().getEtiqueta().equals("1"));
        assertTrue(caminoCritico.getOtrosVertices().contains("2"));
        assertTrue(caminoCritico.getOtrosVertices().contains("3"));
        assertTrue(caminoCritico.getOtrosVertices().contains("4"));
        assertTrue(caminoCritico.getDestino() == "4");
    }
}
