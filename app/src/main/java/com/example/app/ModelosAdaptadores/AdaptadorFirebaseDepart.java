package com.example.app.ModelosAdaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app.R;
import java.util.ArrayList;

public class AdaptadorFirebaseDepart extends RecyclerView.Adapter<AdaptadorFirebaseDepart.ListaDepartHolder>{

    //variables globales
    ArrayList<String> listaDepartFirebase;

    public AdaptadorFirebaseDepart(ArrayList<String> listaDepartFirebase, EventListener eventListener) {
        this.listaDepartFirebase = listaDepartFirebase;
        this.eventListener = eventListener;
    }

    @NonNull
    @Override
    public AdaptadorFirebaseDepart.ListaDepartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_depart_listado_firebase,null,false);
        return new ListaDepartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorFirebaseDepart.ListaDepartHolder holder, int position) {

        holder.departamentText.setText(listaDepartFirebase.get(position));

        holder.setOnClickListeners();

    }

    @Override
    public int getItemCount() {return listaDepartFirebase.size(); }

    public class ListaDepartHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView departamentText;

        public ListaDepartHolder(@NonNull View itemView) {

            super(itemView);

            departamentText = (TextView) itemView.findViewById(R.id.textdepartamentRecycler);
        }

        void setOnClickListeners() {departamentText.setOnClickListener(this);}

        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.textdepartamentRecycler:
                    eventListener.onEventName(listaDepartFirebase.get(getAdapterPosition()));
                    break;

            }
        }
    }

    private EventListener eventListener;

    public interface EventListener {
        void onEventName(String nombre);
    }


}
