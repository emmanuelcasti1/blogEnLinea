package com.example.blogEnLinea.controller;

import com.example.blogEnLinea.model.Rol;
import com.example.blogEnLinea.model.Usuario;
import com.example.blogEnLinea.service.IRolService;
import com.example.blogEnLinea.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Set<Rol> roleList = new HashSet<Rol>();
        Rol leerRol;

        for (Rol rol: usuario.getRoleList()){
            leerRol = rolService.traerRolPorId(rol.getId()).orElse(null);
            if (leerRol != null){
                roleList.add(leerRol);
            }
        }
        if (!roleList.isEmpty()) {

            usuario.setRoleList(roleList);
            Usuario usuarioNuevo = usuarioService.crearUsuario(usuario);
            return ResponseEntity.ok(usuarioNuevo);
        }
        return null;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarioList = usuarioService.traerUsuarios();
        return ResponseEntity.ok(usuarioList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.traerUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        usuarioService.traerUsuarioPorId(id).ifPresent(usuario -> usuarioService.deleteUsuario(usuario.getId()));
        return ResponseEntity.ok("Usuario eliminado");
    }
}
