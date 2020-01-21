package com.example.app.Modelos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app.R;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//clase utilidad adaptador que rellena los campos
public class AdaptadorFichajes extends RecyclerView.Adapter<AdaptadorFichajes.FichajesViewHolder> {
    //variables globales
    ArrayList<FichajeHora> listaFichajes;
    final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    final SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");

    //constructor de clase recibe como argumento array de tipo fichaje
    public AdaptadorFichajes(ArrayList<FichajeHora> listaFichajes) throws IOException, ClassNotFoundException {
        this.listaFichajes = listaFichajes;
    }

    //retorna los itemlist que creamos en el layout en el contexto
    public FichajesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistfichajes,null,false);
        return new FichajesViewHolder(view);
    }

    @Override
    //recoge y sete los valores enviado por el fragment
    public void onBindViewHolder(@NonNull FichajesViewHolder holder, int position) {
        holder.fechaEntrada.setText(listaFichajes.get(position).getFechaEntrada());
        holder.horaEntrada.setText(listaFichajes.get(position).getHoraEntrada());
        holder.tipoHorario.setText(listaFichajes.get(position).getTipoMarcado());
    }

    @Override
    //retorna la cantidad total de los ifchajes
    public int getItemCount() {
        return listaFichajes.size();
    }

    //recoge id de los elementos en las variables definidas
    public class FichajesViewHolder extends RecyclerView.ViewHolder {
        TextView fechaEntrada,horaEntrada,tipoHorario;
        public FichajesViewHolder(@NonNull View itemView) {
            super(itemView);
            fechaEntrada = (TextView) itemView.findViewById(R.id.fechaEntrada);
            horaEntrada = (TextView) itemView.findViewById(R.id.horaEntrada);
            tipoHorario = (TextView) itemView.findViewById(R.id.tipoHorario);
        }
    }
}
