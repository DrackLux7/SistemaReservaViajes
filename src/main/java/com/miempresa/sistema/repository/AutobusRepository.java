
package com.miempresa.sistema.repository;
import com.miempresa.sistema.model.Autobus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutobusRepository extends JpaRepository<Autobus, Integer> {
    boolean existsByPlaca(String placa);
    Optional<Autobus> findByPlaca(String placa);
}