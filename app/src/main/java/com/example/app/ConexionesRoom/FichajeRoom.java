package com.example.app.ConexionesRoom;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity (tableName = "fichajesRoom",foreignKeys = @ForeignKey(entity = UserRoom.class,
        parentColumns = "userNick",
        childColumns = "usuario", onDelete = ForeignKey.CASCADE))

public class FichajeRoom {

    @PrimaryKey (autoGenerate = true)
    private int posicion;

    @ColumnInfo (name = "usuario")
    private String usuario;

    @ColumnInfo (name = "fechaFichaje")
    private String diaFichaje;

    @ColumnInfo (name = "horaFichaje")
    private String horafichaje;

    @ColumnInfo (name = "tipoFichaje")
    private String tipoFichaje;

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDiaFichaje() {
        return diaFichaje;
    }

    public void setDiaFichaje(String diaFichaje) {
        this.diaFichaje = diaFichaje;
    }

    public String getHorafichaje() {
        return horafichaje;
    }

    public void setHorafichaje(String horafichaje) {
        this.horafichaje = horafichaje;
    }

    public String getTipoFichaje() {
        return tipoFichaje;
    }

    public void setTipoFichaje(String tipoFichaje) {
        this.tipoFichaje = tipoFichaje;
    }
}
