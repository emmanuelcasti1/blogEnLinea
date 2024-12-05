package com.example.blogEnLinea.service;

import com.example.blogEnLinea.model.Permiso;
import com.example.blogEnLinea.repository.IPermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermisoService implements IPermisoService{
    @Autowired
    private IPermisoRepository permisoRepository;

    @Override
    public Permiso crearPermiso(Permiso permiso) {
        return permisoRepository.save(permiso);
    }

    @Override
    public Optional<Permiso> traerPermisoPorId(Long id) {
        return permisoRepository.findById(id);
    }

    @Override
    public List<Permiso> traerPermisos() {
        return permisoRepository.findAll();
    }

    @Override
    public Permiso editarPermiso(Permiso permiso) {
        return permisoRepository.save(permiso);
    }

    @Override
    public void eliminarPermiso(Long id) {
        permisoRepository.deleteById(id);
    }
}
