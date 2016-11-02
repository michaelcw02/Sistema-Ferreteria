/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.personas.empleados;

/**
 *
 * @author Michael Chen W.
 */
public class Empleado {
    
    public Empleado() {
        idEmpleado = "";
        nombre = "";
        clave = "";
        activo = false;
        vendedor = false;
        cajero = false;
        despachador = false;
        bodeguero = false;
        administrador = false;
    }

    public Empleado(String idEmpleado, String nombre, String clave, boolean activo, boolean vendedor, boolean cajero, boolean despachador, boolean bodeguero, boolean administrador) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.clave = clave;
        this.activo = activo;
        this.vendedor = vendedor;
        this.cajero = cajero;
        this.despachador = despachador;
        this.bodeguero = bodeguero;
        this.administrador = administrador;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setClave(String clave) {
        this.clave = clave;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public void setVendedor(boolean vendedor) {
        this.vendedor = vendedor;
    }
    public void setCajero(boolean cajero) {
        this.cajero = cajero;
    }
    public void setDespachador(boolean despachador) {
        this.despachador = despachador;
    }
    public void setBodeguero(boolean bodeguero) {
        this.bodeguero = bodeguero;
    }
    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }
    
    public String getIdEmpleado() {
        return idEmpleado;
    }
    public String getNombre() {
        return nombre;
    }
    public String getClave() {
        return clave;
    }
    public boolean isActivo() {
        return activo;
    }
    public boolean isVendedor() {
        return vendedor;
    }
    public boolean isCajero() {
        return cajero;
    }
    public boolean isDespachador() {
        return despachador;
    }
    public boolean isBodeguero() {
        return bodeguero;
    }
    public boolean isAdministrador() {
        return administrador;
    }
    
    String idEmpleado;
    String nombre;
    String clave;
    boolean activo;
    boolean vendedor;
    boolean cajero;
    boolean despachador;
    boolean bodeguero;
    boolean administrador;
}
