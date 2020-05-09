package com.example.app.Interfaces;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.room.Room;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.app.ConexionesRoom.MyDatabaseRoom;
import com.example.app.ConexionesRoom.UserRoom;
import com.example.app.R;
import java.util.List;

public class FragmentPerfilUser extends Fragment {

    TextView nombre,apellidos,usernick,correo;

    public static MyDatabaseRoom myDatabaseRoom;

    private String recuperamos_variable_string;

    String stringnombre,stringapellido,stringusernick,stringcorreo;

    public FragmentPerfilUser() {
        // Required empty public constructor
    }
    public static FragmentPerfilUser newInstance(String param1, String param2) {
        FragmentPerfilUser fragment = new FragmentPerfilUser();
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
        View vista = inflater.inflate(R.layout.fragment_perfil_user, container, false);

        myDatabaseRoom = Room.databaseBuilder(getActivity().getApplicationContext(),MyDatabaseRoom.class, "usuariosLoginRoom.db").allowMainThreadQueries().build();

        nombre = vista.findViewById(R.id.nombreRecup);

        apellidos = vista.findViewById(R.id.apellidosRecup);

        usernick = vista.findViewById(R.id.usernickRecup);

        correo = vista.findViewById(R.id.correoRecup);

        recuperamos_variable_string = getActivity().getIntent().getStringExtra("usuario");

        List<UserRoom> listadoRoom = FragmentPerfilUser.myDatabaseRoom.utilidadesDao().selectUsuario(recuperamos_variable_string);

        for (UserRoom user : listadoRoom){
            stringnombre = user.getNombre();

            stringapellido = user.getApellidos();

            stringusernick = user.getUserNick();

            stringcorreo = user.getCorreo();

            System.out.println("LOS DATOS SON: "+user.getNombre()+user.getApellidos()+user.getUserNick()+user.getCorreo());
        }

        nombre.setText("nombre: "+stringnombre);

        apellidos.setText("apellido: "+stringapellido);

        usernick.setText("userNick: "+stringusernick);

        correo.setText("correo: "+stringcorreo);

        return vista;

    }
}
