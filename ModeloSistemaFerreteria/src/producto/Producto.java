/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producto;

/**
 *
 * @author Michael Chen W.
 */
public class Producto {
    
    public Producto(String codigo, String descripcion, String unidadMedida, double precio, boolean activo) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.unidadMedida = unidadMedida;
        this.precio = precio;
        this.activo = activo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    

    public String getCodigo() {
        return codigo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getUnidadMedida() {
        return unidadMedida;
    }
    public double getPrecio() {
        return precio;
    }
    public boolean isActivo() {
        return activo;
    }
    
    String codigo;
    String descripcion;
    String unidadMedida;
    double precio;
    boolean activo;
}
