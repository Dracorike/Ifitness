package com.example.ifitness.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.example.ifitness.R;
import com.example.ifitness.controller.AtividadeController;
import com.example.ifitness.databinding.ActivityMainBinding;
import com.example.ifitness.model.Atividade;
import com.example.ifitness.model.AtividadeAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding bind;
    private NavController nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        initDrawerLayout();
        fragmentManager(new MainFragment());

    }

    public void fragmentManager(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_fragment, fragment);
        transaction.commit();
    }

    public void initDrawerLayout(){
        setSupportActionBar(bind.actionBarMain);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, bind.drawerLayoutMain,
                bind.actionBarMain, R.string.open_drawer, R.string.close_drawer);
        bind.drawerLayoutMain.addDrawerListener(toggle);
        toggle.syncState();

        bind.navControllerMain.setNavigationItemSelectedListener(this);
    }


    private void switchActivity(Class classe){
        Intent intent = new Intent(getApplicationContext(), classe);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if(bind.drawerLayoutMain.isDrawerOpen(GravityCompat.START)){
            bind.drawerLayoutMain.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.option_login:
                switchActivity(LoginActivity.class);
                break;
            case R.id.option_create:
                switchActivity(CreateAccountActivity.class);
                break;
        }
        bind.drawerLayoutMain.closeDrawer(GravityCompat.START);
        return true;
    }
}