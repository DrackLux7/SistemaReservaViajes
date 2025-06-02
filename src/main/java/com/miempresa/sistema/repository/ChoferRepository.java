
package com.miempresa.sistema.repository;


import com.miempresa.sistema.model.Chofer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoferRepository extends JpaRepository<Chofer, Integer> {
    Optional<Chofer> findByCodigo(String codigo);
}