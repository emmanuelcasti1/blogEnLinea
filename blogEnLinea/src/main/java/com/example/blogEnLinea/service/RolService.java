package com.example.blogEnLinea.service;

import com.example.blogEnLinea.model.Rol;
import com.example.blogEnLinea.repository.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService implements IRolService {
    @Autowired
    private IRolRepository rolRepository;

    @Override
    public Rol crearRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public Optional<Rol> traerRolPorId(Long id) {
        return rolRepository.findById(id);
    }

    @Override
    public List<Rol> traerRoles() {
        return rolRepository.findAll();
    }

    @Override
    public Rol editarRol(Rol autor) {
        return rolRepository.save(autor);
    }

    @Override
    public void eliminarRol(Long id) {
        rolRepository.deleteById(id);
    }
}
