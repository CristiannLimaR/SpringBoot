package com.cristianlima.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cristianlima.webapp.biblioteca.model.Empleado;


public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {

}
