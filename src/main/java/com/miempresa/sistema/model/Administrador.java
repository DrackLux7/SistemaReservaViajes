
package com.miempresa.sistema.model;

import jakarta.persistence.*;

@Entity
@Table(name = "administrador")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String apellidos;
    private String telefono;

    @OneToOne
    @JoinColumn(name = "id_inicio_sesion", referencedColumnName = "id", unique = true)
    private IniciarSesion inicioSesion;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {    
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public IniciarSesion getInicioSesion() {
        return inicioSesion;
    }

    public void setInicioSesion(IniciarSesion inicioSesion) {
        this.inicioSesion = inicioSesion;
    }
    
}