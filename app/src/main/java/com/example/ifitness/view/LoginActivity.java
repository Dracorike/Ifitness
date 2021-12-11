package com.example.ifitness.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ifitness.controller.AccountController;
import com.example.ifitness.databinding.ActivityLoginBinding;
import com.example.ifitness.model.Usuario;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding bind;
    private AccountController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        controller = new AccountController(this);
        initComponents();
    }

    private void initComponents(){
        bind.buttonLogin.setOnClickListener(it -> {
            Usuario usuario = new Usuario();
            usuario.setEmail(bind.editEmailLogin.getText().toString());
            usuario.setPassword(bind.editPasswordLogin.getText().toString());
            controller.loginUser(usuario);
        });
        bind.textClickaccountLogin.setOnClickListener(it ->{
            controller.switchActivity(CreateAccountActivity.class);
        });

    }


}