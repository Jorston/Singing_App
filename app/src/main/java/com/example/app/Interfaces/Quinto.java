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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Quinto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DatabaseReference mRootReference;
    Button botonEnviar;
    EditText tMensaje;
    String mensaje;
    private String recuperamos_variable_string;

    private OnFragmentInteractionListener mListener;

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
        mRootReference = FirebaseDatabase.getInstance().getReference();
        recuperamos_variable_string = getActivity().getIntent().getStringExtra("usuario");
        tMensaje = vista.findViewById(R.id.tMensaje);
        botonEnviar = vista.findViewById(R.id.btnEnviarMensaje);

        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensaje = tMensaje.getText().toString();
                Map<String, Object> datosMensaje = new HashMap<>();
                datosMensaje.put("nombre", recuperamos_variable_string);
                datosMensaje.put("mensaje", mensaje);
                mRootReference.child("Mensajes Insidencias").push().setValue(datosMensaje);
                tMensaje.setText("");
                showMessage("Tu mensaje fue enviado a Firebase");
            }
        });
        return vista;
    }

    //metodo atajo para el toast vista usuario
    protected void showMessage(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
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
