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
import com.example.app.Modelos.CreateDatos;
import com.example.app.Modelos.Trabajadores;
import java.util.ArrayList;

public class Quinto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ArrayList<Trabajadores> listaTrabajadores;
    RecyclerView recycler;

    public Quinto() {
        // Required empty public constructor
    }

    public static Quinto newInstance(String param1, String param2) {
        Quinto fragment = new Quinto();
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

        View vista = inflater.inflate(R.layout.fragment_quinto, container, false);
        listaTrabajadores = new ArrayList<>();
        recycler = vista.findViewById(R.id.recyclerid);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        llenarLista();
        CreateDatos adapter =new CreateDatos(listaTrabajadores);
        recycler.setAdapter(adapter);
        return vista;
    }

    private void llenarLista() {
        listaTrabajadores.add( new Trabajadores("image1","trabajador1","apellido1","cargo1"));
        listaTrabajadores.add( new Trabajadores("image2","trabajador2","apellido2","cargo2"));
        listaTrabajadores.add( new Trabajadores("image3","trabajador3","apellido3","cargo3"));
        listaTrabajadores.add( new Trabajadores("image4","trabajador4","apellido4","cargo4"));
        listaTrabajadores.add( new Trabajadores("image5","trabajador5","apellido5","cargo5"));
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
