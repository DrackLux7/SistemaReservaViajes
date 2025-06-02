
package com.miempresa.sistema.dto;

import com.miempresa.sistema.model.Viaje;

public class ViajeDTO {
    private int id;
    private String fecha;
    private String hora;
    private String origen;
    private String destino;

    public ViajeDTO(Viaje viaje) {
        this.id = viaje.getId();
        this.fecha = viaje.getFecha().toString();
        this.hora = viaje.getHora().toString();
        this.origen = viaje.getOrigen();
        this.destino = viaje.getDestino();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
       

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

}
