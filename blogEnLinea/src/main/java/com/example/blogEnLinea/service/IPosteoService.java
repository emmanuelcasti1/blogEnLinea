package com.example.blogEnLinea.service;

import com.example.blogEnLinea.model.Posteo;

import java.util.List;
import java.util.Optional;

public interface IPosteoService {
    public Posteo crearPosteo(Posteo posteo);
    Optional <Posteo> traerPosteoPorId(Long id);
    public List<Posteo> traerPosteos();
    public Posteo editarPosteo(Posteo posteo);
    public void eliminarPosteo(Long id);
}