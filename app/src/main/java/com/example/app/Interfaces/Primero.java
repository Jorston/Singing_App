package com.example.app.Interfaces;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.app.ConexionesRoom.MetodosRoom;
import com.example.app.ConexionesRoom.MyDatabaseRoom;
import com.example.app.R;
import com.example.app.DataConexiones.EscrituraFichaje;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Primero extends Fragment {
    //declaracion variables globales
    private OnFragmentInteractionListener mListener;

    ImageButton botonEntrada,botonSalida;

    View vista;

    MainActivity mainActivity;

    TextView textUsuario,textFecha;

    final Date date = new Date();

    public static MyDatabaseRoom myDatabaseRoom;

    final MetodosRoom metodosRoom = new MetodosRoom();

    public Primero() {
        // Required empty public constructor

    }

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // mainActivity = new MainActivity();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       //instaciamos objeto escritura para utilizar sus metodos
        final EscrituraFichaje escritura = new EscrituraFichaje();

        //declaramos variables para usarlas en el contexto
        final String recuperamos_variable_string,fechaComoCadena,horaComoCadena;

        //le damos valor al objeto escritura con el contexto activity
        escritura.setContext(getActivity());

        //construccion de formateo de fecha
        final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        final SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");

        fechaComoCadena = sdf.format(new Date());

        horaComoCadena = hourFormat.format(new Date());

        //recuperacion de dato enviado por activity Main
        recuperamos_variable_string = getActivity().getIntent().getStringExtra("usuario");

        //variables para setear por el id
        vista = inflater.inflate(R.layout.fragment_primero, container, false);

        textUsuario = vista.findViewById(R.id.textUsuario);

        textUsuario.setText(recuperamos_variable_string);

        textFecha = vista.findViewById(R.id.textFecha);

        textFecha.setText(fechaComoCadena);

        botonEntrada = vista.findViewById(R.id.btnEntrada);

        botonSalida = vista.findViewById(R.id.btnSalida);

        //boton ejecuta el metodo para captura fichaje entrada
        botonEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //metodo validador que escibira el archivo si no existe
                if (escritura.validadorFichero(recuperamos_variable_string,fechaComoCadena,horaComoCadena,"entrada")){

                    escritura.lecturaFichajes();

                    showMessage("El archivo fichaje no existia fue creado tu fichaje fue "+fechaComoCadena+" "+ horaComoCadena);

                }else {

                    escritura.escrituraFichajes(recuperamos_variable_string,fechaComoCadena,horaComoCadena,"entrada");

                    escritura.lecturaFichajes();

                    showMessage("El archivo ya existia fue sobreescrito"+fechaComoCadena+" "+ horaComoCadena);
                }

                //metodo para insertar en BD con Room instancia a la conexion y objetos de la clase MyDatabaseRoom de Room y creamos la base de datos
                myDatabaseRoom = Room.databaseBuilder(getActivity().getApplicationContext(),MyDatabaseRoom.class, "usuariosLoginRoom.db").allowMainThreadQueries().build();

                metodosRoom.insertarFichajes(recuperamos_variable_string,fechaComoCadena,horaComoCadena,"entrada");

                showMessage("fichaje Room introducido correctamente en la BD");

                myDatabaseRoom.close();

            }
        });

        //boton ejecuta el metodo para captura fichaje salida
        botonSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //metodo validador que escibira el archivo si no existe
                if (escritura.validadorFichero(recuperamos_variable_string,fechaComoCadena,horaComoCadena,"salida")){

                    escritura.lecturaFichajes();

                    showMessage("El archivo fichaje no existia fue creado tu fichaje fue "+fechaComoCadena+" "+ horaComoCadena);

                }else {
                    escritura.escrituraFichajes(recuperamos_variable_string,fechaComoCadena,horaComoCadena,"salida");

                    escritura.lecturaFichajes();

                    showMessage("El archivo ya existia fue sobreescrito"+fechaComoCadena+" "+ horaComoCadena);
                }

                //metodo para insertar en BD con Room instancia a la conexion y objetos de la clase MyDatabaseRoom de Room y creamos la base de datos
                myDatabaseRoom = Room.databaseBuilder(getActivity().getApplicationContext(),MyDatabaseRoom.class, "usuariosLoginRoom.db").allowMainThreadQueries().build();

                metodosRoom.insertarFichajes(recuperamos_variable_string,fechaComoCadena,horaComoCadena,"salida");

                showMessage("fichaje Room introducido correctamente en la BD");

                myDatabaseRoom.close();

            }
        });
        return vista;
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

