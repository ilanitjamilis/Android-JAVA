//ILANIT JAMILIS - TP°2 - 6°IB 2017
package com.example.a42102869.tp_2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ActividadJuego extends AppCompatActivity {
    ImageButton[] botones;
    Integer movimientosUtilizados;
    String nombreJugadorRecibido;
    String modoJuegoRecibido;
    Drawable.ConstantState codImgCambiar;
    Integer i = 0;
    Boolean[] jugadasInteligentes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_juego);

        movimientosUtilizados = 0;

        Bundle datosRecibidos;
        datosRecibidos = this.getIntent().getExtras();

        nombreJugadorRecibido = datosRecibidos.getString("nombreJugador");
        utilidades.setJugadorEnLista(nombreJugadorRecibido);

        modoJuegoRecibido = datosRecibidos.getString("modoJuego");

        TextView nombreJugador;
        nombreJugador = (TextView)findViewById(R.id.nombreJugador);
        nombreJugador.setText(nombreJugadorRecibido);

        jugadasInteligentes = new Boolean[9];

        botones = new ImageButton[9];
        botones[0]=(ImageButton)findViewById(R.id.btnFoto1);
        botones[1]=(ImageButton)findViewById(R.id.btnFoto2);
        botones[2]=(ImageButton)findViewById(R.id.btnFoto3);
        botones[3]=(ImageButton)findViewById(R.id.btnFoto4);
        botones[4]=(ImageButton)findViewById(R.id.btnFoto5);
        botones[5]=(ImageButton)findViewById(R.id.btnFoto6);
        botones[6]=(ImageButton)findViewById(R.id.btnFoto7);
        botones[7]=(ImageButton)findViewById(R.id.btnFoto8);
        botones[8]=(ImageButton)findViewById(R.id.btnFoto9);

        boolean botonesIguales = false;
        do{
            for(int boton=0; boton <= 8; boton++){
                asignarImagenAlBoton(botones,boton);
            }
            Drawable.ConstantState[] codigosImagenesBotones = codigosBotones(botones);
            botonesIguales = verificarBotonesIguales(codigosImagenesBotones);
        }while(botonesIguales);

        if(modoJuegoRecibido.compareTo("azar")==0){
            final Timer miReloj;
            miReloj = new Timer();

            TimerTask miTareaARepetir;
            miTareaARepetir = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            jugadaAlAzar(botones);
                            boolean gano = verificarGano(botones);
                            movimientosUtilizados++;
                            TextView movimientos;
                            movimientos = (TextView)findViewById(R.id.movimientosUtilizados);
                            movimientos.setText(movimientosUtilizados.toString());
                            if(gano){
                                miReloj.cancel();
                                ganoJuego();
                            }
                        }
                    });
                }
            };
            miReloj.schedule(miTareaARepetir, 0, 350);
        }

        else{
            if(modoJuegoRecibido.compareTo("inteligente")==0){
                codImgCambiar = codImgMenosVeces(botones);

                final Timer miReloj;
                miReloj = new Timer();

                TimerTask miTareaARepetir;
                miTareaARepetir = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(i==0){
                                    pensarJugadaInteligente(jugadasInteligentes);
                                }
                                jugadaInteligente(botones, codImgCambiar, jugadasInteligentes);
                                boolean gano = verificarGano(botones);
                                TextView movimientos;
                                movimientos = (TextView)findViewById(R.id.movimientosUtilizados);
                                movimientos.setText(movimientosUtilizados.toString());
                                if(gano){
                                    miReloj.cancel();
                                    ganoJuego();
                                }
                            }
                        });
                    }
                };
                miReloj.schedule(miTareaARepetir, 0, 200);
            }
        }
    }

    public Drawable.ConstantState[] codigosBotones(ImageButton[] misBotones) {
        Drawable.ConstantState[] codigosImagenesBotones = new Drawable.ConstantState[9];
        for (int i = 0; i <= 8; i++) {
            codigosImagenesBotones[i] = verQueImagenEs(misBotones[i]);
        }
        return codigosImagenesBotones;
    }

    public boolean verificarBotonesIguales (Drawable.ConstantState[] codigosImagenesBotones){
        boolean todosIguales = false;
        if(codigosImagenesBotones[0]==codigosImagenesBotones[1]&&codigosImagenesBotones[0]==codigosImagenesBotones[2]&&codigosImagenesBotones[0]==codigosImagenesBotones[3]&&codigosImagenesBotones[0]==codigosImagenesBotones[4]&&codigosImagenesBotones[0]==codigosImagenesBotones[5]&&codigosImagenesBotones[0]==codigosImagenesBotones[6]&&codigosImagenesBotones[0]==codigosImagenesBotones[7]&&codigosImagenesBotones[0]==codigosImagenesBotones[8]){
            todosIguales=true;
        }
        return todosIguales;
    }

    public Drawable.ConstantState codImgMenosVeces (ImageButton [] misBotones){
        Drawable.ConstantState miCodImg;
        Drawable.ConstantState miCodAnalizo;

        Integer vecesImg1 = 0;
        Integer vecesImg2 = 0;

        Drawable.ConstantState codImg1 = getCodImg1();
        String codImg1Analizo = codImg1.toString();

        Drawable.ConstantState codImg2 = getCodImg2();
        String codImg2Analizo = codImg2.toString();

        for(int i=0; i<=8; i++){
            miCodAnalizo = verQueImagenEs(misBotones[i]);

            if(miCodAnalizo.toString().compareTo(codImg1Analizo)==0){
                vecesImg1++;
            }
            else{
                vecesImg2++;
            }
        }

        if(vecesImg1<vecesImg2){
            return codImg1;
        }
        else{
            return codImg2;
        }
    }

    public void asignarImagenAlBoton(ImageButton[] misBotones, Integer numImg){
        Random generadorAzar;
        generadorAzar = new Random();

        int numeroImagen;
        numeroImagen = generadorAzar.nextInt(2);

        if(numeroImagen == 0){
            misBotones[numImg].setImageResource(R.drawable.img1);
        }
        else{
            misBotones[numImg].setImageResource(R.drawable.img2);
        }
    }

    public void apretoBtn (View vista) {
        movimientosUtilizados++;
        ImageButton botonPresionado;
        botonPresionado = (ImageButton)vista;

        String tagBotonPresionado;
        tagBotonPresionado = botonPresionado.getTag().toString();

        int numBtnPresionado;
        numBtnPresionado = Integer.parseInt(tagBotonPresionado);

        jugadaDeBotonX(numBtnPresionado, botones);

        TextView movimientos;
        movimientos = (TextView)findViewById(R.id.movimientosUtilizados);
        movimientos.setText(movimientosUtilizados.toString());


        boolean gano;
        Drawable.ConstantState[] codigosImagenesBotones = codigosBotones(botones);
        gano = verificarBotonesIguales(codigosImagenesBotones);
        if(gano){
            ganoJuego();
        }
    }

    public void ganoJuego (){
        Bundle paqueteDatos;
        paqueteDatos = new Bundle();
        paqueteDatos.putInt("movimientosUtilizados",movimientosUtilizados);
        paqueteDatos.putString("nombreJugador",nombreJugadorRecibido);

        Integer minJugadas = utilidades.getMinCantJugadas();

        if(movimientosUtilizados > minJugadas){
            paqueteDatos.putString("caso","perdio");
        }
        else{
            paqueteDatos.putString("caso","gano");
            utilidades.setMinCantJugadas(movimientosUtilizados);
            utilidades.setNombreMinCantJugadas(nombreJugadorRecibido);
        }

        Intent irGanaste;
        irGanaste = new Intent (ActividadJuego.this, ActividadGanaste.class);
        irGanaste.putExtras(paqueteDatos);
        startActivity(irGanaste);
    }

    public Drawable.ConstantState verQueImagenEs(ImageButton imgButton){
        Drawable.ConstantState codImg;
        codImg = imgButton.getDrawable().getConstantState();
        return codImg;
    }

    public void cambiarImagenBoton(ImageButton[] misBotones,Integer numImg){
        Drawable.ConstantState codigoImg1 = getCodImg1();
        Drawable.ConstantState codImgRecibida = getCodImg(misBotones, numImg);

        if(codigoImg1 == codImgRecibida){
            misBotones[numImg].setImageResource(R.drawable.img2);
        }
        else{
            misBotones[numImg].setImageResource(R.drawable.img1);
        }
    }

    public Drawable.ConstantState getCodImg (ImageButton[] misBotones, Integer numImg){
        Drawable.ConstantState codImgRecibida;
        codImgRecibida = misBotones[numImg].getDrawable().getConstantState();

        return codImgRecibida;
    }

    public Drawable.ConstantState getCodImg1 (){
        Drawable.ConstantState codigoImg1;
        codigoImg1 = getResources().getDrawable(R.drawable.img1).getConstantState();

        return codigoImg1;
    }

    public Drawable.ConstantState getCodImg2 (){
        Drawable.ConstantState codigoImg2;
        codigoImg2 = getResources().getDrawable(R.drawable.img2).getConstantState();

        return codigoImg2;
    }

    public void jugadaDeBotonX (Integer numBoton, ImageButton[] botones){
        switch (numBoton) {
            case 0:
                cambiarImagenBoton(botones,0);
                cambiarImagenBoton(botones,1);
                cambiarImagenBoton(botones,3);
                cambiarImagenBoton(botones,4);
                break;

            case 1:
                cambiarImagenBoton(botones,0);
                cambiarImagenBoton(botones,1);
                cambiarImagenBoton(botones,2);
                cambiarImagenBoton(botones,4);
                break;

            case 2:
                cambiarImagenBoton(botones,1);
                cambiarImagenBoton(botones,2);
                cambiarImagenBoton(botones,4);
                cambiarImagenBoton(botones,5);
                break;

            case 3:
                cambiarImagenBoton(botones,0);
                cambiarImagenBoton(botones,3);
                cambiarImagenBoton(botones,4);
                cambiarImagenBoton(botones,6);
                break;

            case 4:
                cambiarImagenBoton(botones,1);
                cambiarImagenBoton(botones,3);
                cambiarImagenBoton(botones,4);
                cambiarImagenBoton(botones,5);
                cambiarImagenBoton(botones,7);
                break;

            case 5:
                cambiarImagenBoton(botones,2);
                cambiarImagenBoton(botones,4);
                cambiarImagenBoton(botones,5);
                cambiarImagenBoton(botones,8);
                break;

            case 6:
                cambiarImagenBoton(botones,3);
                cambiarImagenBoton(botones,4);
                cambiarImagenBoton(botones,6);
                cambiarImagenBoton(botones,7);
                break;

            case 7:
                cambiarImagenBoton(botones,4);
                cambiarImagenBoton(botones,6);
                cambiarImagenBoton(botones,7);
                cambiarImagenBoton(botones,8);
                break;

            case 8:
                cambiarImagenBoton(botones,4);
                cambiarImagenBoton(botones,5);
                cambiarImagenBoton(botones,7);
                cambiarImagenBoton(botones,8);
                break;
        }
    }

    public void jugadaAlAzar(ImageButton[] misBotones) {
        Random generadorAzar;
        generadorAzar = new Random();
        Integer botonAzar;
        botonAzar = generadorAzar.nextInt(9);
        jugadaDeBotonX(botonAzar, misBotones);
    }

    public Boolean[] pensarJugadaInteligente(Boolean[] jugadasInteligentes){
        Drawable.ConstantState codImgAnalizo;
        for(int a=0; a<=8; a++){
            codImgAnalizo = getCodImg(botones, a);
            if(codImgAnalizo.toString().compareTo(codImgCambiar.toString())==0){
                jugadasInteligentes[a]=true;
            }
            else{
                jugadasInteligentes[a]=false;
            }
        }
        return jugadasInteligentes;
    }

    public void jugadaInteligente (ImageButton[] misBotones, Drawable.ConstantState codImgCambiar, Boolean[] jugadasInteligentes){
        Drawable.ConstantState codImgAnalizo = getCodImg(misBotones, i);
        if(jugadasInteligentes[i]==true){
            jugadaDeBotonX(i, misBotones);
            movimientosUtilizados++;
        }
        i++;
        if(i==9){
            i = 0;
        }
    }

    public boolean verificarGano(ImageButton[] misBotones){
        boolean ganoJuego;
        ganoJuego = false;

        boolean todosIguales;
        Drawable.ConstantState[] codigosImagenesBotones = codigosBotones(botones);
        todosIguales = verificarBotonesIguales(codigosImagenesBotones);

        if (todosIguales) {
            ganoJuego = true;
        }
        return ganoJuego;
    }

    public void irPantallaPrincipal(View vista){
        Intent volverInicio;
        volverInicio = new Intent (ActividadJuego.this, ActividadPrincipal.class);
        startActivity(volverInicio);
    }
}
