
package com.systematic.app.biblioteca.models;

/**
 *
 * @author anthony
 */
public class Editorial {
    
    private Integer idEditorial;
    private String nombreEditorial;

    public Editorial() {
    }

    public Editorial(Integer idEditorial, String nombreEditorial) {
        this.idEditorial = idEditorial;
        this.nombreEditorial = nombreEditorial;
    }

    public Integer getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(Integer idEditorial) {
        this.idEditorial = idEditorial;
    }

    public String getNombreEditorial() {
        return nombreEditorial;
    }

    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Editorial{");
        sb.append("idEditorial=").append(idEditorial);
        sb.append(", nombreEditorial=").append(nombreEditorial);
        sb.append('}');
        return sb.toString();
    }
    
}
