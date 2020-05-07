package com.example.app.Interfaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.TextView;
import com.example.app.ConexionMetodosFirebase.AdminDepartDetalles;
import com.example.app.ModelosAdaptadores.AdaptadorDetallesDepartFirebase;
import com.example.app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class AdminDetallesListDepart extends AppCompatActivity {

    ArrayList<AdminDepartDetalles> listadoDetallesDepartamentos;

    RecyclerView recyclerDepartDetallesFirebase;

    DatabaseReference mRootReference;

    TextView tituloDepart;

    String valorEnviado;

    final String IMAGENDEFAULT = "https://firebasestorage.googleapis.com/v0/b/appfirebaseproject-b2083.appspot.com/o/default.png?alt=media&token=9b9124ba-98bc-433f-adc7-26ea28c02355";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_detalles_list_depart);

        mRootReference = FirebaseDatabase.getInstance().getReference();

        recyclerDepartDetallesFirebase = findViewById(R.id.recyclerDetallesDepartAdmin);

        recyclerDepartDetallesFirebase.setLayoutManager(new LinearLayoutManager(this));

        tituloDepart = (TextView) findViewById(R.id.textDepartSeleccionado);

        Bundle extrasEnviados = getIntent().getExtras();

        if (extrasEnviados != null){

            valorEnviado = extrasEnviados.getString("datoEnviado");

            tituloDepart.setText(valorEnviado);
        }

        //lisener de los datos que estan en firebase
        mRootReference.child("Departamentos").child(valorEnviado).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                llenadoDatos(dataSnapshot);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void llenadoDatos(DataSnapshot dataSnapshot) {

        listadoDetallesDepartamentos = new ArrayList<AdminDepartDetalles>();

        for(final DataSnapshot snapshot : dataSnapshot.getChildren()){

            mRootReference.child(valorEnviado).child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    AdminDepartDetalles adminDepartDetallesFirebase = snapshot.getValue(AdminDepartDetalles.class);

                    adminDepartDetallesFirebase.getApellido();

                    adminDepartDetallesFirebase.getCorreo();

                    adminDepartDetallesFirebase.getContrasenha();

                    adminDepartDetallesFirebase.getDepartamento();

                    adminDepartDetallesFirebase.getImagen();

                    adminDepartDetallesFirebase.getNombre();

                    adminDepartDetallesFirebase.setUrlImagen(adminDepartDetallesFirebase.getImagen());

                    if (adminDepartDetallesFirebase.getImagen().equals(IMAGENDEFAULT)){

                        adminDepartDetallesFirebase.setUrlImagen(IMAGENDEFAULT);

                        adminDepartDetallesFirebase.getUrlImagen();
                    }
                    adminDepartDetallesFirebase.getUrlImagen();

                    listadoDetallesDepartamentos.add(adminDepartDetallesFirebase);

                    AdaptadorDetallesDepartFirebase adaptador = new AdaptadorDetallesDepartFirebase(listadoDetallesDepartamentos,getApplicationContext());

                    recyclerDepartDetallesFirebase.setAdapter(adaptador);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}