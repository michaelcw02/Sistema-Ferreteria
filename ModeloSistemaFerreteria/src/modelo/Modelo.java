/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Michael Chen W.
 */
public class Modelo {
    
    private Modelo() {
        
    }
    static public Modelo getInstance() {
        if(instance == null)
            instance = new Modelo();
        return instance;
    }
    
    
    
    public static Modelo instance;
}
