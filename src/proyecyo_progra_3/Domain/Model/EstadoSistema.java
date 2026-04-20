/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Domain.Model;

import java.time.LocalDateTime;

/**
 *
 * @author Usuario
 */
public class EstadoSistema {
    private int humedadRaw;
    private int humedadPorcentaje;
    private TanqueAgua tanque;
    private String estadoRiego;
    private boolean bombaActiva;
    private LocalDateTime ultimaActualizacion;
    
    public EstadoSistema() {
        this.humedadRaw = 0;
        this.humedadPorcentaje = 0;
        this.tanque = new TanqueAgua();
        this.estadoRiego = "DESCONOCIDO";
        this.bombaActiva = false;
        this.ultimaActualizacion = LocalDateTime.now();
    }
    
    // ✅ MÉTODO COMPATIBLE CON TU ESP32 ACTUAL
    public void actualizarDesdeESP32(int humedadRaw, int humedadPorcentaje, 
                                      int distancia, String estadoTanque, 
                                      String estadoRiego) {
        this.humedadRaw = humedadRaw;
        this.humedadPorcentaje = humedadPorcentaje;
        this.tanque.setDistanciaCm(distancia);
        this.tanque.setEstadoTanque(estadoTanque);
        this.estadoRiego = estadoRiego;
        this.bombaActiva = estadoRiego.contains("REGANDO");
        this.ultimaActualizacion = LocalDateTime.now();
    }
    
    // Getters
    public int getHumedadRaw() { return humedadRaw; }
    public int getHumedadPorcentaje() { return humedadPorcentaje; }
    public TanqueAgua getTanque() { return tanque; }
    public String getEstadoRiego() { return estadoRiego; }
    public boolean isBombaActiva() { return bombaActiva; }
    public LocalDateTime getUltimaActualizacion() { return ultimaActualizacion; }
    
    public void setBombaActiva(boolean activa) {
        this.bombaActiva = activa;
    }
    
    @Override
    public String toString() {
        return String.format("Humedad: %d%% | Tanque: %s | %s", 
            humedadPorcentaje, tanque.getEstadoTanque(), estadoRiego);
    }
}