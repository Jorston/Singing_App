package com.example.app.Modelos;

public class UserInfo {
    private String DNI, nombre;

    public UserInfo(String dni, String nombre) {
        DNI = dni;
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
