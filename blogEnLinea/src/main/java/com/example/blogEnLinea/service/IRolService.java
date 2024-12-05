package com.example.blogEnLinea.service;

import com.example.blogEnLinea.model.Rol;

import java.util.List;
import java.util.Optional;

public interface IRolService {
    public Rol crearRol(Rol rol);
    Optional <Rol> traerRolPorId(Long id);
    public List<Rol> traerRoles();
    public Rol editarRol(Rol rol);
    public void eliminarRol(Long id);
}