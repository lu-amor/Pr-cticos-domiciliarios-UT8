package com.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class TVertice<T> implements IVertice, IVerticeKevinBacon, IVerticeContagio {

    @SuppressWarnings("rawtypes")
    private final Comparable etiqueta; // etiqueta no era final
    private final LinkedList<TAdyacencia> adyacentes; //adyacentes no era final
    private boolean visitado;
    private T datos;
    private int numeroBp;
    private int tiempoFinalización; // tiempo en que recorre todas las adyacencias del vertice
    private int numeroBajo;
    private int numBea;
    @SuppressWarnings("rawtypes")
    private TVertice predecesor;

    @SuppressWarnings("rawtypes")
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public T getDatos() {
        return datos;
    }

    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    public boolean getVisitado() {
        return this.visitado;
    }

    @Override
    public int getBacon() {
        return this.numBea;
    }

    @Override
    public void setBacon(int newBacon) {
        this.numBea = newBacon;
    }

    /**
     * @return the predecesor
     */
    @SuppressWarnings("rawtypes")
    public TVertice getPredecesor() {
        return predecesor;
    }

    /**
     * @param predecesor the predecesor to set
     */
    @SuppressWarnings("rawtypes")
    public void setPredecesor(TVertice predecesor) {
        this.predecesor = predecesor;
    }

    /**
     * @return the BEA number
     */
    public int getNumeroBEA(){
        return numeroBajo;

    }
    /**
     * @param numBEA the BEA number to set
     */
    public void setNumeroBEA(int numBEA){
        this.numBea = numBEA;
    }

    /**
     * @return the BP number
     */
    public int getNumeroBP(){
        return numeroBp;
    }

    /**
     * @param numBP the BP number to set
     */
    public void setNumeroBP(int numBP){
        this.numeroBp = numBP;
    }

    /**
     * @return the finish time it takes to visit the ady to the vertex
     */
    public int getTiempoDeFinalizacion(){
        return tiempoFinalización;
    }

    /**
     * @param TiempoDeFinalizacion the finish time to set
     */
    public void setTiempoDeFinalizacion(int tiempoDeFinalizacion){
        this.tiempoFinalización = tiempoDeFinalizacion;
    }

    /**
     * @return the low number
     */
    public int getNumeroBajo(){
        return numeroBajo;
    }

    /**
     * @param numBajo the low number to set
     */
    public void setNumeroBajo(int numBajo){
        this.numeroBp = numBajo;
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
        numeroBp = -1;
        numeroBajo = -1;
        tiempoFinalización = -1;
        numBea = -1;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void bpf(Collection<TVertice> visitados) {
        setVisitado(true);
        visitados.add(this);
        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();
            if (!vertAdy.getVisitado()) {
                vertAdy.bpf(visitados);
            }
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        this.setVisitado(true);
        for (TAdyacencia adyacencia : this.getAdyacentes()) {
            TVertice destino = adyacencia.getDestino();
            
            if (!destino.getVisitado()) {
                
                if (destino.getEtiqueta().compareTo(etVertDest) == 0) {
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia(adyacencia);
                    todosLosCaminos.getCaminos().add(copia);
                } else {
                    caminoPrevio.agregarAdyacencia(adyacencia);
                    destino.todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos); // LLAMADA RECURSIVA

                }
                caminoPrevio.eliminarAdyacencia(adyacencia); 
                // Revertir la adyacencia después de la recursión
                // para asegurar la consistencia del caminoPrevio a través de todas las iteraciones del bucle.
            }
        }
        this.setVisitado(false);
        return todosLosCaminos;
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void bea(Collection<TVertice> visitados) {
        setVisitado(true);
        visitados.add(this);
        for (TAdyacencia adyacente : this.getAdyacentes()) {
            TVertice verticeAdyacente = adyacente.getDestino();
            if (!visitados.contains(verticeAdyacente)) {
                verticeAdyacente.bea(visitados);
            }
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public TVertice siguienteAdyacente(TVertice w) {
        TAdyacencia adyacente = buscarAdyacencia(w.getEtiqueta());
        int index = adyacentes.indexOf(adyacente);
        if (index + 1 < adyacentes.size()) {
            return adyacentes.get(index + 1).getDestino();
        }
        return null;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public boolean tieneCiclo(LinkedList<Comparable> camino) {
        this.setVisitado(true);
        camino.add(this.getEtiqueta());

        for (TAdyacencia adyacente : adyacentes) {
            TVertice<T> destino = adyacente.getDestino();
            if (camino.contains(destino.getEtiqueta())) {
                return true;
            }
            if (!destino.getVisitado() && destino.tieneCiclo(camino)) {
                return true;
            }
        }
        camino.remove(this.getEtiqueta());
        return false;
    }

    @SuppressWarnings({ "rawtypes"})
    public boolean tieneCiclo(TCamino camino) {
        setVisitado(true);

        Collection<Comparable> otrosVertices = camino.getOtrosVertices();

    
        otrosVertices.add(getEtiqueta());
        //camino.setOtrosVertices(otrosVertices);
    
        for (TAdyacencia adyacente : this.getAdyacentes()) {
            TVertice destino = adyacente.getDestino();
            if (destino.getVisitado() && camino.getOtrosVertices().contains(destino.getEtiqueta())) {
                // Ciclo encontrado, el camino ya contiene las etiquetas del ciclo
                return true;
            } else if (!destino.getVisitado()) {
                if (destino.tieneCiclo(camino)) {
                    // Se encontró un ciclo recursivamente
                    return true;
                }
            }
        }
        // Revertir la visita al finalizar el análisis de este vértice
        setVisitado(false);
        otrosVertices.remove(this.getEtiqueta());
        //camino.setOtrosVertices(otrosVertices);
        //camino.getOtrosVertices().remove(this.getEtiqueta());
        return false;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean conectadoCon(TVertice destino) {
        setVisitado(true);
        
        if(getEtiqueta().equals(destino.getEtiqueta())) {
            return true;
        }

        for (TAdyacencia adyacencia: getAdyacentes()){
            TVertice verticeAdyacente = adyacencia.getDestino();

            if (!verticeAdyacente.getVisitado()){
                if (verticeAdyacente.conectadoCon(destino)) {
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void puntosArticulacion(LinkedList<TVertice> puntos, int[] cont) {
        cont[0]++;
        this.numeroBp = cont[0];
        this.numeroBajo = cont[0];
        LinkedList<TVertice> hijos = new LinkedList<>();
        for (TAdyacencia adyacente : this.getAdyacentes()) {
            TVertice destino = adyacente.getDestino();
            if (!destino.getVisitado()) {
                destino.puntosArticulacion(puntos, cont);
                hijos.add(destino);
                this.numeroBajo = Math.min(this.numeroBajo, destino.numeroBajo);
            } else {
                this.numeroBajo = Math.min(this.numeroBajo, destino.numeroBp);
            }
        }
        if (this.numeroBp > 1) {
            for (TVertice hijo : hijos) {
                if (hijo.numeroBajo >= this.numeroBp) {
                    puntos.add(this);
                }
            }
        } else {
            if (hijos.size() > 1) {
                puntos.add(this);
            }
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public int numBea(String etiquetaObjetivo) {
        if (this.getEtiqueta().equals(etiquetaObjetivo)) {
            return 0;
        }
        
        Queue<TVertice> cola = new LinkedList<>();
        Queue<Integer> grados = new LinkedList<>();
        
        cola.add(this);
        grados.add(0);
        setVisitado(true);
        
        while (!cola.isEmpty()) {
            TVertice actual = cola.poll();
            int gradoActual = grados.poll();
            
            for (TAdyacencia adyacente : (LinkedList<TAdyacencia>) actual.getAdyacentes()) {
                TVertice verticeAdyacente = adyacente.getDestino();
                if (!verticeAdyacente.getVisitado()) {
                    if (verticeAdyacente.getEtiqueta().equals(etiquetaObjetivo)) {
                        return 1 + gradoActual;
                    }
                    cola.add(verticeAdyacente);
                    grados.add(gradoActual + 1);
                    verticeAdyacente.setVisitado(true);
                }
            }
        }
        return -1;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Set<TVertice> buscarMaxEnlacesDesdeVertice(int maxEnlaces, Set<TVertice> visitados) { // listarContactos 2018
        Set<TVertice> resultado = new HashSet<>();
        Queue<TVertice> cola = new LinkedList<>();
        Queue<Integer> niveles = new LinkedList<>();

        cola.offer(this);
        niveles.offer(0);
        visitados.add(this);

        while (!cola.isEmpty()) {
            TVertice actual = cola.poll();
            int nivelActual = niveles.poll();

            if (nivelActual <= maxEnlaces) {
                resultado.add(actual);
            } else {
                break;
            }

            for (TAdyacencia adyacente : (LinkedList<TAdyacencia>) actual.adyacentes) {
                TVertice verticeAdyacente = adyacente.getDestino();
                if (!visitados.contains(verticeAdyacente)) {
                    cola.offer(verticeAdyacente);
                    niveles.offer(nivelActual + 1);
                    visitados.add(verticeAdyacente);
                }
            }
        }
        return resultado;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map<TVertice, Integer> obtenerDistancias() { // listarContactos 2020 (Caso COVID)
        Map<TVertice, Integer> distancias = new HashMap<>();
        Queue<TVertice> cola = new LinkedList<>();
        Set<TVertice> visitados = new HashSet<>();
    
        cola.add(this);
        distancias.put(this, 0);
        visitados.add(this);
    
        while (!cola.isEmpty()) {
            TVertice actual = cola.poll();
            int distActual = distancias.get(actual);
    
            for (TAdyacencia ady : (LinkedList<TAdyacencia>) actual.getAdyacentes()) {
                TVertice verticeAdy = ady.getDestino();
                if (!visitados.contains(verticeAdy)) {
                    distancias.put(verticeAdy, distActual + 1);
                    cola.add(verticeAdy);
                    visitados.add(verticeAdy);
                }
            }
        }
    
        distancias.remove(this);
        return distancias;
    }

    @Override
    public void obtenerAnillos(TAnillosContagio losAnillos, int maxDistancia) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerAnillos'");
    }


}
