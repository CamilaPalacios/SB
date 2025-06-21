
package com.systematic.app.biblioteca.models;

/**
 *
 * @author anthony
 */

public class Usuario {
    
    private Integer idUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefonoCelular;
    private String email;
    private String nickNAme;
    private String password;
    private Cargo cargo;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, String telefonoCelular, String email, String nickNAme, String password, Cargo cargo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefonoCelular = telefonoCelular;
        this.email = email;
        this.nickNAme = nickNAme;
        this.password = password;
        this.cargo = cargo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickNAme() {
        return nickNAme;
    }

    public void setNickNAme(String nickNAme) {
        this.nickNAme = nickNAme;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{");
        sb.append("idUsuario=").append(idUsuario);
        sb.append(", nombre=").append(nombre);
        sb.append(", apellidoPaterno=").append(apellidoPaterno);
        sb.append(", apellidoMaterno=").append(apellidoMaterno);
        sb.append(", telefonoCelular=").append(telefonoCelular);
        sb.append(", email=").append(email);
        sb.append(", nickNAme=").append(nickNAme);
        sb.append(", password=").append(password);
        sb.append(", cargo=").append(cargo);
        sb.append('}');
        return sb.toString();
    }
    
}
