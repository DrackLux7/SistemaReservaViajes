
package com.miempresa.sistema.controller;

import com.miempresa.sistema.model.Chofer;

import com.miempresa.sistema.service.ChoferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/choferes")
public class ChoferController {

    @Autowired
    private ChoferService choferService;

    @GetMapping
    public List<Chofer> listar() {
        return choferService.listarTodos();
    }

    @GetMapping("/{id}")
    public Chofer obtener(@PathVariable int id) {
        return choferService.obtenerPorId(id);
    }

    @PostMapping
    public Chofer crear(@RequestBody Chofer chofer) {
        return choferService.crear(chofer);
    }

    @PutMapping("/{id}")
    public Chofer actualizar(@PathVariable int id, @RequestBody Chofer datos) {
        return choferService.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        choferService.eliminar(id);
    }
    
     @GetMapping("/codigo/{codigo}")
    public Chofer buscarPorCodigo(@PathVariable String codigo) {
        return choferService.buscarPorCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Chofer no encontrado"));
    }
}