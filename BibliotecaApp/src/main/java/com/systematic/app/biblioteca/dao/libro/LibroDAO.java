package com.systematic.app.biblioteca.dao.libro;

import com.systematic.app.biblioteca.dao.CRUD;
import com.systematic.app.biblioteca.models.Libro;
import com.systematic.app.biblioteca.models.LibroPopularDTO;

import java.util.List;

public interface LibroDAO extends CRUD<Libro, Integer> {

    List<Libro> buscarPorTitulo(String titulo);

    List<Libro> obtenerLibrosDisponibles();

    boolean reducirStock(Integer idLibro);

    boolean aumentarStock(Integer idLibro);

    int contar();               // Total de libros

    int contarDisponibles();    // Libros con cantidad > 0

    int contarPrestados();      // Libros con préstamos activos

    List<LibroPopularDTO> obtenerLibrosMasPrestados(int limit); // Dashboard
    
  
}
