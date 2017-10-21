package ilanitjamilis.repasoparaprueba2dotrimestre;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ilanit Jamilis on 15/8/2017.
 */

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
