package com.example.app.Interfaces;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.app.ConexionPSQL.ConexionPsql;
import com.example.app.R;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Delete extends Fragment {

    private OnFragmentInteractionListener mListener;

    Button btnEliminar;

    TextView nickEliminar;

    View vista;

    boolean interruptor;

    public Delete() {
        // Required empty public constructor
    }

    public static Delete newInstance(String param1, String param2) {
        Delete fragment = new Delete();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_delete, container, false);

        btnEliminar = vista.findViewById(R.id.botonDelete);

        nickEliminar = vista.findViewById(R.id.textnickDelete);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //instacia de postgres
                final hiloEliminarnick hiloEliminacion = new hiloEliminarnick(nickEliminar.getText().toString());

                hiloEliminacion.execute();
            }
        });



        return vista;
    }

    //clase multitarea
    public class hiloEliminarnick extends AsyncTask<String,Void,String> {

        private String nickUsuario;

        public hiloEliminarnick(String nickUsuario) {
            this.nickUsuario = nickUsuario;
        }

        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... strings) {

            //conexion para PSQL Instanciamos objetos
            ConexionPsql conexionPsql = new ConexionPsql();

            Connection con = null;

            con = conexionPsql.conectar();

            //si coneccion insertamos en PSQL
            if (con != null){

                try {

                    String deleteUser = "DELETE FROM userspostsql where usernick = ? ;";

                    PreparedStatement eliminarUser;

                    eliminarUser = con.prepareStatement(deleteUser);

                    eliminarUser.setString(1,nickEliminar.getText().toString());

                    eliminarUser.executeUpdate();

                    interruptor = true;


                } catch (SQLException e) {

                    e.printStackTrace();
                }
            }else{
                
            }

            return null;
        }

        protected void onPostExecute(String s) {

            super.onPostExecute(s);

            if (interruptor){

                showMessage("Usuario eliminado");

            }else{

                showMessage("no existe el usuario");
            }

        }
    }

    //metodo atajo para el toast vista usuario
    protected void showMessage(String message){

        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
