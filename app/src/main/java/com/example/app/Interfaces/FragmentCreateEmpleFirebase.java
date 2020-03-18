package com.example.app.Interfaces;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.app.ModelosAdaptadores.AdaptadorFirebaseDepart;
import com.example.app.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static android.app.Activity.RESULT_OK;

public class FragmentCreateEmpleFirebase extends Fragment{

    private AdminHomeFragment.OnFragmentInteractionListener mListener;

    RecyclerView recyclerDepartFirebase;

    DatabaseReference mRootReference;

    TextView valorDepart,FireNombre,FireApellido,FireCorreo;

    String contenedor;

    Button btnSubirFoto,btnGuardarDatosFire;

    ImageView imageActualizar;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private  static  final  int GALLERIA = 1;

    private StorageReference mStorage;

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

        imageActualizar = vista.findViewById(R.id.imageActualizarFirebase);

        btnSubirFoto = vista.findViewById(R.id.btnSubirImageFirebase);

        btnGuardarDatosFire = vista.findViewById(R.id.btnGuardarDepartFirebase);

        mRootReference = FirebaseDatabase.getInstance().getReference();

        recyclerDepartFirebase = vista.findViewById(R.id.recyclerDepartFirebase);

        recyclerDepartFirebase.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        btnGuardarDatosFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                comprobarDepartmento();

                showMessage("EMPLEADO CREADO");
            }
        });

        btnSubirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                intent.setType("image/*");

                startActivityForResult(intent,GALLERIA);
            }
        });

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

    public void  onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == GALLERIA && resultCode == RESULT_OK){

            Uri uri = data.getData();

            mStorage = FirebaseStorage.getInstance().getReference().child(mAuth.toString());

            mStorage.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {

                    Task<Uri> downloadUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl();

                    Picasso.with(getActivity().getApplicationContext())
                            .load(String.valueOf(downloadUrl))
                            .fit()
                            .centerInside()
                            .into(imageActualizar);
                    Log.v("downloadUrl", String.valueOf(downloadUrl));

                    Log.d("SUCCESS","SUBIDA DE FOTO CORRECTA");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Log.d("ERROR","ERROR AL SUBIR FOTO");
                }
            });

            showMessage("la foto se subio con exito en firebase");
        }
    }

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
            mRootReference.child("DepartamentosReal").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
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

        empleado.put("imagen","ruta-ejemplo.jpg");

        mRootReference.child("DepartamentosReal").child(valorTexto).push().setValue(empleado);

        valorDepart.setText("");

        FireNombre.setText("");

        FireApellido.setText("");

        FireCorreo.setText("");
    }

    //metodo atajo para el toast vista usuario
    protected void showMessage(String message){

        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }
}
