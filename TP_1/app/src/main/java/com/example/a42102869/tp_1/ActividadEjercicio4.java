package com.example.a42102869.tp_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActividadEjercicio4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_ejercicio4);
    }

    public void volverAtras(View vista){ /*Declaro una función para poder volver a la activity principal*/
        Intent irAtras;
        irAtras = new Intent (ActividadEjercicio4.this, ActividadPrincipal.class);
        startActivity(irAtras);
    }

    public void MostrarMensaje(String mensaje){ /*Declaro una función para mostrar un toast con un mensaje*/
        Toast alerta;
        alerta=Toast.makeText(this,mensaje,Toast.LENGTH_SHORT);
        alerta.show();
    }

    public void ProcesarEj4(View vista){

        String miTexto; /*Declaro las variables que luego tendré que utilizar*/
        int numPalabra;
        String miPalabra="";
        String mensaje;

        EditText txt; /*Asocio el EditText de la vista con uno del entorno de procesamiento*/
        txt=(EditText)findViewById(R.id.txt);
        miTexto=txt.getText().toString().trim();

        EditText txtNum; /*Asocio el EditText de la vista con uno del entorno de procesamiento*/
        txtNum=(EditText)findViewById(R.id.txtNum);
        String miTxtNum=txtNum.getText().toString().trim();



        if(miTexto.length()>0==false){ /*Checkeo que se haya ingresado texto*/
            mensaje="Texto obligatorio.";
            MostrarMensaje(mensaje);
        }
        if(miTxtNum.matches("")){ /*Checkeo que haya ingresado un número*/
            mensaje="Número obligatorio.";
            MostrarMensaje(mensaje);
        }
        if(miTexto.length()>0 && miTxtNum.matches("")==false){ /*Si ambas condiciones son falsas, osea que ambos casilleros estan completados, se hace lo siguiente*/
            numPalabra=Integer.parseInt(miTxtNum); /*Guardo el string donde tengo el número guardado, en una variable de tipo Int, convirtiéndola*/
            String[] palabras=miTexto.split(" "); /*Declaro un vector donde guardaré las palabras ingresadas como texto, dividiéndolas por un espacio*/
            Boolean palabraExiste=false; /*Declaro una variable diciendo que la palabra requerida no existe*/
            if(numPalabra <= palabras.length){ /*Me fijo si puede existir la palabra x*/
                palabraExiste=true; /*Si existe digo que existe*/
            }
            if(palabraExiste) { /*Si la palabra existe hago lo siguiente*/
                miPalabra = palabras[numPalabra - 1]; /*Me la guardo*/
                mensaje = "La palabra número " + numPalabra + " es: " + miPalabra + "."; /*Hago un mensaje mostrando que la palabra número x es y*/
            }
            else
            {
                mensaje="La palabra número "+numPalabra+" no existe."; /*Si la palabra no existe lo aviso*/
            }
            MostrarMensaje(mensaje); /*Muestro el mensaje correspondiente*/
        }
    }
}
