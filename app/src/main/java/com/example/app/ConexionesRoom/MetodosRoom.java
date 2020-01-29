package com.example.app.ConexionesRoom;

import com.example.app.Interfaces.FormRegister;
import com.example.app.Interfaces.MainActivity;
import com.example.app.Interfaces.Primero;
import java.util.List;

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

    //validacion de usuario en el login de MainActivity
    public boolean validarUsuarios(String user, String contrasenha){

        int contador = 0;

        boolean cambio = false;

        List<UserRoom> listaUsuarios= MainActivity.myDatabaseRoom.utilidadesDao().mostrarUsuarios();

        for (UserRoom users : listaUsuarios){

            System.out.println("USUARIOS: "+users.getUserNick()+"\n"+users.getContrasenha());

            if ((user.equals(users.getUserNick())) && (contrasenha.equals(users.getContrasenha()))){

                System.out.println("CONTADOR ES EQUAL : "+contador);

                contador++;

            }else {

                System.out.println("CONTADOR ES NOOOEQUAL : "+contador);
            }
        }

        //metodo auxiliar para validar el usuario que hara login
        if (contador>0){

            System.out.println("CONTADOR VERDA ES : "+contador);

            cambio=true;

        }else{

            System.out.println("CONTADOR FALSO ES : "+contador);

            cambio=false;
        }

        return cambio;
    }

}
