package com.example.app.Interfaces;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.app.ConexionesRoom.MetodosRoomAdmin;
import com.example.app.ConexionesRoom.MyDatabaseRoom;
import com.example.app.ModelosAdaptadores.AdaptadorFirebaseDepart;
import com.example.app.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static android.app.Activity.RESULT_OK;

public class FragmentCreateEmpleFirebase extends Fragment{

    private AdminHomeFragment.OnFragmentInteractionListener mListener;

    RecyclerView recyclerDepartFirebase;

    DatabaseReference mRootReference;

    TextView valorDepart,FireNombre,FireApellido,FireCorreo,hiddenImagen,FireContrasenha;

    String contenedor;

    Button btnSubirFoto,btnGuardarDatosFire;

    ImageView imageActualizar;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private  static  final  int GALLERIA = 1;

    private StorageReference mStorage;

    //recursos room
    public static MyDatabaseRoom myDatabaseRoom;

    final MetodosRoomAdmin metodosRoomAdmin = new MetodosRoomAdmin();

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

        valorDepart = vista.findViewById(R.id.txtValorDepartSelect);

        FireNombre = vista.findViewById(R.id.FirebaseNombre);

        FireApellido = vista.findViewById(R.id.FirebaseApellido);

        FireCorreo = vista.findViewById(R.id.FirebaseCorreo);

        FireContrasenha = vista.findViewById(R.id.FirebaseContrasenha);

        imageActualizar = vista.findViewById(R.id.imageActualizarFirebase);

        hiddenImagen = vista.findViewById(R.id.valorLinkImagen);

        btnSubirFoto = vista.findViewById(R.id.btnSubirImageFirebase);

        btnGuardarDatosFire = vista.findViewById(R.id.btnGuardarDepartFirebase);

        mRootReference = FirebaseDatabase.getInstance().getReference();

        mStorage = FirebaseStorage.getInstance().getReference();

        recyclerDepartFirebase = vista.findViewById(R.id.recyclerDepartFirebase);

        recyclerDepartFirebase.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        //instancia a la conexion y objetos de la clase MyDatabaseRoom de Room y creamos la base de datos
        myDatabaseRoom = Room.databaseBuilder(getActivity().getApplicationContext(),MyDatabaseRoom.class, "usuariosLoginRoom.db").allowMainThreadQueries().build();

        btnGuardarDatosFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (valorDepart.getText().toString().equals("")){
                    showMessage("ELIGA UN DEPARTAMENTO");
                }else{
                    comprobarDepartmento();

                    insertRoomFirebase();

                    showMessage("EMPLEADO CREADO");

                }


            }
        });

        btnSubirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);

                intent.setType("image/*");

                startActivityForResult(intent,GALLERIA);
            }
        });

        mRootReference.child("Departamentos").addValueEventListener(new ValueEventListener() {
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

    @Override
    public void  onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == GALLERIA && resultCode == RESULT_OK){

            Uri uri = data.getData();

            final StorageReference filepath = mStorage.child("fotos").child(uri.getLastPathSegment());

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    showMessage("la foto se subio con exito en firebase");

                    filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            final Uri descarga = uri;

                            Glide.with(getActivity().getApplicationContext())
                                    .load(descarga).into(imageActualizar);

                            hiddenImagen.setText(descarga.toString());
                        }
                    });

                }

            });

        }//fin if

    }//fin del onActivityResult

    private void llenadoDatos(@NonNull DataSnapshot dataSnapshot) {

        final AdaptadorFirebaseDepart.EventListener eventListener;

        eventListener = new AdaptadorFirebaseDepart.EventListener() {
            @Override
            public void onEventName(String nombre) {
                valorDepart.setText(nombre);
                showMessage(valorDepart.getText().toString());
            }
        };

        final ArrayList listado = new ArrayList<String>();

        for (final  DataSnapshot snapshot : dataSnapshot.getChildren()){
            mRootReference.child("Departamentos").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    contenedor = String.valueOf(dataSnapshot.getKey());

                    listado.add(contenedor);

                    AdaptadorFirebaseDepart adaptadorFirebaseDepart = new AdaptadorFirebaseDepart(listado,eventListener);

                    recyclerDepartFirebase.setAdapter(adaptadorFirebaseDepart);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }

    private void comprobarDepartmento() {

        final String valorTexto = valorDepart.getText().toString();

        Map<String, Object> empleado = new HashMap<>();

        empleado.put("nombre",FireNombre.getText().toString());

        empleado.put("apellido",FireApellido.getText().toString());

        empleado.put("correo",FireCorreo.getText().toString());

        empleado.put("contrasenha",FireContrasenha.getText().toString());

        if (hiddenImagen.getText().toString().equals("")){

            empleado.put("imagen","https://firebasestorage.googleapis.com/v0/b/appfirebaseproject-b2083.appspot.com/o/default.png?alt=media&token=9b9124ba-98bc-433f-adc7-26ea28c02355");

        }else{

            empleado.put("imagen",hiddenImagen.getText().toString());
        }

        mRootReference.child("Departamentos").child(valorTexto).push().setValue(empleado);

        Glide.with(getActivity().getApplicationContext())
                .load("").into(imageActualizar);
    }

    private void insertRoomFirebase(){

        metodosRoomAdmin.insertarUserRoomAdmin(FireNombre.getText().toString(),FireApellido.getText().toString(),FireCorreo.getText().toString(),FireNombre.getText().toString(),FireContrasenha.getText().toString(),FireContrasenha.getText().toString());

        FireContrasenha.setText("");

        FireApellido.setText("");

        FireCorreo.setText("");

        FireNombre.setText("");

        valorDepart.setText("");

        FireNombre.setFocusableInTouchMode(true);

        FireNombre.requestFocus();

        myDatabaseRoom.close();
    }

    //metodo atajo para el toast vista usuario
    protected void showMessage(String message){

        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }
}
