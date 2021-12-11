package com.example.ifitness.controller;

import com.example.ifitness.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class ProfileController {
    private FirebaseFirestore store = FirebaseFirestore.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private Usuario user = new Usuario();
    public Usuario getUserInformation(){
        user.setEmail(auth.getCurrentUser().getEmail());
        store.collection("User")
                .whereEqualTo("Email", user.getEmail())
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for (QueryDocumentSnapshot document: task.getResult()){
                            user.setNome(document.getData().get("Nome").toString());
                            user.setAtividadesFeitas(Long.parseLong(document.getData().get("atividades feitas").toString()));
                        }
                    }
                });
        return user;
    }
}
