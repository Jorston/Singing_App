package com.example.app.Interfaces;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.app.R;


public class AdminCreateDepart extends Fragment {

    public AdminCreateDepart() {
        // Required empty public constructor
    }

    public static AdminCreateDepart newInstance(String param1, String param2) {
        AdminCreateDepart fragment = new AdminCreateDepart();
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
        View vista = inflater.inflate(R.layout.fragment_admin_create_depart, container, false);
        return vista;
    }
}
