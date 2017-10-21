package com.example.a42102869.tp_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActividadEjercicio3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_ejercicio3);
    }

    public void volverAtras(View vista){ /*Declaro una función para poder volver a la activity principal*/
        Intent irAtras;
        irAtras=new Intent (ActividadEjercicio3.this, ActividadPrincipal.class);
        startActivity(irAtras);
    }

    public void MostrarMensaje(String mensaje){ /*Declaro una función para mostrar un toast con un mensaje*/
        Toast alerta;
        alerta=Toast.makeText(this,mensaje,Toast.LENGTH_SHORT);
        alerta.show();
    }

    public void ProcesarEj3(View vista){

        String miTexto; /*Declaro las variables que luego tendré que utilizar*/
        String textoAlReves = "";
        String mensaje;

        EditText txt; /*Asocio el EditText de la vista con uno del entorno de procesamiento*/
        txt=(EditText)findViewById(R.id.txt);

        miTexto=txt.getText().toString().trim(); /*Guardo el contenido del EditText en una variable de tipo String*/

        if(miTexto.length()==0){ /*Me fijo si no se ha ingresado nada como texto*/
            mensaje="No ha ingresado texto";
            MostrarMensaje(mensaje);
        }
        else{
            if(miTexto.length()>10) { /*En el caso de que se haya ingresado texto, me fijo si este supera los 10 caracteres*/
                String letra; /*Declaro una variable donde pondré la letra que quiero guardar en el momento de la transición*/
                for (int i = (miTexto.length()) - 1; i >= 0; i--) { /*Hago una repetitiva que durará la cantidad de caracteres que el texto ingresado tiene, yendo de atrás hacia adelante*/
                    letra = miTexto.substring(i, i + 1); /*En cada iteración agarro la primera letra y la guardo en la variable donde estará el texto al revés*/
                    textoAlReves = textoAlReves + letra;
                }
                if (miTexto.compareTo(textoAlReves) == 0){ /*Cuando terminé de dar vuelta el texto, me fijo si el texto original y el invertido son iguales*/
                    mensaje="El texto ingresado es capicúa."; /*En ese caso, el texto es capicúa entonces lo aviso en un mensaje*/
                }
                else{
                    mensaje = "Su texto al revés es: " + textoAlReves + "."; /*Si no es capicúa, muestro cual es la palabra dada vuelta*/
                }
                MostrarMensaje(mensaje); /*Muestro el mensaje correspondiente*/
            }
            else{
                mensaje="El texto debe tener más de 10 caracteres."; /*Si el texto ingresado no supera los 10 caracteres lo notifico*/
                MostrarMensaje(mensaje);
            }
        }
    }
}
