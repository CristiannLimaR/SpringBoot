package com.cristianlima.webapp.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianlima.webapp.biblioteca.model.Libro;
import com.cristianlima.webapp.biblioteca.model.Prestamo;
import com.cristianlima.webapp.biblioteca.repository.PrestamoRepository;
import com.cristianlima.webapp.biblioteca.util.LibreriaAlert;
import com.cristianlima.webapp.biblioteca.util.MethodType;

import javafx.scene.control.ButtonType;

@Service
public class PrestamoService implements IPrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private LibroService libroService;

    @Autowired
    private LibreriaAlert libreriaAlert; // Inyección del bean LibreriaAlert

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
        if (methodType == MethodType.POST) {
            if (!verificarCliente(prestamo)) {
                if (verificarLibro(prestamo, null)) {
                    if (verificarCantidad(prestamo)) {
                        for (Libro libro : prestamo.getLibros()) {
                            Libro libroCompleto = libroService.buscarLibroPorId(libro.getId());
                            libroService.actualizarDisponibilidad(libroCompleto, false);
                        }
                        prestamoRepository.save(prestamo);
                        libreriaAlert.mostrarAlertaInfo(401);
                        return 1;
                    } else {
                        return 4;
                    }
                } else {
                    libreriaAlert.mostrarAlertaInfo(402);
                    return 3;
                }

            } else {
                libreriaAlert.mostrarAlertaInfo(403);
                return 2;
            }
        } else if (methodType == MethodType.PUT) {

            if (verificarCantidad(prestamo)) {
                if (libreriaAlert.mostrarAlertaConfirmacion(106).get() == ButtonType.OK) {
                    libreriaAlert.mostrarAlertaInfo(401);
                    if (!prestamo.getVigencia()) {
                        cambiarDisponibilidadLibro(prestamo.getLibros(), true);
                    }
                    prestamoRepository.save(prestamo);
                    return 1;
                }

            } else {
                return 3;
            }
        } else {
            libreriaAlert.mostrarAlertaInfo(402);
            return 0;
        }
        return null;

    }

    @Override
    public void librosRegresados(Prestamo prestamo, Prestamo newPrestamo) {
        List<Libro> librosRegresados = new ArrayList<>();
        for (Libro libro : prestamo.getLibros()) {
            Libro libroCompleto = libroService.buscarLibroPorId(libro.getId());
            if (!newPrestamo.getLibros().contains(libroCompleto)) {
                librosRegresados.add(libroCompleto);
            }
        }
        cambiarDisponibilidadLibro(librosRegresados, true);
        cambiarDisponibilidadLibro(newPrestamo.getLibros(), false);
    }

    @Override
    public void cambiarDisponibilidadLibro(List<Libro> libros, Boolean disponibilidad) {
        for (Libro libro : libros) {
            Libro libroCompleto = libroService.buscarLibroPorId(libro.getId());
            libroService.actualizarDisponibilidad(libroCompleto, disponibilidad);
        }
    }

    @Override
    public void eliminarPrestamo(Prestamo prestamo) {
        try {
            if(libreriaAlert.mostrarAlertaConfirmacion(405).get() == ButtonType.OK){
                for (Libro libro : prestamo.getLibros()) {
                    Libro libroCompleto = libroService.buscarLibroPorId(libro.getId());
                    libroService.actualizarDisponibilidad(libroCompleto, true);
                }
        
                prestamoRepository.delete(prestamo);
            }
        } catch (Exception e) {
            libreriaAlert.mostrarAlertaInfo(404);
        }
        
        
    }

    @Override
    public Boolean verificarCliente(Prestamo newPrestamo) {
        List<Prestamo> prestamos = listarPrestamos();
        Boolean tienePrestamoVigente = false;

        for (Prestamo prestamo : prestamos) {
            if (prestamo.getCliente().getDpi().equals(newPrestamo.getCliente().getDpi()) && prestamo.getVigencia()) {
                tienePrestamoVigente = true;
                break;
            }
        }

        return tienePrestamoVigente;
    }

    @Override
    public Boolean verificarLibro(Prestamo newPrestamo, Prestamo prestamo) {
        Boolean flag = true;
        List<Libro> libros = new ArrayList<>();
        if (prestamo != null) {
            for (Libro libro : newPrestamo.getLibros()) {
                Libro libroCompleto = libroService.buscarLibroPorId(libro.getId());
                if (!prestamo.getLibros().contains(libro)) {
                    if (!libroCompleto.getDisponibilidad()) {
                        flag = false;
                        break;
                    }
                }
            }

        } else {
            for (Libro libro : newPrestamo.getLibros()) {
                Libro libroCompleto = libroService.buscarLibroPorId(libro.getId());
                libros.add(libroCompleto);
            }

            for (Libro libro : libros) {
                if (!libro.getDisponibilidad()) {
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }

    @Override
    public Boolean verificarCantidad(Prestamo newPrestamo) {
        List<Libro> libros = new ArrayList<>();

        for (Libro libro : newPrestamo.getLibros()) {
            Libro libroCompleto = libroService.buscarLibroPorId(libro.getId());
            libros.add(libroCompleto);
        }
        return libros.size() <= 3;
    }
}