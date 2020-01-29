package com.example.app.ConexionesRoom;

public class MetodosUtilidadesRoom {

    public boolean validacionContrasenha(String password, String repPassword){

        boolean validator = false;

        if (password.equals(repPassword)){

            validator = true;

        }else{

            validator = false;
        }

        return validator;
    }
}
