package com.example.ifitness.data;

import android.content.Context;

import androidx.room.Room;

import com.example.ifitness.model.Atividade;

import java.util.List;

public class Repository {
    private Context context;
    private AppDatabase database;
    public Repository(Context context){
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
