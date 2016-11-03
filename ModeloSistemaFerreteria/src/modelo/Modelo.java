/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import adaptadores.AdaptadorSubject;
import interfaces.Observer;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.cobros.ConjuntoPagos;
import modelo.database.DataBaseConnection;
import modelo.factura.ConjuntoFacturas;
import modelo.inventarios.ConjuntoInventarios;
import modelo.inventarios.Inventario;
import modelo.personas.clientes.*;
import modelo.personas.empleados.*;
import modelo.productos.*;

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
//CLIENTES

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

    public Cliente createCliente(String cedula, String nombre, String telefono, String email, int descuento) {
        return new Cliente(cedula, nombre, telefono, email, descuento);
    }

    public void addCliente(Cliente cliente) throws Exception {
        conjuntoClientes.addCliente(cliente);
    }

    public void deleteClient(String id) throws Exception {
        conjuntoClientes.deleteCliente(id);
    }

    public void updateClient(Cliente cliente) throws Exception {
        conjuntoClientes.updateCliente(cliente);
    }

    public boolean verificarExistenciaCliente(String id) {
        try {
            Cliente em = conjuntoClientes.getClienteByID(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public String mensajeCliente(Cliente cliente) {
        String nombre = cliente.getNombre();
        String email = cliente.getEmail();
        String telefono = cliente.getTelefono();
        String id = cliente.getCedula();
        int descuento = cliente.getDescuento();
        String msj=("NOMBRE: " + nombre + '\n' + "EMAIL: " + email
                                + '\n' + "TELEFONO: " + telefono + '\n' + "CEDULA: " + id
                                + '\n' + "MONTO DESCUENTO: " + descuento+'\n');
        return msj;
    }
    //PRODUCTOS.....
    public void addProducto(String cod, String desc, String unidad, double precio, boolean activo) {
        Producto p = new Producto(cod, desc, unidad, precio, activo);
        try {
            conjuntoProductos.addProducto(p);
            observers.notificar();
        } catch (Exception ex) {
        }
    }

    public boolean verificarExistenciaProducto(String cod) {
        try {
            Producto p = conjuntoProductos.getProductoByCod(cod);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Producto getProducto(String cod) {
        try {
            return conjuntoProductos.getProductoByCod(cod);
        } catch (Exception ex) {
            return null;
        }
    }

    public LinkedList<Producto> getAllProductos() {
        return conjuntoProductos.getAllProductos();
    }

    public void updateProducto(String cod, String desc, String unidad, double precio, boolean activo) {
        Producto p = new Producto(cod, desc, unidad, precio, activo);
        try {
            conjuntoProductos.updateProducto(p);
            observers.notificar();
        } catch (Exception ex) {
        }
    }

    public void deleteProducto(String cod) {
        try {
            conjuntoProductos.deleteProducto(cod);
            observers.notificar();
        } catch (Exception e) {
        }
    }
//EMPLEADOS

    public boolean verificarExistenciaEmpleado(String id) {
        try {
            Empleado em = conjuntoEmpleados.getEmpleadoByID(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void addEmpleado(String idEmpleado, String nombre, String clave, boolean activo, boolean vendedor, boolean cajero, boolean despachador, boolean bodeguero, boolean administrador) {
        Empleado emp = new Empleado(idEmpleado, nombre, clave, activo, vendedor, cajero, despachador, bodeguero, administrador);
        try {
            conjuntoEmpleados.addEmpleado(emp);
        } catch (Exception ex) {
        }
    }

    public void deleteEmpleado(String id) {
        try {
            conjuntoEmpleados.deleteEmpleado(id);
        } catch (Exception ex) {
        }
    }

    public LinkedList<Empleado> searchEmpleadoByName(String nombre) {
        try {
            return conjuntoEmpleados.searchEmpleadoByName(nombre);
        } catch (Exception ex) {
            return null;
        }
    }

    public Empleado searchEmpleadoByID(String id) {
        try {
            return conjuntoEmpleados.getEmpleadoByID(id);
        } catch (Exception ex) {
            return null;
        }
    }

    public void updateEmpleado(Empleado empl) {
        try {
            conjuntoEmpleados.updateEmpleado(empl);
        } catch (Exception ex) {

        }
    }

    public Empleado createEmpleado(String idEmpleado, String nombre, String clave, boolean activo,
            boolean vendedor, boolean cajero, boolean despachador, boolean bodeguero, boolean administrador) {
        Empleado empleado = new Empleado(idEmpleado, nombre, clave, activo, vendedor, cajero, despachador, bodeguero, administrador);
        return empleado;
    }

    public String mensajeEmpleado(Empleado em) {
        String nombre = em.getNombre();
        String id = em.getIdEmpleado();
        String clave = em.getClave();
        String vendedor = "false";
        if (em.isVendedor()) {
            vendedor = "true";
        }
        String bodeguero = "false";
        if (em.isBodeguero()) {
            bodeguero = "true";
        }
        String cajero = "false";
        if (em.isCajero()) {
            cajero = "true";
        }
        String despachador = "false";
        if (em.isDespachador()) {
            despachador = "true";
        }
        String admi = "false";
        if (em.isAdministrador()) {
            admi = "true";
        }

        String msj = ("NOMBRE: " + nombre + '\n' + "CEDULA: " + id + '\n' + "CLAVE: " + clave
                + '\n' + "ES_VENDEDOR: " + vendedor + '\n' + "ES_CAJERO: " + cajero + '\n' + "ES_BODEGUERO: "
                + bodeguero + '\n' + "ES_DESPACHADOR: " + despachador + '\n' + "ES_ADMINISTRADOR: " + admi);
        return msj;
    }

    public LinkedList<Inventario> getAllInventarios() {
        return conjuntoInventarios.getAllInventario();
    }

    public void addInventario(Date date, String pro, int cant) {
        try {
            Producto producto = conjuntoProductos.getProductoByCod(pro);
            Inventario inventario = new Inventario(date, producto, cant);
            conjuntoInventarios.addInventario(inventario);
            observers.notificar();
            System.out.println("notify");
        } catch (Exception ex) {
        }
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
