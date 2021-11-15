package com.example.ifitness.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ifitness.model.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Insert
    void createNewUser(Usuario usuario);

    @Delete
    void deleteUser(Usuario usuario);

    @Query("SELECT * FROM usuario WHERE email = :email")
    Usuario loginUser(String email);

    @Query("SELECT * FROM usuario")
    List<Usuario> getAllUsers();
}
