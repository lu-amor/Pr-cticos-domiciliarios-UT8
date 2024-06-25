package com.example;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author diego
 */
public class TCamino {

    @SuppressWarnings("rawtypes")
    private final TVertice origen;
    @SuppressWarnings("rawtypes")
    // private final Collection<Comparable> otrosVertices;
    private final Collection<Comparable> otrosVertices;
    // es una lista de etiquetas de los vertices
    // ATENCIÓN: PONER LA CLASE CONCRETA QUE
    // SEA MÁS APROPIADA

    private Double costoTotal;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public TCamino(TVertice v) {
        this.costoTotal = 0.0d;
        this.origen = v;
        this.otrosVertices = new LinkedList();

    }
    
    public void imprimirEtiquetasConsola() {
        System.out.println(imprimirEtiquetas());
    }

    @SuppressWarnings("rawtypes")
    public String imprimirEtiquetas() {
        StringBuilder sb = new StringBuilder();
        sb.append("Origen: ")
            .append(getOrigen().getEtiqueta());
        for (Comparable adyacente : getOtrosVertices()) {
            sb.append(" -> ")
                .append(adyacente);
        }
        return sb.toString();
    }


    public boolean agregarAdyacencia(TAdyacencia adyacenciaActual) {
        if (adyacenciaActual.getDestino() != null) {
            costoTotal += adyacenciaActual.getCosto();
            return getOtrosVertices().add(adyacenciaActual.getDestino().getEtiqueta());
        }
        return false;
    }

    public boolean eliminarAdyacencia(TAdyacencia adyacenciaActual) {
        if (getOtrosVertices().contains(adyacenciaActual.getDestino().getEtiqueta())) {
            costoTotal -= adyacenciaActual.getCosto();
            return getOtrosVertices().remove(adyacenciaActual.getDestino().getEtiqueta());
        }
        return false;
    }

    @SuppressWarnings("rawtypes")
    public TVertice getOrigen() {
        return origen;
    }

    @SuppressWarnings("rawtypes")
    public Collection<Comparable> getOtrosVertices() {
        return otrosVertices;
    }

    /*public void setOtrosVertices(Collection<Comparable> otrosVertices) {
        this.otrosVertices = otrosVertices;
    }*/

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public TCamino copiar() {
        TVertice origenCopia = new TVertice(this.getOrigen().getEtiqueta());
        TCamino copia = new TCamino(origenCopia);
        copia.setCostoTotal(this.getCostoTotal());
        origenCopia.getAdyacentes().addAll(this.getOrigen().getAdyacentes());
        copia.getOtrosVertices().addAll(this.getOtrosVertices());

        return copia;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public String imprimirDesdeClave(Comparable clave) {
        StringBuilder sb = new StringBuilder();
        boolean start = false;
        Collection<Comparable> listaDefinitiva = new LinkedList<Comparable>();
        listaDefinitiva.add(this.getOrigen().getEtiqueta());
        listaDefinitiva.addAll(this.getOtrosVertices());

        for (Iterator<Comparable> iterator = listaDefinitiva.iterator(); iterator.hasNext();) {
            Comparable next = iterator.next();
            if (next.compareTo(clave) == 0) {
                start = true;
            }
            if (start) {
                sb.append(" " + next + " ");
            }
        }
        return sb.toString();
    }

}
