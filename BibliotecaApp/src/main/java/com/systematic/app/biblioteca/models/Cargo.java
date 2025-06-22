
package com.systematic.app.biblioteca.models;

/**
 *
 * @author anthony
 */
public class Cargo {
    
    private Integer idCargo;
    private String nombreCargo;

    public Cargo() {
    }

    public Cargo(Integer id, String nombreCargo) {
        this.idCargo = id;
        this.nombreCargo = nombreCargo;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
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
        sb.append("idCargo=").append(idCargo);
        sb.append(", nombreCargo=").append(nombreCargo);
        sb.append('}');
        return sb.toString();
    }
    
}
