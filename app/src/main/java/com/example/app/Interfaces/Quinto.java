package com.example.app.Interfaces;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Quinto extends Fragment {

    DatabaseReference mRootReference;

    Button botonEnviar;

    EditText tMensaje;

    String mensaje,fechaComoCadena,horaComoCadena;

    private String recuperamos_variable_string;

    //construccion de formateo de fecha
    final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    final SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");

    private OnFragmentInteractionListener mListener;

    public Quinto() {
        // Required empty public constructor
    }

    public static Quinto newInstance(String param1, String param2) {
        Quinto fragment = new Quinto();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_quinto, container, false);

        mRootReference = FirebaseDatabase.getInstance().getReference();

        fechaComoCadena = sdf.format(new Date());

        horaComoCadena = hourFormat.format(new Date());

        recuperamos_variable_string = getActivity().getIntent().getStringExtra("usuario");

        tMensaje = vista.findViewById(R.id.tMensaje);

        botonEnviar = vista.findViewById(R.id.btnEnviarMensaje);

        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                envioMensaje();
            }
        });

        return vista;
    }

    private void envioMensaje() {

        mensaje = tMensaje.getText().toString();

        Map<String, Object> datosMensaje = new HashMap<>();

        datosMensaje.put("nombre", recuperamos_variable_string);

        datosMensaje.put("mensaje", mensaje);

        datosMensaje.put("fecha",fechaComoCadena+" "+horaComoCadena);

        mRootReference.child("Mensajes Insidencias").child("usuarios").push().setValue(datosMensaje);

        tMensaje.setText("");

        showMessage("Tu mensaje fue enviado a Firebase");
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
