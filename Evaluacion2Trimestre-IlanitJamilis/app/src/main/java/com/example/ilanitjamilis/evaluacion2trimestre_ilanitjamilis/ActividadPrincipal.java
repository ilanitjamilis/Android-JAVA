package com.example.ilanitjamilis.evaluacion2trimestre_ilanitjamilis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ActividadPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
    }

    public void irActividadDatos (View vista){
        Intent irActividadDatos = new Intent (ActividadPrincipal.this, ActividadDatos.class);
        startActivity(irActividadDatos);
    }

    public void irActividadLista (View vista){
        Intent irActividadLista = new Intent (ActividadPrincipal.this, ActividadLista.class);
        startActivity(irActividadLista);
        Log.d("ila", "va a actividad lista");
    }

    public void irActividadCambios (View vista){
        Intent irActividadCambios = new Intent (ActividadPrincipal.this, ActividadCambios.class);
        startActivity(irActividadCambios);
    }
}
