/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Applicaction.Service;

import proyecyo_progra_3.Domain.Model.TanqueAgua;
import proyecyo_progra_3.Domain.Ports.Output.IniciarRiegoUseCase;

import proyecyo_progra_3.Domain.Ports.Output.RiegoPort;
import proyecyo_progra_3.Domain.Service.SistemaRiegoService;

/**
 *
 * @author Usuario
 */
public class IniciarRiegoInteractor implements IniciarRiegoUseCase {

    private final SistemaRiegoService service;
    private final RiegoPort riegoPort;
    private final TanqueAgua tanque;

    public IniciarRiegoInteractor(SistemaRiegoService service,
                                  RiegoPort riegoPort,
                                  TanqueAgua tanque) {
        this.service = service;
        this.riegoPort = riegoPort;
        this.tanque = tanque;
    }

    public String ejecutar() {

        if (!service.puedeIniciarRiego(tanque)) {
            return "No se puede iniciar riego";
        }



        riegoPort.enviarComando(1);

        service.activarRiego(tanque);

        return "Riego iniciado";
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
