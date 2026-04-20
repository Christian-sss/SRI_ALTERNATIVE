/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Domain.Service;

import proyecyo_progra_3.Domain.Model.TanqueAgua;

/**
 *
 * @author Usuario
 */
public class SeguridadHidrica {
    
    
    private static final Double NIVEL_MINIMO_CM = 10.0;
    
    
    public boolean puedeRegar(TanqueAgua tanque) {
        
        
        if(tanque != null && tanque.tieneAgua()) {
            return true;
        }

        
        return false;
    }
    
    
    
    public boolean esMomentoCritico(TanqueAgua tanque) {
        return tanque != null && !tanque.tieneAgua();
    }
    
        
    
    
    
}
