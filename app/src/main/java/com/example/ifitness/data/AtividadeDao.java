package com.example.ifitness.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ifitness.model.Atividade;

import java.util.List;
@Dao
public interface AtividadeDao {
    @Insert
    void insertAtividade(Atividade atividade);
    @Query("SELECT * FROM Atividade")
    List<Atividade> getAllAtividades();
    @Delete
    void deleteAtividade(Atividade atividade);
}
