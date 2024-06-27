package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({ "rawtypes", "unchecked", "unused"})
public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido {
    
    protected TAristas lasAristas = new TAristas();
    //protected TVertices vertices;

    /**
     * @param vertices
     * @param aristas
     */
    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);     
        lasAristas.insertarAmbosSentidos(aristas);
    }

    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
        return tempbool;
    }

    public TAristas getLasAristas() {
        return lasAristas;
    }

    @Override
    public TGrafoNoDirigido Prim() {
        Set<Comparable> U = new HashSet<>();
        Set<Comparable> V = new HashSet<>(getVertices().keySet());
        TAristas aristasAAM = new TAristas();
        double costoPrim = 0;

        Comparable primerVertice = V.iterator().next();
        V.remove(primerVertice);
        U.add(primerVertice);

        while (!V.isEmpty()) {
            TArista tempArista = getLasAristas().buscarMin(U, V);
            if (tempArista != null) {
                aristasAAM.add(tempArista);
                V.remove(tempArista.getEtiquetaDestino());
                U.add(tempArista.getEtiquetaDestino());
                costoPrim += tempArista.getCosto();
            }
        }

        Collection<TVertice> nuevosVertices = new ArrayList<>();
        for (Comparable etiqueta : U) {
            nuevosVertices.add(getVertices().get(etiqueta));
        }
        return new TGrafoNoDirigido(nuevosVertices, aristasAAM);
    }

    @Override
    public TGrafoNoDirigido Kruskal() {
        TAristas aristasAAM = new TAristas();
        TAristas copiaAristas = new TAristas();

        for (TArista arista : lasAristas) {
            copiaAristas.add(new TArista(arista.etiquetaOrigen, arista.etiquetaDestino, arista.costo));
        }

        Collections.sort(copiaAristas, Comparator.comparing(TArista::getCosto));

        Map<Comparable, Set<Comparable>> componentes = new HashMap<>();
        // hay una elemento por cada vértice que contiene el conjunto al cual pertenece
        for (TVertice vertice : getVertices().values()) {
            Set<Comparable> componente = new HashSet<>();
            componente.add(vertice.getEtiqueta());
            componentes.put(vertice.getEtiqueta(), componente);
        }

        for (TArista arista : copiaAristas) {
            Comparable origen = arista.getEtiquetaOrigen();
            Comparable destino = arista.getEtiquetaDestino();

            Set<Comparable> componenteOrigen = componentes.get(origen);
            Set<Comparable> componenteDestino = componentes.get(destino);

            if (!componenteOrigen.equals(componenteDestino)) {
                aristasAAM.add(arista);
                componenteOrigen.addAll(componenteDestino);
                for (Comparable vertice : componenteDestino) {
                    componentes.put(vertice, componenteOrigen);
                }
            }
            if(aristasAAM.size() == getVertices().size() - 1) break; 
        }
        return new TGrafoNoDirigido(getVertices().values(), aristasAAM);
    }

    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        this.desvisitarVertices();
        Collection<TVertice> visitados = new ArrayList<>();
        TVertice verticeOrigen = buscarVertice(etiquetaOrigen);
        if (verticeOrigen != null) {
            verticeOrigen.bea(visitados);
        }
        return visitados;
    }

    /* metodo ya implementado en TGrafoDirigido, TGrafoNoDirigido lo hereda de ahi
    @SuppressWarnings("rawtypes")
    @Override
    public Collection<TVertice> bea() {
        throw new UnsupportedOperationException("Not supported yet.");
    }*/

    @Override
    public boolean conectados(TVertice v, TVertice w) {
        desvisitarVertices();
        TVertice origen = buscarVertice(v.getEtiqueta());
        TVertice destino = buscarVertice(w.getEtiqueta());
        if (origen != null && destino != null){
            return origen.conectadoCon(destino);
        }
        return false;
    }

    @Override
    public LinkedList<TVertice> puntosArticulacion(Comparable etOrigen) {
        this.desvisitarVertices();
        LinkedList<TVertice> puntos = new LinkedList<>();
        TVertice verticeOrigen = this.buscarVertice(etOrigen);
        if (verticeOrigen != null) {
            int[] cont = {0};
            verticeOrigen.puntosArticulacion(puntos, cont);
        }
        return puntos;
    }

    public Collection<TVertice> bpfNoCompleto() {
        desvisitarVertices();
        Collection<TVertice> visitados = new ArrayList<>();
        TVertice vertice = getVertices().values().iterator().next();
        vertice.bpf(visitados);
        return visitados;
    }

    public int numBea(Comparable etiquetaInicio, Comparable etiquetaObjetivo) {
        desvisitarVertices();
        TVertice verticeInicio = buscarVertice(etiquetaInicio); 
        if (verticeInicio != null) {
            return verticeInicio.numBea(etiquetaObjetivo);
        }
        return -1;
    }

    public Set<TVertice> obtenerVerticesConMaxEnlaces(String etiquetaInicio, int maxEnlaces) { // listarContactos parcial 2018 (Kevin Bacon)
        desvisitarVertices();
        if (existeVertice(etiquetaInicio)) {
            return new HashSet<>();
        }
        TVertice verticeInicio = buscarVertice(etiquetaInicio); 
        Set<TVertice> visitados = new HashSet<>();
        visitados.add(verticeInicio);
        return verticeInicio.buscarMaxEnlacesDesdeVertice(maxEnlaces, visitados);
    }

    public List<Map.Entry<TVertice, Integer>> obtenerDistanciasDesdeVertice(String etiquetaVertice) { // listarContactos 2020 (Caso  COVID)
        TVertice vertice = buscarVertice(etiquetaVertice);
        if (vertice != null) {
            Map<TVertice, Integer> distancias = vertice.obtenerDistancias();
            List<Map.Entry<TVertice, Integer>> listaDistancias = new ArrayList<>(distancias.entrySet());

            Collections.sort(listaDistancias, (Map.Entry<TVertice, Integer> o1, Map.Entry<TVertice, Integer> o2) -> o1.getValue().compareTo(o2.getValue()));
            
            return listaDistancias;
        }
        return new ArrayList<>();
    }

    @Override
    public TCamino caminoCritico() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'caminoCritico'");
    }

    @Override
    public TCaminos obtenerCaminosNoCriticos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerCaminosNoCriticos'");
    }

    @Override
    public void clasificarArcos(TVertice verticeOrigen, ListaArcos arcosArbol, ListaArcos arcosRetroceso,
            ListaArcos arcosAvance, ListaArcos arcosCruzados) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clasificarArcos'");
        
    }

    @Override
    public boolean esFuertementeConexo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'esFuertementeConexo'");
    }

    @Override
    public TCaminos obtenerComponentesFuertementeConectados() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerComponentesFuertementeConectados'");
    }

    @Override
    public boolean esConexoAcordeACantidadComponentes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'esConexoAcordeACantidadComponentes'");
    }

    @Override
    public LinkedList<TVertice> obtenerOrdenParcial() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerOrdenParcial'");
    }

    @Override
    public LinkedList<Comparable> ordenParcial() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ordenParcial'");
    }

    @Override
    public List<TVertice> sortTopologico() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sortTopologico'");
    }
}
