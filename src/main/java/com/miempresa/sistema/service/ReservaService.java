package com.miempresa.sistema.service;

import com.miempresa.sistema.model.Reserva;
import com.miempresa.sistema.model.Usuario;
import com.miempresa.sistema.model.Viaje;
import com.miempresa.sistema.repository.ReservaRepository;
import com.miempresa.sistema.repository.ViajeRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ViajeRepository viajeRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> obtenerReservasCanceladasPorUsuarioYFecha(int idUsuario, LocalDate fechaLimite) {
        return reservaRepository.findCanceladasByUsuarioAndFechaLimite(
            idUsuario,
            Reserva.EstadoReserva.CANCELADA,
            fechaLimite
        );
    }

    public List<Reserva> obtenerReservasCanceladasPorUsuarioYFecha(int idUsuario, LocalDateTime fechaLimite) {
        return reservaRepository.findByUsuarioIdAndEstadoAndFechaReservaBefore(
            idUsuario,
            Reserva.EstadoReserva.CANCELADA,
            fechaLimite
        );
    }

    public boolean hayCapacidadDisponible(Viaje viaje, int cantidadSolicitada) {
        int capacidadTotal = viaje.getAutobus().getCapacidad();
        int reservados = reservaRepository.totalPasajesReservadosActivosPorViaje(viaje, Reserva.EstadoReserva.ACTIVA);
        return (capacidadTotal - reservados) >= cantidadSolicitada;
    }

    @Transactional
    public Reserva realizarReserva(Usuario usuario, Viaje viaje, int cantidadPasajes) {
        if (!hayCapacidadDisponible(viaje, cantidadPasajes)) {
            throw new IllegalArgumentException("No hay suficientes asientos disponibles.");
        }

        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setViaje(viaje);
        reserva.setCantidadPasajes(cantidadPasajes);
        reserva.setEstado(Reserva.EstadoReserva.ACTIVA);
        reserva.setFechaReserva(LocalDateTime.now());

        return reservaRepository.save(reserva);
    }

    @Transactional
    public void cancelarReserva(int idReserva, String motivo) {
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));

        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime limite = reserva.getFechaReserva().plusHours(24);

        if (ahora.isAfter(limite) && !"TIEMPO".equalsIgnoreCase(motivo)) {
            throw new IllegalStateException("No se puede cancelar manualmente. Han pasado más de 24 horas.");
        }

        reserva.setEstado(Reserva.EstadoReserva.CANCELADA);
        reserva.setMotivoCancelacion(motivo);
        reservaRepository.save(reserva);
    }

    @Transactional
    public void cancelarReservasExpiradasPorTiempo() {
        List<Reserva> expiradas = reservaRepository.findAll().stream()
            .filter(r -> r.getEstado() == Reserva.EstadoReserva.ACTIVA)
            .filter(r -> r.getFechaReserva().plusHours(24).isBefore(LocalDateTime.now()))
            .toList();

        for (Reserva r : expiradas) {
            r.setEstado(Reserva.EstadoReserva.CANCELADA);
            r.setMotivoCancelacion("TIEMPO");
            reservaRepository.save(r);
        }
    }

    public List<Reserva> obtenerReservasPorUsuario(int usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    public int obtenerAsientosDisponibles(Viaje viaje) {
        int capacidadTotal = viaje.getAutobus().getCapacidad();
        int reservados = reservaRepository.totalPasajesReservadosActivosPorViaje(viaje, Reserva.EstadoReserva.ACTIVA);
        return capacidadTotal - reservados;
    }
}


