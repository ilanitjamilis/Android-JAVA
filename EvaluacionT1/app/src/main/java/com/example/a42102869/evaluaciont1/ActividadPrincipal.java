package com.example.a42102869.evaluaciont1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ActividadPrincipal extends AppCompatActivity {

    String mensaje;
    Integer cantBotonPresionado;
    Integer cantBotonPresionadoConCheckbox;
    Integer cantAmbosTextosIguales;
    Integer cantCaracteresTextoMasLargo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        cantBotonPresionado = 0;
        cantBotonPresionadoConCheckbox = 0;
        cantAmbosTextosIguales = 0;
        cantCaracteresTextoMasLargo = 0;
    }

    public void Procesar (View vista){

        EditText editText1 = (EditText)findViewById(R.id.edittext1);
        String miEditText1 = editText1.getText().toString().trim();
        miEditText1.toLowerCase();

        EditText editText2 = (EditText)findViewById(R.id.edittext2);
        String miEditText2 = editText2.getText().toString().trim();
        miEditText2.toLowerCase();

        CheckBox checkBox1 = (CheckBox)findViewById(R.id.checkbox1);
        Boolean miCheckBox = checkBox1.isChecked();

        if(miEditText1.length()==0||miEditText2.length()==0){
            mensaje = "Debe completar ambos casilleros!";
            MostrarMensaje(mensaje);
        }
        else {
            if (miEditText1.compareTo("fin") == 0 || miEditText2.compareTo("fin") == 0) {
                Bundle paqueteDeDatos = new Bundle();
                paqueteDeDatos.putInt("cantBotonPresionado", cantBotonPresionado);
                paqueteDeDatos.putInt("cantBotonPresionadoConCheckbox", cantBotonPresionadoConCheckbox);
                paqueteDeDatos.putInt("cantAmbosTextosIguales", cantAmbosTextosIguales);
                paqueteDeDatos.putInt("cantCaracteresTextoMasLargo", cantCaracteresTextoMasLargo);

                Intent irResultados = new Intent(ActividadPrincipal.this, ActividadResultado.class);
                irResultados.putExtras(paqueteDeDatos);
                startActivity(irResultados);
            }
            else{
                Log.d("ila","00");
                cantBotonPresionado++;
                if(miCheckBox){
                    cantBotonPresionadoConCheckbox++;
                }
                Log.d("ila","01");
                if(miEditText1.compareTo(miEditText2)==0){
                    Log.d("ila","02");
                    cantAmbosTextosIguales++;
                }
                Log.d("ila","03");
                if(miEditText1.length()>cantCaracteresTextoMasLargo){
                    cantCaracteresTextoMasLargo = miEditText1.length();
                }
                if(miEditText2.length()>cantCaracteresTextoMasLargo){
                    cantCaracteresTextoMasLargo = miEditText2.length();
                }

                editText1.setText("");
                editText2.setText("");
            }
        }
    }

    public void MostrarMensaje(String miMensaje){
        Toast.makeText(this, miMensaje, Toast.LENGTH_SHORT).show();
    }
}
