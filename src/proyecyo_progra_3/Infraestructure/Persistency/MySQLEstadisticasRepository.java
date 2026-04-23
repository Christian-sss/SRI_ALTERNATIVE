/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Infraestructure.Persistency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import proyecyo_progra_3.Domain.Ports.Output.EstadisticasRepository;



/**
 *
 * @author Usuario
 */
public class MySQLEstadisticasRepository implements EstadisticasRepository {


    private final Connection conn;


    public MySQLEstadisticasRepository(Connection conexion) {
        this.conn = conexion;
    }

    private static final String OBTENER_HUMEDAD_PROMEDIO = """
             SELECT DATE_FORMAT(fecha_hora, '%H:00') as hora, AVG(humedad_suelo) as promedio
             FROM telemetria_sensores 
             WHERE DATE(fecha_hora) = CURDATE()
             GROUP BY HOUR(fecha_hora)
             ORDER BY fecha_hora ASC                          
    """;
    
    private static final String OBTENER_CONTEO = """
               SELECT modo_riego, COUNT(*) as cantidad  
               FROM sesiones_riego
               GROUP BY modo_riego                                 
    """;

    private static final String GUARDAR_DATOS = """
            INSERT INTO telemetria_sensores (humedad_suelo, distancia_agua_cm) VALUES
             (?, ?)";
            
            """;

    private static final String REGISTRAR_DATOS = """
            INSERT INTO sesiones_riego (modo_riego, humedad_inicial, humedad_final, motivo_parada) VALUES
             (?, ?, ?, ?);
            
            """;



    @Override
    public void guardarLecturaSensores(int humedad, int distancia) {


        try (var stmt = conn.prepareStatement(GUARDAR_DATOS)) {
            stmt.setInt(1, humedad);
            stmt.setInt(2, distancia);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al guardar telemetría: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void registrarSesionRiego(String modo, int humedadInicial, int humedadFinal, String motivo) {


        try (var stmt = conn.prepareStatement(REGISTRAR_DATOS)) {
            stmt.setString(1, modo);
            stmt.setInt(2, humedadInicial);
            stmt.setInt(3, humedadFinal);
            stmt.setString(4, motivo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al guardar sesión de riego: " + e.getMessage());
        }



    }


    @Override
    public Map<String, Double> obtenerHumedadPromedioPorHora() {




        Map<String, Double> datos = new LinkedHashMap<>();

        try (var stmt = conn.prepareStatement(OBTENER_HUMEDAD_PROMEDIO);
             var rs = stmt.executeQuery()) {

            while (rs.next()) {
                datos.put(rs.getString("hora"), rs.getDouble("promedio"));
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar estadísticas: " + e.getMessage());
            e.printStackTrace();
        }

        return datos;
    }

    @Override
    public Map<String, Integer> obtenerConteoModosRiego() {
        Map<String, Integer> datos = new LinkedHashMap<>();

        try (var stmt = conn.prepareStatement(OBTENER_CONTEO);
             var rs = stmt.executeQuery()) {

            while (rs.next()) {

                String modo = rs.getString("modo_riego");
                int cantidad = rs.getInt("cantidad");

                datos.put(modo, cantidad);
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        return datos;

    }
    
    
    
    
    
}
