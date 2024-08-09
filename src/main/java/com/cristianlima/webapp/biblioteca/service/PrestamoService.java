package com.cristianlima.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianlima.webapp.biblioteca.model.Libro;
import com.cristianlima.webapp.biblioteca.model.Prestamo;
import com.cristianlima.webapp.biblioteca.repository.PrestamoRepository;
import com.cristianlima.webapp.biblioteca.util.MethodType;

@Service
public class PrestamoService implements IPrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    public Prestamo buscarPrestamoPorId(Long id) {
        return prestamoRepository.findById(id).orElse(null);
    }

    @Override
    public Integer guardarPrestamo(Prestamo prestamo, MethodType methodType) {
        if (methodType.equals(MethodType.POST)) {
            if (!verificarCliente(prestamo)) {
                if (verificarLibro(prestamo)) {
                    prestamoRepository.save(prestamo);
                    return 1;
                } else {
                    return 3;
                }

            } else {
                return 2;
            }
        } else if (methodType.equals(MethodType.PUT)) {
            prestamoRepository.save(prestamo);
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public void eliminarPrestamo(Prestamo prestamo) {
        prestamoRepository.delete(prestamo);
    }

    @Override
    public Boolean verificarCliente(Prestamo newPrestamo) {
        List<Prestamo> prestamos = listarPrestamos();
        Boolean flag = false;
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getCliente().getDpi().equals(newPrestamo.getCliente().getDpi())) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public Boolean verificarLibro(Prestamo newPrestamo) {
        if (newPrestamo == null || newPrestamo.getLibros() == null) {
            return false;
        }

        Boolean flag = true;
        List<Libro> libros = newPrestamo.getLibros();
        for (Libro libro : libros) {
            if (libro == null || libro.getDisponibilidad() == null ||  !libro.getDisponibilidad()) {
                flag = false;
                break;
            }
        }
        return flag;
    }

}
