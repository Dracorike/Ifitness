package com.example.ifitness.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.ifitness.model.Usuario;
import com.example.ifitness.view.LoginActivity;
import com.example.ifitness.view.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AccountController {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore store = FirebaseFirestore.getInstance();
    private boolean isCreated;
    private Context context;

    public AccountController(Context context){
        this.context = context;
    }

    public void createNewAccount(Usuario attributs){
        auth.createUserWithEmailAndPassword(attributs.getEmail(), attributs.getPassword())
                .addOnCompleteListener( task -> {
                    if (task.isSuccessful()){
                        Map<String, Object> user = new HashMap<>();
                        user.put("Email", attributs.getEmail());
                        user.put("Nome", attributs.getNome());
                        user.put("password", attributs.getPassword());
                        store.collection("User").add(user)
                                .addOnCompleteListener(it -> {
                                    switchActivity(LoginActivity.class);
                                    createMensage("Conta criada com sucesso!");
                                })
                                .addOnFailureListener(it ->{
                                    createMensage("Algo deu errado na criação de conta!");
                                });
                    }else{
                        createMensage("Algo deu errado na task!");
                    }
                });
    }

    public void loginUser(Usuario usuario){
        auth.signInWithEmailAndPassword(usuario.getEmail(), usuario.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        switchActivity(MainActivity.class);
                    }else{
                        createMensage("Algo deu errado no Login");
                    }
                });
    }

    public void switchActivity(Class classe){
        Intent intent = new Intent(context, classe);
        context.startActivity(intent);
    }

    private void createMensage(String mensage){
        Toast.makeText(context, mensage, Toast.LENGTH_LONG).show();
    }

}
