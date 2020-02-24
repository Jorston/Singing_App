package com.example.app.Interfaces;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.room.Room;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.app.ConexionPSQL.ConexionPsql;
import com.example.app.ConexionesRoom.MetodosRoom;
import com.example.app.ConexionesRoom.MyDatabaseRoom;
import com.example.app.R;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeUpdate extends Fragment {

    private OnFragmentInteractionListener mListener;

    Button botonUpdate;

    TextView nombreUpdateAntiguo,nombreUpdateNuevo;

    View vista;

    boolean interruptor;

    public static MyDatabaseRoom myDatabaseRoom;

    final MetodosRoom metodosRoom = new MetodosRoom();

    public DeUpdate() {
        // Required empty public constructor
    }

    public static DeUpdate newInstance(String param1, String param2) {
        DeUpdate fragment = new DeUpdate();
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
        vista = inflater.inflate(R.layout.fragment_de_update, container, false);

        botonUpdate = vista.findViewById(R.id.btnActualizarCambio);

        nombreUpdateAntiguo = vista.findViewById(R.id.textUpdatenombreAnt);

        nombreUpdateNuevo = vista.findViewById(R.id.textUpdatenombreNuevo);

        botonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //instacia de postgres
                final hiloUpdatenombre updatenicks= new hiloUpdatenombre(nombreUpdateNuevo.getText().toString(),nombreUpdateAntiguo.getText().toString());

                updatenicks.execute();

            }
        });

        return vista;
    }

    //clase multitarea
    public class hiloUpdatenombre extends AsyncTask<String,Void,String> {

        private final String nombreAntiguo;

        private final String nombreNuevo;

        public hiloUpdatenombre(String nombreAntiguo, String nombreNuevo) {

            this.nombreAntiguo = nombreAntiguo;

            this.nombreNuevo = nombreNuevo;
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

                    //primer valor es el nuevo que entrada en la tabla
                    String updateUsername = "UPDATE userspostsql set usernick = ? where usernick = ? ;";

                    PreparedStatement prepaupdateUsername;

                    prepaupdateUsername = con.prepareStatement(updateUsername);

                    prepaupdateUsername.setString(1, nombreUpdateNuevo.getText().toString());

                    prepaupdateUsername.setString(2, nombreUpdateAntiguo.getText().toString());

                    prepaupdateUsername.executeUpdate();

                    interruptor = true;

                } catch (SQLException e) {

                    e.printStackTrace();
                }

            }else{

                //instancia a la conexion y objetos de la clase MyDatabaseRoom de Room y creamos la base de datos
                myDatabaseRoom = Room.databaseBuilder(getContext().getApplicationContext(), MyDatabaseRoom.class, "usuariosLoginRoom.db").allowMainThreadQueries().build();

                metodosRoom.actualizarUserNick(nombreUpdateAntiguo.getText().toString(),nombreUpdateNuevo.getText().toString());

                System.out.println("VALOR DE ANT "+nombreUpdateAntiguo.getText().toString()+"OTRO NOMBRE "+ nombreUpdateNuevo.getText().toString());

                myDatabaseRoom.close();

                interruptor = true;

            }

            return null;
        }

        protected void onPostExecute(String s) {

            super.onPostExecute(s);

            if (interruptor){
                showMessage("nick actualizado");
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
