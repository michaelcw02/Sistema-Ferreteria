/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.factura;

import java.util.LinkedList;

/**
 *
 * @author Michael Chen W.
 */
public class ListaFactura {

    public ListaFactura() {
        facturas = new LinkedList<>();
    }
    
    public void agregarFactura(Factura fac) {
        facturas.add(fac);
    }
    
//BLABLABLA    
    
    
    LinkedList<Factura> facturas;
}
