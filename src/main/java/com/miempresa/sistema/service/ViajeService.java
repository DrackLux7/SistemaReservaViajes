
package com.miempresa.sistema.service;

import com.miempresa.sistema.model.Viaje;
import com.miempresa.sistema.repository.ViajeRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    public List<Viaje> listarTodos() {
        return viajeRepository.findAll();
    }

    public Viaje obtenerPorId(int id) {
        return viajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));
    }

    public Viaje crear(Viaje viaje) {
        return viajeRepository.save(viaje);
    }

    public Viaje actualizar(int id, Viaje datos) {
        Viaje existente = obtenerPorId(id);
        existente.setFecha(datos.getFecha());
        existente.setHora(datos.getHora());
        existente.setOrigen(datos.getOrigen());
        existente.setDestino(datos.getDestino());
        existente.setAutobus(datos.getAutobus());
        existente.setChofer(datos.getChofer());
        return viajeRepository.save(existente);
    }

    public void eliminar(int id) {
        viajeRepository.deleteById(id);
    }
    
    public List<Viaje> buscarPorFecha(LocalDate fecha) {
    return viajeRepository.findByFecha(fecha);
    }

    public List<Viaje> buscarPorOrigenODestino(String termino) {
        return viajeRepository.findByOrigenContainingIgnoreCaseOrDestinoContainingIgnoreCase(termino, termino);
    }
    
    public List<Viaje> filtrarViajes(String origen, String destino, LocalDate fecha) {
      return viajeRepository.filtrarViajes(origen, destino, fecha);
    }
    
    public List<Viaje> buscarPorOrigenDestinoYFecha(String origen, String destino, LocalDate fecha) {
    return viajeRepository.findByOrigenContainingIgnoreCaseAndDestinoContainingIgnoreCaseAndFecha(
        origen, destino, fecha
    );
    }
}