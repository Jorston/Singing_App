package com.example.app.Interfaces;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.app.ModelosAdaptadores.AdaptadorFirebaseDepart;
import com.example.app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class FragmentCreateEmpleFirebase extends Fragment {

    private AdminHomeFragment.OnFragmentInteractionListener mListener;

    RecyclerView recyclerDepartFirebase;

    DatabaseReference mRootReference;

    public FragmentCreateEmpleFirebase() {
        // Required empty public constructor
    }

    public static FragmentCreateEmpleFirebase newInstance(String param1, String param2) {
        FragmentCreateEmpleFirebase fragment = new FragmentCreateEmpleFirebase();
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
        View vista = inflater.inflate(R.layout.fragment_create_emple_firebase, container, false);

        mRootReference = FirebaseDatabase.getInstance().getReference();

        recyclerDepartFirebase = vista.findViewById(R.id.recyclerDepartFirebase);

        recyclerDepartFirebase.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

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

    private void llenadoDatos(@NonNull DataSnapshot dataSnapshot) {

        final ArrayList listado = new ArrayList<String>();

        for (final  DataSnapshot snapshot : dataSnapshot.getChildren()){
            mRootReference.child("DepartamentosReal").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String contenedor = String.valueOf(dataSnapshot.getKey());

                    listado.add(contenedor);

                    AdaptadorFirebaseDepart adaptadorFirebaseDepart = new AdaptadorFirebaseDepart(listado);

                    recyclerDepartFirebase.setAdapter(adaptadorFirebaseDepart);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }
}
