package com.cristianlima.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cristianlima.webapp.biblioteca.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long>{

}
