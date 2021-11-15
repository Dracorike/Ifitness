package com.example.ifitness.data.repository;

import android.content.Context;

import androidx.room.Room;

import com.example.ifitness.data.AppDatabase;
import com.example.ifitness.model.Atividade;

import java.util.List;

public class RepositoryAtividade {
    private Context context;
    private AppDatabase database;
    public RepositoryAtividade(Context context){
        this.context = context;
        database =
                Room.databaseBuilder(context, AppDatabase.class, "database-name")
                        .allowMainThreadQueries().build();
    }
    public void saveAtividade(Atividade atividade){
        database.atividadeDao().insertAtividade(atividade);
    }
    public List<Atividade> getAllAtividades(){
        return database.atividadeDao().getAllAtividades();
    }
}
