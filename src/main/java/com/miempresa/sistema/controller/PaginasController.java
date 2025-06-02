
package com.miempresa.sistema.controller;

import com.miempresa.sistema.model.Administrador;
import com.miempresa.sistema.model.IniciarSesion;
import com.miempresa.sistema.model.Usuario;
import com.miempresa.sistema.repository.AdministradorRepository;
import com.miempresa.sistema.repository.IniciarSesionRepository;
import com.miempresa.sistema.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.ui.Model; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginasController {
    
    @Autowired
    private IniciarSesionRepository iniciarSesionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @GetMapping({"/", "/index"})
    public String inicio() {
        return "index";  
    }

    @GetMapping("/login")
    public String login() {
        return "login"; 
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro";  
    }
    
    @GetMapping("/autobuses")
    public String autobuses() {
        return "autobuses";  
    }
    
    @GetMapping("/choferes")
    public String choferes() {
        return "choferes";  
    }
    
    @GetMapping("/reservas")
    public String reservas() {
        return "reservas";  
    }

    @GetMapping("/registroviaje")
    public String registroviaje() {
        return "registroviaje";  
    }
    
    @GetMapping("/reservascanceladas")
    public String reservascanceladas() {
        return "reservascanceladas";  
    }
    
    
    @GetMapping("/panelusuario")
    public String panelUsuario(Authentication auth, Model model) {
        String email = auth.getName();  
        Optional<IniciarSesion> login = iniciarSesionRepository.findByEmail(email);

        if (login.isPresent() && login.get().getRol().equalsIgnoreCase("usuario")) {
            Optional<Usuario> usuario = usuarioRepository.findByInicioSesion(login.get());
            usuario.ifPresent(u -> model.addAttribute("usuario", u));
            return "panelusuario";
        }
        return "redirect:/login?error=403";
    }

    @GetMapping("/paneladministrador")
    public String panelAdministrador(Authentication auth, Model model) {
        String email = auth.getName();
        Optional<IniciarSesion> login = iniciarSesionRepository.findByEmail(email);

        if (login.isPresent() && login.get().getRol().equalsIgnoreCase("administrador")) {
            Optional<Administrador> admin = administradorRepository.findByInicioSesion(login.get());
            admin.ifPresent(a -> model.addAttribute("admin", a));
            return "paneladministrador";
        }
        return "redirect:/login?error=403";
    }
    
     @GetMapping("/buscar")
    public String mostrarBusqueda() {
        return "buscar"; 
    }
}