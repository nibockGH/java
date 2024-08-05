package com.example.mecanicos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reparacion {
    private int id;
    private int vehiculoId;
    private String descripcion;
    private LocalDate fecha;
    private BigDecimal costo;

    // Constructor, getters y setters
    public Reparacion(int vehiculoId, String descripcion, LocalDate fecha, BigDecimal costo) {
        this.vehiculoId = vehiculoId;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.costo = costo;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }
}
