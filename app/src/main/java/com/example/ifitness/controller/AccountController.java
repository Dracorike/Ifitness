package com.example.ifitness.controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.ifitness.data.repository.RepositoryUsuario;
import com.example.ifitness.model.Usuario;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class AccountController {
    private FirebaseFirestore data = FirebaseFirestore.getInstance();
    private boolean created;
    private boolean logged = false;

    public AccountController(Context context){
    }

    public boolean createNewAccount(Usuario attributs){
        Map<String, Object> users = new HashMap<>();
        users.put("nome", attributs.getNome());
        users.put("email", attributs.getEmail());
        users.put("password", attributs.getPassword());

        data.collection("User")
                .add(users)
                .addOnSuccessListener(it -> isCreated(true))
                .addOnFailureListener(it -> isCreated(false));
        if(this.created){
            return true;
        }else{
            return false;
        }
    }

    private void  isCreated(boolean isTrueOrFalse){
        this.created = isTrueOrFalse;
    }


    public boolean loginUser(Usuario usuario){
        CollectionReference usersRef = data.collection("User");
        Query query = usersRef.whereEqualTo("email", usuario.getEmail());
        query.get().addOnCompleteListener(it -> {
           if (it.isSuccessful()){
               for (QueryDocumentSnapshot document: it.getResult()){
                   if(document.getData().get("email").equals(usuario.getEmail()) &&
                   document.getData().get("password").equals(usuario.getPassword())){
                       logged = true;
                       break;
                   }
               }
           }else{
               logged = false;
           }
        });
        return logged;
    }

}
