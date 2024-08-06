package com.cristianlima.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianlima.webapp.biblioteca.model.Cliente;
import com.cristianlima.webapp.biblioteca.repository.IClienteRepository;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarClientePorId(Long id){
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente guardarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminarCliente(Cliente cliente){
        clienteRepository.delete(cliente);
    }
    

}
