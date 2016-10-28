/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.cobros;

import java.util.LinkedList;

/**
 *
 * @author Michael Chen W.
 */
public class ListaPagos {

    public ListaPagos() {
        pagos = new LinkedList<>();
    }
    public void agregarPago(Pago pago) {
        pagos.add(pago);
    }   
    
    
    LinkedList<Pago> pagos;
}
