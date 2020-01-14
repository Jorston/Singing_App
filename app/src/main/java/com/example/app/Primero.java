package com.example.app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.app.data.EscrituraFichaje;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Primero extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    ImageButton botonEntrada;
    View vista;

    public Primero() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Primero.
     */
    // TODO: Rename and change types and number of parameters
    public static Primero newInstance(String param1, String param2) {
        Primero fragment = new Primero();
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
        vista = inflater.inflate(R.layout.fragment_primero, container, false);

        final Date date = new Date();
        final SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        final EscrituraFichaje escritura = new EscrituraFichaje();
        escritura.setContext(getActivity());
        botonEntrada = vista.findViewById(R.id.btnEntrada);
        botonEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escritura.escrituraFichajes("jorge",date,date,date,date);
                Toast.makeText(getContext(),dateFormat.format(date)+"boton presionadp"+hourFormat.format(date),Toast.LENGTH_SHORT).show();
                escritura.lecturaFichajes();
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
