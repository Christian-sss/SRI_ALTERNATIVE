/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Domain.Ports.Output;

import java.util.Optional;
import proyecyo_progra_3.Domain.Model.User;

/**
 *
 * @author Usuario
 */
public interface UserRepository {
    
    
    void save();
    Optional<User> findById(Long id);
    
    
}
