package com.example.UT8_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import com.example.TArista;
import com.example.TGrafoNoDirigido;
import com.example.TVertice;

public class ObtenerDistanciasDesdeVerticeTest {
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
    public void testObtenerDistanciasDesdeVertice() {
        // Obtener las distancias desde el vértice v1
        List<Entry<TVertice, Integer>> distancias = grafo.obtenerDistanciasDesdeVertice(v1.getEtiqueta().toString());

        // Verificar que el tamaño de la lista sea correcto
        assertEquals(4, distancias.size());

        // Verificar las distancias esperadas
        for (Entry<TVertice, Integer> entry : distancias) {
            TVertice vertice = entry.getKey();
            Integer distancia = entry.getValue();

            switch (vertice.getEtiqueta().toString()) {
                case "A":
                    assertEquals(0, distancia.intValue());
                    break;
                case "B":
                    assertEquals(1, distancia.intValue());
                    break;
                case "C":
                    assertEquals(1, distancia.intValue());
                    break;
                case "D":
                    assertEquals(2, distancia.intValue());
                    break;
                case "E":
                    assertEquals(3, distancia.intValue());
                    break;
                default:
                    assertTrue("Vértice no esperado en el resultado", false);
            }
        }
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testObtenerDistanciasDesdeVerticeInexistente() {
        // Obtener las distancias desde un vértice inexistente
        List<Entry<TVertice, Integer>> distancias = grafo.obtenerDistanciasDesdeVertice("Z");

        // Verificar que la lista esté vacía
        assertEquals(0, distancias.size());
    }

}
