package com.example.a42102869.tp_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActividadPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
    }

    public void irEjercicio1 (View vista){
        Intent actividadDestino;
        actividadDestino=new Intent (ActividadPrincipal.this, ActividadEjercicio1.class);
        startActivity(actividadDestino);
    }

    public void irEjercicio2 (View vista){
        Intent actividadDestino;
        actividadDestino=new Intent (ActividadPrincipal.this, ActividadEjercicio2.class);
        startActivity(actividadDestino);
    }

    public void irEjercicio3 (View vista){
        Intent actividadDestino;
        actividadDestino=new Intent (ActividadPrincipal.this, ActividadEjercicio3.class);
        startActivity(actividadDestino);
    }

    public void irEjercicio4 (View vista){
        Intent actividadDestino;
        actividadDestino=new Intent (ActividadPrincipal.this, ActividadEjercicio4.class);
        startActivity(actividadDestino);
    }

}
