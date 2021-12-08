package com.example.ifitness.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.ifitness.data.repository.RepositoryUsuario;
import com.example.ifitness.model.Usuario;

public class AccountController {
    private Context context;
    private RepositoryUsuario repositoryUsuario;
    public AccountController(Context context){
        this.context = context;
        repositoryUsuario = new RepositoryUsuario(context);
    }

    public boolean createNewAccount(Usuario user){
        if (!user.getEmail().isEmpty() && !user.getNome().isEmpty() && !user.getPassword().isEmpty()){
            repositoryUsuario.saveUsuario(user);
            Toast.makeText(context, "Repositório Criado com sucesso!", Toast.LENGTH_SHORT).show();
            return true;
        }else {
            Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean loginUser(Usuario usuario){
            Usuario user = repositoryUsuario.loginUser(usuario);
            if(user == null){
                Toast.makeText(context, "Email ou senha incorretos!",
                        Toast.LENGTH_SHORT).show();
                return false;
            }else if(usuario.getEmail().equals(user.getEmail()) && usuario.getEmail().equals(user.getEmail())){
                return true;
            }else {
                Toast.makeText(context, "Email ou senha incorretos!", Toast.LENGTH_SHORT).show();
                return false;
            }
    }

}
