package com.example.a3enrayas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView txtresult;
    private RadioGroup radiogroup;
    private RadioButton modofacil;
    private RadioButton modoDificil;
    private ToggleButton tgbtn;
    private Integer[] botones;
    private int tablero[];

    //Se usa para deshabilitar el tablero de juego
    private ImageButton deshbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //metemos en el array todos los botones
        botones = new Integer[]{R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};


        //inicializamos el tablero
        tablero = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

        //enlazamos el texto y lo hacemos invisible
        txtresult = (TextView) findViewById(R.id.txtResult);
        txtresult.setVisibility(View.INVISIBLE);

        //capturamos los botones
        tgbtn = (ToggleButton) findViewById(R.id.btnInicio);
        radiogroup = (RadioGroup) findViewById(R.id.RadioGroup);
        modofacil = (RadioButton) findViewById(R.id.rbFacil);
        modoDificil = (RadioButton) findViewById(R.id.rbDificil);

        //Desabilitamos el tablero de juego

        for(Integer inte : botones)
        {
            deshbtn = (ImageButton)  findViewById(inte);

            if(deshbtn != null)
            {
                deshbtn.setEnabled(false);
            }
        }

        tgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tgbtn.isChecked())
                {

                    //Habilitamos el tablero de juego
                    for(Integer inte : botones)
                    {
                        deshbtn = (ImageButton)  findViewById(inte);

                        if(deshbtn != null)
                        {
                            deshbtn.setEnabled(true);
                        }
                    }

                    //Deshabilitamos los botones de niveles
                    modofacil.setEnabled(false);
                    modoDificil.setEnabled(false);

                    if (radiogroup.getCheckedRadioButtonId() == R.id.rbFacil)
                    {



                    }

                }
                else
                {

                    modofacil.setEnabled(true);
                    modoDificil.setEnabled(true);
                }
            }
        });

    }

    public void pintarJugada(View v)
    {

        Aleatoria aleatoria = new Aleatoria(botones, tablero, MainActivity.this);

        //capturamos el boton pulsado en el tablero
        int numBoton = Arrays.asList(botones).indexOf(v.getId());

        ImageButton imgbt = (ImageButton) findViewById(v.getId());
        imgbt.setImageResource(R.drawable.humano);

        tablero[numBoton] = 1;

        aleatoria.play();

    }
}


