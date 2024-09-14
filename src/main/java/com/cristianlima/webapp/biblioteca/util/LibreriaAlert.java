package com.cristianlima.webapp.biblioteca.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class LibreriaAlert {

    public void mostrarAlertaInfo(int code) {
        Alert alert = null;

        switch (code) {
            case 400:
                // Alerta para cantidad de libros excedida
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Cantidad de libros excedida");
                alert.setHeaderText("Cantidad máxima alcanzada");
                alert.setContentText("No puedes prestar más de 3 libros por préstamo.");
                break;

            case 401:
                // Alerta de confirmación de registro o edición exitosa
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText("Operación realizada con éxito");
                alert.setContentText("El préstamo ha sido registrado o editado exitosamente.");
                break;

            case 402:
                // Alerta de libro no disponible
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Libro no disponible");
                alert.setHeaderText("Libro en préstamo");
                alert.setContentText("Uno o más libros seleccionados no están disponibles.");
                break;
            case 403:
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cliente con Prestamo Activo");
                alert.setHeaderText("En cliente ya tiene un prestamo");
                alert.setContentText("Un cliente solo puede tener un prestamo activo");
                break;

            case 404:
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Al eliminar o editar");
                alert.setHeaderText("Error");
                alert.setContentText("No se pueden modificar o eliminar Registros con refencias en otras tablas");
                break;

            case 405:
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Categoria Duplicada");
                alert.setHeaderText("Error");
                alert.setContentText("Esta categoria ya existe");
                break;

            case 406:
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error DPI duplicado");
                alert.setHeaderText("Error");
                alert.setContentText("Ya hay un empleado con este DPI");
                break;

            default:
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Al eliminar o editar");
                alert.setHeaderText("Error");
                alert.setContentText("Ha ocurrido un error inesperado.");
                break;
        }

        alert.showAndWait();
    }

    public Optional<ButtonType> mostrarAlertaConfirmacion(int code) {
        Optional<ButtonType> action = Optional.empty();
        Alert alert;

        switch (code) {
            case 405:
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmar eliminación");
                alert.setHeaderText("Eliminar préstamo");
                alert.setContentText("¿Estás seguro de que deseas eliminar este préstamo?");
                action = alert.showAndWait();
                break;

            case 106:
                // Alerta de confirmación para editar
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmar edición");
                alert.setHeaderText("Editar préstamo");
                alert.setContentText("¿Estás seguro de que deseas editar este préstamo?");
                action = alert.showAndWait();
                break;

            default:
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Acción no permitida");
                alert.setHeaderText("Operación inválida");
                alert.setContentText("No puedes realizar esta operación.");
                alert.showAndWait();
                break;
        }

        return action;
    }
}
