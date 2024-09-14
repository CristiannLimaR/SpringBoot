package com.cristianlima.webapp.biblioteca.service;

import java.util.List;

import com.cristianlima.webapp.biblioteca.model.Libro;
import com.cristianlima.webapp.biblioteca.util.MethodType;

public interface ILibroService {

    public List<Libro> listarLibros();

    public Libro guardarLibro(Libro libro, MethodType methodType);

    public Libro buscarLibroPorId(Long id);

    public void eliminarLibro(Libro libro);

    public void actualizarDisponibilidad(Libro libro, Boolean disponibilidad);
    
}
