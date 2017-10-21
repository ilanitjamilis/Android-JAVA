package com.example.ilanitjamilis.evaluacion2trimestre_ilanitjamilis;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActividadDatos extends AppCompatActivity {

    public boolean miBaseDeDatosAbierta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_datos);
    }

    public void btnAceptarPresionado (View vista){
        EditText miEditText = (EditText)findViewById(R.id.miTextoIngresado);
        String textoIngresado = miEditText.getText().toString().trim();
        Log.d("ila", "texto ingresado: "+textoIngresado);
        if(textoIngresado.compareTo("")!=0) {
            Boolean miTextoYaExiste = VerificarExistenciaTexto(textoIngresado);
            Log.d("ila", "mi texto: "+miTextoYaExiste+" existe");
            if (!miTextoYaExiste) {
                Log.d("ila","mi texto no existe");
                Log.d("ila","tengo que ingresar mi texto");
                IngresarTexto(textoIngresado);
                MostrarMensaje("Texto ingresado correctamente");
                miEditText.setText("");
            }
            else{
                MostrarMensaje("ERROR. El texto ya existe en su base de datos");
            }
        }
        else{
            MostrarMensaje("El campo está vacío! Ingrese texto");
        }
    }

    public void IngresarTexto(String miTexto){
        miBaseDeDatosAbierta = utilidades.baseDeDatosAbierta(this);
        Log.d("ila","abro la bd");
        if(miBaseDeDatosAbierta){
            ContentValues nuevoRegistro = new ContentValues();
            nuevoRegistro.put("texto", miTexto);
            utilidades.baseDatos.insert("textos", null, nuevoRegistro);
            Log.d("ila","inserte mi texto");
            utilidades.baseDatos.close();
            Log.d("ila","cierro la bd");
        }
    }

    public boolean VerificarExistenciaTexto(String texto){
        boolean existe = false;
        //Buscar si existe
        miBaseDeDatosAbierta = utilidades.baseDeDatosAbierta(this);
        if (miBaseDeDatosAbierta) {
            Cursor registros;
            registros = utilidades.baseDatos.rawQuery("select texto from textos", null);
            if (registros.moveToFirst() == true) {
                do {
                    String registroTexto = registros.getString(0);

                    if (registroTexto.compareTo(texto) == 0) {
                        existe = true;
                    }

                } while (registros.moveToNext() == true && !existe);
                utilidades.baseDatos.close();
            }
        }
        return existe;
    }

    public void MostrarMensaje(String mensaje){
        Toast alerta = Toast.makeText(this,mensaje,Toast.LENGTH_LONG);
        alerta.show();
    }
}
