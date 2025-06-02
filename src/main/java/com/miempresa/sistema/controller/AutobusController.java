
package com.miempresa.sistema.controller;

import com.miempresa.sistema.model.Autobus;
import com.miempresa.sistema.service.AutobusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/autobuses")
public class AutobusController {

    @Autowired
    private AutobusService autobusService;

    @GetMapping
    public List<Autobus> listar() {
        return autobusService.listarTodos();
    }

    @GetMapping("/{id}")
    public Autobus obtener(@PathVariable int id) {
        return autobusService.obtenerPorId(id);
    }

    @PostMapping
    public Autobus crear(@RequestBody Autobus autobus) {
        return autobusService.crear(autobus);
    }

    @PutMapping("/{id}")
    public Autobus actualizar(@PathVariable int id, @RequestBody Autobus datos) {
        return autobusService.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        autobusService.eliminar(id);
    }
    
    @GetMapping("/placa/{placa}")
    public Autobus buscarPorPlaca(@PathVariable String placa) {
        return autobusService.buscarPorPlaca(placa)
                .orElseThrow(() -> new RuntimeException("Autobús no encontrado"));
    }
}