package com.example.ifitness.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.IconCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ifitness.R;
import com.example.ifitness.databinding.AtividadeItemBinding;

import java.util.List;

public class AtividadeAdapter extends RecyclerView.Adapter<AtividadeAdapter.ViewHolder> {
    private Context context;
    private List<Atividade> atividades;
    public AtividadeAdapter(Context context, List<Atividade> atividades){
        this.context = context;
        this.atividades = atividades;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.atividade_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind.imageIconAtividade.setImageResource(imageAtividade(atividades.get(position).getTipoAtividade()));
        holder.bind.textDistanceKm.setText(Double.toString(atividades.get(position).getDistanceKm()));
        holder.bind.textTimeAtividade.setText(Integer.toString(atividades.get(position).getDuracao()));
        holder.bind.textCaloriasAtividade.setText(Integer.toString(atividades.get(position).getCalorias()));
    }

    @Override
    public int getItemCount() {
        return atividades.size();
    }

    public int imageAtividade(int tipoAtividade){
        int image;
        switch (tipoAtividade){
            case 0:
                image = R.drawable.ic_walk;
                break;
            case 1:
                image = R.drawable.ic_bike;
                break;
            case 2:
                image = R.drawable.ic_run;
                break;
            case 3:
                image = R.drawable.ic_swim;
                break;
            default:
                image = -1;

        }
        return image;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        public AtividadeItemBinding bind;
        public ViewHolder(View itemView){
            super(itemView);
            bind = AtividadeItemBinding.bind(itemView);
        }

    }

}
