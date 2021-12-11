package com.example.ifitness.controller;

import android.content.Context;
import android.util.Log;

import com.example.ifitness.data.repository.RepositoryAtividade;
import com.example.ifitness.model.Atividade;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtividadeController {
    private List<Atividade> atividades = new ArrayList<>();
    private FirebaseFirestore store = FirebaseFirestore.getInstance();
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

    public void addAtividadeToProfile(FirebaseUser auth){
        try{
            Map<String, Object> newData = new HashMap<>();
            newData.put("atividades feitas", this.atividades.size()+1);
            store.collection("User")
                    .whereEqualTo("Email", auth.getEmail())
                    .get().addOnSuccessListener(task -> {
                String id = task.getDocuments().get(0).getId();
                store.collection("User").document(id)
                        .set(newData, SetOptions.merge())
                        .addOnSuccessListener(it -> Log.i("Sucess", "Sucesso!"))
                        .addOnFailureListener(it -> Log.e("Failure", "Falhou!"));
            });

        }catch (Exception ex ){
            Log.e("Erro", "Error: " + ex.toString());
        }
    }

}
