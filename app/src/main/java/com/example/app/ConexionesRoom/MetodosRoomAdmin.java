package com.example.app.ConexionesRoom;

import com.example.app.Interfaces.AdminLogin;
import com.example.app.Interfaces.FormRegister;
import com.example.app.Interfaces.FragmentCreateEmpleFirebase;

import java.util.List;

public class MetodosRoomAdmin {

    //validacion de usuario en el login admin
    public boolean validarUsuariosAdmin(String user, String contrasenha){

        int contador = 0;

        boolean cambio = false;

        List<UserRoom> listaUsuarios= AdminLogin.myDatabaseRoom.utilidadesDao().mostrarUsuarios();

        for (UserRoom users : listaUsuarios){

            if ((user.equals(users.getUserNick())) && (contrasenha.equals(users.getContrasenha())) && users.getRolUsuario() == 1 ){

                contador++;
            }
        }

        //metodo auxiliar para validar el usuario que hara login
        if (contador>0){

            cambio=true;

        }else{

            cambio=false;
        }

        return cambio;
    }

    //insercion usuario del formulario de registro
    public void insertarUserRoomAdmin(String nombre, String apellidos, String correo, String userNick, String contrasenha, String repcontrasenha) {

        UserRoom userRoom = new UserRoom();

        userRoom.setNombre(nombre);

        userRoom.setApellidos(apellidos);

        userRoom.setCorreo(correo);

        userRoom.setUserNick(userNick);

        userRoom.setContrasenha(contrasenha);

        userRoom.setRepContrasenha(repcontrasenha);

        FragmentCreateEmpleFirebase.myDatabaseRoom.utilidadesDao().agregarUsuario(userRoom);

    }
}
