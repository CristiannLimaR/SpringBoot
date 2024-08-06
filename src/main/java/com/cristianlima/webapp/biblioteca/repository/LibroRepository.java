package com.cristianlima.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cristianlima.webapp.biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro,Long> {

}
