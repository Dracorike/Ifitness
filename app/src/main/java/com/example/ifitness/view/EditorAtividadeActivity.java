package com.example.ifitness.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ifitness.R;
import com.example.ifitness.controller.AtividadeController;
import com.example.ifitness.databinding.ActivityEditorAtividadeBinding;
import com.example.ifitness.model.Atividade;

public class EditorAtividadeActivity extends AppCompatActivity {
    private ActivityEditorAtividadeBinding bind;
    private int tipoAtividade;
    private AtividadeController controller;
    private static final String TAG_ERROR = "##Erro_Exception##";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityEditorAtividadeBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        initComponents();
        controller = new AtividadeController(this);
    }

    private void initComponents(){
        bind.radiogroupTipoAtividade.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.radio_caminhada:
                    tipoAtividade = 0;
                    break;
                case  R.id.radio_ciclismo:
                    tipoAtividade = 1;
                    break;
                case R.id.radio_corrida:
                    tipoAtividade = 2;
                    break;
                case R.id.radio_natacao:
                    tipoAtividade = 3;
                    break;
            }
        });
        bind.buttonSalvarAtividade.setOnClickListener(it -> {
            try {
                salvarAtividade();
            }catch (NullPointerException exception){
                Toast.makeText(
                        getApplicationContext(),
                        "Marque um tipo de atividade",
                        Toast.LENGTH_LONG).show();
            }catch (Exception exception){
                Log.e(TAG_ERROR, exception.getMessage());
            }
        });
    }

    private void salvarAtividade(){
        int tipoAtividade = this.tipoAtividade;
        int duracao = Integer.parseInt(bind.editTime.getText().toString());
        double km  = Double.parseDouble(bind.editDistancia.getText().toString());
        int calorias = Integer.parseInt(bind.editCalorias.getText().toString());
        Atividade atividade = controller.criarAtividade(tipoAtividade, duracao, km, calorias);
        controller.saveAtividade(atividade);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}