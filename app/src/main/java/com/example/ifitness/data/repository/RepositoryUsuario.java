package com.example.ifitness.data.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.room.Room;

import com.example.ifitness.data.AppDatabase;
import com.example.ifitness.model.Usuario;

import java.util.List;

public class RepositoryUsuario {
    private Context context;
    private AppDatabase database;

    public RepositoryUsuario(Context context){
        this.context = context;
        database = Room.databaseBuilder(context, AppDatabase.class, "database-name")
                .allowMainThreadQueries().build();
    }

    public void saveUsuario(Usuario usuario){
        List<Usuario> usuarios = getAllUsers();
        if(!hasUser(usuario, usuarios)){
            database.usuarioDao().createNewUser(usuario);
        }else {
            Toast.makeText(context, "Usuario já existente", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean hasUser(Usuario usuario, List<Usuario> usuarios){
        for (Usuario users: usuarios){
            if(users.getEmail() == usuario.getEmail()){
                return true;
            }
        }
        return false;
    }
    public List<Usuario> getAllUsers(){
        return database.usuarioDao().getAllUsers();
    }

    public Usuario loginUser(Usuario usuario){
        return database.usuarioDao().loginUser(usuario.getEmail());
    }
}
