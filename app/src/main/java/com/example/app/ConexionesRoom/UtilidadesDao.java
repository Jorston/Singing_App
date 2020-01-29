package com.example.app.ConexionesRoom;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface UtilidadesDao {

    @Insert
    public void agregarUsuario(UserRoom user);

    @Insert
    public  void agregarFichaje(FichajeRoom fichaje);

    @Query ("SELECT * FROM usersRoom")
    public List<UserRoom> mostrarUsuarios();

    @Query("SELECT * FROM fichajesRoom")
    public List<FichajeRoom> mostrarFichajes();

    @Query("select userNick from usersRoom")
    public List<UserRoom> verificarUsuarios();
}
