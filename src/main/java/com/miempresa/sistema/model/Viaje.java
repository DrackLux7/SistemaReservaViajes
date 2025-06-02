package com.miempresa.sistema.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "viaje")
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private LocalDate fecha;

    @NotNull
    private LocalTime hora;

    @NotBlank
    private String origen;

    @NotBlank
    private String destino;

    @ManyToOne
    @JoinColumn(name = "id_autobus")
    private Autobus autobus;

    @ManyToOne
    @JoinColumn(name = "id_chofer")
    private Chofer chofer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
  

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public Autobus getAutobus() { return autobus; }
    public void setAutobus(Autobus autobus) { this.autobus = autobus; }

    public Chofer getChofer() { return chofer; }
    public void setChofer(Chofer chofer) { this.chofer = chofer; }
}
