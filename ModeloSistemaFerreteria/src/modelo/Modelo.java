/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import adaptadores.AdaptadorSubject;
import modelo.cobros.ConjuntoPagos;
import modelo.database.DataBaseConnection;
import modelo.factura.ConjuntoFacturas;
import modelo.inventarios.ConjuntoInventarios;
import modelo.personas.clientes.ConjuntoClientes;
import modelo.personas.empleados.ConjuntoEmpleados;
import modelo.productos.ConjuntoProductos;

/**
 *
 * @author Michael Chen W.
 */
public class Modelo {
    
    private Modelo() {
        dbc = new DataBaseConnection();
        conjuntoProductos = new ConjuntoProductos(dbc);
        conjuntoEmpleados = new ConjuntoEmpleados(dbc);
        conjuntoClientes  = new ConjuntoClientes(dbc);
        conjuntoInventarios = new ConjuntoInventarios(dbc);
        conjuntoFacturas = new ConjuntoFacturas(dbc);
        conjuntoPagos = new ConjuntoPagos(dbc);
    }
    static public Modelo getInstance() {
        if(instance == null)
            instance = new Modelo();
        return instance;
    }
    
    
    
    public static Modelo instance;
    AdaptadorSubject    observers;
    DataBaseConnection  dbc;
    ConjuntoProductos   conjuntoProductos;
    ConjuntoEmpleados   conjuntoEmpleados;
    ConjuntoClientes    conjuntoClientes;
    ConjuntoInventarios conjuntoInventarios;
    ConjuntoFacturas    conjuntoFacturas;
    ConjuntoPagos       conjuntoPagos;  
}
