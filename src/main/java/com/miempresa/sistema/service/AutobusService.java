package com.miempresa.sistema.service;

import com.miempresa.sistema.model.Autobus;
import com.miempresa.sistema.repository.AutobusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutobusService {

    @Autowired
    private AutobusRepository autobusRepository;

    public List<Autobus> listarTodos() {
        return autobusRepository.findAll();
    }

    public Autobus obtenerPorId(int id) {
        return autobusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autobús no encontrado"));
    }

public Autobus crear(Autobus autobus) {
    validarPlaca(autobus.getPlaca(), null);
    return autobusRepository.save(autobus);
}

public Autobus actualizar(int id, Autobus datos) {
    Autobus existente = obtenerPorId(id);
    validarPlaca(datos.getPlaca(), id);
    existente.setPlaca(datos.getPlaca());
    existente.setModelo(datos.getModelo());
    existente.setCapacidad(datos.getCapacidad());
    return autobusRepository.save(existente);
}

    public Optional<Autobus> buscarPorPlaca(String placa) {
        return autobusRepository.findByPlaca(placa);
    }
    
    public void eliminar(int id) {
    obtenerPorId(id); 
    autobusRepository.deleteById(id);
}

private void validarPlaca(String placa, Integer idActual) {
    Optional<Autobus> existente = autobusRepository.findByPlaca(placa);
    if (existente.isPresent() && (idActual == null || existente.get().getId() != idActual)) {
        throw new IllegalArgumentException("Ya existe un autobús con esa placa");
    }

    if (!placa.matches("^[A-Z]{2}-\\d{4}$")) {
        throw new IllegalArgumentException("La placa debe tener el formato AA-1234");
    }
}

}