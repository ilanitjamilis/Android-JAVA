package ilanitjamilis.evaluaciont3_ilanitjamilis;

import android.util.Log;
import android.view.MotionEvent;

import org.cocos2d.actions.instant.CallFuncN;
import org.cocos2d.actions.interval.IntervalAction;
import org.cocos2d.actions.interval.JumpBy;
import org.cocos2d.actions.interval.MoveBy;
import org.cocos2d.actions.interval.RotateBy;
import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.actions.interval.Sequence;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.CocosNode;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.particlesystem.ParticleSpiral;
import org.cocos2d.types.CCPoint;
import org.cocos2d.types.CCSize;

import java.util.Random;

/**
 * Created by Ilanit Jamilis on 16/11/2017.
 */

public class clsJuego {

    CCGLSurfaceView _vistaJuego;
    CCSize PantallaDispositivo;
    Sprite imagenFondo;
    Sprite homero;
    Sprite marge;
    Sprite bart;
    Sprite lisa;
    CCPoint ubicacionImagenCuadrante1;
    CCPoint ubicacionImagenCuadrante2;
    CCPoint ubicacionImagenCuadrante3;
    CCPoint ubicacionImagenCuadrante4;
    Boolean llegoAlBorde1;
    Boolean llegoAlBorde2;
    Boolean llegoAlBorde3;
    Boolean llegoAlBorde4;



    public clsJuego(CCGLSurfaceView vistaDelJuego){
        _vistaJuego = vistaDelJuego;
    }

    public void comenzarJuego(){
        Director.sharedDirector().attachInView(_vistaJuego);

        //Me guardo el tamaño de la pantalla de mi dispositivo
        PantallaDispositivo = Director.sharedDirector().displaySize();

        //Ejecuto una escena de mi juego
        Director.sharedDirector().runWithScene(EscenaDelJuego());
    }

    private Scene EscenaDelJuego(){
        Scene escenaADevolver = Scene.node();

        //Declaro las capas y se las asigno a la escena que voy a devolver
        CapaJuego miCapaJuego = new CapaJuego();
        escenaADevolver.addChild(miCapaJuego, 10);
        CapaFondo miCapaFondo = new CapaFondo();
        escenaADevolver.addChild(miCapaFondo, -10);
        return escenaADevolver;
    }

    class CapaFondo extends Layer {

        public CapaFondo(){
            //En el constructor hago que al declarar una variable de este tipo se ponga la imagen de fondo directamente
            PonerImagenFondo();
        }
        public void PonerImagenFondo(){
            imagenFondo = Sprite.sprite("fondo.png");
            imagenFondo.setPosition(PantallaDispositivo.width/2, PantallaDispositivo.height/2);
            float factorAncho, factorAlto;
            factorAncho = PantallaDispositivo.width/imagenFondo.getWidth();
            factorAlto = PantallaDispositivo.height/imagenFondo.getHeight();
            imagenFondo.runAction(ScaleBy.action(0.01f, factorAncho, factorAlto));
            super.addChild(imagenFondo);
        }
    }

    class CapaJuego extends Layer {

        public CapaJuego(){
            homero = Sprite.sprite("homero.png");
            marge = Sprite.sprite("marge.png");
            bart = Sprite.sprite("bart.png");
            lisa = Sprite.sprite("lisa.png");
            this.setIsTouchEnabled(true); //Habilito touch
            PonerImagenes();
        }

        public void PonerImagenes(){

            llegoAlBorde1 = false;
            llegoAlBorde2 = false;
            llegoAlBorde3 = false;
            llegoAlBorde4 = false;

            CCPoint cuadrante1 = new CCPoint();
            cuadrante1.x = PantallaDispositivo.getWidth()/2;
            cuadrante1.y = PantallaDispositivo.getHeight()/2;
            ubicacionImagenCuadrante1 = new CCPoint();
            ubicacionImagenCuadrante1.x = cuadrante1.x/2;
            ubicacionImagenCuadrante1.y = cuadrante1.y/2;
            homero.setPosition(ubicacionImagenCuadrante1.x, ubicacionImagenCuadrante1.y);
            super.addChild(homero);
            Log.d("ubicacion", "cuadrante 1 = x: "+ubicacionImagenCuadrante1.x+" / y: "+ubicacionImagenCuadrante1.y);

            CCPoint cuadrante2 = new CCPoint();
            cuadrante2.x = PantallaDispositivo.getWidth()/2;
            cuadrante2.y = PantallaDispositivo.getHeight()/2;
            ubicacionImagenCuadrante2 = new CCPoint();
            ubicacionImagenCuadrante2.x = cuadrante2.x+cuadrante2.x/2;
            ubicacionImagenCuadrante2.y = cuadrante2.y-cuadrante2.y/2;
            bart.setPosition(ubicacionImagenCuadrante2.x, ubicacionImagenCuadrante2.y);
            super.addChild(bart);
            Log.d("ubicacion", "cuadrante 2 = x: "+ubicacionImagenCuadrante2.x+" / y: "+ubicacionImagenCuadrante2.y);

            CCPoint cuadrante3 = new CCPoint();
            cuadrante3.x = PantallaDispositivo.getWidth()/2;
            cuadrante3.y = PantallaDispositivo.getHeight()/2;
            ubicacionImagenCuadrante3 = new CCPoint();
            ubicacionImagenCuadrante3.x = cuadrante3.x+cuadrante1.x/2;
            ubicacionImagenCuadrante3.y = cuadrante3.y+cuadrante1.y/2;
            marge.setPosition(ubicacionImagenCuadrante3.x, ubicacionImagenCuadrante3.y);
            super.addChild(marge);
            Log.d("ubicacion", "cuadrante 3 = x: "+ubicacionImagenCuadrante3.x+" / y: "+ubicacionImagenCuadrante3.y);

            CCPoint cuadrante4 = new CCPoint();
            cuadrante4.x = PantallaDispositivo.getWidth()/2;
            cuadrante4.y = PantallaDispositivo.getHeight()/2;
            ubicacionImagenCuadrante4 = new CCPoint();
            ubicacionImagenCuadrante4.x = cuadrante4.x-cuadrante4.x/2;
            ubicacionImagenCuadrante4.y = cuadrante4.y+cuadrante4.y/2;
            lisa.setPosition(ubicacionImagenCuadrante4.x, ubicacionImagenCuadrante4.y);
            Log.d("ubicacion", "cuadrante 4 = x: "+ubicacionImagenCuadrante4.x+" / y: "+ubicacionImagenCuadrante4.y);
            super.addChild(lisa);
        }

