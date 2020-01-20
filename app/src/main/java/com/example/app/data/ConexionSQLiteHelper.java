package com.example.app.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    public static final String BASEDATOS = "usuariosLogin.db";

    public ConexionSQLiteHelper(Context context) {

        super(context, BASEDATOS, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UtilidadesDB.CREAR_TABLA_USUARIOS);
        db.execSQL(UtilidadesDbFichajes.CREAR_TABLA_FICHAJES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS db_usuarios");
        db.execSQL("DROP TABLE IF EXISTS db_fichajes");
        onCreate(db);
    }

    public void insertar(String nombre,String apellidos,String correo,String userNick,String contra,String repcontra){
        ContentValues valores = new ContentValues();
        valores.put(UtilidadesDB.CAMPO_NOMBRE, nombre);
        valores.put(UtilidadesDB.CAMPO_APELLIDOS, apellidos);
        valores.put(UtilidadesDB.CAMPO_CORREO, correo);
        valores.put(UtilidadesDB.CAMPO_USERNICK,userNick);
        valores.put(UtilidadesDB.CAMPO_CONTRASENHA, contra);
        valores.put(UtilidadesDB.CAMPO_REPCONTRASENHA, repcontra);
        this.getWritableDatabase().insert(UtilidadesDB.TABLA_USUARIO,null,valores);

    }

    public void insertarFichajes(String user,String fechaFichaje,String horaFichaje, String tipoFichaje){
        ContentValues fichajes = new ContentValues();
        fichajes.put(UtilidadesDbFichajes.USUARIO,user);
        fichajes.put(UtilidadesDbFichajes.FECHA_FICHAJE,fechaFichaje);
        fichajes.put(UtilidadesDbFichajes.HORA_FICHAJE,horaFichaje);
        fichajes.put(UtilidadesDbFichajes.TIPO_FICHAJE,tipoFichaje);
        this.getWritableDatabase().insert(UtilidadesDbFichajes.TABLA_FICHAJES,null,fichajes);
    }

    public void abrir(){
        this.getWritableDatabase();
    }
    public void cerrar(){
        this.close();
    }
}
