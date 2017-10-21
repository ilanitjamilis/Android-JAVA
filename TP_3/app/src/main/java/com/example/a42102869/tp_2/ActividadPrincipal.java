//ILANIT JAMILIS - TP°3 - 6°IB 2017
package com.example.a42102869.tp_2;

import android.content.Intent;
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

public class ActividadPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
    }

    public void irDatosJuego(View vista){
        Intent irDatosJugar;
        irDatosJugar = new Intent (ActividadPrincipal.this, ActividadDatosParaJugar.class);
        startActivity(irDatosJugar);
    }
}
