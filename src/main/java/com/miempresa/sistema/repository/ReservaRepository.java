package com.miempresa.sistema.repository;

import com.miempresa.sistema.model.Reserva;
import com.miempresa.sistema.model.Viaje;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

     @Query("SELECT COALESCE(SUM(r.cantidadPasajes), 0) FROM Reserva r WHERE r.viaje = :viaje AND r.estado = :estado")
int totalPasajesReservadosActivosPorViaje(@Param("viaje") Viaje viaje,
                                          @Param("estado") Reserva.EstadoReserva estado);

    
    List<Reserva> findByUsuarioId(int idUsuario);

    @Query("SELECT r FROM Reserva r WHERE r.usuario.id = :idUsuario AND r.estado = :estado AND DATE(r.fechaReserva) <= :fechaLimite")
List<Reserva> findCanceladasByUsuarioAndFechaLimite(@Param("idUsuario") int idUsuario,
                                                    @Param("estado") Reserva.EstadoReserva estado,
                                                    @Param("fechaLimite") LocalDate fechaLimite);

    
       List<Reserva> findByUsuarioIdAndEstadoAndFechaReservaBefore(
        int usuarioId,
        Reserva.EstadoReserva estado,
        LocalDateTime fechaLimite
    );
       
       
}