package com.example.app.Interfaces;

import android.app.Activity;
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

import com.example.app.ConexionesRoom.FichajeRoom;
import com.example.app.ConexionesRoom.MyDatabaseRoom;
import com.example.app.ConexionesRoom.UserRoom;
import com.example.app.ModelosAdaptadores.FichajeHora;
import com.example.app.ModelosAdaptadores.ListAdapterDatosBD;
import com.example.app.R;
import com.example.app.DataConexiones.ConexionSQLiteHelper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Cuarto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // declaracion de variables globales
    private String mParam1;
    private String mParam2;
    private String recuperamos_variable_string;
    private TextView usersRoom,fichajesRoom;
    private OnFragmentInteractionListener mListener;


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

        View vista = inflater.inflate(R.layout.fragment_cuarto, container, false);

        recuperamos_variable_string = getActivity().getIntent().getStringExtra("usuario");

        usersRoom = vista.findViewById(R.id.usuariosRoom);

        fichajesRoom = vista.findViewById(R.id.fichajesRoom);

        List<UserRoom> listadoRoom = Primero.myDatabaseRoom.utilidadesDao().mostrarUsuarios();

        String info = "";

        for (UserRoom user : listadoRoom){

            String nombre = user.getNombre();

            String correo = user.getCorreo();

            String userNick = user.getUserNick();

            info = info + "\n"+"nombre: "+nombre+"\n correo: "+correo+"\n userNick: "+userNick+"\n";
        }

        usersRoom.setText(info);



        List<FichajeRoom> listadoFichajesRoom = Primero.myDatabaseRoom.utilidadesDao().mostrarFichajes();

        String fichaje = "";

        for (FichajeRoom fichajeR : listadoFichajesRoom){
            String usuario = fichajeR.getUsuario();
            String diaFichaje = fichajeR.getDiaFichaje();
            String horaFichaje = fichajeR.getHorafichaje();
            String tipoFichaje = fichajeR.getTipoFichaje();

            fichaje = fichaje+"\n"+usuario+"  "+diaFichaje+"  "+horaFichaje+"  "+tipoFichaje+"\n";
        }

        fichajesRoom.setText(fichaje);
        return vista;
    }



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
