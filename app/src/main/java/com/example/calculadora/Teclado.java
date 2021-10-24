package com.example.calculadora;

import android.widget.EditText;

public class Teclado {
    private EditText editText;

    public Teclado(EditText editText){
        this.editText = editText;
    }

    //OFF
    public void off(){
        System.exit(0);
    }

    // C
    public void c(){
        this.editText.getText().clear();
    }

    // DEL
    public void del(){
        String operaciones = this.editText.getText().toString().toString();

        try {
            this.editText.setText(operaciones.substring(0,operaciones.length()-1));
        } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException){
            System.out.println("Se han borrado todos los caracteres.");
        }
    }

    // PUNTO
    public void punto(){
        boolean puedeEscribir = true;
        String s = editText.getText().toString();
        int l = s.length()-1;

        if (this.editText.length() <= 0){

            this.editText.setText("0.");
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
                this.editText.append(".");
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

        String s = editText.getText().toString();
        if (s.length() > 0){
            int l = s.length()-1;

            if (s.charAt(l) != '+'){
                this.editText.append("+");
            }
        }
    }

    public void restar(){
        String s = editText.getText().toString();
        if (s.length() > 0){
            int l = s.length()-1;

            if (s.charAt(l) != '-'){
                this.editText.append("-");
            }
        }
    }

    public void multiplicar(){
        String s = editText.getText().toString();
        if (s.length() > 0){
            int l = s.length()-1;

            if (s.charAt(l) != '*'){
                this.editText.append("*");
            }
        }
    }

    public void dividir(){
        String s = editText.getText().toString();
        if (s.length() > 0){
            int l = s.length()-1;

            if (s.charAt(l) != '/'){
                this.editText.append("/");
            }
        }
    }

    public void porcentaje(){
        String s = editText.getText().toString();
        if (s.length() > 0){
            int l = s.length()-1;

            if (s.charAt(l) != '%'){
                this.editText.append("%");
            }
        }
    }

    public void raizCuadrada(){
        this.editText.append("√");
    }

    // NUMEROS
    public void cero(){
        String s = editText.getText().toString();
        if (s.length() > 0){
            int l = s.length()-1;

            if (s.charAt(l) != '0'){
                this.editText.append("0");
            }
        }
    }

    public void uno(){
        this.editText.append("1");
    }

    public void dos(){
        this.editText.append("2");
    }

    public void tres(){
        this.editText.append("3");
    }

    public void cuatro(){
        this.editText.append("4");
    }

    public void cinco(){
        this.editText.append("5");
    }

    public void seis(){
        this.editText.append("6");
    }

    public void siete(){
        this.editText.append("7");
    }

    public void ocho(){
        this.editText.append("8");
    }

    public void nueve(){
        this.editText.append("9");
    }
}
