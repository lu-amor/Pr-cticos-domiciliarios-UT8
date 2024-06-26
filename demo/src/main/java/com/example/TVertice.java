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
public class TVertice<T> implements IVertice, IVerticeKevinBacon{

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
        return numBea;

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
        this.visitado = true;
        LinkedList<TVertice> lista = new LinkedList();
        lista.add(this);
        visitados.add(this);
        while(!lista.isEmpty()){
            TVertice primero = lista.remove(0);
            LinkedList<TAdyacencia> ady = primero.getAdyacentes();
            for(TAdyacencia t : ady){
                if(!t.getDestino().getVisitado()){
                    t.getDestino().setVisitado(true);
                    lista.add(t.getDestino());
                    visitados.add(t.getDestino());
                }
            }
        }
    }

    /*
    nuestro
    @Override
    public void bea(Collection<TVertice> visitados) {
        Queue<TVertice> cola = new LinkedList<>();
        this.setVisitado(true);
        cola.add(this);
        while (!cola.isEmpty()) {
            TVertice verticeActual = cola.poll();
            visitados.add(verticeActual);
            for (TAdyacencia adyacente : (Collection<TAdyacencia>) verticeActual.getAdyacentes()) {
                TVertice verticeAdyacente = adyacente.getDestino();
                if (!verticeAdyacente.getVisitado()) {
                    verticeAdyacente.setVisitado(true);
                    cola.add(verticeAdyacente);
                }
            }
        }
    } */

    /*
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
    }*/

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
        setVisitado(true);
        camino.add(this.getEtiqueta());
        boolean ciclo = false;
        for (TAdyacencia adyacencia : this.getAdyacentes()) {
            TVertice w = adyacencia.getDestino();
            if (!w.getVisitado()) {
                ciclo = w.tieneCiclo(camino);
                if (ciclo) {
                    break;
                }
            } else {
                if (camino.contains(w.getEtiqueta())) {
                    ciclo = true;
                    break;
                }

            }

        }
        camino.remove(this.getEtiqueta());
        return ciclo;
    }

    /*
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
    }*/

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
    public boolean conectadoCon(TVertice etVertDest) {
        this.setVisitado(true);
        for (TAdyacencia adyacencia : this.getAdyacentes()) {
            TVertice destino = adyacencia.getDestino();
            if (!destino.getVisitado()) {
                if (destino.getEtiqueta().compareTo(etVertDest) == 0) {                    
                    return true;
                } else {                    
                    boolean existe =  destino.conectadoCon(etVertDest);
                    if (existe) {
                        return existe;
                    }
                }                
            }
        }
        this.setVisitado(false);
        return false;
    }

    /*
    nuestro
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
    } */

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

    /*
    versión 2
     * public LinkedList<TVertice> puntosArticulacion(LinkedList<TVertice> puntosArticulacion) {
        setVisitado(true);
        Collection<Integer> opcionesBajo = new HashSet<>();
        opcionesBajo.add(num_bp);
        Collection<Integer> numBajoHijos = new HashSet<>();
        int contador_numBp = this.num_bp;

        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();
            contador_numBp++;

            if (!vertAdy.getVisitado()) {
                vertAdy.setNumBp(contador_numBp);
                opcionesBajo.add(vertAdy.getNumBp());
                vertAdy.puntosArticulacion(puntosArticulacion);
                numBajoHijos.add(vertAdy.getNumBajo());
            } else if ((this.num_bp +1) < vertAdy.getNumBp()){ //intento de distinguir arco de arbol de arco de retroceso
                opcionesBajo.add(vertAdy.getNumBajo());
            }
        }
        
        this.numBajo = calculoNumBajo(opcionesBajo);

        for (int opcion : numBajoHijos) {
            if (opcion > this.num_bp) {
                puntosArticulacion.add(this);
                return puntosArticulacion;
            }
        }
        return puntosArticulacion;
    }

    public int calculoNumBajo(Collection<Integer> opcionesBajo) {
        int numBajo = Integer.MAX_VALUE;
        for (Integer opcion : opcionesBajo) {
            if (opcion.compareTo(numBajo) < 0) {
                numBajo = opcion;
            }
        }
        return numBajo;
    }
     */

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

    public void obtenerSortTopologico(LinkedList<TVertice> sortTopologico){
        if (getVisitado()) {
            return; // Evita revisitar un vértice ya visitado
        }
        setVisitado(true);

        for (TAdyacencia adyacente : adyacentes) {    
            TVertice verticeDestino = adyacente.getDestino();
            if (!verticeDestino.getVisitado()){
                verticeDestino.obtenerOrdenParcial(sortTopologico);
            }
        }

        if (!sortTopologico.contains(this)){
            sortTopologico.add(this); // Agrega la tarea al inicio de la lista
        }
    }

    public void obtenerOrdenParcial(LinkedList<TVertice> orden) {
        if (getVisitado()) {
            return; // Evita revisitar un vértice ya visitado
        }
        setVisitado(true);
        
        // intento de agregar un marcador 
        TVertice centinela = new TVertice<>("$");

        if (adyacentes.size() == 1) {
            adyacentes.getFirst().getDestino().ordenParcial(orden);
        } else {
            for (TAdyacencia adyacente : adyacentes) {    
                TVertice verticeDestino = adyacente.getDestino();
                if (!verticeDestino.getVisitado()){
                    verticeDestino.obtenerOrdenParcial(orden);
                    orden.add(centinela); 
                }
            }
        }

        if (!orden.contains(this)){
            orden.add(this); // Agrega la tarea al inicio de la lista
        }
    }

    public void ordenParcial(LinkedList<Comparable> result) {
        setVisitado(true);

        if (adyacentes.size() == 1) {
            adyacentes.getFirst().getDestino().ordenParcial(result);
        } else {
            for (TAdyacencia ady : adyacentes) {
                TVertice destino = ady.getDestino();
                if (!destino.getVisitado()) {
                    destino.ordenParcial(result);
                    result.add("$");
                }
            }
        }

        if (!result.contains(etiqueta))
            result.add(etiqueta);
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

    public void clasificarArcosBPF(ListaArcos arcosArbol, ListaArcos arcosRetroceso) {
        this.setVisitado(true);
        for (TAdyacencia adyacencia : this.getAdyacentes()) {
            TVertice destino = adyacencia.getDestino();
            if (!destino.getVisitado()) {
                arcosArbol.add(new TArista(this.getEtiqueta(), destino.getEtiqueta(), adyacencia.getCosto()));
                destino.clasificarArcosBPF(arcosArbol, arcosRetroceso);
            } else {
                arcosRetroceso.add(new TArista(this.getEtiqueta(), destino.getEtiqueta(),adyacencia.getCosto()));
            }
        }
    }

    public void clasificarArcosBEA(ListaArcos arcosArbol, ListaArcos arcosCruzados) {
        Queue<TVertice> cola = new LinkedList<>();
        this.setVisitado(true);
        cola.add(this);
    
        while (!cola.isEmpty()) {
            TVertice verticeActual = cola.poll();
            for (TAdyacencia adyacencia : (Collection<TAdyacencia>) verticeActual.getAdyacentes()) {
                TVertice destino = adyacencia.getDestino();
                if (!destino.getVisitado()) {
                    destino.setVisitado(true);
                    arcosArbol.add(new TArista(verticeActual.getEtiqueta(), destino.getEtiqueta(), adyacencia.getCosto()));
                    cola.add(destino);
                } else {
                    arcosCruzados.add(new TArista(verticeActual.getEtiqueta(), destino.getEtiqueta(), adyacencia.getCosto()));
                }
            }
        }
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

    /**
     * @return the bea number
     */
    @Override
    public int getBacon() {
        return getNumeroBEA();
    }

    /**
     * @param numBacon the bacon number to set
     */
    @Override
    public void setBacon(int numBacon) {
        setNumeroBEA(numBacon);
    }


    @Override
    public void listarContactos(Collection<TVertice> visitados, int maxSaltos) {
        Queue<TVertice> queue = new LinkedList<>();
        queue.add(this);
        setVisitado(true);
        setBacon(0);
        
        while (!queue.isEmpty()) {
            TVertice current = queue.poll();
            int currentBacon = current.getBacon();
            
            if (currentBacon >= maxSaltos) {
                break;
            }
            
            for (TAdyacencia adyacencia : (Collection<TAdyacencia>) current.getAdyacentes()) {
                TVertice vecino =  adyacencia.getDestino();
                if (!vecino.getVisitado()) {
                    vecino.setVisitado(true);
                    vecino.setBacon(currentBacon + 1);
                    queue.add(vecino);
                    visitados.add(vecino);
                }
            }
        }
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
}
