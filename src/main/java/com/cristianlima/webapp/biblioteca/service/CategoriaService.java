package com.cristianlima.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianlima.webapp.biblioteca.model.Categoria;
import com.cristianlima.webapp.biblioteca.repository.CategoriaRespository;

@Service
public class CategoriaService implements ICategoriaService{

    @Autowired
    private CategoriaRespository categoriaRespository;

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRespository.findAll();
    }

    @Override
    public Categoria buscarCategoriaPorId(Long id) {
       return categoriaRespository.findById(id).orElse(null);
    }

    @Override
    public void guardarCategoria(Categoria categoria) {
       categoriaRespository.save(categoria);
    }

    @Override
    public void eliminarCategoria(Categoria categoria) {
       categoriaRespository.delete(categoria);
    }

}
