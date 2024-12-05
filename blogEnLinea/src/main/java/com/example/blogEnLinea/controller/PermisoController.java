package com.example.blogEnLinea.controller;

import com.example.blogEnLinea.model.Permiso;
import com.example.blogEnLinea.service.IPermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permisos")
public class PermisoController {
    @Autowired
    private IPermisoService permisoService;

    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<Permiso> crearPermiso(@RequestBody Permiso permiso) {
        Permiso PermisoNuevo = permisoService.crearPermiso(permiso);
        return ResponseEntity.ok(PermisoNuevo);
    }

    @GetMapping
    public ResponseEntity<List<Permiso>> listarPermisos() {
        List<Permiso> permisoList = permisoService.traerPermisos();
        return ResponseEntity.ok(permisoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permiso> obtenerPermiso(@PathVariable Long id) {
        Optional <Permiso> permiso = permisoService.traerPermisoPorId(id);
        return permiso.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Permiso> modificarPermiso(@PathVariable Long id, @RequestBody Permiso modificar) {
        Permiso permisoExistente = permisoService.traerPermisoPorId(id).orElse(null);
        if (permisoExistente != null) {
            permisoExistente.setNombrePermiso(modificar.getNombrePermiso());
            permisoService.editarPermiso(permisoExistente);
            return ResponseEntity.ok(permisoExistente);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPermiso(@PathVariable Long id) {
        permisoService.traerPermisoPorId(id).ifPresent(permiso -> permisoService.eliminarPermiso(permiso.getId()));
        return ResponseEntity.ok("Permiso eliminado");
    }

}
