package com.example.ilanitjamilis.tp7;

import android.util.Log;
import android.view.MotionEvent;

import org.cocos2d.actions.instant.CallFuncN;
import org.cocos2d.actions.interval.IntervalAction;
import org.cocos2d.actions.interval.JumpBy;
import org.cocos2d.actions.interval.MoveBy;
import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.actions.interval.Sequence;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.CocosNode;
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
    Sprite miSprite1Fuego;
    Sprite miSprite2Agua;
    Sprite imagenFondo;
    Label lblTituloJuego;
    boolean seTocanSpritesAlIniciar;

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
            imagenFondo = Sprite.sprite("fondo.png");
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

    boolean moverSprite1;
    boolean moverSprite2;

    class CapaFrente extends Layer {

        public CapaFrente(){
            miSprite1Fuego = Sprite.sprite("fire.png");
            miSprite2Agua = Sprite.sprite("water.png");
            this.setIsTouchEnabled(true); //Habilito touch
            PonerMisObjetosEnLugarRandomSinTocarse();
            //super.schedule("PonerPelotaLugarRandom", 3);
        }

        @Override
        public boolean ccTouchesBegan (MotionEvent event){
            Log.d("Toque comienza", "X: "+event.getX()+" - Y: "+event.getY());

            moverSprite1 = false;
            moverSprite2 = false;

            //Ver si estoy dentro de un sprite
            moverSprite1 = EstoyDentroDeSprite1(event.getX(), PantallaDispositivo.getHeight()-event.getY());
            Log.d("mover sprite", "sprite 1: "+ moverSprite1);
            moverSprite2 = EstoyDentroDeSprite2(event.getX(), PantallaDispositivo.getHeight()-event.getY());
            Log.d("mover sprite", "sprite 2: "+ moverSprite2);

            //Si estoy en sprite 1 mover ese, si estoy en sprite 2 mover el sprite 2 (eso pasa en touches moved)

            return true;
        }

        @Override
        public boolean ccTouchesMoved (MotionEvent event){
            Log.d("Toque mueve", "X: "+event.getX()+" - Y: "+event.getY());

            MoverMiSprite(event.getX(), PantallaDispositivo.getHeight()-event.getY());

            return true;
        }

        @Override
        public boolean ccTouchesEnded (MotionEvent event){
            Log.d("Toque termina", "X: "+event.getX()+" - Y: "+event.getY());

            boolean choqueDeSprites = InterseccionEntreSprites(miSprite1Fuego, miSprite2Agua);

            CallFuncN finDeSecuencia = CallFuncN.action(this, "terminoSecuenciaCuadrado");

            MoveBy abajo, derecha, arriba, izquierda;
            abajo = MoveBy.action(1, 0, -200);
            derecha = MoveBy.action(1, 200, 0);
            arriba = MoveBy.action(1, 0, 200);
            izquierda = MoveBy.action(1, -200, 0);
            IntervalAction miSecuenciaCuadrado = Sequence.actions(abajo, derecha, arriba, izquierda, finDeSecuencia);

            if(choqueDeSprites && moverSprite1){

                //Secuencia con spirte 1
                Log.d("choqueSprites", "true");

                miSprite1Fuego.runAction(JumpBy.action(3.0f, 0, 0, 300, 6)); //con esto salta el otro en el lugar mientras se hace la secuencia
                miSprite2Agua.runAction(miSecuenciaCuadrado); //hace el cuadrado y despues llama a que se ubiquen los sprites de manera random nuevamente

            }else if (choqueDeSprites && moverSprite2){

                //Secuencia con spirte 2
                Log.d("choqueSprites", "true");

                miSprite2Agua.runAction(JumpBy.action(3.0f, 0, 0, 300, 6)); //con esto salta el otro en el lugar mientras se hace la secuencia
                miSprite1Fuego.runAction(miSecuenciaCuadrado); //hace el cuadrado y despues llama a que se ubiquen los sprites de manera random nuevamente
            }

            return true;
        }

        public void terminoSecuenciaCuadrado(CocosNode objetoLlamador){
            //Hago que cuando termine la secuencia del cuadrado se vuelvan a poner los objetos en lugares random nuevamente
            PonerMisObjetosEnLugarRandomSinTocarse();
        }

        boolean EstoyDentroDeSprite1(float miXdondeToco, float miyDondeToco){
            boolean estoyDentro = false;

            float posicionX, posicionY;
            posicionX = miXdondeToco;
            posicionY = miyDondeToco;

            int SpriteIzquierda, SpriteDerecha, SpriteAbajo, SpriteArriba;
            SpriteIzquierda =(int) (miSprite1Fuego.getPositionX() - miSprite1Fuego.getWidth()/2);
            SpriteDerecha =(int) (miSprite1Fuego.getPositionX() + miSprite1Fuego.getWidth()/2);
            SpriteAbajo =(int) (miSprite1Fuego.getPositionY() - miSprite1Fuego.getHeight()/2);
            SpriteArriba =(int) (miSprite1Fuego.getPositionY() + miSprite1Fuego.getHeight()/2);

            if(EstaEntre(posicionX, SpriteDerecha, SpriteIzquierda) && EstaEntre(posicionY, SpriteArriba, SpriteAbajo)){
                estoyDentro = true;
            }

            return estoyDentro;
        }

        boolean EstoyDentroDeSprite2(float miXdondeToco, float miyDondeToco){
            boolean estoyDentro = false;

            float posicionX, posicionY;
            posicionX = miXdondeToco;
            posicionY = miyDondeToco;

            int SpriteIzquierda, SpriteDerecha, SpriteAbajo, SpriteArriba;
            SpriteIzquierda =(int) (miSprite2Agua.getPositionX() - miSprite2Agua.getWidth()/2);
            SpriteDerecha =(int) (miSprite2Agua.getPositionX() + miSprite2Agua.getWidth()/2);
            SpriteAbajo =(int) (miSprite2Agua.getPositionY() - miSprite2Agua.getHeight()/2);
            SpriteArriba =(int) (miSprite2Agua.getPositionY() + miSprite2Agua.getHeight()/2);

            if(EstaEntre(posicionX, SpriteDerecha, SpriteIzquierda) && EstaEntre(posicionY, SpriteArriba, SpriteAbajo)){
                estoyDentro = true;
            }

            return estoyDentro;
        }

        void MoverMiSprite(float posX, float posY){

            /*float movHorizontal, movVertical, suavizadorMovimiento;
            movHorizontal = posX - PantallaDispositivo.getWidth() / 2;
            movVertical = posY - PantallaDispositivo.getHeight() / 2;

            suavizadorMovimiento = 20;
            movHorizontal = movHorizontal / suavizadorMovimiento;
            movVertical = movVertical / suavizadorMovimiento;

            float posFinalX, posFinalY;*/

            if(moverSprite1) {
                /*posFinalX = miSprite1Fuego.getPositionX() + movHorizontal;
                posFinalY = miSprite1Fuego.getPositionY() + movVertical;

                if (posFinalX < miSprite1Fuego.getWidth() / 2) {
                    posFinalX = miSprite1Fuego.getWidth() / 2;
                }
                if (posFinalX > PantallaDispositivo.getWidth() - miSprite1Fuego.getWidth() / 2) {
                    posFinalX = PantallaDispositivo.getWidth() - miSprite1Fuego.getWidth() / 2;
                }
                if (posFinalY < miSprite1Fuego.getHeight() / 2) {
                    posFinalY = miSprite1Fuego.getHeight() / 2;
                }
                if (posFinalY > PantallaDispositivo.getHeight() - miSprite1Fuego.getHeight() / 2) {
                    posFinalY = PantallaDispositivo.getHeight() - miSprite1Fuego.getHeight() / 2;
                }*/ //Todo esto es para entlencer el proceso de de mover el sprite

                miSprite1Fuego.setPosition(posX, posY);

            }else if(moverSprite2){
                /*posFinalX = miSprite2Agua.getPositionX() + movHorizontal;
                posFinalY = miSprite2Agua.getPositionY() + movVertical;

                if (posFinalX < miSprite2Agua.getWidth() / 2) {
                    posFinalX = miSprite2Agua.getWidth() / 2;
                }
                if (posFinalX > PantallaDispositivo.getWidth() - miSprite2Agua.getWidth() / 2) {
                    posFinalX = PantallaDispositivo.getWidth() - miSprite2Agua.getWidth() / 2;
                }
                if (posFinalY < miSprite2Agua.getHeight() / 2) {
                    posFinalY = miSprite2Agua.getHeight() / 2;
                }
                if (posFinalY > PantallaDispositivo.getHeight() - miSprite2Agua.getHeight() / 2) {
                    posFinalY = PantallaDispositivo.getHeight() - miSprite2Agua.getHeight() / 2;
                }*/ //Todo esto es para entlencer el proceso de de mover el sprite

                miSprite2Agua.setPosition(posX, posY);

            }
        }

        public void PonerMisObjetosEnLugarRandomSinTocarse(){

            seTocanSpritesAlIniciar = true;

            CCPoint medidasSprite1Fuego  = new CCPoint();
            CCPoint medidasSprite2Agua = new CCPoint();

            CCPoint posicionInicialSprite1 = new CCPoint();
            CCPoint posicionInicialSprite2 = new CCPoint();

            medidasSprite1Fuego.x = miSprite1Fuego.getWidth();
            medidasSprite1Fuego.y = miSprite1Fuego.getHeight();

            medidasSprite2Agua.x = miSprite2Agua.getWidth();
            medidasSprite2Agua.y = miSprite2Agua.getHeight();

            Random generadorAzar = new Random();

            int i = 1;
            while(seTocanSpritesAlIniciar) {
                Log.d("ila", "entra a tomar medidas vez: "+i);
                posicionInicialSprite1.x = generadorAzar.nextInt((int) (PantallaDispositivo.width - medidasSprite1Fuego.x));
                posicionInicialSprite1.x += medidasSprite1Fuego.x /2;

                posicionInicialSprite1.y = generadorAzar.nextInt((int) (PantallaDispositivo.height - medidasSprite1Fuego.y));
                posicionInicialSprite1.y += medidasSprite1Fuego.y /2;

                posicionInicialSprite2.x = generadorAzar.nextInt((int) (PantallaDispositivo.width - medidasSprite2Agua.x));
                posicionInicialSprite2.x += medidasSprite2Agua.x /2;

                posicionInicialSprite2.y = generadorAzar.nextInt((int) (PantallaDispositivo.height - medidasSprite2Agua.y));
                posicionInicialSprite2.y += medidasSprite2Agua.y/2;

                miSprite1Fuego.setPosition(posicionInicialSprite1.x, posicionInicialSprite1.y);
                miSprite2Agua.setPosition(posicionInicialSprite2.x, posicionInicialSprite2.y);

                seTocanSpritesAlIniciar = InterseccionEntreSprites(miSprite1Fuego, miSprite2Agua);
                i++;
            }

            super.addChild(miSprite2Agua);
            super.addChild(miSprite1Fuego); //Lo pongo ultimo para que cuando lo mueva se vea arriba
        }

        boolean InterseccionEntreSprites (Sprite Sprite1, Sprite Sprite2) {
            boolean devolver = false;
            int Sprite1Izquierda, Sprite1Derecha, Sprite1Abajo, Sprite1Arriba;
            int Sprite2Izquierda, Sprite2Derecha, Sprite2Abajo, Sprite2Arriba;
            Sprite1Izquierda=(int) (Sprite1.getPositionX() - Sprite1.getWidth()/2);
            Sprite1Derecha =(int) (Sprite1.getPositionX() + Sprite1.getWidth()/2);
            Sprite1Abajo =(int) (Sprite1.getPositionY() - Sprite1.getHeight()/2);
            Sprite1Arriba =(int) (Sprite1.getPositionY() + Sprite1.getHeight()/2);
            Sprite2Izquierda =(int) (Sprite2.getPositionX() - Sprite2.getWidth()/2);
            Sprite2Derecha =(int) (Sprite2.getPositionX() + Sprite2.getWidth()/2);
            Sprite2Abajo =(int) (Sprite2.getPositionY() - Sprite2.getHeight()/2);
            Sprite2Arriba =(int) (Sprite2.getPositionY() + Sprite2.getHeight()/2);

            //Borde izq y borde inf de Sprite 1 está dentro de Sprite 2
            if (EstaEntre(Sprite1Izquierda, Sprite2Izquierda, Sprite2Derecha) && EstaEntre(Sprite1Abajo, Sprite2Abajo, Sprite2Arriba)) {
                devolver = true;
            }
            //Borde izq y borde sup de Sprite 1 está dentro de Sprite 2
            if (EstaEntre(Sprite1Izquierda, Sprite2Izquierda, Sprite2Derecha) && EstaEntre(Sprite1Arriba, Sprite2Abajo, Sprite2Arriba)) {
                devolver = true;
            }
            //Borde der y borde sup de Sprite 1 está dentro de Sprite 2
            if (EstaEntre(Sprite1Derecha, Sprite2Izquierda, Sprite2Derecha) && EstaEntre(Sprite1Arriba, Sprite2Abajo, Sprite2Arriba)) {
                devolver = true;
            }
            //Borde der y borde inf de Sprite 1 está dentro de Sprite 2
            if (EstaEntre(Sprite1Derecha, Sprite2Izquierda, Sprite2Derecha) && EstaEntre(Sprite1Abajo, Sprite2Abajo, Sprite2Arriba)) {
                devolver = true;
            }
            //Borde izq y borde inf de Sprite 2 está dentro de Sprite 1
            if (EstaEntre(Sprite2Izquierda, Sprite1Izquierda, Sprite1Derecha) && EstaEntre(Sprite2Abajo, Sprite1Abajo, Sprite1Arriba)) {
                devolver = true;
            }

            //Borde izq y borde sup de Sprite 1 está dentro de Sprite 1
            if (EstaEntre(Sprite2Izquierda, Sprite1Izquierda, Sprite1Derecha) && EstaEntre(Sprite2Arriba, Sprite1Abajo, Sprite1Arriba)) {
                devolver = true;
            }
            //Borde der y borde sup de Sprite 2 está dentro de Sprite 1
            if (EstaEntre(Sprite2Derecha, Sprite1Izquierda, Sprite1Derecha) && EstaEntre(Sprite2Arriba, Sprite1Abajo, Sprite1Arriba)) {
                devolver = true;
            }
            //Borde der y borde inf de Sprite 2 está dentro de Sprite 1
            if (EstaEntre(Sprite2Derecha, Sprite1Izquierda, Sprite1Derecha) && EstaEntre(Sprite2Abajo, Sprite1Abajo, Sprite1Arriba)) {
                devolver = true;
            }
            return devolver;
        }

        boolean EstaEntre (float numAComparar, int numMenor, int numMayor){
            boolean devolver;
            if(numMenor>numMayor){
                int aux = numMayor;
                numMayor = numMenor;
                numMenor = aux;
            }
            if(numAComparar >= numMenor && numAComparar <= numMayor) {
                devolver = true;
            }
            else{
                devolver = false;
            }
            return devolver;
        }
    }

    class CapaLabel extends Layer {

        public CapaLabel(){
            //En el constructor hago que al declarar una variable de este tipo se ponga el titulo de fondo directamente
            PonerTituloJuego();
        }

        public void PonerTituloJuego(){
            lblTituloJuego = Label.label("TP7 - Ilanit Jamilis", "Verdana", 50);
            float altoTitulo, anchoTitulo;
            altoTitulo = lblTituloJuego.getHeight();
            anchoTitulo = lblTituloJuego.getWidth();
            CCColor3B colorTitulo = new CCColor3B(0, 0, 0);
            lblTituloJuego.setColor(colorTitulo);
            lblTituloJuego.setPosition(PantallaDispositivo.width-anchoTitulo/2, altoTitulo/2);
            super.addChild(lblTituloJuego);
        }
    }
}
