package com.example.app.ModelosAdaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app.R;
import java.util.ArrayList;

public class AdaptadorUsersPSQL extends RecyclerView.Adapter<AdaptadorUsersPSQL.ListaPSQLViewHolder> {

    private ArrayList<MformRegister> listasUsuariosPsql;

    public AdaptadorUsersPSQL(ArrayList<MformRegister> listasUsuariosPsql) {

        this.listasUsuariosPsql = listasUsuariosPsql;
    }

    public void setListaEsdev(ArrayList<MformRegister> listasUsuariosPsql) {
        this.listasUsuariosPsql = listasUsuariosPsql;
    }
    @NonNull
    @Override
    public AdaptadorUsersPSQL.ListaPSQLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistuserspsql,null,false);

        return new ListaPSQLViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPSQLViewHolder holder, int position) {

        holder.usuariopsql.setText(listasUsuariosPsql.get(position).getUserNick());

        holder.nombrepsql.setText(listasUsuariosPsql.get(position).getNombre());

        holder.emailpsql.setText(listasUsuariosPsql.get(position).getCorreo());

        holder.contrasenhapsql.setText(listasUsuariosPsql.get(position).getContrasenha());

    }

    @Override
    public int getItemCount() {
        return listasUsuariosPsql.size();
    }

    public class ListaPSQLViewHolder extends RecyclerView.ViewHolder {

        TextView usuariopsql,nombrepsql,emailpsql,contrasenhapsql;

        public ListaPSQLViewHolder(@NonNull View itemView) {

            super(itemView);

            usuariopsql = itemView.findViewById(R.id.usuariopsql);

            nombrepsql = itemView.findViewById(R.id.nombrepsql);

            emailpsql = itemView.findViewById(R.id.emailpsql);

            contrasenhapsql = itemView.findViewById(R.id.contrasenhapsql);
        }
    }
}
