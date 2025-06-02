package com.miempresa.sistema.controller;

import com.miempresa.sistema.dto.ReservaDTO;
import com.miempresa.sistema.model.Reserva;
import com.miempresa.sistema.model.Usuario;
import com.miempresa.sistema.model.Viaje;
import com.miempresa.sistema.repository.UsuarioRepository;
import com.miempresa.sistema.repository.ViajeRepository;
import com.miempresa.sistema.service.ReservaService;
import com.miempresa.sistema.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;
    private final ViajeRepository viajeRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioservice;

  @Autowired
        public ReservaController(ReservaService reservaService, 
                                 ViajeRepository viajeRepository, 
                                 UsuarioRepository usuarioRepository,
                                 UsuarioService usuarioservice) {
            this.reservaService = reservaService;
            this.viajeRepository = viajeRepository;
            this.usuarioRepository = usuarioRepository;
            this.usuarioservice = usuarioservice;
        }

    // Obtener reservas canceladas del usuario logueado antes de una fecha
  @GetMapping("/canceladas")
    public List<Reserva> obtenerReservasCanceladas(@RequestParam String fechaLimite, Authentication authentication) {
        LocalDateTime fecha = LocalDateTime.parse(fechaLimite);
        Usuario usuario = usuarioservice.obtenerUsuarioAutenticado(authentication);
        return reservaService.obtenerReservasCanceladasPorUsuarioYFecha(usuario.getId(), fecha);
    }


    // Realizar una nueva reserva
    @PostMapping
public Reserva reservarViaje(@RequestBody Reserva reserva, Authentication authentication) {
    Usuario usuario = usuarioservice.obtenerUsuarioAutenticado(authentication); // ← usa autenticado
    int idViaje = reserva.getViaje().getId();
    int cantidadPasajes = reserva.getCantidadPasajes();

    Viaje viaje = viajeRepository.findById(idViaje)
            .orElseThrow(() -> new IllegalArgumentException("Viaje no encontrado"));

    return reservaService.realizarReserva(usuario, viaje, cantidadPasajes);
}


    // Cancelar una reserva
  @PostMapping("/{id}/cancelar")
    public ResponseEntity<Map<String, Object>> cancelarReserva(
            @PathVariable int id,
            @RequestParam(defaultValue = "USUARIO") String motivo
    ) {
        reservaService.cancelarReserva(id, motivo);
        return ResponseEntity.ok(Map.of("mensaje", "Reserva cancelada correctamente"));
    }
        
    @GetMapping("/mias")
    public List<ReservaDTO> obtenerMisReservas(Authentication authentication) {
        Usuario usuario = usuarioservice.obtenerUsuarioAutenticado(authentication);
        List<Reserva> reservas = reservaService.obtenerReservasPorUsuario(usuario.getId());
        return reservas.stream().map(ReservaDTO::new).toList();
    }
    
    // Consultar asientos disponibles para un viaje
    @GetMapping("/disponibles/{idViaje}")
    public ResponseEntity<Map<String, Object>> obtenerAsientosDisponibles(@PathVariable int idViaje) {
        Viaje viaje = viajeRepository.findById(idViaje)
                .orElseThrow(() -> new IllegalArgumentException("Viaje no encontrado"));

        int disponibles = reservaService.obtenerAsientosDisponibles(viaje);
        return ResponseEntity.ok(Map.of(
                "viajeId", idViaje,
                "asientosDisponibles", disponibles
        ));
    }

}

