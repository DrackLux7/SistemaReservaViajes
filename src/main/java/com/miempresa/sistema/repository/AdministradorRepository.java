
package com.miempresa.sistema.repository;

import com.miempresa.sistema.model.Administrador;
import com.miempresa.sistema.model.IniciarSesion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Optional<Administrador> findByInicioSesion(IniciarSesion inicioSesion);
}