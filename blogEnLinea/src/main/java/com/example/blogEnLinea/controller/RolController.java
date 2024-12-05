package com.example.blogEnLinea.controller;

import com.example.blogEnLinea.model.Permiso;
import com.example.blogEnLinea.model.Rol;
import com.example.blogEnLinea.service.IPermisoService;
import com.example.blogEnLinea.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/roles")
public class RolController {
    @Autowired
    private IRolService rolService;

    @Autowired
    private IPermisoService permisoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Rol> crearRol(@RequestBody Rol rol) {
        Set<Permiso> permisoList = new HashSet<>();
        Permiso leerPermiso;

        for(Permiso permiso: rol.getPermisoList()){
            leerPermiso = permisoService.traerPermisoPorId(permiso.getId()).orElse(null);
            if(leerPermiso != null){
                permisoList.add(leerPermiso);
            }
        }
        rol.setPermisoList(permisoList);
        Rol rolNuevo = rolService.crearRol(rol);
        return ResponseEntity.ok(rolNuevo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Rol>> listarRols() {
        List<Rol> rolList = rolService.traerRoles();
        return ResponseEntity.ok(rolList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerRolPorId(@PathVariable Long id) {
        Optional<Rol> rol = rolService.traerRolPorId(id);
        return rol.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<Rol> actualizarRol(@PathVariable Long id, @RequestBody Rol modificar) {
        Rol rol = rolService.traerRolPorId(id).orElse(null);
        if (rol != null) {
            // Actualizar el nombre del rol
            rol.setNombreRol(modificar.getNombreRol());

            // Validar y actualizar la lista de permisos
            Set<Permiso> permisosActualizados = new HashSet<>();
            for (Permiso permiso : modificar.getPermisoList()) {
                Permiso permisoExistente = permisoService.traerPermisoPorId(permiso.getId()).orElse(null);
                if (permisoExistente != null) {
                    permisosActualizados.add(permisoExistente);
                }
            }

            rol.setPermisoList(permisosActualizados);
            rolService.editarRol(rol);
            return ResponseEntity.ok(rol);
        }
        return ResponseEntity.notFound().build();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRol(@PathVariable("id") Long id) {
        rolService.traerRolPorId(id).ifPresent(rol -> rolService.eliminarRol(rol.getId()));
        return ResponseEntity.ok("Rol eliminado");
    }
}
