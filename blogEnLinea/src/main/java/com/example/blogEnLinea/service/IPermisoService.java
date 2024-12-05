package com.example.blogEnLinea.service;

import com.example.blogEnLinea.model.Permiso;

import java.util.List;
import java.util.Optional;

public interface IPermisoService {
    public Permiso crearPermiso(Permiso permiso);
    Optional <Permiso> traerPermisoPorId(Long id);
    public List<Permiso> traerPermisos();
    public Permiso editarPermiso(Permiso permiso);
    public void eliminarPermiso(Long id);
}