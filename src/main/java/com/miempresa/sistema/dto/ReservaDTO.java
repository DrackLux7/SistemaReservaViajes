
package com.miempresa.sistema.dto;

import com.miempresa.sistema.model.Reserva;

public class ReservaDTO {
    
    private int id;
    private ViajeDTO viaje;
    private int cantidadPasajes;
    private String estado;

    public ReservaDTO(Reserva reserva) {
        this.id = reserva.getId();
        this.viaje = new ViajeDTO(reserva.getViaje());
        this.cantidadPasajes = reserva.getCantidadPasajes();
        this.estado = reserva.getEstado().toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ViajeDTO getViaje() {
        return viaje;
    }

    public void setViaje(ViajeDTO viaje) {
        this.viaje = viaje;
    }

    public int getCantidadPasajes() {
        return cantidadPasajes;
    }

    public void setCantidadPasajes(int cantidadPasajes) {
        this.cantidadPasajes = cantidadPasajes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
}
