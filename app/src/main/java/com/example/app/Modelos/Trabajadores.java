package com.example.app.Modelos;

public class Trabajadores{
    private String nombre,apellidos,cargo;
    private  String imagenId;

    public Trabajadores(String imagenId,String nombre, String apellidos, String cargo) {
        this.imagenId = imagenId;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cargo = cargo;
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

    public String getImagenId() {
        return imagenId;
    }

    public void setImagenId(String imagenId) {
        this.imagenId = imagenId;
    }

}
