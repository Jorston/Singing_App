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
import com.example.app.ConexionMetodosFirebase.UserMensajeFirebase;
import com.example.app.ModelosAdaptadores.AdaptadorFirebaseIncidencias;
import com.example.app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class AdminHomeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    ArrayList<UserMensajeFirebase> listaMensajeFireba;

    RecyclerView recyclerMensajesFirebase;

    DatabaseReference mRootReference;

    public AdminHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_admin_home, container, false);

        mRootReference = FirebaseDatabase.getInstance().getReference();

        recyclerMensajesFirebase = vista.findViewById(R.id.recyclerMensajesFirebase);

        recyclerMensajesFirebase.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        //lisener de los datos que estan en firebase
        mRootReference.child("Mensajes Insidencias").child("usuarios").addValueEventListener(new ValueEventListener() {
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

    private void llenadoDatos(@NonNull DataSnapshot dataSnapshot) {
        listaMensajeFireba= new ArrayList<UserMensajeFirebase>();

        for (final DataSnapshot snapshot : dataSnapshot.getChildren()){

            mRootReference.child("usuarios").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    UserMensajeFirebase userMensajeFirebase = snapshot.getValue(UserMensajeFirebase.class);

                    userMensajeFirebase.getNombre();

                    userMensajeFirebase.getMensaje();

                    userMensajeFirebase.getFecha();

                    listaMensajeFireba.add(userMensajeFirebase);

                    AdaptadorFirebaseIncidencias adaptadorFirebaseIncidencias = new AdaptadorFirebaseIncidencias(listaMensajeFireba);

                    recyclerMensajesFirebase.setAdapter(adaptadorFirebaseIncidencias);

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
