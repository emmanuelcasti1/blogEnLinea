package com.example.blogEnLinea.repository;

import com.example.blogEnLinea.model.Posteo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPosteoRepository extends JpaRepository<Posteo, Long> {
}
