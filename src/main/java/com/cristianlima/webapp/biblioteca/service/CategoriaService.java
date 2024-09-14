package com.cristianlima.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianlima.webapp.biblioteca.model.Categoria;
import com.cristianlima.webapp.biblioteca.repository.CategoriaRespository;
import com.cristianlima.webapp.biblioteca.util.LibreriaAlert;
import com.cristianlima.webapp.biblioteca.util.MethodType;

import javafx.scene.control.ButtonType;

@Service
public class CategoriaService implements ICategoriaService{

    @Autowired
    private CategoriaRespository categoriaRespository;

    @Autowired
    LibreriaAlert libreriaAlert;

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRespository.findAll();
    }

    @Override
    public Categoria buscarCategoriaPorId(Long id) {
       return categoriaRespository.findById(id).orElse(null);
    }

    @Override
    public Boolean guardarCategoria(Categoria categoria, MethodType methodType ) {
      try {
         if(methodType == MethodType.POST){
            if(!verificarCategoriaDuplicado(categoria)){
               categoriaRespository.save(categoria);
               libreriaAlert.mostrarAlertaInfo(401);
               return true;
            }else{
               libreriaAlert.mostrarAlertaInfo(405);
            }
         }else{
            if(libreriaAlert.mostrarAlertaConfirmacion(106).get()== ButtonType.OK){
               if(!verificarCategoriaDuplicado(categoria)){
                  categoriaRespository.save(categoria);
                  libreriaAlert.mostrarAlertaInfo(401);
                  return true;
               }else{
                  libreriaAlert.mostrarAlertaInfo(405);
               }
            }
         }
      } catch (Exception e) {
         libreriaAlert.mostrarAlertaInfo(404);
      }
      
       return false;
    }

    @Override
    public void eliminarCategoria(Categoria categoria) {
      try {
         if(libreriaAlert.mostrarAlertaConfirmacion(106).get()== ButtonType.OK){
            categoriaRespository.delete(categoria);
         }
      } catch (Exception e) {
         libreriaAlert.mostrarAlertaInfo(404);
      }
      
       
    }

   @Override
   public Boolean verificarCategoriaDuplicado(Categoria newCategoria) {
     List<Categoria> categorias = listarCategorias();
     Boolean flag = false;
     for (Categoria categoria : categorias) {
         if(newCategoria.getNombreCategoria().trim().equalsIgnoreCase(categoria.getNombreCategoria())){
            flag = true;
         }
     }
     return flag;
   }

}
