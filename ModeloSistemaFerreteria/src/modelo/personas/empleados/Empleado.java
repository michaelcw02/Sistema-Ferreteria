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

    public Empleado(int idEmpleado, String nombre, String apellido, String clave) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.clave = clave;
        activo = true;
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
    
    public int getIdEmpleado() {
        return idEmpleado;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
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
    
    int idEmpleado;
    String nombre;
    String apellido;
    String clave;
    boolean activo;
    boolean vendedor;
    boolean cajero;
    boolean despachador;
    boolean bodeguero;
    boolean administrador;
}
