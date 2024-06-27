package com.example.UT8_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.example.TArista;
import com.example.TGrafoKevinBacon;
import com.example.TVertice;

@SuppressWarnings("rawtypes")
public class ListarContactosTest {
    private TGrafoKevinBacon grafo;
    private TVertice kevinBacon;
    private TVertice tomHanks;
    private TVertice sallyField;
    private TVertice megRyan;
    private TVertice billPaxton;

    @Before
    public void setUp() {
        kevinBacon = new TVertice("Kevin Bacon");
        tomHanks = new TVertice("Tom Hanks");
        sallyField = new TVertice("Sally Field");
        megRyan = new TVertice("Meg Ryan");
        billPaxton = new TVertice("Bill Paxton");

        Set<TVertice> vertices = new HashSet<>(Arrays.asList(kevinBacon, tomHanks, sallyField, megRyan, billPaxton));
        Set<TArista> aristas = new HashSet<>();
        aristas.add(new TArista(kevinBacon.getEtiqueta(), tomHanks.getEtiqueta(), 1));
        aristas.add(new TArista(tomHanks.getEtiqueta(), sallyField.getEtiqueta(), 1));
        aristas.add(new TArista(kevinBacon.getEtiqueta(), billPaxton.getEtiqueta(), 1));
        aristas.add(new TArista(billPaxton.getEtiqueta(), megRyan.getEtiqueta(), 1));

        grafo = new TGrafoKevinBacon(vertices, aristas);
    }

    @Test
    public void testListarContactosMaxSaltos1() {
        Set<TVertice> contactos = grafo.listarContactos(kevinBacon.getEtiqueta().toString(), 1);

        assertEquals(3, contactos.size());

        Set<TVertice> visitados = new HashSet<>(Arrays.asList(kevinBacon, tomHanks, billPaxton));

        for (TVertice vertice : visitados){
            TVertice v = grafo.buscarVertice(vertice.getEtiqueta());
            assertTrue(contactos.contains(v));
            assertTrue(v.getVisitado());
        }
    }

    @Test
    public void testListarContactosMaxSaltos2() {
        Set<TVertice> contactos = grafo.listarContactos("Kevin Bacon", 2);

        assertEquals(5, contactos.size());

        Set<TVertice> visitados = new HashSet<>(Arrays.asList(kevinBacon, tomHanks, billPaxton, sallyField, megRyan));

        for (TVertice vertice : visitados){
            TVertice v = grafo.buscarVertice(vertice.getEtiqueta());
            assertTrue(contactos.contains(v));
            assertTrue(v.getVisitado());
        }
    }

    @Test
    public void testListarContactosInexistente() {
        Set<TVertice> contactos = grafo.listarContactos("Actor Inexistente", 2);
        assertEquals(0, contactos.size());
    }
}
