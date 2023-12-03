package com.example.a3enrayas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView txtresult;
    private ToggleButton tgbtn;
    private Integer[] botones;
    private int tablero[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //enlazamos el texto y lo hacemos invisible
        txtresult = (TextView) findViewById(R.id.txtResult);
        txtresult.setVisibility(View.INVISIBLE);

        tgbtn = (ToggleButton) findViewById(R.id.btnInicio);

        //metemos en el array todos los botones
        botones = new Integer[]{R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9};

        //inicializamos el tablero
        tablero = new int[]{0,0,0,0,0,0,0,0,0};

    }

    public void pintarJugada(View v)
    {
        Aleatoria aleatoria = new Aleatoria(botones,tablero,this);

        //capturamos el boton pulsado en el tablero
        int numBoton = Arrays.asList(botones).indexOf(v.getId());

        ImageButton imgbt = findViewById(v.getId());
        imgbt.setImageResource(R.drawable.humano);

        tablero[numBoton] = 1;

        aleatoria.play();

    }

    public void btnPartida(View view) {


    }
}