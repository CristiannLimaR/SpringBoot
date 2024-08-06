package com.cristianlima.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cristianlima.webapp.biblioteca.model.Libro;
import com.cristianlima.webapp.biblioteca.service.LibroService;

@Controller
@RestController
@RequestMapping(value = "")
public class LibroController {
    @Autowired
    LibroService libroService;

    @GetMapping("/libros")
    public List<Libro> listarLibros(){
        return libroService.listarLibros();
    }

    @GetMapping("/libros")
    public ResponseEntity<Libro> buscarLibroPorId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(libroService.buscarLibroPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/libros")
    public ResponseEntity<Map<String,String>> guardarLibro(@RequestBody Libro libro){
        Map<String,String> response =  new HashMap<>();
        try {
            libroService.guardarLibro(libro);
            response.put("message", "Libro agregado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("err", "No se ha podido agregar el Libro");
            return ResponseEntity.badRequest().body(response);
        }
    }

    
}
