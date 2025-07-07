package com.systematic.app.biblioteca.models;

public class Libro {
    private int idLibro;
    private String titulo;
    private int anioPublicacion;
    private String isbn;
    private int cantidad;
    private Autor autor;
    private CategoriaLibro categoria;
    private Editorial editorial;

    // Constructores
    public Libro() {}

    public Libro(int idLibro, String titulo, int anioPublicacion, String isbn, int cantidad) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.isbn = isbn;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getIdLibro() { return idLibro; }
    public void setIdLibro(int idLibro) { this.idLibro = idLibro; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getAnioPublicacion() { return anioPublicacion; }
    public void setAnioPublicacion(int anioPublicacion) { this.anioPublicacion = anioPublicacion; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }

    public CategoriaLibro getCategoria() { return categoria; }
    public void setCategoria(CategoriaLibro categoria) { this.categoria = categoria; }

    public Editorial getEditorial() { return editorial; }
    public void setEditorial(Editorial editorial) { this.editorial = editorial; }
}