package com.example.app.Modelos;

import java.io.Serializable;

public class Fichaje implements Serializable {
    private String mail;
    private String horaFichada;
    private TipoFichaje tipoFichaje;

    public Fichaje(String nombre, String horaFichada, TipoFichaje tipoFichaje) {
        this.mail = nombre;
        this.horaFichada = horaFichada;
        this.tipoFichaje = tipoFichaje;
    }

    public CharSequence getTipoFichaje() {
        return tipoFichaje;
    }

    public void setTipoFichaje(TipoFichaje tipoFichaje) {
        this.tipoFichaje = tipoFichaje;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getHoraFichada() {
        return horaFichada;
    }

    public void setHoraFichada(String horaFichada) {
        this.horaFichada = horaFichada;
    }
}
