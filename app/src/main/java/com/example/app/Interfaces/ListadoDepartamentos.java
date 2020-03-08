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

import com.example.app.R;
import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.EventListener;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.FirebaseFirestoreException;

//import javax.annotation.Nullable;

public class ListadoDepartamentos extends Fragment {

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerDepartEmpleados;

    //FirebaseFirestore mFirestore;

    TextView textodep;

    public ListadoDepartamentos() {
        // Required empty public constructor
    }

    public static ListadoDepartamentos newInstance(String param1, String param2) {
        ListadoDepartamentos fragment = new ListadoDepartamentos();
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
        View vista = inflater.inflate(R.layout.fragment_listado_departamentos, container, false);

        textodep = vista.findViewById(R.id.textdepart);

        //mFirestore = FirebaseFirestore.getInstance();

        recyclerDepartEmpleados = vista.findViewById(R.id.recyclerDepartEmple);

        recyclerDepartEmpleados.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        /*mFirestore.collection("Empresa") .document("idEmpresa").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.exists()){
                    String nombre = documentSnapshot.getString("nombre");
                    String apellido = documentSnapshot.getString("apellido");

                    System.out.println(" NOMBRE "+nombre+" APELLIDO "+apellido);
                }

            }
        });*/

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
