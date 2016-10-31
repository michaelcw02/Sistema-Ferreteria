/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Interfaz.*;
import modelo.Modelo;
import modelo.personas.empleados.Empleado;

/**
 *
 * @author Michael Chen W.
 */
public class Control {
    
    public Control() {
        modelo = Modelo.getInstance();
        login = new VentanaLogin(this);
        menu  = new VentanaMenu(this);
        //
        setObservers();
    }
    private void setObservers() {
        modelo.agregar(menu);
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
    
    private Modelo modelo;
    private VentanaLogin login;
    private VentanaMenu  menu;

}
