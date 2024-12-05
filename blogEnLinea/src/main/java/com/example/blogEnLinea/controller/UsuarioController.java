package com.example.blogEnLinea.controller;

import com.example.blogEnLinea.model.Rol;
import com.example.blogEnLinea.model.Usuario;
import com.example.blogEnLinea.service.IRolService;
import com.example.blogEnLinea.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolService rolService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        String encodedPassword = passwordEncoder.encode(usuario.getPassword());
        Set<Rol> roleList = new HashSet<Rol>();
        Rol leerRol;

        for (Rol rol : usuario.getRoleList()) {
            leerRol = rolService.traerRolPorId(rol.getId()).orElse(null);
            if (leerRol != null) {
                roleList.add(leerRol);
            }
        }
        if (!roleList.isEmpty()) {

            Usuario newUser = new Usuario();
            newUser.setUsername(usuario.getUsername());
            newUser.setPassword(encodedPassword);
            newUser.setEnabled(true);
            newUser.setAccountNotExpired(true);
            newUser.setAccountNotLocked(true);
            newUser.setCredentialNotExpired(true);
            newUser.setRoleList(roleList);
            usuarioService.crearUsuario(newUser);
            return ResponseEntity.ok(newUser);
        }
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarioList = usuarioService.traerUsuarios();
        return ResponseEntity.ok(usuarioList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.traerUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario modificar) {
        Usuario usuario = usuarioService.traerUsuarioPorId(id).orElse(null);
        if(usuario!=null){
            usuario.setRoleList(modificar.getRoleList());
            usuario.setUsername(modificar.getUsername());
            usuario.setPassword(modificar.getPassword());
            usuarioService.editarUsuario(usuario);
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        usuarioService.traerUsuarioPorId(id).ifPresent(usuario -> usuarioService.deleteUsuario(usuario.getId()));
        return ResponseEntity.ok("Usuario eliminado");
    }
}
