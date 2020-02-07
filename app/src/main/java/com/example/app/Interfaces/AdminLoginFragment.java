package com.example.app.Interfaces;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.app.ConexionesRoom.MetodosRoomAdmin;
import com.example.app.ConexionesRoom.MyDatabaseRoom;
import com.example.app.R;

public class AdminLoginFragment extends Fragment implements AdminHomeFragment.OnFragmentInteractionListener{

    Button btnLogin;

    TextView textuser, textpassword;

    ProgressBar progressBar;

    public static MyDatabaseRoom myDatabaseRoom;

    final MetodosRoomAdmin metodosRoom = new MetodosRoomAdmin();

    private OnFragmentInteractionListener mListener;

    public AdminLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_admin_login, container, false);

        myDatabaseRoom = Room.databaseBuilder(getActivity().getApplicationContext(),MyDatabaseRoom.class, "usuariosLoginRoom.db").allowMainThreadQueries().build();

        btnLogin = vista.findViewById(R.id.btnLoginAdmin);

        textuser = vista.findViewById(R.id.textUserAdmin);

        textpassword = vista.findViewById(R.id.textPasswordAdmin);

        progressBar = vista.findViewById(R.id.progressBar);

        progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#369C6C"), PorterDuff.Mode.SRC_IN);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btnLogin.setEnabled(false);

                //condicion de si existe el usuario le damos acceso sino no puede intrar a la aplicacion
                if (metodosRoom.validarUsuariosAdmin(textuser.getText().toString(),textpassword.getText().toString())){

                    progressBar.setVisibility(View.VISIBLE);

                    new CountDownTimer(2000,1000){

                        @Override
                        public void onTick(long millisUntilFinished) {

                            try {

                                Thread.sleep(1000);

                            }catch(InterruptedException e) {

                                e.printStackTrace();
                            }
                        }

                        public void onFinish(){
                            progressBar.setVisibility(View.GONE);
                        }

                    }.start();

                    Fragment adminHome = new AdminHomeFragment();

                    FragmentTransaction transactionuno = getActivity().getSupportFragmentManager().beginTransaction();

                    transactionuno.replace(R.id.contenedorAdmin,adminHome);

                    transactionuno.commit();

                    showMessage("Usuario Administrador aceptado");

                }else{

                    btnLogin.setEnabled(true);

                    showMessage("Usted no es Administrador");

                }
            }

        });

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

    @Override
    public void onFragmentInteraction(Uri uri) {

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
