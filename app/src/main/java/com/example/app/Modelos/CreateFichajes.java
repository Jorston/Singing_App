package com.example.app.Modelos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;

import java.util.ArrayList;

public class CreateFichajes extends RecyclerView.Adapter<CreateFichajes.ViewHolder> {

    ArrayList<Fichaje> listadoFichaje;

    public CreateFichajes(ArrayList<Fichaje> listadoFichaje) {
        this.listadoFichaje = listadoFichaje;
    }

    @NonNull
    @Override
    public CreateFichajes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistdos,null,false);
        return new CreateFichajes.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreateFichajes.ViewHolder holder, int position) {
    holder.dniFichado.setText(listadoFichaje.get(position).getDni());
    holder.nombreFichado.setText(listadoFichaje.get(position).getNombre());
    holder.horaFichado.setText((CharSequence) listadoFichaje.get(position).getHoraFichada());
    holder.tipoFichado.setText(listadoFichaje.get(position).getTipoFichaje());
    }

    @Override
    public int getItemCount() {
        return listadoFichaje.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dniFichado,nombreFichado,horaFichado,tipoFichado;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dniFichado = (TextView) itemView.findViewById(R.id.dniFichaje);
            nombreFichado = (TextView) itemView.findViewById(R.id.nombreFichaje);
            horaFichado = (TextView) itemView.findViewById(R.id.horaFichada);
            tipoFichado = (TextView) itemView.findViewById(R.id.tipoFichaje);
        }
    }
}
