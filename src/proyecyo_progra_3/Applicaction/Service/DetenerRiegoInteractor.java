/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Applicaction.Service;


import proyecyo_progra_3.Domain.Ports.Input.RiegoPortUseCase;

/**
 *
 * @author Usuario
 */
public class DetenerRiegoInteractor {
    
    
    private final RiegoPortUseCase port;

    
    
    public DetenerRiegoInteractor(RiegoPortUseCase port) {
        this.port = port;
    }
    
    
    
    public void ejecutar() {
        port.detenerRiego();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
