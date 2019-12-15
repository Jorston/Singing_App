package com.example.app.Modelos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app.R;
import java.util.ArrayList;

public class CreateRegistrados extends RecyclerView.Adapter<CreateRegistrados.ViewHolder> {
    ArrayList<MformRegister>registrados;

    public CreateRegistrados(ArrayList<MformRegister> registrados) {
        this.registrados = registrados;
    }

    @NonNull
    @Override
    public CreateRegistrados.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistdos,null,false);
        return new CreateRegistrados.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull CreateRegistrados.ViewHolder holder, int position) {
        holder.NombreRegistrado.setText(registrados.get(position).getNombre());
        holder.ApellidosRegistrados.setText(registrados.get(position).getApellidos());
        holder.UserNickRegistrado.setText(registrados.get(position).getUserNick());
        holder.CorreoRegistrado.setText(registrados.get(position).getCorreo());
        holder.ContraRegistrado.setText(registrados.get(position).getContrasenha());
        holder.RepContraRegistrado.setText(registrados.get(position).getRepcontrasenha());
    }

    @Override
    public int getItemCount() {
        return registrados.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //TextView dniFichado,nombreFichado,apellidoFichaje,horaFichado,tipoFichado;
        TextView NombreRegistrado,ApellidosRegistrados,UserNickRegistrado,CorreoRegistrado,ContraRegistrado,RepContraRegistrado;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            NombreRegistrado = (TextView) itemView.findViewById(R.id.NombreRegistro);
            ApellidosRegistrados = (TextView) itemView.findViewById(R.id.ApellidosRegistro);
            UserNickRegistrado = (TextView) itemView.findViewById(R.id.UserNickRegistro);
            CorreoRegistrado = (TextView) itemView.findViewById(R.id.CorreoRegistro);
            ContraRegistrado = (TextView) itemView.findViewById(R.id.ContrasenhaRegistro);
            RepContraRegistrado = (TextView) itemView.findViewById(R.id.RepContrRegistro);
        }
    }
}
