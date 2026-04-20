/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Applicaction.Service;

import proyecyo_progra_3.Domain.Ports.Input.IniciarRiegoUseCase;
import proyecyo_progra_3.Domain.Ports.Input.SensorPort;
import proyecyo_progra_3.Domain.Service.SeguridadHidrica;

import proyecyo_progra_3.Domain.Ports.Input.RiegoPortUseCase;

/**
 *
 * @author Usuario
 */
public class IniciarRiegoInteractor implements IniciarRiegoUseCase {
    
    private final RiegoPortUseCase port;
    private final SeguridadHidrica seguridadHidrica;
    private final SensorPort sensor;
    
    
    
    public IniciarRiegoInteractor(RiegoPortUseCase port, SeguridadHidrica sh,SensorPort sensor) {
        this.port = port;
        this.seguridadHidrica = sh;
        this.sensor = sensor;
        
    }

    @Override
    public void ejecutar() {
        
        var nivel = sensor.obtenerNivelTanque();
        
 
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
