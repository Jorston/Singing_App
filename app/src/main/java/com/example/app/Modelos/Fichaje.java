package com.example.app.Modelos;

public class Fichaje {
    private String dni, nombre , apellido;
    private String horaFichada;
    private TipoFichaje tipoFichaje;

    public Fichaje(String dni, String nombre,String apellido, String horaFichada, TipoFichaje tipoFichaje) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.horaFichada = horaFichada;
        this.tipoFichaje = tipoFichaje;
    }

    public Fichaje(){}

    public CharSequence getTipoFichaje() {
        return tipoFichaje;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public String getHoraFichada() {
        return horaFichada;
    }

    public void setHoraFichada(String horaFichada) {
        this.horaFichada = horaFichada;
    }
}
