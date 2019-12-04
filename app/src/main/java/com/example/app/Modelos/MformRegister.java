package com.example.app.Modelos;

import java.io.Serializable;

public class MformRegister implements Serializable {
    private String nombre,apellidos,correo,contrasenha,repcontrasenha;

    public MformRegister(String nombre, String apellidos, String correo, String contrasenha, String repcontrasenha) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasenha = contrasenha;
        this.repcontrasenha = repcontrasenha;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getRepcontrasenha() {
        return repcontrasenha;
    }

    public void setRepcontrasenha(String repcontrasenha) {
        this.repcontrasenha = repcontrasenha;
    }

    @Override
    public String toString() {
        return "MformRegister{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasenha='" + contrasenha + '\'' +
                ", repcontrasenha='" + repcontrasenha + '\'' +
                '}';
    }
}
