package com.example.ifitness.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ifitness.R;
import com.example.ifitness.controller.AtividadeController;
import com.example.ifitness.databinding.FragmentMainBinding;
import com.example.ifitness.model.AtividadeAdapter;

public class MainFragment extends Fragment {
    private FragmentMainBinding bind;
    private AtividadeController controller;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bind = FragmentMainBinding.inflate(inflater, container, false);
        controller = new AtividadeController(getContext());
        initComponents();
        return bind.getRoot();
    }
    private void switchActivity(Class classe){
        Intent intent = new Intent(getContext(), classe);
        startActivity(intent);
    }

    private AtividadeAdapter newAdapterRecyclerView(){
        return new AtividadeAdapter(getContext(), controller.getAtividadeList());
    }
    private void initComponents(){
        bind.buttonAddAtividade.setOnClickListener(it -> {
            switchActivity(EditorAtividadeActivity.class);
        });
        if (!controller.getAtividadeList().isEmpty()){
            bind.recyclerListAtividades.setVisibility(View.VISIBLE);
            bind.recyclerListAtividades.setAdapter(newAdapterRecyclerView());
            bind.recyclerListAtividades.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        bind.textGeneralDetails.setText(controller.generalDetails());
    }
}