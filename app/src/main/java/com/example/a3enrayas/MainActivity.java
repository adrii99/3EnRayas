package com.example.a3enrayas;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.util.Arrays;


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

    /*
    estadoPar = 1 gana el humano
    estadoPar = -1 gana la maquina
    estadoPar = 2 empate
     */
    private int estadoPar,totalFichas = 0;

    /*
    turno humano = 1
    turno maquina = -1
    */
    private int turno = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //metemos en el array todos los botones
        botones = new Integer[]{R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};

        //inicializamos el tablero
        tablero = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

        //enlazamos el texto
        txtresult = (TextView) findViewById(R.id.txtResult);

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

                    txtresult.setText("The game is running....");
                }
                else
                {
                    txtresult.setText("!!The game is stopped¡¡");
                    modofacil.setEnabled(true);
                    modoDificil.setEnabled(true);
                }
            }
        });

    }

    //Funcion que pinta las jugadas en el tablero
    public void pintarJugada(View v)
    {
        //Instanciamos la clase aleatoria y directa
        Aleatoria aleatoria = new Aleatoria(botones, tablero, MainActivity.this);
        Directa directa = new Directa(botones,tablero,MainActivity.this);

        if(estadoPar == 0)
        {
            //capturamos el boton pulsado en el tablero
            int numBoton = Arrays.asList(botones).indexOf(v.getId());

            turno = 1;

            if(tablero[numBoton] == 0)
            {
                ImageButton imgbt = (ImageButton) findViewById(v.getId());
                imgbt.setImageResource(R.drawable.humano);

                //Marcamos la posicion en el tablero
                tablero[numBoton] = 1;

                //contamos las fichas puestas
                totalFichas += 1;

                //Comprobamos el estado de la partida
                estadoPar = comprobarPar();

                terminarPar();

                if (estadoPar == 0)
                {
                    //Vemos que modo de juego se ha elegido
                    if (radiogroup.getCheckedRadioButtonId() == R.id.rbFacil)
                    {
                        aleatoria.play();
                        turno = -1;
                        totalFichas += 1;
                        estadoPar = comprobarPar();
                        terminarPar();
                    }
                    else if (radiogroup.getCheckedRadioButtonId() == R.id.rbDificil)
                    {
                        directa.play();
                        turno = -1;
                        totalFichas += 1;
                        estadoPar = comprobarPar();
                        terminarPar();
                    }
                }
            }
        }

    }

    //Funcion que comprueba el estado de la partida
    public int comprobarPar()
    {
      int estado = 0;

      //Comprueba filas
      if(Math.abs(tablero[0]+tablero[1]+tablero[2]) == 3)
      {
          estado = 1 * turno;
      }
      else if(Math.abs(tablero[3]+tablero[4]+tablero[5]) == 3)
      {
          estado = 1 * turno;
      }
      else if(Math.abs(tablero[6]+tablero[7]+tablero[8]) == 3)
      {
          estado = 1 * turno;
      }
      //Comprueba columnas
      else if(Math.abs(tablero[0]+tablero[3]+tablero[6]) == 3)
      {
          estado = 1 * turno;
      }
      else if(Math.abs(tablero[1]+tablero[4]+tablero[7]) == 3)
      {
          estado = 1 * turno;
      }
      else if(Math.abs(tablero[2]+tablero[5]+tablero[8]) == 3)
      {
          estado = 1 * turno;
      }
      //Comprueba diagonales
      else if(Math.abs(tablero[0]+tablero[4]+tablero[8]) == 3)
      {
          estado = 1 * turno;
      }
      else if(Math.abs(tablero[2]+tablero[4]+tablero[6]) == 3)
      {
          estado = 1 * turno;
      }
      //Empate
      else if(totalFichas == 9)
      {
          estado = 2;
      }

      return estado;
    }

    //Funcion que termimna la partida
    public void terminarPar()
    {
        if(estadoPar == 1)
        {
            txtresult.setVisibility(View.VISIBLE);
            txtresult.setText("Has Ganadoo!!!");
            txtresult.setTextColor(Color.GREEN);
        }
        else if(estadoPar == -1)
        {
            txtresult.setVisibility(View.VISIBLE);
            txtresult.setText("Ha ganado la maquina.... :(");
            txtresult.setTextColor(Color.RED);
        }
        else if(estadoPar == 2)
        {
            txtresult.setVisibility(View.VISIBLE);
            txtresult.setText("Ha habido un empate");
            txtresult.setTextColor(Color.BLUE);
        }

    }
}


