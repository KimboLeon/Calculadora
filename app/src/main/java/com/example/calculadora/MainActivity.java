package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * @author Anthony Leon
 * @version 03/11/2021/
 */

public class MainActivity extends AppCompatActivity {
    private EditText txtOperaciones;
    private EditText txtResultado;
    private Teclado teclado;
    private Button btnPuntoComa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtOperaciones = (EditText) findViewById(R.id.txtOperaciones);
        txtResultado = (EditText) findViewById(R.id.txtResultado);
        btnPuntoComa = (Button) findViewById(R.id.btnPunto);
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
                    case R.id.btnSIN:teclado.sin();break;
                    case R.id.btnCOS:teclado.cos();break;
                    case R.id.btnTAN:teclado.tan();break;
                    case R.id.btnCSC:teclado.csc();break;
                    case R.id.btnSEC:teclado.sec();break;
                    case R.id.btnCTG:teclado.ctg();break;
                    case R.id.btnFactorial:teclado.factorial();break;
                    case R.id.btnExponente:teclado.exponente();break;
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


        // PUNTO/COMA

        View.OnClickListener punto = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teclado.punto();
            }
        };

        View.OnLongClickListener coma = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                teclado.coma();
                return true;
            }
        };

        btnPuntoComa.setOnClickListener(punto);
        btnPuntoComa.setOnLongClickListener(coma);

        // IGUAL

        View.OnClickListener ResultadoBtnIgual = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teclado.igual();
            }
        };

        findViewById(R.id.btnIgual).setOnClickListener(ResultadoBtnIgual);

        // PARENTESIS

        View.OnClickListener abrirParentesis = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teclado.abrirParentesis();
            }
        };

        View.OnLongClickListener cerrarParentesis = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                teclado.cerrarParentesis();
                return true;
            }
        };

        findViewById(R.id.btnParentesis).setOnClickListener(abrirParentesis);
        findViewById(R.id.btnParentesis).setOnLongClickListener(cerrarParentesis);
    }
}