package com.example.a42102869.tp_4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 42102869 on 13/6/2017.
 */

public class MiBaseDeDatosTP4 extends SQLiteOpenHelper {

    public MiBaseDeDatosTP4 (Context contexto, String nombre, SQLiteDatabase.CursorFactory fabrica, int version){
        super(contexto,nombre,fabrica,version);
    }

    @Override
    public void onCreate (SQLiteDatabase baseDeDatos){
        String sqlCrearTablaJugadores;
        sqlCrearTablaJugadores = "create table usuarios (user text, password text, ultimaConexion text)";
        baseDeDatos.execSQL(sqlCrearTablaJugadores);
    }

    @Override
    public void onUpgrade (SQLiteDatabase baseDeDatos, int versionAnterior, int versionNueva){

    }
}
