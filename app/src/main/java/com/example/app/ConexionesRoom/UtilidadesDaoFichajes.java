package com.example.app.ConexionesRoom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UtilidadesDaoFichajes {

    @Insert
    public  void agregarFichaje(FichajeRoom fichaje);

    @Query("SELECT * FROM fichajesRoom")
    public List<FichajeRoom> mostrarFichajes();
}
