package com.example;


public class ListasDeListaArcos {
    private ListaArcos arcosArbol;
    private ListaArcos arcosRetroceso;
    private ListaArcos arcosAvance;
    private ListaArcos arcosCruzados;

    public ListasDeListaArcos() {
        this.arcosArbol = new ListaArcos();
        this.arcosRetroceso =  new ListaArcos();
        this.arcosAvance =  new ListaArcos();
        this.arcosCruzados =  new ListaArcos();
    }

    public ListaArcos getArcosArbol() {
        return arcosArbol;
    }

    public ListaArcos getArcosRetroceso() {
        return arcosRetroceso;
    }

    public ListaArcos getArcosAvance() {
        return arcosAvance;
    }

    public ListaArcos getArcosCruzados() {
        return arcosCruzados;
    }

    public String imprimirListasDeArcos(){
        StringBuilder sb = new StringBuilder();

            sb.append("Arcos de Árbol: ");
            sb.append("\n");
            sb.append(arcosArbol.imprimirArcos());
            sb.append("\n");

            sb.append("Arcos de Avance: ");
            sb.append("\n");
            sb.append(arcosAvance.imprimirArcos());
            sb.append("\n");

            sb.append("Arcos de Retroceso: ");
            sb.append("\n");
            sb.append(arcosRetroceso.imprimirArcos());
            sb.append("\n");

            sb.append("Arcos Cruzados: ");
            sb.append("\n");
            sb.append(arcosCruzados.imprimirArcos());
            sb.append("\n");
            
        
        return sb.toString();
    }

    public void imprimirListasDeArcosConsola(){
        System.out.println(imprimirListasDeArcos());
    }
}

