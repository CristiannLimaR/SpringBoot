package com.cristianlima.webapp.biblioteca.service;

import java.util.List;

import com.cristianlima.webapp.biblioteca.model.Prestamo;

public interface IPrestamoService {
    public List<Prestamo> listarPrestamos();

    public Prestamo buscarPrestamoPorId(Long id);

    public Prestamo guardarPrestamo(Prestamo prestamo);

    public void eliminarPrestamo(Prestamo prestamo);
}