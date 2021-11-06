package com.example.ifitness.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ifitness.model.Atividade;

@Database(entities = {Atividade.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AtividadeDao atividadeDao();
}