        private void rotarSpriteDerecha(Sprite miSprite) {
            miSprite.runAction(RotateBy.action(2.0f, 360.0f));
        }

        private void rotarSpriteIzquierda(Sprite miSprite) {
            miSprite.runAction(RotateBy.action(2.0f, -360.0f));
        }

        @Override
        public boolean ccTouchesBegan (MotionEvent event){
            Log.d("touch", "ccTouchesBegan");

            Random generadorAzar = new Random();
            Integer spriteCuadranteMover = generadorAzar.nextInt((int) (4));
            Log.d("rotar", spriteCuadranteMover+"");

            switch (spriteCuadranteMover){
                case 0:
                    Log.d("rotacionDistinta", "cuadrante 1: homero");
                    rotarSpriteDerecha(homero);
                    rotarSpriteIzquierda(bart);
                    rotarSpriteIzquierda(marge);
                    rotarSpriteIzquierda(lisa);
                    break;
                case 1:
                    Log.d("rotacionDistinta", "cuadrante 2: bart");
                    rotarSpriteDerecha(bart);
                    rotarSpriteIzquierda(homero);
                    rotarSpriteIzquierda(marge);
                    rotarSpriteIzquierda(lisa);
                    break;
                case 2:
                    rotarSpriteDerecha(marge);
                    rotarSpriteIzquierda(homero);
                    rotarSpriteIzquierda(bart);
                    rotarSpriteIzquierda(lisa);
                    Log.d("rotacionDistinta", "cuadrante 3: marge");
                    break;
                case 3:
                    rotarSpriteDerecha(lisa);
                    rotarSpriteIzquierda(homero);
                    rotarSpriteIzquierda(bart);
                    rotarSpriteIzquierda(marge);
                    Log.d("rotacionDistinta", "cuadrante 4: lisa");
                    break;
            }

            return true;
        }

        @Override
        public boolean ccTouchesMoved (MotionEvent event){
            Log.d("touch", "ccTouchesMoved");

            if(ubicacionImagenCuadrante1.x + homero.getWidth()/2 < PantallaDispositivo.getWidth()) {
                ubicacionImagenCuadrante1.x = ubicacionImagenCuadrante1.x + 30;
                homero.setPosition(ubicacionImagenCuadrante1.x, ubicacionImagenCuadrante1.y);
                super.addChild(homero);
            }
            else if(!llegoAlBorde1){
                llegoAlBorde1 = true;
                Log.d("llegoAlBorde", "cuadrante 1: homero");
            }

            if(ubicacionImagenCuadrante2.x + bart.getWidth()/2 < PantallaDispositivo.getWidth()) {
                ubicacionImagenCuadrante2.x = ubicacionImagenCuadrante2.x + 30;
                bart.setPosition(ubicacionImagenCuadrante2.x, ubicacionImagenCuadrante2.y);
                super.addChild(bart);
            }
            else if(!llegoAlBorde2){
                llegoAlBorde2 = true;
                Log.d("llegoAlBorde", "cuadrante 2: bart");
            }

            if(ubicacionImagenCuadrante3.x + marge.getWidth()/2 < PantallaDispositivo.getWidth()) {
                ubicacionImagenCuadrante3.x = ubicacionImagenCuadrante3.x + 30;
                marge.setPosition(ubicacionImagenCuadrante3.x, ubicacionImagenCuadrante3.y);
                super.addChild(marge);
            }
            else if(!llegoAlBorde3){
                llegoAlBorde3 = true;
                Log.d("llegoAlBorde", "cuadrante 3: marge");
            }

            if(ubicacionImagenCuadrante4.x + lisa.getWidth()/2 < PantallaDispositivo.getWidth()) {
                ubicacionImagenCuadrante4.x = ubicacionImagenCuadrante4.x + 30;
                lisa.setPosition(ubicacionImagenCuadrante4.x, ubicacionImagenCuadrante4.y);
                super.addChild(lisa);
            }
            else if(!llegoAlBorde4){
                llegoAlBorde4 = true;
                Log.d("llegoAlBorde", "cuadrante 4: lisa");
            }

            return true;
        }

        @Override
        public boolean ccTouchesEnded (MotionEvent event){
            Log.d("touch", "ccTouchesEnded");

            //Sprite agrandarse y achicarse tres veces, usando secuencias.
            ScaleBy agrandar, achicar;
            agrandar = ScaleBy.action(1.0f, 1.2f, 1.2f);
            achicar = ScaleBy.action(1.0f, 0.8f, 0.8f);
            CallFuncN finDeSecuencia = CallFuncN.action(this, "terminoSecuenciaLatir");
            IntervalAction secuenciaLatir = Sequence.actions(agrandar, achicar, agrandar, achicar, agrandar, achicar, finDeSecuencia);
            homero.runAction(secuenciaLatir);

            return true;
        }

        public void terminoSecuenciaLatir(CocosNode objetoLlamador) {
            Log.d("secuencia", "termino");
        }

    }
}
