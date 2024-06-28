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
        TGrafoNoDirigido arbolPrim = new TGrafoNoDirigido(this.getVertices().values(), new LinkedList<>());
        double costoTotal = 0.0d;

        if (this.getVertices().isEmpty()) {
            return arbolPrim;
        }

        TAristas aristas = this.getLasAristas();
        LinkedList<Comparable> vertices = new LinkedList<>();
        for (TVertice vertice : this.getVertices().values()) {
            vertices.add(vertice.getEtiqueta());
        }

        Collection<Comparable> U = new LinkedList<>();

        U.add(vertices.removeFirst());
        while (!vertices.isEmpty()) {
            TArista a = aristas.buscarMin(U, vertices);
            TVertice v = this.getVertices().get(a.getEtiquetaDestino());
            vertices.remove(v.getEtiqueta());
            U.add(v.getEtiqueta());
            arbolPrim.insertarArista(a);
            costoTotal += a.getCosto();
        }
        System.out.println(costoTotal);
        return arbolPrim;
    }

    /*
    nuestro
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
    } */

    @Override
    public TGrafoNoDirigido Kruskal() {
               LinkedList<TArista> aristasKruskal = new LinkedList(); //Aqui se almacenaran las aristas seleccionadas.
        Map<Comparable, TVertice> vertices = getVertices();

        if (!vertices.isEmpty()) {
            desvisitarVertices();
            HashMap<Comparable, LinkedList<TVertice>> colecciones = new HashMap(vertices.size());
            LinkedList<TVertice> colTemp;

            //Creamos una "coleccion" para cada arista
            for (TVertice v : vertices.values()) {
                colTemp = new LinkedList();
                colTemp.add(v);
                colecciones.put(v.getEtiqueta(), colTemp);
            }

            //Ordenamos todas las aristas del grafo de menor costo a mayor
            LinkedList<TArista> aristasOrdenadas = new LinkedList();
            forAristas:
            for (TArista a : lasAristas) {
                if (aristasOrdenadas.isEmpty() || aristasOrdenadas.getFirst().getCosto() > a.getCosto()) {
                    aristasOrdenadas.addFirst(a);
                    continue;
                }
                for (int i = 1; i < aristasOrdenadas.size(); i++) {
                    if (aristasOrdenadas.get(i).getCosto() > a.getCosto()) {
                        aristasOrdenadas.add(i - 1, a);
                        continue forAristas;
                    }
                }
                aristasOrdenadas.add(a);
            }

            //Conectamos las colecciones de vertices (no conectados) mediante la arista de menor costo posible
            TArista menorArista;
            LinkedList<TVertice> colOrigen, colDestino;
            while (!aristasOrdenadas.isEmpty()) {
                menorArista = aristasOrdenadas.pollFirst();
                colOrigen = colecciones.get(menorArista.etiquetaOrigen);
                colDestino = colecciones.get(menorArista.etiquetaDestino);
                if (colOrigen != colDestino) {
                    colOrigen.addAll(colDestino);
                    for (TVertice v : colDestino) {
                        if (colecciones.get(v.getEtiqueta()) != colOrigen) {
                            colecciones.replace(v.getEtiqueta(), colOrigen);
                        }
                    }
                    aristasKruskal.add(menorArista);
                }
            }
        }

        TGrafoNoDirigido grafo = new TGrafoNoDirigido(vertices.values(), aristasKruskal);
        return grafo;
    }

    /*
    nuestro
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
    } */

    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        if (this.getVertices().isEmpty()) {
            return null;
        } else {
            this.desvisitarVertices();
            if(this.existeVertice(etiquetaOrigen))
            {
                TVertice vert= super.buscarVertice(etiquetaOrigen);
                Collection<TVertice> verts = new LinkedList<TVertice>();
                vert.bea(verts);
                return verts;
            }
            return null;
        }
    }

    /*
    nuestro
    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        this.desvisitarVertices();
        Collection<TVertice> visitados = new ArrayList<>();
        TVertice verticeOrigen = buscarVertice(etiquetaOrigen);
        if (verticeOrigen != null) {
            verticeOrigen.bea(visitados);
        }
        return visitados;
    } */

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

    /*
     * @Override
	public LinkedList<TVertice> puntosArticulacion(Comparable etOrigen) {
        desvisitarVertices();
        TVertice vertice = this.buscarVertice(etOrigen);
        LinkedList<TVertice> puntosArticulacion = new LinkedList<>();
        if (vertice != null) {
            vertice.setNumBp(1);
            return vertice.puntosArticulacion(puntosArticulacion);
        }
        return puntosArticulacion;
    }
     */

    @Override
    public boolean esConexo() {
        Collection<TVertice> visitados = bpf();
        return visitados.size() == getVertices().size();
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

    public void clasificarArcosBPF(TVertice verticeOrigen, ListaArcos arcosArbol, ListaArcos arcosRetroceso) {
        desvisitarVertices();
        verticeOrigen.clasificarArcosBPF(arcosArbol, arcosRetroceso);
    }

    public void clasificarArcosBEA(TVertice verticeOrigen, ListaArcos arcosArbol, ListaArcos arcosCruzados) {
        desvisitarVertices();
        verticeOrigen.clasificarArcosBEA(arcosArbol, arcosCruzados);
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
