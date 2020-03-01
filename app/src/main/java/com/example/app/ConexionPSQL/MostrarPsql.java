package com.example.app.ConexionPSQL;

import android.os.AsyncTask;
import androidx.lifecycle.MutableLiveData;
import com.example.app.ModelosAdaptadores.MformRegister;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MostrarPsql {

    //clase multitarea
    public static class hiloShowUsers extends AsyncTask<Void,Void, ArrayList<MformRegister>> {

        ArrayList<MformRegister> listaUsuers = new ArrayList<>();

        private final MutableLiveData<ArrayList<MformRegister>> result = new MutableLiveData<>();

        public MutableLiveData<ArrayList<MformRegister>> getResult() {
            return result;
        }

        @Override
        protected ArrayList<MformRegister> doInBackground(Void... Void) {

            //conexion para PSQL Instanciamos objetos
            ConexionPsql conexionPsql = new ConexionPsql();

            Connection con = null;

            con = conexionPsql.conectar();

            //si coneccion insertamos en PSQL
            if (con != null) {

                String sql = "SELECT * FROM userspostsql";

                try {
                    PreparedStatement preparedStatement = con.prepareStatement(sql);

                    ResultSet resultado = preparedStatement.executeQuery();

                    while (resultado.next()){

                        MformRegister usuarioLogin = new MformRegister();

                        usuarioLogin.setUserNick(resultado.getString(1));

                        usuarioLogin.setNombre(resultado.getString(2));

                        usuarioLogin.setApellidos(resultado.getString(3));

                        usuarioLogin.setCorreo(resultado.getString(4));

                        usuarioLogin.setContrasenha(resultado.getString(5));

                        usuarioLogin.setRepcontrasenha(resultado.getString(6));

                        listaUsuers.add(usuarioLogin);

                        System.out.println("ESTAMOS EN LA LISTA");

                    }

                } catch (SQLException e) {

                    e.printStackTrace();

                    System.out.println("EN EL CATCH");
                }
            }
            return listaUsuers;

        }//fin doInBackground

        @Override
        protected void onPostExecute(ArrayList<MformRegister> mformRegisters) {

            super.onPostExecute(mformRegisters);

            result.postValue(mformRegisters);
        }
    }
}