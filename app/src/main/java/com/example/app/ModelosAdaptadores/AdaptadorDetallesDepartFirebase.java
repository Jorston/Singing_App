package com.example.app.ModelosAdaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.app.ConexionMetodosFirebase.AdminDepartDetalles;
import com.example.app.R;
import java.util.ArrayList;

public class AdaptadorDetallesDepartFirebase extends RecyclerView.Adapter<AdaptadorDetallesDepartFirebase.ListaDetallesDepartHolder> {

    //variables globales
    ArrayList<AdminDepartDetalles> listaDepartDetallesFirebase;

    private Context context;

    public AdaptadorDetallesDepartFirebase(ArrayList<AdminDepartDetalles> listaDepartDetallesFirebase, Context context) {
        this.listaDepartDetallesFirebase = listaDepartDetallesFirebase;
        this.context = context;
    }

    public AdaptadorDetallesDepartFirebase.ListaDetallesDepartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_depart_detall_admin_fire,null,false);
        return  new ListaDetallesDepartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorDetallesDepartFirebase.ListaDetallesDepartHolder holder, int position) {

        holder.apellidos.setText(listaDepartDetallesFirebase.get(position).getApellido());

        holder.correos.setText(listaDepartDetallesFirebase.get(position).getCorreo());

        holder.contrasenhaFirebase.setText(listaDepartDetallesFirebase.get(position).getContrasenha());

        holder.urlImagenes.setText(listaDepartDetallesFirebase.get(position).getImagen());

        holder.nombres.setText(listaDepartDetallesFirebase.get(position).getNombre());

        holder.departamentoFirebase.setText(listaDepartDetallesFirebase.get(position).getDepartamento());

        Glide.with(context)
                .load(listaDepartDetallesFirebase.get(position).getUrlImagen())
                .into(holder.imagenesUSuario);

    }

    @Override
    public int getItemCount() {
        return listaDepartDetallesFirebase.size();
    }

    public class ListaDetallesDepartHolder extends RecyclerView.ViewHolder {

        TextView nombres,apellidos,correos,urlImagenes,contrasenhaFirebase,departamentoFirebase;

        ImageView imagenesUSuario;

        public ListaDetallesDepartHolder(@NonNull View itemView) {
            super(itemView);

            apellidos = (TextView) itemView.findViewById(R.id.apellidosDeptDetall);

            correos = (TextView) itemView.findViewById(R.id.correoDeptDetall);

            urlImagenes = (TextView) itemView.findViewById(R.id.imageDeptDetall);

            nombres = (TextView) itemView.findViewById(R.id.nombreDeptDetall);

            contrasenhaFirebase = (TextView) itemView.findViewById(R.id.contrasenhaDeptDetall);

            departamentoFirebase = (TextView) itemView.findViewById(R.id.departDepartDetall);

            imagenesUSuario = (ImageView) itemView.findViewById(R.id.imagenUsuario);

        }
    }
}
