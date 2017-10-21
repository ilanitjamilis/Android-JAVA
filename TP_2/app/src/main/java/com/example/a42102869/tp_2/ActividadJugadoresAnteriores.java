//ILANIT JAMILIS - TP°2 - 6°IB 2017
package com.example.a42102869.tp_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ActividadJugadoresAnteriores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_jugadores_anteriores);

        ArrayList<String> jugadores = utilidades.getListaJugadores();

        ListView miListaJugadores = (ListView)findViewById(R.id.listaJugadores);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, jugadores);
        miListaJugadores.setAdapter(arrayAdapter);
    }

    public void irAtras(View vista){
        Intent volverAtras;
        volverAtras = new Intent (ActividadJugadoresAnteriores.this, ActividadVerEstadisticas.class);
        startActivity(volverAtras);
    }
}
