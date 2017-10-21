package com.example.a42102869.evaluaciont1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActividadResultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_resultado);

        TextView miTextViewParaCantBotonPresionado = (TextView)findViewById(R.id.paraBotonPresionado);
        TextView miTextViewParaCantBotonPresionadoConCheckBox = (TextView)findViewById(R.id.paraBotonPresionadoCheckbox);
        TextView miTextViewParaCantAmbosTextosIguales = (TextView)findViewById(R.id.paraAmbosTextosIguales);
        TextView miTextViewParaCaracteresTextoMasLargo = (TextView)findViewById(R.id.paraTextoMasLargo);

        Bundle datosRecibidos = this.getIntent().getExtras();
        Integer cantBotonPresionadoRecibido = datosRecibidos.getInt("cantBotonPresionado");
        Integer cantBotonPresionadoConCheckBoxRecibido = datosRecibidos.getInt("cantBotonPresionadoConCheckbox");
        Integer cantAmbosTextosIgualesRecibido = datosRecibidos.getInt("cantAmbosTextosIguales");
        Integer cantCaracteresTextoMasLargoRecibido = datosRecibidos.getInt("cantCaracteresTextoMasLargo");

        miTextViewParaCantBotonPresionado.setText(cantBotonPresionadoRecibido.toString());
        miTextViewParaCantBotonPresionadoConCheckBox.setText(cantBotonPresionadoConCheckBoxRecibido.toString());
        miTextViewParaCantAmbosTextosIguales.setText(cantAmbosTextosIgualesRecibido.toString());
        miTextViewParaCaracteresTextoMasLargo.setText(cantCaracteresTextoMasLargoRecibido.toString());
    }

    public void VolverAInicio(View vista){
        Intent irInicio = new Intent(ActividadResultado.this, ActividadPrincipal.class);
        startActivity(irInicio);
    }
}
