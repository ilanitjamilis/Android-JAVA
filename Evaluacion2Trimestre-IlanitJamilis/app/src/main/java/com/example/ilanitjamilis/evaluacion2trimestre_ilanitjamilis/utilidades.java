package com.example.ilanitjamilis.evaluacion2trimestre_ilanitjamilis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class utilidades {

    public static MiBaseDeDatos accesoBase;
    public static SQLiteDatabase baseDatos;

    public static boolean baseDeDatosAbierta(Context vista){

        boolean responder;

        accesoBase = new MiBaseDeDatos (vista, "miBase", null, 1);
        baseDatos = accesoBase.getWritableDatabase();

        if(baseDatos != null){
            responder = true;
        }
        else{
            responder = false;
        }

        return responder;
    }
}