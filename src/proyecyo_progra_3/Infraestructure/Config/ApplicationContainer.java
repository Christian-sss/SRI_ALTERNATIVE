/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Infraestructure.Config;

import proyecyo_progra_3.Applicaction.Service.ConectarESP32Interactor;
import proyecyo_progra_3.Applicaction.Service.DetenerRiegoInteractor;
import proyecyo_progra_3.Applicaction.Service.IniciarRiegoInteractor;
import proyecyo_progra_3.Domain.Model.TanqueAgua;
import proyecyo_progra_3.Domain.Ports.Input.DetenerRiegoUseCase;
import proyecyo_progra_3.Domain.Ports.Input.IniciarRiegoUseCase;
import proyecyo_progra_3.Domain.Ports.Output.RiegoPort;
import proyecyo_progra_3.Domain.Service.SeguridadHidricaService;
import proyecyo_progra_3.Domain.Service.SistemaRiegoService;
import proyecyo_progra_3.Infraestructure.Adapter.Esp32SerialAdapter;
import proyecyo_progra_3.Infraestructure.Adapter.Esp32SerialRiego;

/**
 *
 * @author Usuario
 */
public class ApplicationContainer {

    
    private static ApplicationContainer instance;
    
    private final TanqueAgua tanque;
    private final SeguridadHidricaService seguridad;
    private final SistemaRiegoService service;
    private final Esp32ConnectionManager connectionManager;
    private final Esp32SerialAdapter serialAdapter;
    private final RiegoPort riegoPort;



    public static ApplicationContainer getInstance() {
        if (instance == null) {
            instance = new ApplicationContainer();
        }
        return instance;
    }
    private ApplicationContainer() {
        this.tanque  = new TanqueAgua();
        this.seguridad = new SeguridadHidricaService();
        this.connectionManager = new Esp32ConnectionManager();
        this.service = new SistemaRiegoService(seguridad);
        this.serialAdapter = new Esp32SerialAdapter(tanque,seguridad);
        this.riegoPort = new Esp32SerialRiego(connectionManager);
        
    }
    
    public IniciarRiegoUseCase getIniciarRiegoInteractor() {
        return new IniciarRiegoInteractor(service, riegoPort, tanque);
    }

    public ConectarESP32Interactor getConectarESP32Interactor() {
        return new ConectarESP32Interactor(connectionManager,serialAdapter);
    }

    
    public DetenerRiegoUseCase getDetenerRiegoInteractor() {
        return new DetenerRiegoInteractor(riegoPort,tanque,service);
    }
    
    public TanqueAgua getTanque() {
        return tanque;
    }
    
    
    
    
    
}
