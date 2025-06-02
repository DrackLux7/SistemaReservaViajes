
package com.miempresa.sistema.repository;

import com.miempresa.sistema.model.IniciarSesion;
import com.miempresa.sistema.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByInicioSesion(IniciarSesion inicioSesion);
    Optional<Usuario> findByInicioSesion_Email(String email);
}