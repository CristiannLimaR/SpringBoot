package com.cristianlima.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cristianlima.webapp.biblioteca.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo,Long>{

}
