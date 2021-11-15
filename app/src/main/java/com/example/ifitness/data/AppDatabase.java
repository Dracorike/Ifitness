package com.example.ifitness.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ifitness.data.dao.AtividadeDao;
import com.example.ifitness.data.dao.UsuarioDao;
import com.example.ifitness.model.Atividade;
import com.example.ifitness.model.Usuario;

@Database(entities = {Atividade.class, Usuario.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AtividadeDao atividadeDao();
    public abstract UsuarioDao usuarioDao();
}
