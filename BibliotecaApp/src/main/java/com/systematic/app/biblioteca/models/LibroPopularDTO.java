package com.systematic.app.biblioteca.models;

/**
 * DTO para representar libros más prestados en el sistema.
 */
public class LibroPopularDTO {
    private int id;
    private String titulo;
    private String autor;
    private int totalPrestamos;

    public LibroPopularDTO() {
    }

    public LibroPopularDTO(int id, String titulo, String autor, int totalPrestamos) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.totalPrestamos = totalPrestamos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getTotalPrestamos() {
        return totalPrestamos;
    }

    public void setTotalPrestamos(int totalPrestamos) {
        this.totalPrestamos = totalPrestamos;
    }

    @Override
    public String toString() {
        return "LibroPopularDTO{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", totalPrestamos=" + totalPrestamos +
                '}';
    }
}
