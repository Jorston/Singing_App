package com.example.app.ConexionesRoom;


import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface UtilidadesDao {

    @Insert
    public void agregarUsuario(UserRoom user);
}
