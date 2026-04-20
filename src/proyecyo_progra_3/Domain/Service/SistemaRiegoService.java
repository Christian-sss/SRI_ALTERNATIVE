/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Domain.Service;

import com.fazecast.jSerialComm.SerialPort;
import proyecyo_progra_3.Domain.Model.TanqueAgua;
import proyecyo_progra_3.Infraestructure.Adapter.Esp32SerialAdapter;
import proyecyo_progra_3.Infraestructure.Adapter.Esp32SerialRiego;

/**
 *
 * @author Usuario
 */
public class SistemaRiegoService {
    
    
    
    
    private TanqueAgua tanque;
    private SerialPort puerto;
    private Esp32SerialAdapter receptor;
    private Esp32SerialRiego emisor;
    
    public SistemaRiegoService() {
        this.tanque = new TanqueAgua();
    }
    
    // =============================================
    // CONEXIÓN AL ESP32
    // =============================================
    public boolean conectarESP32(String nombrePuerto) {
        try {
            puerto = SerialPort.getCommPort(nombrePuerto);
            puerto.setBaudRate(115200);
            
            boolean abierto = puerto.openPort();
            if (abierto) {
                
                // Inicializar ambos adaptadores con el MISMO puerto
                
                
                
                receptor = new Esp32SerialAdapter(tanque);
                receptor.usarPuerto(puerto);
                
                emisor = new Esp32SerialRiego(puerto);
                
                System.out.println("[JAVA]  Conectado a " + nombrePuerto);
            }
            return abierto;
        } catch (Exception e) {
            System.err.println("[JAVA]  Error conectando: " + e.getMessage());
            return false;
        }
    }
    

    public String iniciarRiego() {
        
        
        if (!tanque.tieneAgua()) {
            String error = "NO SE PUEDE REGAR: Tanque vacío";
            System.err.println("[JAVA] " + error);
            return error;
        }
        
        if (emisor == null) {
            String error = "NO SE PUEDE REGAR: No hay conexión con ESP32";
            System.err.println("[JAVA] " + error);
            return error;
        }
        
        emisor.iniciarRiego();
        return "✅ Riego iniciado manualmente";
    }
    
    // =============================================
    // RF-07: DETENER RIEGO MANUAL
    // =============================================
    public String detenerRiego() {
        if (emisor == null) {
            return "❌ No hay conexión con ESP32";
        }
        
        emisor.detenerRiego();
        return "✅ Riego detenido manualmente";
    }
    

    public void setOnDataUpdate(Runnable callback) {
        if (receptor != null) {
            receptor.setOnDataReceived(t -> callback.run());
        }
    }
    

    public TanqueAgua getTanque() {
        return tanque;
    }
    
    public boolean estaConectado() {
        return puerto != null && puerto.isOpen();
    }

    public void desconectar() {
        if (puerto != null && puerto.isOpen()) {
            puerto.closePort();
            System.out.println("[JAVA] Puerto desconectado");
        }
    }
    
    
    
    
    
}
