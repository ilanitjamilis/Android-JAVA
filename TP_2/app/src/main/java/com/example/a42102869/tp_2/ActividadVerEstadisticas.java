//ILANIT JAMILIS - TP°2 - 6°IB 2017
package com.example.a42102869.tp_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ActividadVerEstadisticas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_ver_estadisticas);

        Log.d("ila","01");
        String recordS = utilidades.getNombreMinCantJugadas();
        Log.d("ila","02");
        String recordI = utilidades.getMinCantJugadas().toString();
        Log.d("ila","03");

        if(recordI.compareTo("999999")==0){
            Log.d("ila","04");
            recordS="-";
            recordI="-";
        }

        TextView recordSTV;
        recordSTV=(TextView)findViewById(R.id.textViewNombreMenorMovimientos);
        recordSTV.setText(recordS);

        TextView recordITV;
        recordITV = (TextView)findViewById(R.id.textViewMenorMovimientos);
        recordITV.setText(recordI);
    }

    public void irVerJugadoresAnteriores(View vista){
        Intent irJugadoresAnteriores;
        irJugadoresAnteriores = new Intent (ActividadVerEstadisticas.this, ActividadJugadoresAnteriores.class);
        startActivity(irJugadoresAnteriores);
    }

    public void irPantallaPrincipal(View vista){
        Intent volverInicio;
        volverInicio = new Intent (ActividadVerEstadisticas.this, ActividadPrincipal.class);
        startActivity(volverInicio);
    }
}
