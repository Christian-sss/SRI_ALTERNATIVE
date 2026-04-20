/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Domain.Model;

/**
 *
 * @author Usuario
 */




public class TanqueAgua {
    
    
    
  private int humedadRaw;
    private int humedadPorcentaje;
    private int distanciaCm;
    private String estadoTanque;
    private String estadoRiego;
    private boolean tieneAgua;
    private boolean bombaActiva;
    
    public TanqueAgua() {
        this.estadoTanque = "DESCONOCIDO";
        this.estadoRiego = "DESCONOCIDO";
        this.tieneAgua = false;
        this.bombaActiva = false;
    }
    

    public void setHumedadRaw(int raw) { this.humedadRaw = raw; }
    public void setHumedadPorcentaje(int porcentaje) { 
        this.humedadPorcentaje = porcentaje; 
    }
    public void setDistanciaCm(int distancia) { this.distanciaCm = distancia; }
    
    public void setEstadoTanque(String estado) {
        this.estadoTanque = estado;
        this.tieneAgua = !estado.equals("VACÍO");
    }
    
    public void setEstadoRiego(String estado) {
        this.estadoRiego = estado;
        this.bombaActiva = estado.contains("REGANDO");
    }
    
    public int getHumedadPorcentaje() { return humedadPorcentaje; }
    public int getDistanciaCm() { return distanciaCm; }
    public String getEstadoTanque() { return estadoTanque; }
    public String getEstadoRiego() { return estadoRiego; }
    public boolean tieneAgua() { return tieneAgua; }
    public boolean isBombaActiva() { return bombaActiva; }
    
    
    
    @Override
    public String toString() {
        return String.format("Tanque: %s | Humedad: %d%% | %s", 
            estadoTanque, humedadPorcentaje, estadoRiego);
    }
    
}
