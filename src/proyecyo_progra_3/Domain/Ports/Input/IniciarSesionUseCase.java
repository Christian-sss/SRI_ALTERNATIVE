/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyecyo_progra_3.Domain.Ports.Input;

import proyecyo_progra_3.Domain.Model.User;

/**
 *
 * @author Usuario
 */
public interface IniciarSesionUseCase {
    
       User ejecutar(String username, String passwordIngresada);
}
