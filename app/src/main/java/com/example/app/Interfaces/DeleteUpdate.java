package com.example.app.Interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.app.R;

public class DeleteUpdate extends AppCompatActivity implements Delete.OnFragmentInteractionListener,DeUpdate.OnFragmentInteractionListener {

    Button botonActualizar,botonEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delete_update);

        botonActualizar = findViewById(R.id.btnActualizar);

        botonEliminar = findViewById(R.id.btnEliminarinFragment);

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment update = new DeUpdate();

                FragmentTransaction transactionUpdate = getSupportFragmentManager().beginTransaction();

                transactionUpdate.replace(R.id.contenedor_delete_update,update);

                transactionUpdate.commit();
            }
        });

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment delete = new Delete();

                FragmentTransaction transactionDelete = getSupportFragmentManager().beginTransaction();

                transactionDelete.replace(R.id.contenedor_delete_update,delete);

                transactionDelete.commit();

            }
        });


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
