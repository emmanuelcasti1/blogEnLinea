package com.example.blogEnLinea.service;

import com.example.blogEnLinea.model.Posteo;
import com.example.blogEnLinea.repository.IPosteoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PosteoService implements IPosteoService{
    @Autowired
    private IPosteoRepository posteoRepository;

    @Override
    public Posteo crearPosteo(Posteo posteo) {
        return posteoRepository.save(posteo);
    }

    @Override
    public Optional<Posteo> traerPosteoPorId(Long id) {
        return posteoRepository.findById(id);
    }

    @Override
    public List<Posteo> traerPosteos() {
        return posteoRepository.findAll();
    }

    @Override
    public Posteo editarPosteo(Posteo posteo) {
        return posteoRepository.save(posteo);
    }

    @Override
    public void eliminarPosteo(Long id) {
        posteoRepository.deleteById(id);
    }
}
