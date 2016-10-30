/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.cobros;

/**
 *
 * @author Michael Chen W.
 */
public class CounterCheque {
    public CounterCheque(int numero) {
        this.numero = numero;
    }

    public CounterCheque() {
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }
    
    int numero;
}
