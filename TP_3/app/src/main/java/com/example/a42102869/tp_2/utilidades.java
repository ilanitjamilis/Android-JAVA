//ILANIT JAMILIS - TP°3 - 6°IB 2017
package com.example.a42102869.tp_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class utilidades {

    public static boolean jugadorYaJugo = false;
    public static String nombreJugador = "";
    public static Integer partidasRegistradas = 0;
    public static Integer mejorPuntaje = 9999;

    public static miBaseDatosTP3 accesoBaseTP3;
    public static SQLiteDatabase baseDatos;

    public static boolean baseDeDatosAbierta(Context vista){

        boolean responder;

        accesoBaseTP3 = new miBaseDatosTP3(vista, "baseTP3", null, 1);
        baseDatos = accesoBaseTP3.getWritableDatabase();

        if(baseDatos != null){
            responder = true;
        }
        else{
            responder = false;
        }

        return responder;
    }

}
