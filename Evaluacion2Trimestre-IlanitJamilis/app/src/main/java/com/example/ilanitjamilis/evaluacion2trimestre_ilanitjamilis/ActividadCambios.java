package com.example.ilanitjamilis.evaluacion2trimestre_ilanitjamilis;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ActividadCambios extends AppCompatActivity {

    FragmentManager AdministradorFragments;
    FragmentTransaction TransaccionesFragments;
    String textoMasLargo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_cambios);

        irPrincipal();
    }

    public void irPrincipal(){
        AdministradorFragments = getSupportFragmentManager();
        Fragment frgVistaPrincipal = new Fragment_1();

        TransaccionesFragments = AdministradorFragments.beginTransaction();
        TransaccionesFragments.replace(R.id.miAlojadorFragment, frgVistaPrincipal);
        TransaccionesFragments.commit();
    }

    public void irSecundaria(){
        AdministradorFragments = getSupportFragmentManager();
        Fragment frgVistaSecundaria = new Fragment_2();

        TransaccionesFragments = AdministradorFragments.beginTransaction();
        TransaccionesFragments.replace(R.id.miAlojadorFragment, frgVistaSecundaria);
        TransaccionesFragments.commit();
    }

    public void CompararTextos (String texto1, String texto2){

        if(texto1.length()>texto2.length()){
            textoMasLargo = texto1;
            irSecundaria();
        }
        else{
            if(texto2.length()>texto1.length()){
                textoMasLargo = texto2;
                irSecundaria();
            }
            else{
                MostrarMensaje("Ambos textos tienen la misma longitud, intente nuevamente");
            }
        }
    }

    public void MostrarMensaje(String mensaje){
        Toast alerta = Toast.makeText(this,mensaje,Toast.LENGTH_LONG);
        alerta.show();
    }
}
