package com.example.app.ModelosAdaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.ConexionMetodosFirebase.UserMensajeFirebase;
import com.example.app.R;

import java.util.ArrayList;

public class AdaptadorFirebaseIncidencias extends RecyclerView.Adapter<AdaptadorFirebaseIncidencias.ListaInsidenciasHolder> {

    //variables globales
    ArrayList<UserMensajeFirebase> listaMensajesFirebase;

    public AdaptadorFirebaseIncidencias(ArrayList<UserMensajeFirebase> listaMensajesFirebase) {
        this.listaMensajesFirebase = listaMensajesFirebase;
    }


    @NonNull
    @Override
    public AdaptadorFirebaseIncidencias.ListaInsidenciasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistfirebase,null,false);
        return new ListaInsidenciasHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorFirebaseIncidencias.ListaInsidenciasHolder holder, int position) {
        holder.tusuario.setText(listaMensajesFirebase.get(position).getNombre());
        holder.tmensaje.setText(listaMensajesFirebase.get(position).getMensaje());
        holder.tfecha.setText(listaMensajesFirebase.get(position).getFecha().substring(0,10));

    }

    @Override
    public int getItemCount() {
        return listaMensajesFirebase.size();
    }

    public class ListaInsidenciasHolder extends RecyclerView.ViewHolder {
        TextView tusuario,tmensaje,tfecha;
        public ListaInsidenciasHolder(@NonNull View itemView) {
            super(itemView);
            tusuario = (TextView) itemView.findViewById(R.id.usuarioFirebase);
            tmensaje = (TextView) itemView.findViewById(R.id.mensajeFirebase);
            tfecha = (TextView) itemView.findViewById(R.id.fechaFirebase);
        }
    }

}
