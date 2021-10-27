package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * @author Anthony Leon
 * @version 22/10/2021/
 */

public class MainActivity extends AppCompatActivity {
    private EditText txtOperaciones;
    private EditText txtResultado;
    private Teclado teclado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtOperaciones = (EditText) findViewById(R.id.txtOperaciones);
        txtResultado = (EditText) findViewById(R.id.txtResultado);
        teclado = new Teclado(txtOperaciones, txtResultado);

        View.OnClickListener pulsarTecla = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btnOFF:teclado.off();break;
                    case R.id.btnDividir:teclado.dividir();break;
                    case R.id.btnMultiplicar:teclado.multiplicar();break;
                    case R.id.btnC:teclado.c();break;
                    case R.id.btnDel:teclado.del();break;
                    case R.id.btnSumar:teclado.sumar();break;
                    case R.id.btnRestar:teclado.restar();break;
                    case R.id.btnRaizCuadrada:teclado.raizCuadrada();break;
                    case R.id.btnPorcentaje:teclado.porcentaje();break;
                    case R.id.btnPunto:teclado.punto();break;
                    /*
                    case R.id.btnSIN:teclado.();break;
                    case R.id.btnCOS:teclado.();break;
                    case R.id.btnTAN:teclado.();break;
                    case R.id.btnCSC:teclado.();break;
                    case R.id.btnSEC:teclado.();break;
                    case R.id.btnCTG:teclado.();break;
                    case R.id.btnXExclamacion:teclado.();break;
                    case R.id.btnSuperIndice:teclado.();break;
                     */
                    case R.id.btnCero:teclado.cero();break;
                    case R.id.btnUno:teclado.uno();break;
                    case R.id.btnDos:teclado.dos();break;
                    case R.id.btnTres:teclado.tres();break;
                    case R.id.btnCuatro:teclado.cuatro();break;
                    case R.id.btnCinco:teclado.cinco();break;
                    case R.id.btnSeis:teclado.seis();break;
                    case R.id.btnSiete:teclado.siete();break;
                    case R.id.btnOcho:teclado.ocho();break;
                    case R.id.btnNueve:teclado.nueve();break;
                }
            }
        };

        ArrayList<View> allButtons = ((LinearLayout)findViewById(R.id.lyMain)).getTouchables();

        for (View v :
                allButtons) {
            v.setOnClickListener(pulsarTecla);
        }



        View.OnClickListener ResultadoBtnIgual = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teclado.igual();
            }
        };

        findViewById(R.id.btnIgual).setOnClickListener(ResultadoBtnIgual);

        View.OnClickListener resultadoTiempoReal = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };
    }
}