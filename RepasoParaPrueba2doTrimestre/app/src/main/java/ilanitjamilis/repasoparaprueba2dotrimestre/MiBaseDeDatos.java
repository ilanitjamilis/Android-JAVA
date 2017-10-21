package ilanitjamilis.repasoparaprueba2dotrimestre;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ilanit Jamilis on 15/8/2017.
 */

public class MiBaseDeDatos extends SQLiteOpenHelper {

    public MiBaseDeDatos (Context contexto, String nombre, SQLiteDatabase.CursorFactory fabrica, int version){
        super(contexto,nombre,fabrica,version);
    }

    @Override
    public void onCreate (SQLiteDatabase baseDeDatos){
        String sqlCrearTablaTextos;
        sqlCrearTablaTextos = "create table textos (texto text)";
        baseDeDatos.execSQL(sqlCrearTablaTextos);
    }

    @Override
    public void onUpgrade (SQLiteDatabase baseDeDatos, int versionAnterior, int versionNueva){

    }
}
