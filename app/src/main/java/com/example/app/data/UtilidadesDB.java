package com.example.app.data;

public class UtilidadesDB {
    //nombre,apellidos,correos,userNick,contra,repContra
    //constantes campos y tabla de DB usuarios
    public static final String TABLA_USUARIO = "db_usuarios";
    public static final String CAMPO_ID = "id_usuario";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_APELLIDOS = "apellidos";
    public static final String CAMPO_CORREO = "correo";
    public static final String CAMPO_USERNICK = "usernick";
    public static final String CAMPO_CONTRASENHA = "contrasenha";
    public static final String CAMPO_REPCONTRASENHA = "repcontrasenha";


    public static final String CREAR_TABLA_USUARIOS = "CREATE TABLE"+TABLA_USUARIO+"("+CAMPO_ID+" INTEGER,"+CAMPO_NOMBRE+" TEXT,"
            +CAMPO_APELLIDOS+" TEXT "+CAMPO_CORREO+" TEXT "+CAMPO_USERNICK+" TEXT "+CAMPO_CONTRASENHA+" TEXT "+CAMPO_REPCONTRASENHA+" TEXT )";
}
