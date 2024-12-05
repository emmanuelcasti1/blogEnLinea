package com.example.blogEnLinea.controller;

import com.example.blogEnLinea.model.Autor;
import com.example.blogEnLinea.model.Posteo;
import com.example.blogEnLinea.service.IAutorService;
import com.example.blogEnLinea.service.IPosteoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autores")
public class AutorController {
    @Autowired
    private IAutorService autorService;

    @Autowired
    private IPosteoService posteoService;


        @PostMapping
        public ResponseEntity<Autor> crearAutor(@RequestBody Autor autor) {
            if (autor.getPosteosList() == null) {
                autor.setPosteosList(new ArrayList<>());
            }
            Autor newAutor = autorService.crearAutor(autor);
            return ResponseEntity.ok(newAutor);
        }


    @GetMapping
    public ResponseEntity<List<Autor>> traerAutores() {
        return ResponseEntity.ok(autorService.traerAutores());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Autor> editarAutor(@PathVariable Long id, @RequestBody Autor autor) {
        Autor autor1 = autorService.traerAutorPorId(id).orElse(null);
        if(autor1!=null){
            autor1.setPosteosList(autor.getPosteosList());
            autor1.setNombreCompleto(autor.getNombreCompleto());
            autorService.editarAutor(autor1);
            return ResponseEntity.ok(autor1);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAutor(@PathVariable Long id) {
        autorService.traerAutorPorId(id).ifPresent(autor -> autorService.eliminarAutor(autor.getId()));
        return ResponseEntity.ok("Autor eliminado");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> obtenerAutorPorId(@PathVariable Long id) {
        Optional <Autor> autor = autorService.traerAutorPorId(id);
        return autor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
