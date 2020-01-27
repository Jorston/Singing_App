package com.example.app.ConexionesRoom;

import com.example.app.Interfaces.FormRegister;
import com.example.app.Interfaces.Primero;

public class MetodosRoom {

    //insercion usuario del formulario de registro
    public void insertarUserRoom(String nombre, String apellidos, String correo, String userNick, String contrasenha, String repcontrasenha) {

        UserRoom userRoom = new UserRoom();

        userRoom.setNombre(nombre);

        userRoom.setApellidos(apellidos);

        userRoom.setCorreo(correo);

        userRoom.setUserNick(userNick);

        userRoom.setContrasenha(contrasenha);

        userRoom.setRepContrasenha(repcontrasenha);

        FormRegister.myDatabaseRoom.utilidadesDao().agregarUsuario(userRoom);
    }

    //insercion de fichajes
    public void insertarFichajes(String usuario,String diaFichaje,String horaFichaje,String tipoFichaje){

        FichajeRoom fichajeRoom = new FichajeRoom();

        fichajeRoom.setUsuario(usuario);

        fichajeRoom.setDiaFichaje(diaFichaje);

        fichajeRoom.setHorafichaje(horaFichaje);

        fichajeRoom.setTipoFichaje(tipoFichaje);

        Primero.myDatabaseRoom.utilidadesDao().agregarFichaje(fichajeRoom);
    }

}
