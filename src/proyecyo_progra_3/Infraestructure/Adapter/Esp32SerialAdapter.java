/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Infraestructure.Adapter;

import proyecyo_progra_3.Domain.Ports.Input.SensorPort;


/**
 *
 * @author Usuario
 */


import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.util.function.Consumer;

import proyecyo_progra_3.Domain.Model.TanqueAgua;



public class Esp32SerialAdapter  {

    
    private SerialPort puertoActivo;
    private TanqueAgua tanque;
    private Consumer<TanqueAgua> onDataReceived;
    private StringBuilder bufferRecepcion = new StringBuilder();
    
    
    // Variables temporales para agrupar las 4 líneas de datos
    private int tempHumedadRaw = 0;
    private int tempHumedadPorcentaje = 0;
    private int tempDistancia = 0;
    private String tempEstadoTanque = "";
    private String tempEstadoRiego = "";
    private int contadorLineas = 0; // Para saber cuándo tenemos todos los datos
    
    public Esp32SerialAdapter(TanqueAgua tanque) {
        this.tanque = tanque;
    }
    
    
    public void usarPuerto(SerialPort puerto) {
        this.puertoActivo = puerto;
        configurarListener();
        System.out.println("[JAVA] Receptor configurado en puerto compartido");
    }
    
    public boolean conectar(String nombrePuerto) {
        puertoActivo = SerialPort.getCommPort(nombrePuerto);
        puertoActivo.setBaudRate(115200);
        
        boolean abierto = puertoActivo.openPort();
        if (abierto) {
            configurarListener();
            System.out.println("JAVA Conectado a " + nombrePuerto);
        }
        return abierto;
    }
    
    private void configurarListener() {
        puertoActivo.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() { 
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; 
            }
            
            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) 
                    return;
                    
                byte[] newData = new byte[puertoActivo.bytesAvailable()];
                puertoActivo.readBytes(newData, newData.length);
                String datos = new String(newData);
                
                procesarLineas(datos);
            }
        });
    }
    
    private void procesarLineas(String nuevosDatos) {
        bufferRecepcion.append(nuevosDatos);
        String contenido = bufferRecepcion.toString();
        
        while (contenido.contains("\n")) {
            int finLinea = contenido.indexOf("\n");
            String linea = contenido.substring(0, finLinea).trim();
            contenido = contenido.substring(finLinea + 1);
            
            if (!linea.isEmpty()) {
                parsearLinea(linea);
            }
        }
        bufferRecepcion = new StringBuilder(contenido);
    }
    
    private void parsearLinea(String linea) {
        // Línea 1: "Sensor Raw: 1850 | Humedad: 45%"
        if (linea.startsWith("Sensor Raw:")) {
            try {
                // Extraer valor raw
                int idxRaw = linea.indexOf("Sensor Raw:") + 12;
                int idxSeparador = linea.indexOf("|");
                String rawStr = linea.substring(idxRaw, idxSeparador).trim();
                tempHumedadRaw = Integer.parseInt(rawStr);
                tanque.setHumedadRaw(tempHumedadRaw);
                
                // Extraer porcentaje
                int idxHum = linea.indexOf("Humedad:") + 8;
                int idxPorcentaje = linea.indexOf("%");
                String humStr = linea.substring(idxHum, idxPorcentaje).trim();
                tempHumedadPorcentaje = Integer.parseInt(humStr);
                tanque.setHumedadPorcentaje(tempHumedadPorcentaje);
                
                contadorLineas++;
            } catch (Exception e) {
                System.err.println("[ERROR] Parseando humedad: " + linea);
            }
        }
        // Línea 2: "% | DISTANCIA TANQUE: 12 cm"
        else if (linea.contains("DISTANCIA TANQUE:")) {
            try {
                int idxDist = linea.indexOf("DISTANCIA TANQUE:") + 18;
                int idxCm = linea.indexOf("cm");
                String distStr = linea.substring(idxDist, idxCm).trim();
                tempDistancia = Integer.parseInt(distStr);
                tanque.setDistanciaCm(tempDistancia);
                
                contadorLineas++;
            } catch (Exception e) {
                System.err.println("[ERROR] Parseando distancia: " + linea);
            }
        }
        // Línea 3: "LLENO", "MEDIO" o "VACÍO"
        else if (linea.equals("LLENO") || linea.equals("MEDIO") || linea.equals("VACÍO")) {
            tempEstadoTanque = linea;
            tanque.setEstadoTanque(tempEstadoTanque);
            contadorLineas++;
        }
        // Línea 4: "REGANDO", "BLOQUEO (SIN AGUA)" o "ESTADO: ESPERA (HUMEDAD OK)"
        else if (linea.startsWith("REGANDO") || 
                 linea.startsWith("BLOQUEO") || 
                 linea.startsWith("ESTADO:")) {
            tempEstadoRiego = linea;
            tanque.setEstadoRiego(tempEstadoRiego);
            contadorLineas++;
        }
        // Mensaje de inicio
        else if (linea.contains("SISTEMA INTEGRADO INICIADO")) {
            System.out.println("[ESP32] ✅ " + linea);
        }
        
        // Cuando tenemos las 4 líneas, notificamos a la GUI
        if (contadorLineas >= 4) {
            if (onDataReceived != null) {
                onDataReceived.accept(tanque);
            }
            
            // Mostrar en consola
            System.out.println("[ESP32] " + tanque.toString());
            
            contadorLineas = 0; // Reiniciar para el siguiente ciclo
        }
    }
    
    // Enviar comandos (si decides controlar manualmente)
    public void enviarComando(String comando) {
        if (puertoActivo != null && puertoActivo.isOpen()) {
            String envio = comando + "\n";
            byte[] buffer = envio.getBytes();
            puertoActivo.writeBytes(buffer, buffer.length);
            System.out.println("[JAVA] Comando enviado: " + comando);
        }
    }
    
    public void setOnDataReceived(Consumer<TanqueAgua> callback) {
        this.onDataReceived = callback;
    }
    
    public void desconectar() {
        if (puertoActivo != null) {
            puertoActivo.closePort();
            System.out.println("[JAVA] Puerto desconectado");
        }
    }


}
