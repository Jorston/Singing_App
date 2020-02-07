package com.example.app.Interfaces;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.app.ConexionesRoom.FichajeRoom;
import com.example.app.ConexionesRoom.MyDatabaseRoom;
import com.example.app.ModelosAdaptadores.AdaptadorRoomFichaje;
import com.example.app.R;
import java.util.List;

public class Segundo extends Fragment {

    private OnFragmentInteractionListener mListener;

    private TextView nombreUsuario;

    private String recuperamos_variable_string;

    RecyclerView recyclerFichajesRoom;

    AdaptadorRoomFichaje adaptadorRoomFichaje;

    public static MyDatabaseRoom myDatabaseRoom;


    public Segundo() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_segundo, container, false);

        recuperamos_variable_string = getActivity().getIntent().getStringExtra("usuario");

        recyclerFichajesRoom = vista.findViewById(R.id.recyclerFichajesRoom);

        nombreUsuario = vista.findViewById(R.id.usuarioFichaje);

        nombreUsuario.setText(recuperamos_variable_string);

        recyclerFichajesRoom.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        myDatabaseRoom = Room.databaseBuilder(getActivity().getApplicationContext(),MyDatabaseRoom.class, "usuariosLoginRoom.db").allowMainThreadQueries().build();

        List<FichajeRoom> listRoom = Segundo.myDatabaseRoom.utilidadesDaoFichajes().mostrarFichajes(recuperamos_variable_string);

        adaptadorRoomFichaje = new AdaptadorRoomFichaje(listRoom);

        recyclerFichajesRoom.setAdapter(adaptadorRoomFichaje);

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
