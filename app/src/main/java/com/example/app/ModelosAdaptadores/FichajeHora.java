package com.example.app.ModelosAdaptadores;

import java.io.Serializable;

public class FichajeHora implements Serializable {
    private String user,tipoMarcado,fechaEntrada, horaEntrada;
    //private Date fechaEntrada, horaEntrada;
    private static final long serailVersionUI = 1L;

    public  FichajeHora(){

    }

    public FichajeHora(String user, String fechaEntrada, String horaEntrada, String tipoMarcado) {
        this.user = user;
        this.fechaEntrada = fechaEntrada;
        this.horaEntrada = horaEntrada;
        this.tipoMarcado = tipoMarcado;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public void setTipoMarcado(String tipoMarcado) { this.tipoMarcado = tipoMarcado; }

    public String getUser() {
        return user;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public String getTipoMarcado() { return tipoMarcado; }

}
