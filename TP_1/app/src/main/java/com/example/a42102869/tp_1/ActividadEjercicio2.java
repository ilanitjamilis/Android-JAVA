package com.example.a42102869.tp_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ActividadEjercicio2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_ejercicio2);
    }

    public void volverAtras(View vista){ /*Declaro una función para poder volver a la activity principal*/
        Intent irAtras;
        irAtras=new Intent (ActividadEjercicio2.this, ActividadPrincipal.class);
        startActivity(irAtras);
    }

    public void MostrarMensaje(String mensaje){ /*Declaro una función para mostrar un toast con un mensaje*/
        Toast alerta;
        alerta=Toast.makeText(this,mensaje,Toast.LENGTH_SHORT);
        alerta.show();
    }

    public void ProcesarEj2 (View vista){

        String miTexto; /*Declaro las variables que luego tendré que utilizar*/
        String letra;
        String mensaje;

        EditText txt; /*Asocio el EditText de la vista con uno del entorno de procesamiento*/
        txt=(EditText)findViewById(R.id.txt);

        CheckBox check; /*Asocio el CheckBox de la vista con uno del entorno de procesamiento*/
        check =(CheckBox)findViewById(R.id.miChck);

        EditText editLetra; /*Asocio el EditText de la vista con uno del entorno de procesamiento*/
        editLetra=(EditText)findViewById(R.id.txtLetra);

        miTexto=txt.getText().toString().trim(); /*Guardo el contenido del EditText en una variable de tipo String*/

        letra=editLetra.getText().toString().trim(); /*Guardo el contenido del EditText en una variable de tipo String*/

        if(miTexto.length()==0) { /*Me fijo si no se ha ingresado nada como texto*/
            mensaje = "Ingrese texto!";
            MostrarMensaje(mensaje);
        }
        else if(letra.length()==1) { /*En el caso de que se haya ingresado texto, me fijo si se ha ingresado un caracter*/
            /*Si ambos casilleros fueron completados correctamente se hace lo siguiente*/
            if (check.isChecked() == true && miTexto.length() < 10) { /*Me fijo si el checkbox está checkeado y si el texto no llega a los 10 caracteres*/
                mensaje = "El texto ingresado no supera los 10 caracteres."; /*Ambas condiciones son verdaderas, no se puede proseguir, y lo aviso*/
                MostrarMensaje(mensaje);
            } else { /*Si se puede procesar hago lo siguiente*/
                int cantidadLetrasX = 0; /*Declaro e inicio un contador para el caracter x en 0*/
                miTexto = miTexto.toLowerCase(); /*Paso el texto ingresado a minúscula para poder checkear bien*/

                int posicionPrimerLetra; /*Declaro una variable donde guardaré la posición donde aparecerá el caracter x por primera vez*/
                posicionPrimerLetra = miTexto.indexOf("a"); /*Me fijo donde es la primera vez que aparece el caracter x, y lo guardo*/

                if (posicionPrimerLetra >= 0) { /*Me fijo si la posición existe, porque si no existe quiere decir que nuncá se ingresó dicho caracter, y por lo tanto no analizaré caracter por caracter*/
                    String verLetra; /*Declaro una variable donde guardaré cada caracter al momento de analizarlo*/
                    for (int i = posicionPrimerLetra; i < miTexto.length(); i++) { /*Hago una repeticion que tendrá la duración necesaria para checkear desde el caracter donde aparece el primero caracter x hasta el final*/
                        verLetra = miTexto.substring(i, i + 1); /*Me guardo el caracter a analizar*/

                        if (verLetra.compareTo(letra) == 0) { /*Me fijo si el caracter que estoy analizando es igual al caracter x*/
                            cantidadLetrasX++; /*Si es el mismo, sumo 1+ al contador de cuantas veces esta el caracter x*/
                        }
                    }
                    mensaje = "Cantidad de caracteres '" + letra + "' ingresados fueron: " + cantidadLetrasX + "."; /*Elaboro el mensaje con la cantidad de caracteres x que se ingresaron*/
                    MostrarMensaje(mensaje); /*Muestro el mensaje*/

                } else {
                    mensaje = "No se ha ingresado ningun/a " + letra + "."; /*Si no se ha ingresado un caracter lo aviso*/
                    MostrarMensaje(mensaje);
                }
            }
        }
        else{ /*Si se ha ingresado más de un caracter donde debe ir solamente uno, o no se ha ingresado ninguno, hago lo siguiente*/
            mensaje="Ingrese 1 caracter!"; /*Notifico que debe ingresar un único caracter*/
            MostrarMensaje(mensaje);
        }
    }
}
