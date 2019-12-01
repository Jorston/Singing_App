package com.example.app.Modelos;

public class Trabajadores {
    private String nombre,apellidos,cargo,correo;

    public Trabajadores(String nombre, String apellidos, String cargo, String correo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cargo = cargo;
        this.correo = correo;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Trabajadores{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", cargo='" + cargo + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}
