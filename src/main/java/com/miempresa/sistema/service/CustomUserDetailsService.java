
package com.miempresa.sistema.service;

import com.miempresa.sistema.model.IniciarSesion;
import com.miempresa.sistema.repository.IniciarSesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IniciarSesionRepository iniciarSesionRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        IniciarSesion user = iniciarSesionRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Credenciales no válidas"));

        UserBuilder builder = User.withUsername(user.getEmail())
            .password(user.getPassword())  
            .roles(user.getRol().toUpperCase()); 

        return builder.build();
    }
    
    
}
