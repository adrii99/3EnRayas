package com.example.a3enrayas;

import android.widget.ImageButton;

import java.util.Random;

public class Aleatoria extends AndroidJugada {

    private Integer[] botones;
    private int tablero[];
    private MainActivity activity;

    public Aleatoria(Integer[] btn, int tab[], MainActivity act) {

        this.botones = btn;
        this.tablero = tab;
        this.activity = act;
    }


    @Override
    public void play() {

        Random aleatorio = new Random();

        //obtiene una posicion aleatoria del tablero
        int posicion = aleatorio.nextInt(tablero.length);

        while(tablero[posicion] != 0)
        {
            posicion = aleatorio.nextInt(tablero.length);
        }

        ImageButton imgbt = (ImageButton) activity.findViewById(botones[posicion]);
        imgbt.setImageResource(R.mipmap.ic_launcher);

        tablero[posicion] = -1;
    }
}

