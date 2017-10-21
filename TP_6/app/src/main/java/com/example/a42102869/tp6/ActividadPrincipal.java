package com.example.a42102869.tp6;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import org.cocos2d.opengl.CCGLSurfaceView;

public class ActividadPrincipal extends Activity {

    CCGLSurfaceView VistaPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.actividad_principal);

        //Oculto la barra de notificaciones mientras que mi aplicacion se este ejecutando
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Asigno mi vista actual a una variable y la seteo como mi contenido
        VistaPrincipal = new CCGLSurfaceView(this);
        setContentView(VistaPrincipal);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Inicializo mi juego
        clsJuego miJuego = new clsJuego(VistaPrincipal);
        miJuego.ComenzarJuego();
    }
}
