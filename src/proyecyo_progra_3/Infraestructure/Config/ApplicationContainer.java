/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Infraestructure.Config;

import proyecyo_progra_3.Applicaction.Service.*;
import proyecyo_progra_3.Domain.Model.TanqueAgua;
import proyecyo_progra_3.Domain.Ports.Input.DetenerRiegoUseCase;
import proyecyo_progra_3.Domain.Ports.Input.RiegoAutomatizadoUseCase;
import proyecyo_progra_3.Domain.Ports.Input.IniciarRiegoUseCase;
import proyecyo_progra_3.Domain.Ports.Input.VisualizarEstadisticasUseCase;
import proyecyo_progra_3.Domain.Ports.Output.EstadisticasRepository;
import proyecyo_progra_3.Domain.Ports.Output.RiegoPort;
import proyecyo_progra_3.Domain.Ports.Output.UserRepository;
import proyecyo_progra_3.Domain.Service.SeguridadHidricaService;
import proyecyo_progra_3.Domain.Service.SistemaRiegoService;
import proyecyo_progra_3.Infraestructure.Adapter.Esp32SerialAdapter;
import proyecyo_progra_3.Infraestructure.Adapter.Esp32SerialRiego;
import proyecyo_progra_3.Infraestructure.Persistency.MySQLEstadisticasRepository;
import proyecyo_progra_3.Infraestructure.Persistency.MySQLUserRepository;

import java.sql.Connection;
import java.sql.SQLException;
import proyecyo_progra_3.Domain.Ports.Input.IniciarSesionUseCase;

/**
 *
 * @author Usuario
 */
public class ApplicationContainer {

    
    private static ApplicationContainer instance;
    private Connection conn;
    private final TanqueAgua tanque;
    private final SeguridadHidricaService seguridad;
    private final SistemaRiegoService service;
    private final Esp32ConnectionManager connectionManager;
    private final Esp32SerialAdapter serialAdapter;
    private final RiegoPort riegoPort;
    private final RiegoAutomatizadoUseCase riegoAutomatizadoUseCase;
    private final IniciarRiegoUseCase iniciarRiegoUseCase;
    private final DetenerRiegoUseCase detenerRiegoUseCase;
    private final VisualizarEstadisticasUseCase visualizarEstadisticasUseCase;
    private final IniciarSesionUseCase iniciarSesionUseCase;
    
    
    private final EstadisticasRepository estadisticasRepository;
    private final UserRepository userRepository;



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



 
 
        try {
            this.conn = MySQLConfig.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        this.estadisticasRepository = new MySQLEstadisticasRepository(conn);
        this.userRepository = new MySQLUserRepository(conn);
       this.iniciarSesionUseCase = new IniciarSesionInteractor(userRepository);

      this.iniciarRiegoUseCase = new IniciarRiegoInteractor(service,riegoPort,tanque,estadisticasRepository);
        this.detenerRiegoUseCase = new DetenerRiegoInteractor(riegoPort,tanque,service,estadisticasRepository);
        this.riegoAutomatizadoUseCase = new RiegoAutomatizadoInteractor(iniciarRiegoUseCase,detenerRiegoUseCase,tanque, estadisticasRepository);
        this.visualizarEstadisticasUseCase = new VisualizarEstadisticasInteractor(estadisticasRepository);
    }



    public IniciarRiegoUseCase getIniciarRiegoInteractor() {
        return iniciarRiegoUseCase;
    }

    public ConectarESP32Interactor getConectarESP32Interactor() {
        return new ConectarESP32Interactor(connectionManager,serialAdapter);
    }

    
    public DetenerRiegoUseCase getDetenerRiegoInteractor() {
        return detenerRiegoUseCase;
    }

    public RiegoAutomatizadoUseCase getRiegoAutomatizado() {
        return riegoAutomatizadoUseCase;
    }
    
    public VisualizarEstadisticasUseCase getVisualizarEstadisticas() {
        return visualizarEstadisticasUseCase;
    }
    
    public IniciarSesionUseCase getIniciarSesion() {
        return iniciarSesionUseCase;
    }
    
    

}
