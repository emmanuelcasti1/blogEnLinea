package com.example.blogEnLinea.service;

import com.example.blogEnLinea.model.Usuario;
import com.example.blogEnLinea.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService{
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> traerUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> traerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public void editarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public String encriptarContrasena(String contrasena) {
        return new BCryptPasswordEncoder().encode(contrasena);
    }
}
