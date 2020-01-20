package com.example.app.data;

public class UtilidadesDbFichajes {
    //constantes campos y tabla de DB Fichajes
    public static final String TABLA_FICHAJES = "db_fichajes";
    public static final String FECHA_ENTRADA ="fechaEntrada";
    public static final String HORA_ENTRADA ="horaEntrada";
    public static final String FECHA_SALIDA ="fechaSalida";
    public static final String HORA_SALIDA ="horaSalida";
    public static final String TIPO_FICHAJE ="tipoFichaje";
    public static final String USUARIO = "usuario";
    public static final String CREAR_TABLA_FICHAJES =
            "CREATE TABLE "+TABLA_FICHAJES+" ( "+FECHA_ENTRADA+" Date,"+HORA_ENTRADA+" Date,"+FECHA_SALIDA+" Date,"+HORA_SALIDA+" Date,"+TIPO_FICHAJE+" ENUM('entrada','salida'),"+USUARIO+" text, FOREIGN KEY ("+USUARIO+") REFERENCES "+UtilidadesDB.TABLA_USUARIO+"("+USUARIO+")";

}
