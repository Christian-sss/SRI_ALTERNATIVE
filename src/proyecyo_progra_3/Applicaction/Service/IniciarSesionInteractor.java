/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Applicaction.Service;

import java.util.Optional;
import proyecyo_progra_3.Domain.Model.User;
import proyecyo_progra_3.Domain.Ports.Input.IniciarSesionUseCase;
import proyecyo_progra_3.Domain.Ports.Output.UserRepository;

/**
 *
 * @author Usuario
 */
public class IniciarSesionInteractor implements IniciarSesionUseCase {
    
    private final UserRepository userRepository;

    public IniciarSesionInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    

    @Override
    public User ejecutar(String username, String passwordIngresada) {

        var user = userRepository
                .buscarPorUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

       
        try {
             if (!user.isActivo()) {
                    throw new Exception("Su cuenta está desactivada.");
                }

  
                if (!passwordIngresada.equals(user.getPasswordHash())) {
                    throw new Exception("Contraseña incorrecta.");
                }
             
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
      
                return user;

    }
    

}
