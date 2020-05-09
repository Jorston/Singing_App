package com.example.app.Interfaces;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.app.ConexionMetodosFirebase.AdminDepartDetalles;
import com.example.app.ConexionesRoom.MyDatabaseRoom;
import com.example.app.ModelosAdaptadores.AdaptadorDetallesDepartFirebase;
import com.example.app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class FragmentPerfilUser extends Fragment {

    //instancias para mostrar datos de Room

    /*TextView nombre,apellidos,usernick,correo,departamento,urlImagen;

      String stringnombre,stringapellido,stringusernick,stringcorreo;

       public static MyDatabaseRoom myDatabaseRoom;
     */

    //instacias para mostrar datos de firebase

    private String recuperamos_variable_string;

    DatabaseReference mRootReference;

    String valorDepartTemporal,acumulador,valorAcumulativoDepart;

    ArrayList<AdminDepartDetalles> listadoDetallesDepartamentos;

    RecyclerView reciclerPerfilUsuario;

    final String IMAGENDEFAULT = "https://firebasestorage.googleapis.com/v0/b/appfirebaseproject-b2083.appspot.com/o/default.png?alt=media&token=9b9124ba-98bc-433f-adc7-26ea28c02355";


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

        recuperamos_variable_string = getActivity().getIntent().getStringExtra("usuario");

        reciclerPerfilUsuario = vista.findViewById(R.id.recyclerPerfilUsuario);

        reciclerPerfilUsuario.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        ValidacionDepart(recuperamos_variable_string);

        //llenado de datos extraidos desde room Room

       /* myDatabaseRoom = Room.databaseBuilder(getActivity().getApplicationContext(),MyDatabaseRoom.class, "usuariosLoginRoom.db").allowMainThreadQueries().build();

        nombre = vista.findViewById(R.id.nombreRecup);

        apellidos = vista.findViewById(R.id.apellidosRecup);

        usernick = vista.findViewById(R.id.usernickRecup);

        correo = vista.findViewById(R.id.correoRecup);

        departamento = vista.findViewById(R.id.departamentoRecup);

        urlImagen = vista.findViewById(R.id.urlImagenRecup);

        recuperamos_variable_string = getActivity().getIntent().getStringExtra("usuario");

        LoginFirebaseDepart loginFirebaseDepart = new LoginFirebaseDepart();

        List<UserRoom> listadoRoom = FragmentPerfilUser.myDatabaseRoom.utilidadesDao().selectUsuario(recuperamos_variable_string);

        for (UserRoom user : listadoRoom){
            stringnombre = user.getNombre();

            stringapellido = user.getApellidos();

            stringusernick = user.getUserNick();

            stringcorreo = user.getCorreo();

            //System.out.println("LOS DATOS SON: "+user.getNombre()+user.getApellidos()+user.getUserNick()+user.getCorreo());
        }

        nombre.setText("nombre: "+stringnombre);

        apellidos.setText("apellido: "+stringapellido);

        usernick.setText("userNick: "+stringusernick);

        correo.setText("correo: "+stringcorreo);*/

        return vista;

    }

    //metodo que obtiene todos los departamentos de firebase
    public void ValidacionDepart(String datoEnviado){

        acumulador = datoEnviado;

        mRootReference = FirebaseDatabase.getInstance().getReference();

        mRootReference.child("Departamentos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                lecturaDepartamentos(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //metodo para obtener las llaves de todos los departamentos
    public String lecturaDepartamentos(DataSnapshot dataSnapshot) {

        for (final  DataSnapshot snapshot : dataSnapshot.getChildren()){
            mRootReference.child("Departamentos").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    valorDepartTemporal = dataSnapshot.getKey();

                    datosUsuarioporDepart(valorDepartTemporal);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        return valorDepartTemporal;
    }

    //obtenemos el nombre de los departamentos mediante su llave
    public void datosUsuarioporDepart(final String valorDepartTemporal){

        mRootReference.child("Departamentos").child(valorDepartTemporal).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                valorAcumulativoDepart = valorDepartTemporal;

                obtencionDatosUserDepart(dataSnapshot,valorAcumulativoDepart);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //obtencion de datos de cada una de las llaves y llenado de reciclerview de datos
    public void obtencionDatosUserDepart(DataSnapshot dataSnapshot, final String valor){

        listadoDetallesDepartamentos = new ArrayList<AdminDepartDetalles>();

        for(final DataSnapshot snapshot : dataSnapshot.getChildren()){

            mRootReference.child(valor).child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    AdminDepartDetalles adminDepartDetallesFirebase = snapshot.getValue(AdminDepartDetalles.class);

                    if (adminDepartDetallesFirebase.getNombre().equals(acumulador)){

                        adminDepartDetallesFirebase.getNombre();

                        adminDepartDetallesFirebase.getApellido();

                        adminDepartDetallesFirebase.getCorreo();

                        adminDepartDetallesFirebase.getDepartamento();

                        adminDepartDetallesFirebase.setUrlImagen(adminDepartDetallesFirebase.getImagen());

                        if (adminDepartDetallesFirebase.getImagen().equals(IMAGENDEFAULT)){

                            adminDepartDetallesFirebase.setUrlImagen(IMAGENDEFAULT);

                            adminDepartDetallesFirebase.getUrlImagen();
                        }
                        adminDepartDetallesFirebase.getUrlImagen();

                        listadoDetallesDepartamentos.add(adminDepartDetallesFirebase);

                        AdaptadorDetallesDepartFirebase adaptadores = new AdaptadorDetallesDepartFirebase(listadoDetallesDepartamentos,getActivity().getApplicationContext());

                        reciclerPerfilUsuario.setAdapter(adaptadores);



                       /* for (AdminDepartDetalles admin: listadoDetallesDepartamentos){

                            System.out.println("LONGITUD DEL LISTADO ES "+listadoDetallesDepartamentos.size());

                            nombresnew =admin.getNombre();

                            apellidosnew = admin.getApellido();

                            correosnew = admin.getCorreo();

                            departnew = admin.getDepartamento();

                            imagennew = admin.getImagen();

                            System.out.println("EL DATO DEL USUARIO ES "+ nombresnew+" "+apellidosnew+" "+correosnew+" "+departnew+" "+imagennew);

                        }*/

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }

}
