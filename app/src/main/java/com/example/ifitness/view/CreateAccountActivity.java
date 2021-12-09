package com.example.ifitness.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ifitness.controller.AccountController;
import com.example.ifitness.databinding.ActivityCreateAccountBinding;
import com.example.ifitness.model.Usuario;

public class CreateAccountActivity extends AppCompatActivity {
    private ActivityCreateAccountBinding bind;
    private AccountController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        controller = new AccountController(this);
        initComponents();
    }

    private void initComponents(){
        bind.buttonCreateaccount.setOnClickListener(it ->{
            Usuario usuario = new Usuario();
            usuario.setNome(bind.editNomeCreate.getText().toString());
            usuario.setEmail(bind.editEmailCreate.getText().toString());
            usuario.setPassword(bind.editPasswordCreate.getText().toString());
            if(controller.createNewAccount(usuario)){
                switchActivity();
            }else {
                Toast.makeText(this, "Algo deu errado", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void switchActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}