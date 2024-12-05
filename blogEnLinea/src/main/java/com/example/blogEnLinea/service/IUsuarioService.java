package com.example.blogEnLinea.service;

import com.example.blogEnLinea.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public List<Usuario> traerUsuarios();
    public Optional<Usuario> traerUsuarioPorId(Long id);
    public Usuario crearUsuario(Usuario usuario);
    public void deleteUsuario(Long id);
    public void editarUsuario(Usuario usuario);
    public String encriptarContrasena(String contrasena);

}

