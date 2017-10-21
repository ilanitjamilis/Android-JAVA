//ILANIT JAMILIS - TP°2 - 6°IB 2017
package com.example.a42102869.tp_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ActividadGanaste extends AppCompatActivity {

    String jugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_ganaste);

        Bundle datosRecibidos;
        datosRecibidos=this.getIntent().getExtras();

        jugador = datosRecibidos.getString("nombreJugador");

        Integer movimientosUtilizados = datosRecibidos.getInt("movimientosUtilizados");
        TextView movimientos;
        movimientos=(TextView)findViewById(R.id.textViewMovimientos);
        movimientos.setText(movimientosUtilizados.toString());

        Integer record = utilidades.getMinCantJugadas();
        TextView recordTV;
        recordTV = (TextView)findViewById(R.id.textViewMovimientosMaximos);
        recordTV.setText(record.toString());

        ImageView fotoResult;
        fotoResult = (ImageView)findViewById(R.id.fotoResultado);

        String caso = datosRecibidos.getString("caso");
        TextView motivo;
        motivo = (TextView)findViewById(R.id.textViewMotivo);
        if(caso.compareTo("perdio")==0){
            motivo.setText("Perdiste el juego "+jugador);
            fotoResult.setImageResource(R.drawable.sadness);
        }
        else {
            motivo.setText("Ganaste el juego "+jugador);
            fotoResult.setImageResource(R.drawable.happiness);
        }
    }

    public void irModosJuego (View vista){
        Bundle datosEnviar;
        datosEnviar = new Bundle();
        datosEnviar.putString("nombreJugador", jugador);
        Intent IrModosJuego;
        IrModosJuego = new Intent (ActividadGanaste.this, ActividadModosJuego.class);
        IrModosJuego.putExtras(datosEnviar);
        startActivity(IrModosJuego);
    }

    public void irPantallaPrincipal(View vista){
        Intent volverInicio;
        volverInicio = new Intent (ActividadGanaste.this, ActividadPrincipal.class);
        startActivity(volverInicio);
    }
}
