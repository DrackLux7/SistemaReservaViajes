package com.miempresa.sistema.service;

import com.miempresa.sistema.dto.RegistroDTO;
import com.miempresa.sistema.model.IniciarSesion;
import com.miempresa.sistema.model.Usuario;
import com.miempresa.sistema.repository.IniciarSesionRepository;
import com.miempresa.sistema.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service 
public class UsuarioService {

    @Autowired
    private IniciarSesionRepository loginRepo;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

     public Usuario registrarUsuario(Usuario usuario) {
        IniciarSesion login = usuario.getInicioSesion();

        if (loginRepo.findByEmail(login.getEmail()).isPresent()) {
            throw new RuntimeException("Email ya registrado.");
        }

        login.setRol("USUARIO");
        login.setPassword(passwordEncoder.encode(login.getPassword()));
        return usuarioRepository.save(usuario);
    }
     
     public Usuario mapRegistroDtoToUsuario(RegistroDTO dto) {
    Usuario usuario = new Usuario();
    usuario.setNombre(dto.getNombre());
    usuario.setApellidos(dto.getApellidos());
    usuario.setTelefono(dto.getTelefono());

    IniciarSesion login = new IniciarSesion();
    login.setEmail(dto.getEmail());
    login.setPassword(dto.getPassword()); 
    login.setRol("USUARIO");

    usuario.setInicioSesion(login);

    return usuario;
}
  
    public Usuario obtenerPorEmail(String email) {
        return usuarioRepository.findByInicioSesion_Email(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
    }

    public Usuario obtenerUsuarioAutenticado(Authentication authentication) {
        String email = authentication.getName();
        return obtenerPorEmail(email);
    }
}