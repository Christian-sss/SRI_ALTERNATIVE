/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Applicaction.Service;

import proyecyo_progra_3.Domain.Ports.Input.VisualizarEstadisticasUseCase;
import proyecyo_progra_3.Domain.Ports.Output.EstadisticasRepository;

import java.util.Map;

/**
 *
 * @author Usuario
 */
public class VisualizarEstadisticasInteractor implements VisualizarEstadisticasUseCase {

    private final EstadisticasRepository repository;


    public VisualizarEstadisticasInteractor(EstadisticasRepository repository) {
        this.repository = repository;
    }
    @Override
    public Map<String, Double> obtenerDatosHumedadHistorica() {
        return repository.obtenerHumedadPromedioPorHora();
    }

    @Override
    public Map<String, Integer> obtenerDatosModosRiego() {
        return repository.obtenerConteoModosRiego();
    }
}
