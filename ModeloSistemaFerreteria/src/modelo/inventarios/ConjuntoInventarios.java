/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.inventarios;

import java.util.LinkedList;

/**
 *
 * @author Michael Chen W.
 */
public class ConjuntoInventarios {

    public ConjuntoInventarios() {
        inventarios = new LinkedList<>();
    }
    
    public void agregar(Inventario item) {
        inventarios.add(item);
    }
    
    LinkedList<Inventario> inventarios;
}
