package com.systematic.app.biblioteca.models;

public class EstadisticasDTO {
    private int totalLibros;
    private int librosDisponibles;
    private int librosPrestados;
    private int totalUsuarios;
    private int prestamosActivos;
    private int prestamosVencidos;

    private int totalCategorias;
    private int totalAutores;
    private int totalEditoriales;
    private int totalCargos;

    public EstadisticasDTO() {}

    // Getters y Setters
    public int getTotalLibros() { return totalLibros; }
    public void setTotalLibros(int totalLibros) { this.totalLibros = totalLibros; }

    public int getLibrosDisponibles() { return librosDisponibles; }
    public void setLibrosDisponibles(int librosDisponibles) { this.librosDisponibles = librosDisponibles; }

    public int getLibrosPrestados() { return librosPrestados; }
    public void setLibrosPrestados(int librosPrestados) { this.librosPrestados = librosPrestados; }

    public int getTotalUsuarios() { return totalUsuarios; }
    public void setTotalUsuarios(int totalUsuarios) { this.totalUsuarios = totalUsuarios; }

    public int getPrestamosActivos() { return prestamosActivos; }
    public void setPrestamosActivos(int prestamosActivos) { this.prestamosActivos = prestamosActivos; }

    public int getPrestamosVencidos() { return prestamosVencidos; }
    public void setPrestamosVencidos(int prestamosVencidos) { this.prestamosVencidos = prestamosVencidos; }

    public int getTotalCategorias() { return totalCategorias; }
    public void setTotalCategorias(int totalCategorias) { this.totalCategorias = totalCategorias; }

    public int getTotalAutores() { return totalAutores; }
    public void setTotalAutores(int totalAutores) { this.totalAutores = totalAutores; }

    public int getTotalEditoriales() { return totalEditoriales; }
    public void setTotalEditoriales(int totalEditoriales) { this.totalEditoriales = totalEditoriales; }

    public int getTotalCargos() { return totalCargos; }
    public void setTotalCargos(int totalCargos) { this.totalCargos = totalCargos; }
}
