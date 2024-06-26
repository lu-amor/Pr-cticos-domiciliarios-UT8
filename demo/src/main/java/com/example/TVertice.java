package com.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TVertice<T> implements IVertice, IVerticeKevinBacon, IVerticeContagio {

    private final Comparable etiqueta; // etiqueta no era final
    private final LinkedList<TAdyacencia> adyacentes; //adyacentes no era final
    private boolean visitado;
    private T datos;
    private int numeroBp; // Tiempo de descubrimiento en la DFS
    private int tiempoFinalización; // Tiempo en que recorre todas las adyacencias del vertice
    private int numeroBajo;
    private int numBea; // Tiempo de descubrimiento en la busqueda por amplitud
    private TVertice predecesor;

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
        numeroBp = -1;
        numeroBajo = -1;
        tiempoFinalización = -1;
        numBea = -1;
    }

    /**
     * @return the vertex key
     */
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    /**
     * @return the adyacents
     */
    @Override
    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    /**
     * @return the data stored in this vertex
     */
    public T getDatos() {
        return datos;
    }

    /**
     * @param value the value (true or false) to set with the purpose of indicate if the vertex was visited or not
     */
    public void setVisitado(boolean value) {
        this.visitado = value;
    }

    /**
     * @return the visited flag
     */
    public boolean getVisitado() {
        return this.visitado;
    }

    /**
     * @return the bea number
     */
    @Override
    public int getBacon() {
        return this.numBea;
    }

    /**
     * @param numBacon the bacon number to set
     */
    @Override
    public void setBacon(int numBacon) {
        this.numBea = numBacon;
    }

    /**
     * @return the predecesor
     */
    public TVertice getPredecesor() {
        return predecesor;
    }

    /**
     * @param predecesor the predecesor to set
     */
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

    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }

    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }

    @Override
    public TVertice siguienteAdyacente(TVertice w) {
        TAdyacencia adyacente = buscarAdyacencia(w.getEtiqueta());
        int index = adyacentes.indexOf(adyacente);
        if (index + 1 < adyacentes.size()) {
            return adyacentes.get(index + 1).getDestino();
        }
        return null;
    }

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

    /*@SuppressWarnings({ "rawtypes", "unchecked" })
    public TCaminos todosLosCaminosCasoEspecial(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos, double contador) {
        this.setVisitado(true); 
        for (TAdyacencia adyacencia : this.getAdyacentes()) { 
            TVertice destino = adyacencia.getDestino(); 

            if (!destino.getVisitado()) { 
                // No necesitamos establecer el costo en cada adyacencia,
                // simplemente contamos el número de vértices recorridos menos uno

                if (destino.getEtiqueta().compareTo(etVertDest) == 0) { 
                    TCamino copia = caminoPrevio.copiar(); 
                    copia.agregarAdyacencia(adyacencia); 
                    // Aquí calculamos el costo como el contador actual (número de vértices recorridos)
                    copia.setCostoTotal(contador);
                    todosLosCaminos.getCaminos().add(copia); 
                } else { 
                    caminoPrevio.agregarAdyacencia(adyacencia);
                    destino.todosLosCaminosCasoEspecial(etVertDest, caminoPrevio, todosLosCaminos, contador + 1); // LLAMADA RECURSIVA
                } 
                caminoPrevio.eliminarAdyacencia(adyacencia); 
            } 
        } 
        this.setVisitado(false); 
        return todosLosCaminos; 
    } */

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

    public void sortTopologico(Stack<TVertice> stack) {
        setVisitado(true);
        for (TAdyacencia adyacencia : (Collection<TAdyacencia>) getAdyacentes()) {
            TVertice destino = adyacencia.getDestino();
            if (!destino.getVisitado()) {
                destino.sortTopologico(stack);
            }
        }
        stack.push(this);
    }

    public void obtenerOrdenParcial(LinkedList<TVertice> orden) {
        if (getVisitado()) {
            return; // Evita revisitar un vértice ya visitado
        }
        setVisitado(true);
        
        // intento de agregar un marcador 
        /*TVertice centinela = new TVertice<>("c");

        if (getAdyacentes().size() >= 2){
            orden.add(centinela);
        }*/

        for (TAdyacencia adyacente : adyacentes) {    
            TVertice verticeDestino = adyacente.getDestino();
            verticeDestino.obtenerOrdenParcial(orden);
        }
        orden.add(this); // Agrega la tarea al inicio de la lista
    }

    public void clasificarArcosGrafoDirigido(ListaArcos arcosArbol, ListaArcos arcosRetroceso, ListaArcos arcosAvance, ListaArcos arcosCruzados, int[] tiempo) {
        this.setVisitado(true);
        this.setNumeroBP(tiempo[0]++);
        for (TAdyacencia adyacencia : this.getAdyacentes()) {
            TVertice destino = adyacencia.getDestino();
            if (!destino.getVisitado()) {
                arcosArbol.add(new TArista(this.getEtiqueta(), destino.getEtiqueta(),adyacencia.getCosto()));
                destino.clasificarArcosGrafoDirigido(arcosArbol, arcosRetroceso, arcosAvance, arcosCruzados, tiempo);
            } else if (destino.getNumeroBP() < this.getNumeroBP() && destino.getTiempoDeFinalizacion() == -1) {
                arcosRetroceso.add(new TArista(this.getEtiqueta(), destino.getEtiqueta(),adyacencia.getCosto()));
            } else if (destino.getNumeroBP() > this.getNumeroBP()) {
                arcosAvance.add(new TArista(this.getEtiqueta(), destino.getEtiqueta(),adyacencia.getCosto()));
            } else {
                arcosCruzados.add(new TArista(this.getEtiqueta(), destino.getEtiqueta(),adyacencia.getCosto()));
            }
        }
        this.setTiempoDeFinalizacion(tiempo[0]++);
    }

    public int numBea(Comparable etiquetaObjetivo) {
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
        Queue<TVertice> cola = new LinkedList<>();
        Queue<Integer> distancias = new LinkedList<>();

        cola.add(this);
        distancias.add(0);
        setVisitado(true);

        while (!cola.isEmpty()) {
            TVertice actual = cola.poll();
            int distancia = distancias.poll();

            if (distancia > maxDistancia && maxDistancia > 0) {
                continue;
            }

            losAnillos.agregarContagio(distancia, actual.getEtiqueta().toString());

            for (TAdyacencia adyacencia : (Collection<TAdyacencia>)actual.getAdyacentes()) {
                TVertice adyacente = adyacencia.getDestino();
                if (!adyacente.getVisitado()) {
                    cola.add(adyacente);
                    distancias.add(distancia + 1);
                    adyacente.setVisitado(true);
                }
            }
        }
        
    }
}
