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
        clientes = new VCatalogoClientes(this);
        productos = new VCatalogoProductos(this);
        empleados = new VMantListaEmple(this);
        inventario = new VInventario(this);
        //
        setObservers();
    }
    
    private void setObservers() {
        modelo.agregar(productos);
        modelo.agregar(inventario);
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
    
    public Cliente createCLiente(String cedula, String nombre, String telefono, String email, int descuento) {
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
    
    public void mostrarClientes() {
        clientes.show();
    }
    
    public void mostrarVentas() {
        ventas.show();
    }
    
    public void mostrarVEmpleados() {
        empleados.show();
    }
    
    public void mostrarProductos() {
        productos.show();
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
    public LinkedList<Producto> getAllProductos() {
        return modelo.getAllProductos();
    }
    public void modificarProducto(String cod, String des, String med, double precio, boolean activo) {
        modelo.updateProducto(cod, des, med, precio, activo);
    }
    
    public void mostrarInventario() {
        inventario.show();
        inventario.setDate(new Date());
    }
    public LinkedList<Inventario>  getAllInventarios() {
        return modelo.getAllInventarios();
    }
    public void addInventario() {
        System.out.println("Missing the add");
    }
    
    
    
    
    
    private Modelo modelo;
    private VentanaLogin login;
    private VentanaMenu menu;
    private VentanaVentas ventas;
    private VCatalogoClientes clientes;
    private VCatalogoProductos productos;
    private VMantListaEmple empleados;
    private VInventario inventario;
}
