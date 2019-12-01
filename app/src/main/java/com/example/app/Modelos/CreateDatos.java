package com.example.app.Modelos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app.R;
import java.util.ArrayList;

public class CreateDatos extends RecyclerView.Adapter<CreateDatos.ViewHolderDatos> {
    ArrayList<Trabajadores> listDatos;

    public CreateDatos(ArrayList<Trabajadores> listDatos) {
        this.listDatos = listDatos;
    }

    public CreateDatos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreateDatos.ViewHolderDatos holder, int position) {
        holder.nombretrabajador.setText(listDatos.get(position).getNombre());
        holder.apellidos.setText(listDatos.get(position).getApellidos());
        holder.correos.setText(listDatos.get(position).getCorreo());
    }

    @Override
    public int getItemCount() {

        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView nombretrabajador,apellidos,correos;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            nombretrabajador = itemView.findViewById(R.id.nombre);
            apellidos = itemView.findViewById(R.id.apellidos);
            correos = itemView.findViewById(R.id.cargo);

        }
    }
}
