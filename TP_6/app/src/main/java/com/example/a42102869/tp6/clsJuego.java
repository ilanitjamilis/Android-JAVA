package com.example.a42102869.tp6;


import android.util.Log;

import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Label;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCColor3B;
import org.cocos2d.types.CCPoint;
import org.cocos2d.types.CCSize;

import java.util.Random;

public class clsJuego {

    CCGLSurfaceView _VistaDelJuego;
    CCSize PantallaDispositivo;
    Sprite miPelota;
    Sprite imagenFondo;
    Label lblTituloJuego;

    public clsJuego(CCGLSurfaceView vistaDelJuego){
        //Uso la vista de mi dispositivo para el juego que ejecutare
        _VistaDelJuego = vistaDelJuego;
    }

    public void ComenzarJuego(){
        Director.sharedDirector().attachInView(_VistaDelJuego);

        //Me guardo el tamaño de la pantalla de mi dispositivo
        PantallaDispositivo = Director.sharedDirector().displaySize();

        //Ejecuto una escena de mi juego
        Director.sharedDirector().runWithScene(EscenaDelJuego());
    }

    private Scene EscenaDelJuego(){
        Scene escenaADevolver = Scene.node();

        //Declaro las capas y se las asigno a la escena que voy a devolver
        CapaFondo miCapaFondo = new CapaFondo();
        CapaFrente miCapaFrente = new CapaFrente();
        CapaLabel miCapaLabel = new CapaLabel();
        escenaADevolver.addChild(miCapaFondo, -10);
        escenaADevolver.addChild(miCapaFrente, +10);
        escenaADevolver.addChild(miCapaLabel, +15);
        return escenaADevolver;
    }

    class CapaFondo extends Layer {

        public CapaFondo(){
            //En el constructor hago que al declarar una variable de este tipo se ponga la imagen de fondo directamente
            PonerImagenFondo();
        }
        public void PonerImagenFondo(){
            imagenFondo = Sprite.sprite("pasto.png");
            imagenFondo.setPosition(PantallaDispositivo.width/2, PantallaDispositivo.height/2);
            float factorAncho, factorAlto;
            factorAncho = PantallaDispositivo.width/imagenFondo.getWidth();
            factorAlto = PantallaDispositivo.height/imagenFondo.getHeight();
            Log.d("ila", "factor ancho: "+factorAncho+", factorAlto: "+factorAlto);
            Log.d("ila", "pantalla ancho: "+PantallaDispositivo.width+"alto: "+PantallaDispositivo.height);
            imagenFondo.runAction(ScaleBy.action(0.01f, factorAncho, factorAlto));
            super.addChild(imagenFondo);
        }
    }

    class CapaFrente extends Layer {

        public CapaFrente(){
            //En el constructor hago que al declarar una variable de este tipo se ejecute cada 3 segundos la accion de poner una pelota
            miPelota = Sprite.sprite("pelota.png");
            //Llamo al metodo antes del timer para que se ejecute ya una vez al comenzar la aplicacion. De no poner esto, recien se veria una pelota pasados 3 segundos
            PonerPelotaLugarRandom(0);
            super.schedule("PonerPelotaLugarRandom", 3);
        }

        public void PonerPelotaLugarRandom(float tiempo){
            CCPoint posicionInicial = new CCPoint();
            CCPoint medidasPelota = new CCPoint();
            CCPoint posicionFinal = new CCPoint();

            medidasPelota.x = miPelota.getWidth();
            medidasPelota.y = miPelota.getHeight();

            Random generadorAzar = new Random();
            posicionInicial.x = generadorAzar.nextInt((int)(PantallaDispositivo.width-medidasPelota.x));
            posicionInicial.x += medidasPelota.x/2;
            posicionInicial.y = generadorAzar.nextInt((int)(PantallaDispositivo.height-medidasPelota.y));
            posicionInicial.y += medidasPelota.y/2;

            miPelota.setPosition(posicionInicial.x, posicionInicial.y);

            float horizontal, vertical;
            horizontal = PantallaDispositivo.width-posicionInicial.x;
            vertical = PantallaDispositivo.height-posicionInicial.y;
            if(horizontal<PantallaDispositivo.width/2){
                posicionFinal.x = 0f + medidasPelota.x/2;
            }
            else{
                posicionFinal.x = PantallaDispositivo.width - + medidasPelota.x/2;
            }

            if(vertical<PantallaDispositivo.height/2){
                posicionFinal.y = 0f + + medidasPelota.y/2;
            }
            else{
                posicionFinal.y = PantallaDispositivo.height - + medidasPelota.y/2;
            }

            miPelota.runAction(MoveTo.action(2, posicionFinal.x, posicionFinal.y));

            super.addChild(miPelota);
        }
    }

    class CapaLabel extends Layer {

        public CapaLabel(){
            //En el constructor hago que al declarar una variable de este tipo se ponga el titulo de fondo directamente
            PonerTituloJuego();
        }

        public void PonerTituloJuego(){
            lblTituloJuego = Label.label("TP6 - Ilanit Jamilis", "Verdana", 50);
            float altoTitulo, anchoTitulo;
            altoTitulo = lblTituloJuego.getHeight();
            anchoTitulo = lblTituloJuego.getWidth();
            CCColor3B colorTitulo = new CCColor3B(245, 255, 240);
            lblTituloJuego.setColor(colorTitulo);
            lblTituloJuego.setPosition(PantallaDispositivo.width-anchoTitulo/2, altoTitulo/2);
            super.addChild(lblTituloJuego);
        }
    }
}
