package com.example.ifitness.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Atividade implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "tipoAtividade")
    private int tipoAtividade;
    @ColumnInfo(name = "duracao")
    private int duracao;
    @ColumnInfo(name = "distanceKm")
    private double distanceKm;
    @ColumnInfo(name = "calorias")
    private int calorias;

    public Atividade(){

    }

    public int getTipoAtividade() {
        return tipoAtividade;
    }
    public void setTipoAtividade(int tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    public int getDuracao() {
        return duracao;
    }
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public double getDistanceKm() {
        return distanceKm;
    }
    public void setDistanceKm(double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public int getCalorias() {
        return calorias;
    }
    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
}
