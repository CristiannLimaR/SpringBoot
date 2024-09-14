package com.cristianlima.webapp.biblioteca.service;

import java.util.List;

import com.cristianlima.webapp.biblioteca.model.Cliente;
import com.cristianlima.webapp.biblioteca.util.MethodType;

public interface IClienteService {
    public List<Cliente> listarClientes();

    public Cliente buscarClientePorId(Long id);

    public Cliente guardarCliente(Cliente cliente, MethodType methodType);

    public void eliminarCliente(Cliente cliente);
}
