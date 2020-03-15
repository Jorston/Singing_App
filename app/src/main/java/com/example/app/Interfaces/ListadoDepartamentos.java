package com.example.app.Interfaces;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.app.ModelosAdaptadores.AdaptadorFirebaseDepart;
import com.example.app.ModelosAdaptadores.AdaptadorListadoDepFirebase;
import com.example.app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class ListadoDepartamentos extends Fragment {

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerDepartEmpleados;

    DatabaseReference mRootReference;

    String contenedor;

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

        mRootReference = FirebaseDatabase.getInstance().getReference();

        recyclerDepartEmpleados = vista.findViewById(R.id.recyclerDepartEmple);

        recyclerDepartEmpleados.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        mRootReference.child("DepartamentosReal").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                llenadoDatos(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return vista;
    }

    private void llenadoDatos(DataSnapshot dataSnapshot) {

        final AdaptadorFirebaseDepart.EventListener eventListener;

        eventListener = new AdaptadorFirebaseDepart.EventListener() {
            @Override
            public void onEventName(String nombre) {
               // valorDepart.setText(nombre);
            }
        };

        final ArrayList listado = new ArrayList<String>();

        for (final  DataSnapshot snapshot : dataSnapshot.getChildren()){
            mRootReference.child("DepartamentosReal").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    contenedor = String.valueOf(dataSnapshot.getKey());

                    listado.add(contenedor);

                    AdaptadorListadoDepFirebase adaptadorFirebaseDepart = new AdaptadorListadoDepFirebase(listado,eventListener);

                    recyclerDepartEmpleados.setAdapter(adaptadorFirebaseDepart);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

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
