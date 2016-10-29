/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.personas.clientes;

import java.util.LinkedList;

/**
 *
 * @author Michael Chen W.
 */
public class ConjuntoClientes {
    public ConjuntoClientes() {
        clientes = new LinkedList<>();
    }
    
    public void agregar(Cliente emp) {
        clientes.add(emp);
    }
    public boolean remover(String cedula) {
        for(Cliente client : clientes)
            if(client.getCedula().equalsIgnoreCase(cedula)) {
                clientes.remove(client);
                return true;
            }
        return false;
    }
    
    LinkedList<Cliente> clientes;
}
