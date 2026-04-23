/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Domain.Ports.Output;

import java.util.Map;

/**
 *
 * @author Usuario
 */
public interface EstadisticasRepository {
    
    

    Map<String, Double> obtenerHumedadPromedioPorHora();
    
    // Devuelve un mapa con <Modo de Riego, Cantidad de veces>
    
    
    Map<String, Integer> obtenerConteoModosRiego();

    void guardarLecturaSensores(int humedad, int distancia);

    void registrarSesionRiego(String modo, int humedadInicial, int humedadFinal, String motivo);
}
