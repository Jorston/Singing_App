package com.example.app.Interfaces;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.app.ModelosAdaptadores.AdaptadorFichajes;
import com.example.app.ModelosAdaptadores.FichajeHora;
import com.example.app.R;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Tercero extends Fragment {

    private OnFragmentInteractionListener mListener;

    private TextView usuarioRegistrado;

    private String recuperamos_variable_string;

    ArrayList<FichajeHora> listaFichajes;

    RecyclerView recyclerFichajes;

    public Tercero() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflamos en layout en el fragment
        View vista = inflater.inflate(R.layout.fragment_tercero, container, false);

        //variables seteadas deacuerdo el id en el dom
        usuarioRegistrado = vista.findViewById(R.id.usuarioFichaje);

        recuperamos_variable_string = getActivity().getIntent().getStringExtra("usuario");

        usuarioRegistrado.setText(recuperamos_variable_string);

        listaFichajes = new ArrayList<FichajeHora>();

        recyclerFichajes = vista.findViewById(R.id.recyclerFichajes);

        recyclerFichajes.setLayoutManager(new LinearLayoutManager(getContext()));

        //funcion de lectura del archivo donde estan los ficheros
        llenarFichaje();

        try {
            //pasamos al adaptador ya el listado lleno en la funcion
            AdaptadorFichajes adapter = new AdaptadorFichajes(listaFichajes);

            //pintamos el adaptador lleno en el recyclerview
            recyclerFichajes.setAdapter(adapter);

        } catch (IOException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }

        return vista;
    }

    private void llenarFichaje() {
        //misma funcion que utilizamos para leer el archivo
        String fileNameFichaje = "FichajesHora.txt";

        ObjectInputStream lectura = null;

        try {
            lectura = new ObjectInputStream(new FileInputStream("/data/data/com.example.app/files/"+fileNameFichaje));

            ArrayList<FichajeHora> listaRegistros = (ArrayList<FichajeHora>) lectura.readObject();

            FichajeHora usuario = new FichajeHora();

            //recorremos el array el cual volcamos los datos del fichero
            for (FichajeHora fichados : listaRegistros){

                //validamos si es el usuario que inicio sesion y mostramos sus marcajes
                if (recuperamos_variable_string.equals(fichados.getUser())){

                    listaFichajes.add(fichados);
                }
            }
        } catch (IOException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
