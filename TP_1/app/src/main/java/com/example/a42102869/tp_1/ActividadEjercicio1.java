package com.example.a42102869.tp_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActividadEjercicio1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_ejercicio1);
    }

    public void volverAtras(View vista){ /*Declaro una función para poder volver a la activity principal*/
        Intent irAtras;
        irAtras=new Intent (ActividadEjercicio1.this, ActividadPrincipal.class);
        startActivity(irAtras);
    }

    public void MostrarMensaje(String mensaje){ /*Declaro una función para mostrar un toast con un mensaje*/
        Toast alerta;
        alerta=Toast.makeText(this,mensaje,Toast.LENGTH_SHORT);
        alerta.show();
    }


    public void ProcesarEj1(View vista){

        int largoTxt1; /*Declaro las variables que luego tendré que utilizar*/
        int largoTxt2;
        String mensaje;

        EditText txt1; /*Asocio el EditText de la vista con uno del entorno de procesamiento (para ambos Edit]Text)*/
        txt1=(EditText)findViewById(R.id.txt1);

        EditText txt2;
        txt2=(EditText)findViewById(R.id.txt2);

        String miTexto1 = txt1.getText().toString().trim(); /*Guardo el contenido del EditText en una variable de tipo String (para ambos textos)*/
        String miTexto2 = txt2.getText().toString().trim();

        largoTxt1=miTexto1.length(); /*Cuentos la cantidad de caracteres que tiene cada texto*/
        largoTxt2=miTexto2.length();

        if(largoTxt1==0||largoTxt2==0) { /*Me aseguro de que ambos casilleros fueron completados*/
            mensaje="Complete ambos casilleros!";
            MostrarMensaje(mensaje);
        }
        else { /*En el caso de que esa condición sea falsa, osea que se hayan completado ambos casilleros, hago el siguiente procedimiento*/

            EditText cantLetras; /*Asocio el EditText, de la cantidad de letras, de la vista con uno del entorno de procesamiento*/
            cantLetras = (EditText) findViewById(R.id.nud);

            String cantidadLetras; /*Guardo el contenido del EditText en una variable de tipo String*/
            cantidadLetras = cantLetras.getText().toString();

            if (cantidadLetras.matches("")==false) { /*Me aseguro de que no hayan dejado la cantidad de letras sin completar*/

                mensaje = "El largo del texto 1: " + largoTxt1 + " caracteres."; /*Elaboro el mensaje que luego mostraré, teninedo en cuenta el largo de caracteres de cada texto que guarde anteriormente*/
                MostrarMensaje(mensaje); /*Llamo a mi función que muesrta los mensajes, y le mando de parámetro el mensaje que quiero mostrar*/

                mensaje = "El largo del texto 2: " + largoTxt2 + " caracteres.";
                MostrarMensaje(mensaje);

                int resultadoResta; /*Declaro variables que necesito utilizar*/
                int resultado;
                resultado = largoTxt1 - largoTxt2; /*Me fijo cual es el excedente de caracteres entre los textos ingresados*/
                if (resultado > 0) {
                    resultadoResta = resultado;
                } else {
                    if (resultado < 0) {
                        resultadoResta = largoTxt2 - largoTxt1;
                    } else {
                        resultadoResta = 0;
                    }
                }

                mensaje = "La cantidad de caracteres excedentes del más largo sobre el más corto es: " + resultadoResta + ".";
                MostrarMensaje(mensaje);


                int miCantidad = Integer.parseInt(cantidadLetras); /*Convierto un número que tenía guardado en una varibale de tipo String, en una de tipo Int*/

                if (miCantidad > 0) { /*Si ese número es mayor a 0 procedo a hacer lo siguiente*/

                    if (largoTxt1 < miCantidad || largoTxt2 < miCantidad) { /*Me aseguro que ambos textos tengan mínimamente la cantidad de caracteres que se han pedido mostrar de cada uno. Si esto no se cumple lo aviso*/
                        mensaje = "Alguno de los textos tiene menos de " + miCantidad + " caracteres.";
                        MostrarMensaje(mensaje);
                    } else { /*Si ambos textos tienen la cantidad de caracteres suficientes se hace lo siguiente*/
                        String cadena; /*Declaro la variable de tipo String donde concatenaré caracteres de ambos textos*/
                        String letrasTxt1 = miTexto1.substring(0, miCantidad); /*Me guardo los primeros x caracteres de cada texto*/
                        String letrasTxt2 = miTexto2.substring(0, miCantidad);
                        cadena = letrasTxt1 + letrasTxt2; /*Los concateno*/
                        mensaje = miCantidad + " primeros caracteres de cada texto: " + cadena + "."; /*Elaboro un mensaje donde se mostrará tal concatenación*/

                        if (miCantidad == 0) { /*Si la cantidad ingresada resultó ser 0, elaboro un mensaje distinto*/
                            mensaje = "Ha ingresado 0 como número, entonces por lo tanto no se le muestra nada.";
                            Log.d("ilanit", "08");
                        }
                        MostrarMensaje(mensaje); /*Muestro el mensaje elaborado*/
                    }
                } else {
                    mensaje = "No ha ingresado número alguno.";
                    MostrarMensaje(mensaje);
                }
            } else{
                mensaje = "No ha ingresado número alguno.";
                MostrarMensaje(mensaje);
            }
        }
    }
}
