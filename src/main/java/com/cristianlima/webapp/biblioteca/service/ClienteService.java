package com.cristianlima.webapp.biblioteca.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianlima.webapp.biblioteca.model.Cliente;
import com.cristianlima.webapp.biblioteca.repository.ClienteRepository;
import com.cristianlima.webapp.biblioteca.util.LibreriaAlert;
import com.cristianlima.webapp.biblioteca.util.MethodType;

import javafx.scene.control.ButtonType;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    LibreriaAlert libreriaAlert;

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente guardarCliente(Cliente cliente, MethodType methodType) {
        try {
            if (methodType == MethodType.POST) {
                libreriaAlert.mostrarAlertaInfo(401);
                return clienteRepository.save(cliente);
            } else {
                if (libreriaAlert.mostrarAlertaConfirmacion(106).get() == ButtonType.OK) {
                    libreriaAlert.mostrarAlertaInfo(401);
                    return clienteRepository.save(cliente);
                }
            }
            return cliente;
        } catch (Exception e) {
            libreriaAlert.mostrarAlertaInfo(404);
        }
        return cliente;
        

    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        try {
            if (libreriaAlert.mostrarAlertaConfirmacion(405).get() == ButtonType.OK) {
                clienteRepository.delete(cliente);
            }
        } catch (Exception e) {
           libreriaAlert.mostrarAlertaInfo(404);
        }
        
        
    }

}
