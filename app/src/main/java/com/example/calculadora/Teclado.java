package com.example.calculadora;

import android.widget.EditText;

import java.text.DecimalFormat;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Teclado {
    private EditText operaciones;
    private EditText resultado;
    private boolean resultadoTiempoReal;

    public Teclado(EditText operaciones, EditText resultado){
        this.operaciones = operaciones;
        this.resultado = resultado;
        this.resultadoTiempoReal = false;
    }
    
    // IGUAL
    public void igual(){
        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (operaciones.length() > 0){
            if (s.charAt(l) != '+' && s.charAt(l) != '-' && s.charAt(l) != '*' && s.charAt(l) != '/'
                    && s.charAt(l) != '.' && s.charAt(l) != '(' && s.charAt(l) != '%'){

                String resultado = "";

                ScriptEngineManager mgr = new ScriptEngineManager();
                ScriptEngine engine = mgr.getEngineByName("js");

                try {
                    resultado = String.valueOf(engine.eval(s));
                } catch (ScriptException e) {
                    e.printStackTrace();
                }

                double f = Double.parseDouble(resultado);
                String formatNumber = "#.####";
                DecimalFormat d = new DecimalFormat(formatNumber);
                resultado = d.format(f);

                this.resultado.setText(resultado.replace(',','.'));



            }
        }
    }
    
    // OFF
    public void off(){
        System.exit(0);
    }

    // C
    public void c(){
        this.operaciones.getText().clear();
        this.resultado.getText().clear();
        this.resultadoTiempoReal = false;
    }

    // DEL
    public void del(){
        String operaciones = this.operaciones.getText().toString().toString();

        try {
            this.operaciones.setText(operaciones.substring(0,operaciones.length()-1));
        } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException){
            System.out.println("Se han borrado todos los caracteres.");
            this.resultadoTiempoReal = false;
        }
    }

    // PUNTO
    public void punto(){
        boolean puedeEscribir = true;
        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (this.operaciones.length() <= 0){

            this.operaciones.setText("0.");
        } else {

            for (int i = l; i >= 0; i--){
                if (s.charAt(i) == '.'){
                    puedeEscribir = false;
                    break;
                } else {
                    if (s.charAt(i) == '+'||s.charAt(i) == '-'||s.charAt(i) == '*' ||s.charAt(i) == '/'
                            ||s.charAt(i) == '.'){
                        break;
                    }
                }
            }

            if (s.charAt(l) == '+'||s.charAt(l) == '-'||s.charAt(l) == '*' ||s.charAt(l) == '/'
                    ||s.charAt(l) == '.'){
                puedeEscribir = false;
            }

            if (puedeEscribir){
                this.operaciones.append(".");
            }
        }
    }

    // SIN

    // COS

    // TAN

    // CSC

    // SEC

    // CTG

    // OFF

    // X!

    // x^

    // OPERACIONES BASICAS

    public void sumar(){

        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (s.length() > 0){
            if (s.charAt(l) != '+'){
                this.operaciones.append("+");
                this.resultadoTiempoReal = true;
            }
        }
    }

    public void restar(){

        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (s.length() > 0){
            if (s.charAt(l) != '-'){
                this.operaciones.append("-");
                this.resultadoTiempoReal = true;
            }
        }
    }

    public void multiplicar(){

        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (s.length() > 0){
            if (s.charAt(l) != '*'){
                this.operaciones.append("*");
                this.resultadoTiempoReal = true;
            }
        }
    }

    public void dividir(){

        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (s.length() > 0){
            if (s.charAt(l) != '/'){
                this.operaciones.append("/");
                this.resultadoTiempoReal = true;
            }
        }
    }

    public void porcentaje(){

        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (s.length() > 0){
            if (s.charAt(l) != '%'){
                this.operaciones.append("%");
                this.resultadoTiempoReal = true;
            }
        }

        if (resultadoTiempoReal){
            igual();
        }
    }

    public void raizCuadrada(){
        this.operaciones.append("√");
    }

    // NUMEROS

    public void cero(){
        String s = operaciones.getText().toString();

        if (s.length() > 0){
            this.operaciones.append("0");
        }

        if (resultadoTiempoReal){
            igual();
        }
    }

    public void uno(){

        this.operaciones.append("1");

        if (resultadoTiempoReal){
            igual();
        }

    }

    public void dos(){

        this.operaciones.append("2");

        if (resultadoTiempoReal){
            igual();
        }
    }

    public void tres(){

        this.operaciones.append("3");

        if (resultadoTiempoReal){
            igual();
        }
    }

    public void cuatro(){

        this.operaciones.append("4");

        if (resultadoTiempoReal){
            igual();
        }
    }

    public void cinco(){

        this.operaciones.append("5");

        if (resultadoTiempoReal){
            igual();
        }
    }

    public void seis(){

        this.operaciones.append("6");

        if (resultadoTiempoReal){
            igual();
        }
    }

    public void siete(){

        this.operaciones.append("7");

        if (resultadoTiempoReal){
            igual();
        }
    }

    public void ocho(){

        this.operaciones.append("8");

        if (resultadoTiempoReal){
            igual();
        }
    }

    public void nueve(){

        this.operaciones.append("9");

        if (resultadoTiempoReal){
            igual();
        }
    }
}
