
package com.miempresa.sistema.controller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class IniciarSesionController {

    @GetMapping("/redirect")
    public String redirectAfterLogin(Authentication auth) {
        System.out.println("ROL: " + auth.getAuthorities()); 

        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"))) {
            return "redirect:/paneladministrador";
        } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USUARIO"))) {
            return "redirect:/panelusuario";
        } else {
            return "redirect:/login?error";
        }
    }
}
