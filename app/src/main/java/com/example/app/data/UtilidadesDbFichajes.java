package com.example.app.data;

public class UtilidadesDbFichajes {
    //constantes campos y tabla de DB Fichajes
    public static final String TABLA_FICHAJES = "db_fichajes";
    public static final String USUARIO = "usuario";
    public static final String FECHA_FICHAJE ="fechaFichaje";
    public static final String HORA_FICHAJE ="horaFichaje";
    public static final String TIPO_FICHAJE ="tipoFichaje";
    public static final String CREAR_TABLA_FICHAJES =
            "CREATE TABLE "+TABLA_FICHAJES+" ( "+USUARIO+" text,"+FECHA_FICHAJE+" Date,"+HORA_FICHAJE+" Date,"+TIPO_FICHAJE+" text);";

            //"CREATE TABLE "+TABLA_FICHAJES+"("+FECHA_ENTRADA+" Date,"+HORA_ENTRADA+" Date,"+FECHA_SALIDA+" Date,"+HORA_SALIDA+" Date,"+TIPO_FICHAJE+" text,"+USUARIO+" text, FOREIGN KEY ("+USUARIO+") REFERENCES "+UtilidadesDB.TABLA_USUARIO+"("+UtilidadesDB.CAMPO_USERNICK+"));";

}
