package com.miempresa.sistema.service;

import com.miempresa.sistema.model.Chofer;
import com.miempresa.sistema.repository.ChoferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChoferService {

    @Autowired
    private ChoferRepository choferRepository;

    public List<Chofer> listarTodos() {
        return choferRepository.findAll();
    }

    public Chofer obtenerPorId(int id) {
        return choferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chofer no encontrado"));
    }

    public Chofer crear(Chofer chofer) {
        validarCodigo(chofer.getCodigo());
        return choferRepository.save(chofer);
    }

    public Chofer actualizar(int id, Chofer datos) {
        Chofer existente = obtenerPorId(id);
        validarCodigo(datos.getCodigo());
        existente.setCodigo(datos.getCodigo());
        existente.setNombre(datos.getNombre());
        existente.setLicencia(datos.getLicencia());
        return choferRepository.save(existente);
    }

    public void eliminar(int id) {
        choferRepository.deleteById(id);
    }

    private void validarCodigo(String codigo) {
        if (!codigo.matches("^CH-\\d{4}$")) {
            throw new IllegalArgumentException("El código debe tener el formato CH-NNNN");
        }
    }
    
     public Optional<Chofer> buscarPorCodigo(String codigo) {
        return choferRepository.findByCodigo(codigo);
    }
}