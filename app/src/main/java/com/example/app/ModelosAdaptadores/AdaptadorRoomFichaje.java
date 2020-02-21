package com.example.app.ModelosAdaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app.ConexionesRoom.FichajeRoom;
import com.example.app.R;
import java.text.SimpleDateFormat;
import java.util.List;

public class AdaptadorRoomFichaje extends RecyclerView.Adapter<AdaptadorRoomFichaje.ListadoRoomDBViewHolder> {

    //variables globales
    List<FichajeRoom> listadoFichajesRoom;

    final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    final SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");

    public AdaptadorRoomFichaje(List<FichajeRoom> listadoFichajesRoom) {

        this.listadoFichajesRoom = listadoFichajesRoom;
    }

    @Override
    public AdaptadorRoomFichaje.ListadoRoomDBViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistroombd,null,false);

        return new ListadoRoomDBViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRoomFichaje.ListadoRoomDBViewHolder holder, int position) {

        holder.diaRoom.setText(listadoFichajesRoom.get(position).getDiaFichaje());

        holder.horaRoom.setText(listadoFichajesRoom.get(position).getHorafichaje());

        holder.tipoFichajeRoom.setText(listadoFichajesRoom.get(position).getTipoFichaje());
    }

    @Override
    public int getItemCount() { return listadoFichajesRoom.size(); }

    public class ListadoRoomDBViewHolder extends RecyclerView.ViewHolder {

        TextView horaRoom,diaRoom,tipoFichajeRoom;

        public ListadoRoomDBViewHolder(@NonNull View itemView) {
            super(itemView);

            diaRoom = (TextView) itemView.findViewById(R.id.fechaEntradaRoom);

            horaRoom = (TextView) itemView.findViewById(R.id.horaEntradaRoom);

            tipoFichajeRoom = (TextView) itemView.findViewById(R.id.tipoHorarioRoom);
        }
    }
}
