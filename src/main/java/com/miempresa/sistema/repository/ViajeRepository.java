
package com.miempresa.sistema.repository;

import com.miempresa.sistema.model.Viaje;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Integer> {
    List<Viaje> findByFecha(LocalDate fecha);
    List<Viaje> findByOrigenContainingIgnoreCaseOrDestinoContainingIgnoreCase(String origen, String destino);
    
    @Query("SELECT v FROM Viaje v WHERE " +
       "(:origen IS NULL OR LOWER(v.origen) LIKE LOWER(CONCAT('%', :origen, '%'))) AND " +
       "(:destino IS NULL OR LOWER(v.destino) LIKE LOWER(CONCAT('%', :destino, '%'))) AND " +
       "(:fecha IS NULL OR v.fecha = :fecha)")
List<Viaje> filtrarViajes(@Param("origen") String origen,
                          @Param("destino") String destino,
                          @Param("fecha") LocalDate fecha
    );
    
    List<Viaje> findByOrigenContainingIgnoreCaseAndDestinoContainingIgnoreCaseAndFecha(
    String origen, String destino, LocalDate fecha
);

}
