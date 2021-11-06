package com.example.ifitness.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.ifitness.controller.AtividadeController;
import com.example.ifitness.databinding.ActivityMainBinding;
import com.example.ifitness.model.Atividade;
import com.example.ifitness.model.AtividadeAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding bind;
    private AtividadeController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        controller = new AtividadeController(this);
        initComponents();
    }

    private void initComponents(){
        bind.buttonAddAtividade.setOnClickListener(it -> {
            switchActivity();
        });
        if (!controller.getAtividadeList().isEmpty()){
            bind.recyclerListAtividades.setVisibility(View.VISIBLE);
            bind.recyclerListAtividades.setAdapter(newAdapterRecyclerView());
            bind.recyclerListAtividades.setLayoutManager(new LinearLayoutManager(this));
        }
        bind.textGeneralDetails.setText(controller.generalDetails());
    }

    private void switchActivity(){
        Intent intent = new Intent(getApplicationContext(), EditorAtividadeActivity.class);
        startActivity(intent);
    }


    private AtividadeAdapter newAdapterRecyclerView(){
        return new AtividadeAdapter(getApplicationContext(), controller.getAtividadeList());
    }

}