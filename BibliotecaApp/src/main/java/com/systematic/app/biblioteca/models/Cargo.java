/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.systematic.app.biblioteca.models;

/**
 *
 * @author anthony
 */
public class Cargo {
    private Integer id;
    private String nombreCargo;

    public Cargo() {
    }

    public Cargo(Integer id, String nombreCargo) {
        this.id = id;
        this.nombreCargo = nombreCargo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cargo{");
        sb.append("id=").append(id);
        sb.append(", nombreCargo=").append(nombreCargo);
        sb.append('}');
        return sb.toString();
    }
    
    
}
