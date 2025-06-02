
package com.miempresa.sistema.dto;

public class AutobusDTO {
    private Long id;
    private String placa;
    private int capacidad;

    public AutobusDTO(Long id, String placa, int capacidad) {
        this.id = id;
        this.placa = placa;
        this.capacidad = capacidad;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
}