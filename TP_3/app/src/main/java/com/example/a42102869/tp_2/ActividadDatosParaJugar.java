//ILANIT JAMILIS - TP°3 - 6°IB 2017
package com.example.a42102869.tp_2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ActividadDatosParaJugar extends AppCompatActivity {

    ImageButton[] botones;
    Integer num1;
    Integer num2;

    public boolean miBaseDeDatosAbierta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_datos_para_jugar);

        utilidades.nombreJugador = "";
        utilidades.jugadorYaJugo = false;
        utilidades.partidasRegistradas = 0;
        utilidades.mejorPuntaje = 9999;


        Random generadorAzar;
        generadorAzar = new Random();

        num1 = generadorAzar.nextInt(9);
        num2 = generadorAzar.nextInt(9);
        String elCaptcha = num1.toString()+"+"+num2.toString();

        TextView captcha;
        captcha=(TextView)findViewById(R.id.textViewCaptcha);
        captcha.setText(elCaptcha);
    }

    public void irModosJuego (View vista){
        Boolean error;
        error = false;
        String mensaje = "";

        EditText nombre;
        nombre = (EditText)findViewById(R.id.editTextNombre);
        String nombreString = nombre.getText().toString().trim();
        if(nombre.length()==0){
            error = true;
            mensaje = "Ingrese su nombre para poder jugar";
        }
        else{
            EditText capcha;
            capcha = (EditText)findViewById(R.id.editTextCaptcha);
            String resultadoCaptcha = capcha.getText().toString().trim();
            if(resultadoCaptcha.compareTo("")==0){
                error = true;
                mensaje = "Ingrese el captcha";
            }
            else{
                Boolean esNumerico = esNumerico(resultadoCaptcha);
                if(!esNumerico){
                    error = true;
                    mensaje = "La suma es incorrecta";
                }
                else{
                    Integer resultado = Integer.parseInt(resultadoCaptcha);
                    Integer resultadoEsperado = num1+num2;
                    if (resultadoEsperado.compareTo(resultado)!= 0) {
                        error = true;
                        mensaje = "La suma es incorrecta";
                    }
                }
            }
        }
        if(error){
            MostrarMensaje(mensaje);
        }
        else {
            AveriguarJugadorExistente(nombreString);

            if(utilidades.jugadorYaJugo){
                mensaje = "Usted ya ha jugado previamente " + utilidades.partidasRegistradas + " veces";
                MostrarMensaje(mensaje);
                mensaje = "Su mejor puntaje ha sido: " + utilidades.mejorPuntaje + " movimientos";
                MostrarMensaje(mensaje);
            }
            else{
                AgregarJugadorEnBaseDeDatos(nombreString);

                mensaje = "Es la primera vez que usted juega, buena suerte!";
                MostrarMensaje(mensaje);
            }


            Bundle paqueteDatos;
            paqueteDatos = new Bundle();
            paqueteDatos.putString("nombreJugador",nombreString);

            Intent irModosJuego;
            irModosJuego = new Intent (ActividadDatosParaJugar.this, ActividadModosJuego.class);
            irModosJuego.putExtras(paqueteDatos);
            startActivity(irModosJuego);
        }
    }

    public boolean esNumerico(String miString){
        try
        {
            double d = Double.parseDouble(miString);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    public void AgregarJugadorEnBaseDeDatos(String nombre){
        miBaseDeDatosAbierta = utilidades.baseDeDatosAbierta(this);
        utilidades.nombreJugador = nombre;
        if(miBaseDeDatosAbierta){
            ContentValues nuevoRegistro;
            nuevoRegistro = new ContentValues();
            nuevoRegistro.put("nombre", nombre);
            nuevoRegistro.put("cantJugadas",0);
            nuevoRegistro.put("record", 0);
            utilidades.baseDatos.insert("jugadores",null,nuevoRegistro);
            utilidades.baseDatos.close();
        }
    }

    public void AveriguarJugadorExistente(String nombre){
        miBaseDeDatosAbierta = utilidades.baseDeDatosAbierta(this);
        if(miBaseDeDatosAbierta) {
            Cursor registros;
            registros = utilidades.baseDatos.rawQuery("select nombre, cantJugadas, record from jugadores", null);
            if (registros.moveToFirst() == true) {
                do {
                    String nombreJugador = registros.getString(0);
                    Integer cantJugadasJugador = registros.getInt(1);
                    Integer recordJugador = registros.getInt(2);

                    Jugador miJugador = new Jugador();
                    miJugador.nombreJ = nombreJugador;
                    miJugador.cantJugadasJ = cantJugadasJugador;
                    miJugador.recordJ = recordJugador;

                    if (nombreJugador.compareTo(nombre) == 0) {
                        utilidades.jugadorYaJugo = true;
                        utilidades.partidasRegistradas = cantJugadasJugador;
                        utilidades.mejorPuntaje = recordJugador;
                        utilidades.nombreJugador = nombreJugador;
                    }

                } while (registros.moveToNext() == true);
                utilidades.baseDatos.close();
            }
        }
    }

    public void MostrarMensaje(String mensaje){ /*Declaro una función para mostrar un toast con un mensaje*/
        Toast alerta;
        alerta = Toast.makeText(this,mensaje,Toast.LENGTH_SHORT);
        alerta.show();
    }

    public void irPantallaPrincipal(View vista){
        Intent volverInicio;
        volverInicio = new Intent (ActividadDatosParaJugar.this, ActividadPrincipal.class);
        startActivity(volverInicio);
    }
}
