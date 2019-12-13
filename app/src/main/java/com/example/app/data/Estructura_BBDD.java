package com.example.app.data;

public class Estructura_BBDD {

    //constructor
    private Estructura_BBDD() {}

    //campos de la tabla constantes
    public static final String TABLE_NAME = "FormRegistros";
    public static final String NOMBRE_COLUMNA1 = "id";
    public static final String NOMBRE_COLUMNA2 = "nombre";
    public static final String NOMBRE_COLUMNA3 = "apellidos";
    public static final String NOMBRE_COLUMNA4 = "usernick";
    public static final String NOMBRE_COLUMNA5 = "correo";
    public static final String NOMBRE_COLUMNA6 = "contrasenha";
    public static final String NOMBRE_COLUMNA7 = "repcontrasenha";

    //construccion de la tabla
    private static final String TEXT_TYPE = "TEXT";
    private static final String COM_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Estructura_BBDD.TABLE_NAME + " (" +
                    Estructura_BBDD.NOMBRE_COLUMNA1 + " INTEGER PRIMARY KEY," +
                    Estructura_BBDD.NOMBRE_COLUMNA2 + TEXT_TYPE + COM_SEP +
                    Estructura_BBDD.NOMBRE_COLUMNA3 + TEXT_TYPE + COM_SEP +
                    Estructura_BBDD.NOMBRE_COLUMNA4 + TEXT_TYPE + COM_SEP +
                    Estructura_BBDD.NOMBRE_COLUMNA5 + TEXT_TYPE + COM_SEP +
                    Estructura_BBDD.NOMBRE_COLUMNA6 + TEXT_TYPE + COM_SEP +
                    Estructura_BBDD.NOMBRE_COLUMNA7 + TEXT_TYPE +")";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Estructura_BBDD.TABLE_NAME;
}
