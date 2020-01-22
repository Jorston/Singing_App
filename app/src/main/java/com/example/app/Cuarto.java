package com.example.app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app.Modelos.FichajeHora;
import com.example.app.Modelos.ListAdapterDatosBD;
import com.example.app.data.ConexionSQLiteHelper;
import com.example.app.data.UtilidadesDbFichajes;

import java.text.ParseException;
import java.util.ArrayList;

import static com.example.app.data.ConexionSQLiteHelper.*;
import static com.example.app.data.UtilidadesDbFichajes.*;

public class Cuarto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // declaracion de variables globales
    private String mParam1;
    private String mParam2;
    private TextView usuarioRegistrado;
    private String recuperamos_variable_string;
    ArrayList<FichajeHora> listadoDBFrag;
    private OnFragmentInteractionListener mListener;
    RecyclerView fichajesrecycler;
    private ConexionSQLiteHelper conexion;
    ListAdapterDatosBD listAdapterDatosBD;


    public Cuarto() {
        // Required empty public constructor
    }

    public static Cuarto newInstance(String param1, String param2) {
        Cuarto fragment = new Cuarto();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //usuarioFichaje
        View vista = inflater.inflate(R.layout.fragment_cuarto, container, false);
        //usuarioRegistrado = vista.findViewById(R.id.usuarioFichaje);
        recuperamos_variable_string = getActivity().getIntent().getStringExtra("usuario");
        //usuarioRegistrado.setText(recuperamos_variable_string);
        listadoDBFrag = new ArrayList<>();
        fichajesrecycler = vista.findViewById(R.id.recyclerFichajesDb);
        fichajesrecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getActivity());
        try {
            listAdapterDatosBD = new ListAdapterDatosBD(conexion.mostrarFichajes());
            fichajesrecycler.setAdapter(listAdapterDatosBD);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        /////////////////////////////consultarListado();
        return vista;
    }



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
