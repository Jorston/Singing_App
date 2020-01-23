package com.example.app.ModelosAdaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ListAdapterDatosBD extends RecyclerView.Adapter<ListAdapterDatosBD.ListadoDBViewHolder> {

    //variables globales
    ArrayList<FichajeHora> listaFichajesDB;
    final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    final SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");

    public ListAdapterDatosBD(ArrayList<FichajeHora> listaFichajesDB) {
        this.listaFichajesDB = listaFichajesDB;
    }

    public ListAdapterDatosBD.ListadoDBViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistbasedatos,null,false);
        return new ListadoDBViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapterDatosBD.ListadoDBViewHolder holder, int position) {
        holder.usuario.setText(listaFichajesDB.get(position).getUser());
        holder.fechaFichaje.setText(listaFichajesDB.get(position).getHoraEntrada());
        holder.horaFichaje.setText(listaFichajesDB.get(position).getHoraEntrada());
        holder.tipoHorario.setText(listaFichajesDB.get(position).getTipoMarcado());
    }

    @Override
    public int getItemCount() { return listaFichajesDB.size(); }

    public class ListadoDBViewHolder extends RecyclerView.ViewHolder {
        TextView usuario,fechaFichaje,horaFichaje,tipoHorario;
        public ListadoDBViewHolder(@NonNull View itemView) {
            super(itemView);
            usuario = (TextView) itemView.findViewById(R.id.usuario);
            fechaFichaje = (TextView) itemView.findViewById(R.id.fechaFichaje);
            horaFichaje = (TextView) itemView.findViewById(R.id.horaFichaje);
            tipoHorario = (TextView) itemView.findViewById(R.id.tipoFichaje);
        }
    }
}
