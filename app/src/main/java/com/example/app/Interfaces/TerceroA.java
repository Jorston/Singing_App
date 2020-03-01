package com.example.app.Interfaces;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.app.ConexionPSQL.MostrarPsql;
import com.example.app.ModelosAdaptadores.AdaptadorUsersPSQL;
import com.example.app.ModelosAdaptadores.MformRegister;
import com.example.app.R;
import java.util.ArrayList;

public class TerceroA extends Fragment {

    private OnFragmentInteractionListener mListener;

    private ArrayList<MformRegister> listaUsuariosPSQL;

    public TerceroA() {
        // Required empty public constructor
    }

    public static TerceroA newInstance(String param1, String param2) {
        TerceroA fragment = new TerceroA();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        View vista = inflater.inflate(R.layout.fragment_tercero2, container, false);

        final RecyclerView recyclerUsers = vista.findViewById(R.id.recyclerUsers);

        recyclerUsers.setLayoutManager(new LinearLayoutManager(getContext()));

        MostrarPsql.hiloShowUsers listas = new MostrarPsql.hiloShowUsers();

        listas.execute();

        listas.getResult().observe(getViewLifecycleOwner(), new Observer<ArrayList<MformRegister>>() {
            @Override
            public void onChanged(ArrayList<MformRegister> mformRegisters) {

                listaUsuariosPSQL = mformRegisters;

                System.out.println("LISTA PRINCIPAL "+listaUsuariosPSQL.size());

                final AdaptadorUsersPSQL adaptador = new AdaptadorUsersPSQL(listaUsuariosPSQL);

                recyclerUsers.setAdapter(adaptador);
            }
        });

        return vista;
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
