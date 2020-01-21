package com.example.app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.data.ConexionSQLiteHelper;
import com.example.app.data.EscrituraFichaje;
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
        // Inflate the layout for this fragment
       //instaciamos objeto escritura para utilizar sus metodos
        final EscrituraFichaje escritura = new EscrituraFichaje();
        final String recuperamos_variable_string,fechaComoCadena,horaComoCadena;
        //le damos valor al objeto escritura con el contexto activity
        escritura.setContext(getActivity());
        //construccion de formateo de fecha
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
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

                //metodo para insertar en BD
                ConexionSQLiteHelper helperentrada = new ConexionSQLiteHelper(getContext());
                helperentrada.abrir();
                helperentrada.insertarFichajes(recuperamos_variable_string,fechaComoCadena,horaComoCadena,"entrada");
                helperentrada.cerrar();
                showMessage("fichaje con la entrada registrada");
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

                //metodo para insertar en BD
                ConexionSQLiteHelper helpersalida = new ConexionSQLiteHelper(getContext());
                helpersalida.abrir();
                helpersalida.insertarFichajes(recuperamos_variable_string,fechaComoCadena,horaComoCadena,"salida");
                helpersalida.cerrar();
                showMessage("fichaje con la salida registrada");
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

