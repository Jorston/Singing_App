package com.example.app.ConexionesRoom;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "usersRoom")

public class UserRoom {

    @PrimaryKey @NonNull
    private String userNick;

    @ColumnInfo
    private String nombre;

    @ColumnInfo (name = "apellidos" )
    private String apellidos;

    @ColumnInfo (name = "correo" )
    private String correo;

    @ColumnInfo (name = "contrasenha" )
    private String contrasenha;

    @ColumnInfo (name = "repContrasenha" )
    private String repContrasenha;

    @ColumnInfo (name = "rolUsuario")
    private  int rolUsuario;

    public int getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(int rolUsuario) {
        this.rolUsuario = rolUsuario;
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

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
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

    public String getRepContrasenha() {
        return repContrasenha;
    }

    public void setRepContrasenha(String repContrasenha) {
        this.repContrasenha = repContrasenha;
    }
}
