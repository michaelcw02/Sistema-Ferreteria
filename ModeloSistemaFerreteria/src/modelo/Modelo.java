/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import adaptadores.AdaptadorSubject;
import interfaces.Observer;
import java.util.LinkedList;
import modelo.cobros.ConjuntoPagos;
import modelo.database.DataBaseConnection;
import modelo.factura.ConjuntoFacturas;
import modelo.inventarios.ConjuntoInventarios;
import modelo.personas.clientes.Cliente;
import modelo.personas.clientes.ConjuntoClientes;
import modelo.personas.empleados.ConjuntoEmpleados;
import modelo.personas.empleados.Empleado;
import modelo.productos.ConjuntoProductos;

/**
 *
 * @author Michael Chen W.
 */
public class Modelo {

    private Modelo() {
        observers = new AdaptadorSubject();
        dbc = new DataBaseConnection();
        conjuntoProductos = new ConjuntoProductos(dbc);
        conjuntoEmpleados = new ConjuntoEmpleados(dbc);
        conjuntoClientes = new ConjuntoClientes(dbc);
        conjuntoInventarios = new ConjuntoInventarios(dbc);
        conjuntoFacturas = new ConjuntoFacturas(dbc);
        conjuntoPagos = new ConjuntoPagos(dbc);
        emp = null;
    }

    static public Modelo getInstance() {
        if (instance == null) {
            instance = new Modelo();
        }
        return instance;
    }

    public void agregar(Observer obs) {
        observers.agregar(obs);
    }

    public void notificar() {
        observers.notificar();
    }

    public Empleado verifyCredentials(String id, String pass) {
        try {
            emp = conjuntoEmpleados.getEmpleadoByIdAndPass(id, pass);
            if (emp.isActivo()) {
                return emp;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Cliente searchClientByID(String id) {
        Cliente cliente = new Cliente();
        try {
            cliente = conjuntoClientes.getClienteByID(id);
            return cliente;
        } catch (Exception e) {
        }
        return null;
    }

    public LinkedList<Cliente> searchClientByName(String name) {
        LinkedList<Cliente> listaResultado = new LinkedList<>();
        try {
            listaResultado = conjuntoClientes.searchClienteByName(name);
            return listaResultado;
        } catch (Exception e) {
        }
        return null;
    }

    public static Modelo instance;
    AdaptadorSubject observers;
    DataBaseConnection dbc;
    ConjuntoProductos conjuntoProductos;
    ConjuntoEmpleados conjuntoEmpleados;
    ConjuntoClientes conjuntoClientes;
    ConjuntoInventarios conjuntoInventarios;
    ConjuntoFacturas conjuntoFacturas;
    ConjuntoPagos conjuntoPagos;
    Empleado emp;   //ESTO ES PARA PROPOSITOS DE TENER EXTRA SEGURIDAD.
}
