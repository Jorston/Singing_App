package com.example.app.ConexionesRoom;

import com.example.app.Interfaces.FormRegister;

public class MetodosRoom {

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
}
