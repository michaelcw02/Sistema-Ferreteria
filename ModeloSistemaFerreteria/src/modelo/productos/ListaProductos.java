/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.productos;

import java.util.LinkedList;

/**
 *
 * @author Michael Chen W.
 */
public class ListaProductos {

    public ListaProductos() {
        productos = new LinkedList<>();
    }
    
    public void agregar(Producto item) {
        productos.add(item);
    }
    public boolean remover(String codigo) {
        for (Producto item : productos) {
            if (item.getCodigo().equals(codigo)) {
                item.setActivo(false);
                return true;
            }
        }
        return false;
    }
    public Producto buscarCod(String cod) {
        for(Producto pro : productos) {
            if(pro.getCodigo().equalsIgnoreCase(cod))
                return pro;
        }
        return null;
    }
    public Producto buscarDesc(String desc) {
        for(Producto pro : productos)
            if(pro.getDescripcion().indexOf(desc) != -1)
                return pro;
        return null;
    }
    
    LinkedList<Producto> productos;
}
