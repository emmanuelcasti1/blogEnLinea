package com.example.blogEnLinea.service;

import com.example.blogEnLinea.model.Autor;
import com.example.blogEnLinea.repository.IAutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService implements IAutorService{
    @Autowired
    private IAutorRepository autorRepository;

    @Override
    public Autor crearAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public Optional<Autor> traerAutorPorId(Long id) {
        return autorRepository.findById(id);
    }

    @Override
    public List<Autor> traerAutores() {
        return autorRepository.findAll();
    }

    @Override
    public Autor editarAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public void eliminarAutor(Long id) {
        autorRepository.deleteById(id);
    }
}
