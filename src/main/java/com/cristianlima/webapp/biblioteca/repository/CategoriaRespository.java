package com.cristianlima.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cristianlima.webapp.biblioteca.model.Categoria;



public interface CategoriaRespository extends JpaRepository<Categoria,Long> {

}
