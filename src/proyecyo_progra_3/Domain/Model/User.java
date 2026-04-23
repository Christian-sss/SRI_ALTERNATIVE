/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Domain.Model;

/**
 *
 * @author Usuario
 */
public class User {
    private int id;
    private String username;
    private String passwordHash;
    private String rol;
    private String estado;


    public User() {
    }

    // Constructor completo
    public User(int id, String username, String passwordHash, String rol, String estado) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.estado = estado;
    }

    // --- GETTERS Y SETTERS ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    

    public boolean isActivo() {
        return "ACTIVO".equalsIgnoreCase(this.estado);
    }
}
