/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Infraestructure.Persistency;

import java.sql.*;
import java.util.Optional;
import proyecyo_progra_3.Domain.Model.User;
import proyecyo_progra_3.Domain.Ports.Output.UserRepository;

/**
 *
 * @author Usuario
 */
public class MySQLUserRepository implements UserRepository {

    private final Connection conn;


    public MySQLUserRepository(Connection conn) {
        this.conn = conn;
    }
    
    
        private static final String BUSCAR_POR_USERNAME = """
            SELECT id, username, password_hash, rol, estado 
            FROM usuarios 
            WHERE username = ?
            """;
  

    @Override
    public Optional<User> buscarPorUsername(String username) {
try (var stmt = conn.prepareStatement(BUSCAR_POR_USERNAME)) {
            
            stmt.setString(1, username);
            
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
  
                    User user = new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password_hash"),
                            rs.getString("rol"),
                            rs.getString("estado")
                    );
                    return Optional.of(user); 
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar usuario: " + e.getMessage());
        }
        
        return Optional.empty();   
    }
    
}
