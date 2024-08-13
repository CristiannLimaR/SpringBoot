package com.cristianlima.webapp.biblioteca.service;

import java.util.List;

import com.cristianlima.webapp.biblioteca.model.Prestamo;
import com.cristianlima.webapp.biblioteca.util.MethodType;

public interface IPrestamoService {
    public List<Prestamo> listarPrestamos();

    public Prestamo buscarPrestamoPorId(Long id);

    public Integer guardarPrestamo(Prestamo prestamo, MethodType methodType);

    public void eliminarPrestamo(Prestamo prestamo);

    public Boolean verificarCliente(Prestamo newPrestamo);

    public Boolean verificarLibro(Prestamo newPrestamo, Prestamo prestamo);

    public Boolean verificarCantidad(Prestamo newPrestamo);

    

}
