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

        Primero.myDatabaseRoom.utilidadesDaoFichajes().agregarFichaje(fichajeRoom);
    }

    //validacion de usuario en el login de MainActivity
    public boolean validarUsuarios(String user, String contrasenha){

        int contador = 0;

        boolean cambio = false;

        List<UserRoom> listaUsuarios= MainActivity.myDatabaseRoom.utilidadesDao().mostrarUsuarios();

        for (UserRoom users : listaUsuarios){

            if ((user.equals(users.getUserNick())) && (contrasenha.equals(users.getContrasenha()))){

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

    //validacion userRegistre Formulario de Registro
    public boolean verificarFormulario(String userNick){
        int acumulador = 0;

        boolean interruptor = false;

        List<UserRoom> listaNicks= MainActivity.myDatabaseRoom.utilidadesDao().mostrarUsuarios();

        for (UserRoom usersNicks : listaNicks){

            if (usersNicks.getUserNick().equals(userNick)){

                acumulador++;

            }
        }
        //metodo auxiliar para validar el usuario que hara login
        if (acumulador>0){

            interruptor=true;

        }else{

            interruptor=false;
        }

        return interruptor;
    }

    //validacion userRegistre Formulario de Registro
    public void insertRol(){

        List<UserRoom> listaNicks= FormRegister.myDatabaseRoom.utilidadesDao().mostrarUsuarios();

        for (UserRoom usersNicks : listaNicks){

            if (usersNicks.getUserNick().equals("Erick")){

                usersNicks.setRolUsuario(1);

            }

            FormRegister.myDatabaseRoom.utilidadesDao().update(usersNicks);
        }
    }

}