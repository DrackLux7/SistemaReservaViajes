 
package com.miempresa.sistema.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
public class Autobus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Pattern(regexp = "^[A-Z]{2}-[0-9]{4}$", message = "Formato de placa inválido. Debe ser AA-NNNN.")
    @Column(unique = true)
    private String placa;

    private String modelo;
    
    private int capacidad;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
