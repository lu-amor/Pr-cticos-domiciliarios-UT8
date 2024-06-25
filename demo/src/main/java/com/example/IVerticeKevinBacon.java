package com.example;

import java.util.Collection;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ernesto
 */
public interface IVerticeKevinBacon {
    int getBacon();
    void setBacon(int numBacon);
    //void listarContactos (Collection<TVertice> visitados, int maxSaltos);
    Set<TVertice> buscarMaxEnlacesDesdeVertice(int maxEnlaces, Set<TVertice> visitados);
    
        
}

