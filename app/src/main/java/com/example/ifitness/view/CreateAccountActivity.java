package com.example.ifitness.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ifitness.databinding.ActivityCreateAccountBinding;

public class CreateAccountActivity extends AppCompatActivity {
    private ActivityCreateAccountBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
    }
}