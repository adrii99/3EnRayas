package com.example.a3enrayas;

public class Directa extends AndroidJugada{

    private Integer[] botones;
    private int tablero[];
    private MainActivity activity;

    public Directa(Integer[] btn, int tab[], MainActivity act) {

        this.botones = btn;
        this.tablero = tab;
        this.activity = act;
    }

    @Override
    void play() {

    }
}
