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
import com.example.app.data.EscrituraFichaje;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Primero extends Fragment {
    //declaracion variables globales
    private OnFragmentInteractionListener mListener;
    ImageButton botonEntrada;
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
        mainActivity = new MainActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       //instaciamos objeto escritura para utilizar sus metodos
        final EscrituraFichaje escritura = new EscrituraFichaje();
        final String recuperamos_variable_string , fechaComoCadena, horaComoCadena;
        escritura.setContext(getActivity());
        //construccion de formateo de fecha
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
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
        botonEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                escritura.escrituraFichajes("jorge",date,date,date,date);
                Toast.makeText(getContext(),"el usuario es: "+recuperamos_variable_string+"  "+horaComoCadena,Toast.LENGTH_SHORT).show();
                escritura.lecturaFichajes();
            }
        });

        return vista;

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
