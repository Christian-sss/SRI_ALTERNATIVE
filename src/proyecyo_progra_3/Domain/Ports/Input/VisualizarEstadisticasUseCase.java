package proyecyo_progra_3.Domain.Ports.Input;

import java.util.Map;

public interface VisualizarEstadisticasUseCase {

    Map<String, Double> obtenerDatosHumedadHistorica();
    Map<String, Integer> obtenerDatosModosRiego();

}
