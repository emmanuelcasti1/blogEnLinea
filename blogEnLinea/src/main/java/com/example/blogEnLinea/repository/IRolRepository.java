package com.example.blogEnLinea.repository;
import com.example.blogEnLinea.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombreRol(String nombreRol);
}
