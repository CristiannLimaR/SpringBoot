package com.cristianlima.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cristianlima.webapp.biblioteca.model.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente,Long>{

}
