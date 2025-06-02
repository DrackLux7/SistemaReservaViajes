package com.miempresa.sistema.controller;

import com.miempresa.sistema.dto.ViajeDTO;
import com.miempresa.sistema.model.Viaje;
import com.miempresa.sistema.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/viajes")
public class ViajeController {

    @Autowired
    private ViajeService viajeService;

    @GetMapping("/dto")
    public List<ViajeDTO> listarDTO() {
        return viajeService.listarTodos()
                           .stream()
                           .map(ViajeDTO::new)
                           .collect(Collectors.toList());
    }

    // Filtro combinado: origen, destino y fecha
    @GetMapping("/filtrar")
    public List<ViajeDTO> filtrarViajes(
        @RequestParam(required = false) String origen,
        @RequestParam(required = false) String destino,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {

        return viajeService.filtrarViajes(origen, destino, fecha)
                           .stream()
                           .map(ViajeDTO::new)
                           .collect(Collectors.toList());
    }

    @GetMapping("/buscar/fecha")
    public List<Viaje> buscarPorFecha(@RequestParam("fecha") String fecha) {
        return viajeService.buscarPorFecha(LocalDate.parse(fecha));
    }

    @GetMapping("/buscar/termino")
    public List<Viaje> buscarPorOrigenODestino(@RequestParam("q") String termino) {
        return viajeService.buscarPorOrigenODestino(termino);
    }


    @GetMapping
    public List<Viaje> listar() {
        return viajeService.listarTodos();
    }

    @PostMapping
    public Viaje crear(@RequestBody Viaje viaje) {
        return viajeService.crear(viaje);
    }

    @PutMapping("/{id}")
    public Viaje actualizar(@PathVariable int id, @RequestBody Viaje viaje) {
        return viajeService.actualizar(id, viaje);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        viajeService.eliminar(id);
    }

    @GetMapping("/{id:\\d+}")
    public Viaje obtener(@PathVariable int id) {
        return viajeService.obtenerPorId(id);
    }
}
