/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.inventarios;

import java.util.Date;
import java.util.Objects;
import modelo.productos.Producto;


/**
 *
 * @author Michael Chen W.
 */
public class Inventario {

    public Inventario() {
        fecha = null;
        producto = null;
        cantidad = 0;
        activo = false;
    }
    
    public Inventario(Date fecha, Producto producto, int cantidad) {
        this.fecha = fecha;
        this.producto = producto;
        this.cantidad = cantidad;
        if(cantidad > 0)
            activo = true;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getFecha() {
        return fecha;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public boolean isActivo() {
        return activo;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Inventario other = (Inventario) obj;
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.producto, other.producto)) {
            return false;
        }
        return true;
    }
    
    Date fecha;
    Producto producto;
    int cantidad;
    boolean activo = false;
}
