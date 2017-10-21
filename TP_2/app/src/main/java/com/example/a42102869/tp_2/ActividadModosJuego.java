//ILANIT JAMILIS - TP°2 - 6°IB 2017
package com.example.a42102869.tp_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class ActividadModosJuego extends AppCompatActivity {
    String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_modos_juego);
    }

    public void irJuegoNormal(View vista){
        Bundle datosRecibidos = this.getIntent().getExtras();
        String nombreRecibido = datosRecibidos.getString("nombreJugador");
        Bundle datosEnviar;
        datosEnviar = new Bundle();
        datosEnviar.putString("nombreJugador", nombreRecibido);
        datosEnviar.putString("modoJuego","normal");
        Intent irJuegoNormal;
        irJuegoNormal = new Intent (ActividadModosJuego.this, ActividadJuego.class);
        irJuegoNormal.putExtras(datosEnviar);
        startActivity(irJuegoNormal);
    }

    public void irJuegoAzar (View vista){
        Bundle datosRecibidos = this.getIntent().getExtras();
        String nombreRecibido = datosRecibidos.getString("nombreJugador");
        Bundle datosEnviar;
        datosEnviar = new Bundle();
        datosEnviar.putString("nombreJugador", nombreRecibido);
        datosEnviar.putString("modoJuego","azar");
        Intent irJuegoAzar;
        irJuegoAzar = new Intent (ActividadModosJuego.this, ActividadJuego.class);
        irJuegoAzar.putExtras(datosEnviar);
        startActivity(irJuegoAzar);
    }


    public void irGanaPorMi (View vista){
        /*mensaje = "Funcionalidad en construcción";
        MostrarMensaje(mensaje);*/
        Bundle datosRecibidos = this.getIntent().getExtras();
        String nombreRecibido = datosRecibidos.getString("nombreJugador");
        Bundle datosEnviar;
        datosEnviar = new Bundle();
        datosEnviar.putString("nombreJugador", nombreRecibido);
        datosEnviar.putString("modoJuego","inteligente");
        Intent irJuegoInteligente;
        irJuegoInteligente = new Intent (ActividadModosJuego.this, ActividadJuego.class);
        irJuegoInteligente.putExtras(datosEnviar);
        startActivity(irJuegoInteligente);
    }

    public void MostrarMensaje(String mensaje){ /*Declaro una función para mostrar un toast con un mensaje*/
        Toast alerta;
        alerta = Toast.makeText(this,mensaje,Toast.LENGTH_SHORT);
        alerta.show();
    }
}
