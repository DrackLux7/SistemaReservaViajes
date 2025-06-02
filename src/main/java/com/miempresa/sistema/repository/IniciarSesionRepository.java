package com.miempresa.sistema.repository;

import com.miempresa.sistema.model.IniciarSesion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IniciarSesionRepository extends JpaRepository<IniciarSesion, Long> {
    Optional<IniciarSesion> findByEmail(String email);    
    boolean existsByEmail(String email);

}