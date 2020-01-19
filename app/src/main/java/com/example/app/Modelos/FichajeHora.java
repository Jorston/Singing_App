package com.example.app.Modelos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FichajeHora implements Serializable {
    private String user,tipoMarcado;
    private Date fechaEntrada, horaEntrada;
    private static final long serailVersionUI = 1L;

    public  FichajeHora(){

    }

    public FichajeHora(String user, Date fechaEntrada, Date horaEntrada, String tipoMarcado) {
        this.user = user;
        this.fechaEntrada = fechaEntrada;
        this.horaEntrada = horaEntrada;
        this.tipoMarcado = tipoMarcado;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public void setTipoMarcado(String tipoMarcado) { this.tipoMarcado = tipoMarcado; }

    public String getUser() {
        return user;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public String getTipoMarcado() { return tipoMarcado; }

}
