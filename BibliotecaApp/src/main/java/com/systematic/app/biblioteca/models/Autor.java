
package com.systematic.app.biblioteca.models;

/**
 *
 * @author anthony
 */
public class Autor {
    
    private Integer idAutor;
    private String nombreAutor;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public Autor() {
    }

    public Autor(Integer idAutor, String nombreAutor, String apellidoPaterno, String apellidoMaterno) {
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Autor{");
        sb.append("idAutor=").append(idAutor);
        sb.append(", nombreAutor=").append(nombreAutor);
        sb.append(", apellidoPaterno=").append(apellidoPaterno);
        sb.append(", apellidoMaterno=").append(apellidoMaterno);
        sb.append('}');
        return sb.toString();
    }
    
}
