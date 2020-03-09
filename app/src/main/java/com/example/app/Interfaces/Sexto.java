package com.example.app.Interfaces;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.app.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import static android.app.Activity.RESULT_OK;

public class Sexto extends Fragment {

    private OnFragmentInteractionListener mListener;

    ImageButton btnSubir,btnSetting;

    Button btnGuardar;

    ImageView imagenActualizar;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private  static  final  int GALLERIA = 1;

    private StorageReference mStorage;

    public Sexto() {
        // Required empty public constructor
    }


    public static Sexto newInstance(String param1, String param2) {
        Sexto fragment = new Sexto();
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
        View vista =inflater.inflate(R.layout.fragment_sexto, container, false);

        btnSubir = vista.findViewById(R.id.btnSubirImage);

        btnSetting = vista.findViewById(R.id.btnSettingImage);

        btnGuardar = vista.findViewById(R.id.btnGuardarImage);

        btnSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                intent.setType("image/*");

                startActivityForResult(intent,GALLERIA);
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
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Log.d("ERROR","SUBIDE DE FOTO CORRECTA");
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

    //metodo atajo para el toast vista usuario
    protected void showMessage(String message){

        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }
}
