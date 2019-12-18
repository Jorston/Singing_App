package com.example.app.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Registros_BD extends SQLiteOpenHelper {

    //public MformRegister(String nombre, String apellidos, String userNick, String correo, String contrasenha, String repcontrasenha) {
    private static final String NOMBRE_BD = "newregistros.db";
    private static final int VERSION_BD = 1;
    private static final String NOMBRE = "NOMBRE";
    private static final String APELLIDOS = "APELLIDOS";
    private static final String USERNICK = "USERNICK";

    private static final String TABLA_REGISTROS =String.format("CREATE TABLE REGISTROS (NOMBRE TEXT PRIMARY KEY,APELLIDOS TEXT,USERNICK TEXT)");

    public Registros_BD(Context context){
        super(context,NOMBRE_BD,null,VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_REGISTROS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(String.format("DROP TABLE IF EXISTS", TABLA_REGISTROS));
        sqLiteDatabase.execSQL(TABLA_REGISTROS);

    }

    public void agregarRegistros(String nombre, String apellidos, String usernick){
        SQLiteDatabase bd = getWritableDatabase();
        if (bd!=null){
            bd.execSQL(String.format("INSERT INTO TABLA_REGISTROS VALUES('"+nombre+"','"+apellidos+"','"+usernick+"')"));
        }else{
            System.out.println("ERROOOOOOOOOOOOOOOOOOORRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
        }
    }
}

