/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Interfaz.*;

/**
 *
 * @author Michael Chen W.
 */
public class Control {
    
    public Control() {        
        login = new VentanaLogin(this);
    }
    
    public void mostrarLogin() {
        login.show();
    }
    public void login(String username, String password) {
        
    }
    
    
    VentanaLogin login;
}
