package com.example.app.Interfaces;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.room.Room;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.app.ConexionesRoom.FichajeRoom;
import com.example.app.ConexionesRoom.MyDatabaseRoom;
import com.example.app.ConexionesRoom.UserRoom;
import com.example.app.R;
import java.util.List;

public class Cuarto extends Fragment {

    private String recuperamos_variable_string;

    private TextView usersRoom,fichajesRoom;

    private OnFragmentInteractionListener mListener;

    public static MyDatabaseRoom myDatabaseRoom;


    public Cuarto() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_cuarto, container, false);

        myDatabaseRoom = Room.databaseBuilder(getActivity().getApplicationContext(),MyDatabaseRoom.class, "usuariosLoginRoom.db").allowMainThreadQueries().build();

        recuperamos_variable_string = getActivity().getIntent().getStringExtra("usuario");

        usersRoom = vista.findViewById(R.id.usuariosRoom);

        fichajesRoom = vista.findViewById(R.id.fichajesRoom);

        List<UserRoom> listadoRoom = Cuarto.myDatabaseRoom.utilidadesDao().mostrarUsuarios();

        String info = "";

        for (UserRoom user : listadoRoom){

            String nombre = user.getNombre();

            String correo = user.getCorreo();

            String userNick = user.getUserNick();

            String contrasenha = user.getContrasenha();

            String repcontrasenha = user.getRepContrasenha();

            int rolUsuario = user.getRolUsuario();

            info = info + "\n"+"nombre: "+nombre+"\n correo: "+correo+"\n userNick: "+userNick+"\n"+"el rol es: "+rolUsuario+"\n"+"\ncontraseña: "+contrasenha+
                    "\nrepcontraseña: "+repcontrasenha;
        }

        usersRoom.setText(info);



        List<FichajeRoom> listadoFichajesRoom = Cuarto.myDatabaseRoom.utilidadesDaoFichajes().mostrarTodosFichajes();

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
