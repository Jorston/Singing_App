package com.example.app.Interfaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import com.example.app.R;

public class AdminLogin extends AppCompatActivity implements AdminLoginFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //inflate fragment para el login
        setContentView(R.layout.activity_admin_login);

        Fragment adminLogin = new AdminLoginFragment();

        FragmentTransaction transactionuno = getSupportFragmentManager().beginTransaction();

        transactionuno.replace(R.id.contenedorAdmin,adminLogin);

        transactionuno.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
