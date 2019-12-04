package com.example.app.Modelos;

import java.util.Date;

public class Fichaje {
    private String dni, nombre;
    private Date horaFichada;
    private TipoFichaje tipoFichaje;

    public Fichaje(String dni, String nombre, Date horaFichada, TipoFichaje tipoFichaje) {
        this.dni = dni;
        this.nombre = nombre;
        this.horaFichada = horaFichada;
        this.tipoFichaje = tipoFichaje;
    }

    public TipoFichaje getTipoFichaje() {
        return tipoFichaje;
    }

    public void setTipoFichaje(TipoFichaje tipoFichaje) {
        this.tipoFichaje = tipoFichaje;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getHoraFichada() {
        return horaFichada;
    }

    public void setHoraFichada(Date horaFichada) {
        this.horaFichada = horaFichada;
    }
}
