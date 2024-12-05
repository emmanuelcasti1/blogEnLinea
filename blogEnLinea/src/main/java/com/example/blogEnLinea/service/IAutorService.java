package com.example.blogEnLinea.service;

import com.example.blogEnLinea.model.Autor;

import java.util.List;
import java.util.Optional;

public interface IAutorService {
    public Autor crearAutor(Autor autor);
    Optional <Autor> traerAutorPorId(Long id);
    public List<Autor> traerAutores();
    public Autor editarAutor(Autor autor);
    public void eliminarAutor(Long id);
}
