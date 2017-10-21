package com.example.a42102869.tp_4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 42102869 on 13/6/2017.
 */

public class utilidades {

    public static Usuario miUsuario = new Usuario();

    public static MiBaseDeDatosTP4 accesoBaseTP4;
    public static SQLiteDatabase baseDatos;

    public static boolean baseDeDatosAbierta(Context vista){

        boolean responder;

        accesoBaseTP4 = new MiBaseDeDatosTP4(vista, "miBaseTP4", null, 1);
        baseDatos = accesoBaseTP4.getWritableDatabase();

        if(baseDatos != null){
            responder = true;
        }
        else{
            responder = false;
        }

        return responder;
    }
}
