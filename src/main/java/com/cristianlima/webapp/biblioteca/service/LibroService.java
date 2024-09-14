package com.cristianlima.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianlima.webapp.biblioteca.model.Libro;
import com.cristianlima.webapp.biblioteca.repository.LibroRepository;
import com.cristianlima.webapp.biblioteca.util.LibreriaAlert;
import com.cristianlima.webapp.biblioteca.util.MethodType;

import javafx.scene.control.ButtonType;

@Service
public class LibroService implements ILibroService {

    @Autowired
    LibroRepository libroRepository;

    @Autowired
    LibreriaAlert libreriaAlert;

    @Override
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @Override
    public Libro guardarLibro(Libro libro, MethodType methodType) {
        try {
            if (methodType == MethodType.POST) {
                libreriaAlert.mostrarAlertaInfo(401);
                return libroRepository.save(libro);
            } else {
                if (libreriaAlert.mostrarAlertaConfirmacion(106).get() == ButtonType.OK) {
                    libreriaAlert.mostrarAlertaInfo(401);
                    return libroRepository.save(libro);
                }
            }
        } catch (Exception e) {
            libreriaAlert.mostrarAlertaInfo(404);
        }
        return libro;
        

    }

    @Override
    public Libro buscarLibroPorId(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarLibro(Libro libro) {
        try {
            if (libreriaAlert.mostrarAlertaConfirmacion(405).get() == ButtonType.OK) {
                libroRepository.delete(libro);
            }
        } catch (Exception e) {
            libreriaAlert.mostrarAlertaInfo(404);
        }
    }

    @Override
    public void actualizarDisponibilidad(Libro libro, Boolean disponibilidad) {
        libro.setDisponibilidad(disponibilidad);
        libroRepository.save(libro);
    }

}
