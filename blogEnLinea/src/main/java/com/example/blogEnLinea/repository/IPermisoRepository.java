package com.example.blogEnLinea.repository;

import com.example.blogEnLinea.model.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermisoRepository extends JpaRepository<Permiso, Long> {
}
