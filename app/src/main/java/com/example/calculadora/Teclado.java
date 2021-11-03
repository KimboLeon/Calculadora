package com.example.calculadora;

import android.widget.EditText;

import java.text.DecimalFormat;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author Anthony Leon
 * @version 03/11/2021/
 */

public class Teclado {
    private EditText operaciones;
    private EditText resultado;
    private String txtOperaciones;
    private boolean resultadoTiempoReal;
    private boolean escribirParentesis;
    private int parentesis;

    private final ScriptEngineManager SEM = new ScriptEngineManager();
    private final ScriptEngine ENGINE = SEM.getEngineByName("js");
    private final String FORMAT_NUMBER = "#.####";
    private final DecimalFormat DECIMAL_FORMAT = new DecimalFormat(FORMAT_NUMBER);

    public Teclado(EditText operaciones, EditText resultado){
        this.operaciones = operaciones;
        this.resultado = resultado;
        this.txtOperaciones = "";
        this.resultadoTiempoReal = false;
        this.escribirParentesis = false;
        this.parentesis = 0;
    }
    
    // IGUAL
    public void igualTiempoReal(){

        double f = 0;
        int l = txtOperaciones.length()-1;

        if (operaciones.length() > 0){

            if (txtOperaciones.charAt(l) != '+' && txtOperaciones.charAt(l) != '-' &&
                    txtOperaciones.charAt(l) != '*' && txtOperaciones.charAt(l) != '/' &&
                    txtOperaciones.charAt(l) != '.' && txtOperaciones.charAt(l) != '(' &&
                    txtOperaciones.charAt(l) != '%' && parentesis == 0){

                try {
                    f = Double.parseDouble(String.valueOf(ENGINE.eval(txtOperaciones)));
                } catch (ScriptException e) {
                    e.printStackTrace();
                }

                this.resultado.setText(DECIMAL_FORMAT.format(f).replace(',','.'));
            }
        }
    }

