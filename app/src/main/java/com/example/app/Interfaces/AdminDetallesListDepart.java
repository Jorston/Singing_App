package com.example.app.Interfaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import com.example.app.ConexionMetodosFirebase.AdminDepartDetalles;
import com.example.app.ConexionMetodosFirebase.UserMensajeFirebase;
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

                    adminDepartDetallesFirebase.getImagen();

                    adminDepartDetallesFirebase.getNombre();

                    listadoDetallesDepartamentos.add(adminDepartDetallesFirebase);

                    AdaptadorDetallesDepartFirebase adaptador = new AdaptadorDetallesDepartFirebase(listadoDetallesDepartamentos);

                    recyclerDepartDetallesFirebase.setAdapter(adaptador);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}