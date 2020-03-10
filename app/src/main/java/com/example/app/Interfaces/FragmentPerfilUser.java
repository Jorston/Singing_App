package com.example.app.Interfaces;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.R;

public class FragmentPerfilUser extends Fragment {

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
        return vista;
    }
}
