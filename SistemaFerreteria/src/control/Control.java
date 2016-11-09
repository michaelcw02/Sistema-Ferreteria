/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Interfaz.*;
import java.util.Date;
import java.util.LinkedList;
import modelo.Modelo;
import modelo.cobros.Pago;
import modelo.factura.Factura;
import modelo.inventarios.Inventario;
import modelo.personas.clientes.Cliente;
import modelo.personas.empleados.Empleado;
import modelo.productos.Producto;

/**
 *
 * @author Michael Chen W.
 */
public class Control {

    public Control() {
        modelo = Modelo.getInstance();
        login = new VentanaLogin(this);
        menu = new VentanaMenu(this);
        ventas = new VentanaVentas(this);
        //clientes = new VCatalogoClientes(this);
        clientes = new VentanaCliente(this);
        productos = new VCatalogoProductos(this);
        empleados = new VMantListaEmple(this);
        inventario = new VInventario(this);
        cobro = new VentanaCobros(this);
        despacho = new VentanaDespacho(this);
        //
        setObservers();
    }

    private void setObservers() {
        modelo.agregar(productos);
        modelo.agregar(inventario);
        modelo.agregar(despacho);
        modelo.agregar(cobro);
        //this keeeps going down.
    }

    public void mostrarLogin() {
        login.show();
    }

    public void ocultarLogin() {
        login.dispose();
    }

    public void mostrarMenu() {
        menu.show();
    }

    public boolean login(String username, String password) {
        Empleado emp = modelo.verifyCredentials(username, password);
        if (emp != null) {
            String name = emp.getNombre();
            menu.updateBanner(name);
            menu.limitarAccesos(emp);
            ocultarLogin();
            mostrarMenu();
            return true;
        }
        return false;
    }

    public Cliente searchClienteByID(String id) {
        return modelo.searchClientByID(id);
    }

    public LinkedList<Cliente> searchClienteByName(String name) {
        return modelo.searchClientByName(name);
    }

    public Cliente createCliente(String cedula, String nombre, String telefono, String email, int descuento) {
        return modelo.createCliente(cedula, nombre, telefono, email, descuento);
    }

    public void addCliente(Cliente cliente) throws Exception {
        modelo.addCliente(cliente);
    }

    public void deleteCliente(String id) throws Exception {
        modelo.deleteClient(id);
    }

    public void updateCliente(Cliente cliente) throws Exception {
        modelo.updateClient(cliente);
    }

    public boolean verificarExistenciaCliente(String id) {
        return modelo.verificarExistenciaCliente(id);
    }

    public String mensajeCliente(Cliente cliente) {
        return modelo.mensajeCliente(cliente);
    }

    public void mostrarClientes() {
        clientes.show();
    }
    
    public void mostrarVentas() {
        ventas.setFactura(new Factura());
        ventas.setFecha(new Date());
        ventas.show();
    }

    public void mostrarVEmpleados() {
        empleados.show();
    }
    
    public void mostrarProductos() {
        productos.show();
    }

    public void mostrarCobro() {
        cobro.show();
    }

    public void addProducto(String codigo, String descripcion, String medida, double precio, boolean activo) {
        modelo.addProducto(codigo, descripcion, medida, precio, activo);
    }

    public boolean verificarExistenciaProducto(String codigo) {
        return (modelo.getProducto(codigo) != null) ? true : false;
    }

    public void deleteProducto(String cod) {
        modelo.deleteProducto(cod);
    }

    public boolean verificarExistenciaEmpleado(String emp) {
        return modelo.verificarExistenciaEmpleado(emp);
    }

    public void addEmpleado(String idEmpleado, String nombre, String clave, boolean activo, boolean vendedor, boolean cajero, boolean despachador, boolean bodeguero, boolean administrador) {
        modelo.addEmpleado(idEmpleado, nombre, clave, activo, vendedor, cajero, despachador, bodeguero, administrador);
    }

    public void deleteEmpleado(String id) {
        try {
            modelo.deleteEmpleado(id);
        } catch (Exception ex) {
        }
    }

    public Empleado createEmpleado(String idEmpleado, String nombre, String clave, boolean activo,
            boolean vendedor, boolean cajero, boolean despachador, boolean bodeguero, boolean administrador) {
        return modelo.createEmpleado(idEmpleado, nombre, clave, activo, vendedor, cajero, despachador, bodeguero, administrador);
    }

    public Empleado searchEmpleadoByID(String text) {
        return modelo.searchEmpleadoByID(text);
    }

    public String empleadoMensaje(Empleado empleado) {
        return modelo.mensajeEmpleado(empleado);
    }

    public void updateEmpleado(Empleado empl) {
        modelo.updateEmpleado(empl);
    }

    public LinkedList<Empleado> searchEmpleadoByName(String nom) {
        return modelo.searchEmpleadoByName(nom);
    }

    public LinkedList<Empleado> getEmpleadosVendedor() {
        return modelo.getEmpleadosVendedor();
    }

    public LinkedList<Producto> getAllProductos() {
        return modelo.getAllProductos();
    }

    public void modificarProducto(String cod, String des, String med, double precio, boolean activo) {
        modelo.updateProducto(cod, des, med, precio, activo);
    }

    public void mostrarDespacho() {
        despacho.show();
    }

    public void mostrarInventario() {
        inventario.show();
        inventario.setDate(new Date());
    }

    public LinkedList<Inventario> getAllInventarios() {
        return modelo.getAllInventarios();
    }

    public void addInventario(Date date, String pro, int cant) {
        modelo.addInventario(date, pro, cant);
    }

    public Factura getFacturaByCod(int cod) {
        return modelo.getFacturaByCod(cod);

    }

    public LinkedList<Factura> getAllFacturas() {
        return modelo.getAllFacturas();
    }

    public void updateFactura(Factura factura) {
        modelo.updateFactura(factura);
    }
    
    public void addFactura(Factura factura) {
        modelo.addFactura(factura);
    }

    //PAGO
    public void addPago(Pago pago) {
        modelo.addPago(pago);
    }

    public int getSumOfProduct(String idProducto) {
        return modelo.getSumOfProduct(idProducto);
    }

    private Modelo modelo;
    private VentanaLogin login;
    private VentanaMenu menu;
    private VentanaVentas ventas;
    //private VCatalogoClientes clientes;
    private VentanaCliente clientes;
    private VCatalogoProductos productos;
    private VMantListaEmple empleados;
    private VInventario inventario;
    private VentanaCobros cobro;
    private VentanaDespacho despacho;

}
