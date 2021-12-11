package com.example.ifitness.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ifitness.R;
import com.example.ifitness.controller.ProfileController;
import com.example.ifitness.databinding.FragmentProfileBinding;
import com.example.ifitness.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;


public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private ProfileController controller = new ProfileController();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore store = FirebaseFirestore.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        initComponents();
        return binding.getRoot();
    }

    private void initComponents(){
        Usuario user = new Usuario();
        user.setEmail(auth.getCurrentUser().getEmail());
        store.collection("User")
                .whereEqualTo("Email", user.getEmail())
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        user.setNome(task.getResult().getDocuments().get(0).getData().get("Nome").toString());
                        user.setAtividadesFeitas(Long.parseLong(task.getResult().getDocuments().get(0).getData().get("atividades feitas").toString()));
                        long atividades = user.getAtividadesFeitas();
                        binding.textNameuserProfile.setText(user.getNome());
                        binding.textEmailuserProfile.setText(user.getEmail());
                        binding.textAtividadesfeitasProfile.setText(Long.toString(atividades));
                    }
                });
    }
}