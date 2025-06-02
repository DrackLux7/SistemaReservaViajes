
package com.miempresa.sistema.controller;

import com.miempresa.sistema.dto.RegistroDTO;
import com.miempresa.sistema.model.IniciarSesion;
import com.miempresa.sistema.model.Usuario;
import com.miempresa.sistema.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody RegistroDTO dto) {
        try {
            if (dto.getEmail() == null || dto.getPassword() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Email y contraseña son obligatorios.");
            }
            
            System.out.println("Email recibido: " + dto.getEmail());
            System.out.println("Password recibido: " + dto.getPassword());

            Usuario usuario = mapear(dto);
            usuarioRepo.save(usuario);

            return ResponseEntity.ok("Usuario registrado.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno: " + e.getMessage());
        }
    }

    private Usuario mapear(RegistroDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellidos(dto.getApellidos());
        usuario.setTelefono(dto.getTelefono());

        IniciarSesion inicioSesion = new IniciarSesion();
        inicioSesion.setEmail(dto.getEmail());

        // Encriptar la contraseña antes de guardar
        String hashedPassword = passwordEncoder.encode(dto.getPassword());
        inicioSesion.setPassword(hashedPassword);

        inicioSesion.setRol("USUARIO");

        usuario.setInicioSesion(inicioSesion);
        return usuario;
    }
}