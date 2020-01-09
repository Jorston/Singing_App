package com.example.app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.app.Modelos.CreateRegistrados;
import com.example.app.Modelos.Fichaje;
import com.example.app.Modelos.MformRegister;

import java.util.ArrayList;

public class Cuarto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ArrayList<MformRegister>registrados;
    RecyclerView fichajesrecycler;

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
        View vista = inflater.inflate(R.layout.fragment_cuarto, container, false);
        registrados = new ArrayList<>();
        fichajesrecycler = vista.findViewById(R.id.recycleridcuarto);
        fichajesrecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        llenarFichajes();
        CreateRegistrados adaptador = new CreateRegistrados(registrados);
        fichajesrecycler.setAdapter(adaptador);
        return vista;
    }

    private void llenarFichajes() {
        MformRegister registros = new MformRegister();
        registrados.add(registros);
        Fichaje per = new Fichaje();
        registrados.add(new MformRegister("jj","jorge","fiorilo","user1","f@gmail.com","123"));
        /*listados.add(new Fichaje("dni1","jorge","apellido1","fecha1" ,tipo));
        listados.add(new Fichaje("dni2","alberto", "apellido2","fecha2",tipoSalida));
        listados.add(new Fichaje("dni3","marcelo", "apellido3","fecha3",tipo));
        listados.add(new Fichaje("dni4","maria","apellido4","fecha4",tipoSalida));
        listados.add(new Fichaje("dni5","gisela","apellido5","fecha5",tipo));*/
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
