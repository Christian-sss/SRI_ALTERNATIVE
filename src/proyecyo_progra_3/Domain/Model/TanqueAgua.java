/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Domain.Model;


import proyecyo_progra_3.Domain.ENUMS.EstadoSistema;

public class TanqueAgua {
    private int humedad;
    private double distancia;
    private boolean bombaActiva;


    private static final int HUMEDAD_MIN = 30;
    private static final double UMBRAL_LLENO = 7.0;
    private static final double UMBRAL_VACIO = 18.0;

    private EstadoSistema estadoActual = EstadoSistema.ESPERA;

    public void actualizarDatos(int humedad, double distancia) {
        this.humedad = humedad;
        this.distancia = distancia;
    }

    public boolean sueloSeco() {
        return humedad < HUMEDAD_MIN;
    }

    public boolean hayAgua() {
        return distancia < UMBRAL_VACIO;
    }

    public boolean estaLleno() {
        return distancia <= UMBRAL_LLENO;
    }

    public String estadoTanque() {
        if (distancia <= UMBRAL_LLENO) {
            return "LLENO";
        } else if (distancia < UMBRAL_VACIO) {
            return "MEDIO";
        } else {
            return "VACIO";
        }
    }

    public boolean debeRegar() {
        return sueloSeco() && hayAgua();
    }

    public EstadoSistema getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(EstadoSistema estadoActual) {
        this.estadoActual = estadoActual;
    }

    public void activarBomba() {
        this.bombaActiva = true;
    }

    public void desactivarBomba() {
        this.bombaActiva = false;
    }

    public boolean isBombaActiva() {
        return bombaActiva;
    }

    public int getHumedad() {
        return humedad;
    }

    public double getDistancia() {
        return distancia;
    }

}
