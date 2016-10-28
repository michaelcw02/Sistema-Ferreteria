/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.personas.clientes;

/**
 *
 * @author Michael Chen W.
 */
public class Cliente {

    public Cliente(String cedula, String nombre, String telefono, String email, int descuento) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.descuento = descuento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public int getDescuento() {
        return descuento;
    }
    
    
    
    String cedula;
    String nombre;
    String telefono;
    String email;
    int descuento;
}