    public void igual(){

        double f = 0;
        int l = txtOperaciones.length()-1;

        if (operaciones.length() > 0){

            if (parentesis > 0){
                for (int i = parentesis; i > 0; i--){
                    this.operaciones.append(")");
                    this.txtOperaciones += ")";
                    this.parentesis--;
                }
            }

            if (txtOperaciones.charAt(l) != '+' && txtOperaciones.charAt(l) != '-' &&
                    txtOperaciones.charAt(l) != '*' && txtOperaciones.charAt(l) != '/' &&
                    txtOperaciones.charAt(l) != '.' && txtOperaciones.charAt(l) != '(' &&
                    txtOperaciones.charAt(l) != '%' && parentesis == 0){

                try {
                    f = Double.parseDouble(String.valueOf(ENGINE.eval(txtOperaciones)));
                } catch (ScriptException e) {
                    e.printStackTrace();
                }

                this.resultado.setText(DECIMAL_FORMAT.format(f).replace(',','.'));
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
        this.txtOperaciones = "";
        this.parentesis = 0;
        this.resultadoTiempoReal = false;
        this.escribirParentesis = false;
    }

    // DEL
    public void del(){
        String operaciones = this.operaciones.getText().toString();

        try {
            this.operaciones.setText(operaciones.substring(0,operaciones.length()-1));
            this.txtOperaciones = operaciones.substring(0,operaciones.length()-1);
            igualTiempoReal();
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
            this.txtOperaciones += "0.";
        } else {

            for (int i = l; i >= 0; i--){
                if (s.charAt(i) == '.'){
                    puedeEscribir = false;
                    break;
                } else {
                    if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/'
                            || s.charAt(i) == '.' ){
                        break;
                    }
                }
            }

            if (s.charAt(l) == '+'||s.charAt(l) == '-'||s.charAt(l) == '*' ||s.charAt(l) == '/'
                    ||s.charAt(l) == '.' || s.charAt(l) == '(' || s.charAt(l) == ')'){
                puedeEscribir = false;
            }

            if (puedeEscribir){
                this.operaciones.append(".");
                this.txtOperaciones += ".";
            }
        }
    }

    public void coma(){

        this.operaciones.append(",");
        this.txtOperaciones += ",";
    }

    // PARENTESIS

    public void abrirParentesis(){

        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (s.length() > 0){
            if (s.charAt(l) != '0' && s.charAt(l) != '1' && s.charAt(l) != '2' && s.charAt(l) != '3'
                    && s.charAt(l) != '4' && s.charAt(l) != '5' && s.charAt(l) != '6'
                    && s.charAt(l) != '7' && s.charAt(l) != '8' && s.charAt(l) != '9' && s.charAt(l) != ')'){

                this.operaciones.append("(");
                this.txtOperaciones += "(";
                this.parentesis++;
                this.escribirParentesis = true;
            }
        } else {
            this.operaciones.append("(");
            this.txtOperaciones += "(";
            this.parentesis++;
            this.escribirParentesis = true;
        }

    }

    public void cerrarParentesis(){

        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (s.charAt(l) != '('){
            if (this.escribirParentesis){
                if (this.parentesis > 0){
                    this.operaciones.append(")");
                    this.txtOperaciones += ")";
                    this.parentesis--;

                    if (resultadoTiempoReal){
                        igualTiempoReal();
                    }
                }
            }
        }
    }

    // SIN
    public void sin(){

        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (s.length() > 0){
            if (s.charAt(l) != '0' && s.charAt(l) != '1' && s.charAt(l) != '2' && s.charAt(l) != '3'
                    && s.charAt(l) != '4' && s.charAt(l) != '5' && s.charAt(l) != '6'
                    && s.charAt(l) != '7' && s.charAt(l) != '8' && s.charAt(l) != '9'){

                this.operaciones.append("SIN(");
                this.txtOperaciones += "Math.sin(";
                this.parentesis++;
                this.escribirParentesis = true;
                this.resultadoTiempoReal = true;
            }
        } else {
            this.operaciones.append("SIN(");
            this.txtOperaciones += "Math.sin(";
            this.parentesis++;
            this.escribirParentesis = true;
            this.resultadoTiempoReal = true;
        }
    }

    // COS
    public void cos(){

        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (s.length() > 0){
            if (s.charAt(l) != '0' && s.charAt(l) != '1' && s.charAt(l) != '2' && s.charAt(l) != '3'
                    && s.charAt(l) != '4' && s.charAt(l) != '5' && s.charAt(l) != '6'
                    && s.charAt(l) != '7' && s.charAt(l) != '8' && s.charAt(l) != '9'){

                this.operaciones.append("COS(");
                this.txtOperaciones += "Math.cos(";
                this.parentesis++;
                this.escribirParentesis = true;
                this.resultadoTiempoReal = true;
            }
        } else {
            this.operaciones.append("COS(");
            this.txtOperaciones += "Math.cos(";
            this.parentesis++;
            this.escribirParentesis = true;
            this.resultadoTiempoReal = true;
        }
    }

    // TAN
    public void tan(){

        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (s.length() > 0){
            if (s.charAt(l) != '0' && s.charAt(l) != '1' && s.charAt(l) != '2' && s.charAt(l) != '3'
                    && s.charAt(l) != '4' && s.charAt(l) != '5' && s.charAt(l) != '6'
                    && s.charAt(l) != '7' && s.charAt(l) != '8' && s.charAt(l) != '9'){

                this.operaciones.append("TAN(");
                this.txtOperaciones += "Math.tan(";
                this.parentesis++;
                this.escribirParentesis = true;
                this.resultadoTiempoReal = true;
            }
        } else {
            this.operaciones.append("TAN(");
            this.txtOperaciones += "Math.tan(";
            this.parentesis++;
            this.escribirParentesis = true;
            this.resultadoTiempoReal = true;
        }
    }

    // CSC
    public void csc(){

        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (s.length() > 0){
            if (s.charAt(l) != '0' && s.charAt(l) != '1' && s.charAt(l) != '2' && s.charAt(l) != '3'
                    && s.charAt(l) != '4' && s.charAt(l) != '5' && s.charAt(l) != '6'
                    && s.charAt(l) != '7' && s.charAt(l) != '8' && s.charAt(l) != '9'){

                this.operaciones.append(("CSC("));
                this.txtOperaciones += "Math.csc(";
                this.parentesis++;
                this.escribirParentesis = true;
                this.resultadoTiempoReal = true;
            }
        } else {
            this.operaciones.append(("CSC("));
            this.txtOperaciones += "Math.csc(";
            this.parentesis++;
            this.escribirParentesis = true;
            this.resultadoTiempoReal = true;
        }
    }

    // SEC
    public void sec(){

        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (s.length() > 0){
            if (s.charAt(l) != '0' && s.charAt(l) != '1' && s.charAt(l) != '2' && s.charAt(l) != '3'
                    && s.charAt(l) != '4' && s.charAt(l) != '5' && s.charAt(l) != '6'
                    && s.charAt(l) != '7' && s.charAt(l) != '8' && s.charAt(l) != '9'){

                this.operaciones.append("SEC(");
                this.txtOperaciones += "Math.sec(";
                this.parentesis++;
                this.escribirParentesis = true;
                this.resultadoTiempoReal = true;
            }
        } else {
            this.operaciones.append("SEC(");
            this.txtOperaciones += "Math.sec(";
            this.parentesis++;
            this.escribirParentesis = true;
            this.resultadoTiempoReal = true;
        }
    }

    // CTG
    public void ctg(){

        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (s.length() > 0){
            if (s.charAt(l) != '0' && s.charAt(l) != '1' && s.charAt(l) != '2' && s.charAt(l) != '3'
                    && s.charAt(l) != '4' && s.charAt(l) != '5' && s.charAt(l) != '6'
                    && s.charAt(l) != '7' && s.charAt(l) != '8' && s.charAt(l) != '9'){

                this.operaciones.append("CTG(");
                this.txtOperaciones += "Math.atan(";
                this.parentesis++;
                this.escribirParentesis = true;
                this.resultadoTiempoReal = true;
            }
        } else {
            this.operaciones.append("CTG(");
            this.txtOperaciones += "Math.atan(";
            this.parentesis++;
            this.escribirParentesis = true;
            this.resultadoTiempoReal = true;
        }
    }

    // X!
    public void factorial(){

        String s = operaciones.getText().toString();

        if (s.length() > 0){

            int l = s.length()-1;
            int factorial = 1;
            int num = Character.getNumericValue(s.charAt(l));

            if (num == 0){
                factorial = 1;
            } else {
                for (int x=2;x<=num;x++)
                    factorial = factorial * x;
            }

            if (s.length() == 1){
                this.operaciones.setText("x!("+num+")");
                this.txtOperaciones = String.valueOf(factorial);
            } else if (s.length() > 2){
                this.operaciones.setText(s.substring(0,l) + "x!("+num+")");
                this.txtOperaciones = s.substring(0, l) + factorial;
            }

            this.escribirParentesis = true;
            this.resultadoTiempoReal = true;

            if (resultadoTiempoReal){
                igualTiempoReal();
            }
        }
    }

    // x^
    public void exponente(){

        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (s.length() > 0){
            if (s.charAt(l) != '0' && s.charAt(l) != '1' && s.charAt(l) != '2' && s.charAt(l) != '3'
                    && s.charAt(l) != '4' && s.charAt(l) != '5' && s.charAt(l) != '6'
                    && s.charAt(l) != '7' && s.charAt(l) != '8' && s.charAt(l) != '9'){

                this.operaciones.append("X^(");
                this.txtOperaciones += "Math.pow(";
                this.parentesis++;
                this.escribirParentesis = true;
                this.resultadoTiempoReal = true;
            }
        } else {
            this.operaciones.append("X^(");
            this.txtOperaciones += "Math.pow(";
            this.parentesis++;
            this.escribirParentesis = true;
            this.resultadoTiempoReal = true;
        }
    }

    // OPERACIONES BASICAS

    public void sumar(){

        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (s.length() > 0){
            if (s.charAt(l) != '+' && s.charAt(l) != '('){
                this.operaciones.append("+");
                this.txtOperaciones += "+";
                this.resultadoTiempoReal = true;
            }
        }
    }

    public void restar(){

        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (s.length() > 0){
            if (s.charAt(l) != '-' && s.charAt(l) != '('){
                this.operaciones.append("-");
                this.txtOperaciones += "-";
                this.resultadoTiempoReal = true;
            }
        }
    }

    public void multiplicar(){

        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (s.length() > 0){
            if (s.charAt(l) != '*' && s.charAt(l) != '('){
                this.operaciones.append("*");
                this.txtOperaciones += "*";
                this.resultadoTiempoReal = true;
            }
        }
    }

    public void dividir(){

        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (s.length() > 0){
            if (s.charAt(l) != '/' && s.charAt(l) != '('){
                this.operaciones.append("/");
                this.txtOperaciones += "/";
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
                this.txtOperaciones += "%";
                this.resultadoTiempoReal = true;
            }
        }

        if (resultadoTiempoReal){
            igualTiempoReal();
        }
    }

    public void raizCuadrada(){

        String s = operaciones.getText().toString();
        int l = s.length()-1;

        if (s.length() > 0){
            if (s.charAt(l) != '0' && s.charAt(l) != '1' && s.charAt(l) != '2' && s.charAt(l) != '3'
                    && s.charAt(l) != '4' && s.charAt(l) != '5' && s.charAt(l) != '6'
                    && s.charAt(l) != '7' && s.charAt(l) != '8' && s.charAt(l) != '9') {

                this.operaciones.append("√(");
                this.txtOperaciones += "Math.sqrt(";
                this.parentesis++;
                this.escribirParentesis = true;
                this.resultadoTiempoReal = true;
            }
        } else {
            this.operaciones.append("√(");
            this.txtOperaciones += "Math.sqrt(";
            this.parentesis++;
            this.escribirParentesis = true;
            this.resultadoTiempoReal = true;
        }
    }

    // NUMEROS

    public void cero(){
        String s = operaciones.getText().toString();

        if (s.length() > 0){
            this.operaciones.append("0");
            this.txtOperaciones += "0";
        }

        if (resultadoTiempoReal){
            igualTiempoReal();
        }
    }

    public void uno(){

        this.operaciones.append("1");
        this.txtOperaciones += "1";

        if (resultadoTiempoReal){
            igualTiempoReal();
        }

    }

    public void dos(){

        this.operaciones.append("2");
        this.txtOperaciones += "2";

        if (resultadoTiempoReal){
            igualTiempoReal();
        }
    }

    public void tres(){

        this.operaciones.append("3");
        this.txtOperaciones += "3";

        if (resultadoTiempoReal){
            igualTiempoReal();
        }
    }

    public void cuatro(){

        this.operaciones.append("4");
        this.txtOperaciones += "4";

        if (resultadoTiempoReal){
            igualTiempoReal();
        }
    }

    public void cinco(){

        this.operaciones.append("5");
        this.txtOperaciones += "5";

        if (resultadoTiempoReal){
            igualTiempoReal();
        }
    }

    public void seis(){

        this.operaciones.append("6");
        this.txtOperaciones += "6";

        if (resultadoTiempoReal){
            igualTiempoReal();
        }
    }

    public void siete(){

        this.operaciones.append("7");
        this.txtOperaciones += "7";

        if (resultadoTiempoReal){
            igualTiempoReal();
        }
    }

    public void ocho(){

        this.operaciones.append("8");
        this.txtOperaciones += "8";

        if (resultadoTiempoReal){
            igualTiempoReal();
        }
    }

    public void nueve(){

        this.operaciones.append("9");
        this.txtOperaciones += "9";

        if (resultadoTiempoReal){
            igualTiempoReal();
        }
    }
}
