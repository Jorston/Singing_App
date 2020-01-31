package com.example.app.ConexionesRoom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface UtilidadesDao {

    @Insert
    public void agregarUsuario(UserRoom user);

    @Update
    public void update(UserRoom user);

    @Query ("SELECT * FROM usersRoom")
    public List<UserRoom> mostrarUsuarios();


}
