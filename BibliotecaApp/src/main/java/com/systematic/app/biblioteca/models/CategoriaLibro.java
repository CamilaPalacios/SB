
package com.systematic.app.biblioteca.models;

/**
 *
 * @author anthony
 */
public class CategoriaLibro {
    
    private Integer idCategoria;
    private String nombreCategoria;

    public CategoriaLibro() {
    }

    public CategoriaLibro(Integer idCategoria, String nombreCategoria) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CategoriaLibro{");
        sb.append("idCategoria=").append(idCategoria);
        sb.append(", nombreCategoria=").append(nombreCategoria);
        sb.append('}');
        return sb.toString();
    }
    
}
