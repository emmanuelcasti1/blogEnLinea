package com.example.blogEnLinea.controller;

import com.example.blogEnLinea.model.Autor;
import com.example.blogEnLinea.service.IPosteoService;
import com.example.blogEnLinea.model.Posteo;
import com.example.blogEnLinea.service.IAutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posteos")
public class PosteoController {
    @Autowired
    private IPosteoService posteoService;

    @Autowired
    private IAutorService autorService;

    @PostMapping
    public ResponseEntity<Posteo> crearPosteo(@RequestBody Posteo posteo) {
        Posteo posteoNuevo = posteoService.crearPosteo(posteo);
        return ResponseEntity.ok(posteoNuevo);
    }

    @GetMapping
    public ResponseEntity<List<Posteo>> listarPosteos() {
        List<Posteo> posteoList = posteoService.traerPosteos();
        return ResponseEntity.ok(posteoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Posteo> obtenerPosteo(@PathVariable Long id) {
        Optional<Posteo> posteo = posteoService.traerPosteoPorId(id);
        return posteo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Posteo> editarPosteo(@PathVariable Long id, @RequestBody Posteo modificar) {
        Posteo posteo = posteoService.traerPosteoPorId(id).orElse(null);
        Autor autor = autorService.traerAutorPorId(modificar.getAutor().getId()).orElse(null);
        if (posteo!= null){
            posteo.setAutor(autor);
            posteo.setDescripcion(modificar.getDescripcion());
            posteo.setTitulo(modificar.getTitulo());
            posteoService.editarPosteo(posteo);
            return ResponseEntity.ok(posteo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPosteo(@PathVariable Long id) {
        posteoService.traerPosteoPorId(id).ifPresent(posteo -> posteoService.eliminarPosteo(posteo.getId()));
        return ResponseEntity.ok("Posteo eliminado");
    }
}
