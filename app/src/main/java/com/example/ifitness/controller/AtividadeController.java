package com.example.ifitness.controller;

import android.content.Context;

import com.example.ifitness.data.repository.RepositoryAtividade;
import com.example.ifitness.model.Atividade;


import java.util.ArrayList;
import java.util.List;

public class AtividadeController {
    private List<Atividade> atividades = new ArrayList<>();
    private Context context;
    private RepositoryAtividade repository;
    public AtividadeController(Context context){
        this.context = context;
        repository = new RepositoryAtividade(context);
        atividades.addAll(repository.getAllAtividades());
    }

    public Atividade criarAtividade(int tipoAtividade, int duracao, double km, int calorias){
        Atividade atividade = new Atividade();
        atividade.setTipoAtividade(tipoAtividade);
        atividade.setDuracao(duracao);
        atividade.setDistanceKm(km);
        atividade.setCalorias(calorias);
        return atividade;
    }
    public void saveAtividade(Atividade atividade){
        repository.saveAtividade(atividade);
    }

    public List<Atividade> getAtividadeList(){
        return atividades;
    }
    public String generalDetails(){
        int atividades = this.atividades.size();
        int minutos = 0;
        double km = 0.0;
        int calorias = 0;
        for (Atividade at: this.atividades){
            minutos += at.getDuracao();
            km += at.getDistanceKm();
            calorias += at.getCalorias();
        }
        return (atividades + " atividade(s)-" + minutos + " minutos-" + km + " km-" + calorias + " calorias");
    }
}
