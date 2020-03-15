package com.example.app.ModelosAdaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app.Interfaces.AdminDetallesListDepart;
import com.example.app.R;
import java.util.ArrayList;

public class AdaptadorListadoDepFirebase extends RecyclerView.Adapter<AdaptadorListadoDepFirebase.ListadosFirebaseHolder>{

    //variables globales
    ArrayList<String> listaDepartFirebase;

    public AdaptadorListadoDepFirebase(ArrayList<String> listaDepartFirebase, AdaptadorFirebaseDepart.EventListener eventListener) {
        this.listaDepartFirebase = listaDepartFirebase;
    }

    @NonNull
    @Override
    public AdaptadorListadoDepFirebase.ListadosFirebaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistadodepartfirebase,null,false);

        return new ListadosFirebaseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorListadoDepFirebase.ListadosFirebaseHolder holder, int position) {

        holder.textListado.setText(listaDepartFirebase.get(position));

        holder.setOnClickListeners();

    }

    @Override
    public int getItemCount() {
        return listaDepartFirebase.size();
    }

    public class ListadosFirebaseHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textListado;

        Context context;

        Button botonDetalles;

        public ListadosFirebaseHolder(@NonNull View itemView) {

            super(itemView);

            context = itemView.getContext();

            textListado = (TextView) itemView.findViewById(R.id.textListdepartamentRecycler);

            //implementamos boton para ver detalles de elemento

            botonDetalles = (Button) itemView.findViewById(R.id.btnDetallesDepart);
        }

        void setOnClickListeners() {botonDetalles.setOnClickListener(this);}

        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.btnDetallesDepart:

                    Intent intent = new Intent(context, AdminDetallesListDepart.class);

                    intent.putExtra("datoEnviado",textListado.getText());

                    context.startActivity(intent);

                    break;
            }
        }
    }
}
