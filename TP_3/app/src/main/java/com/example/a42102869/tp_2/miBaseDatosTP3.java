package com.example.a42102869.tp_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class miBaseDatosTP3 extends SQLiteOpenHelper{

    public miBaseDatosTP3(Context contexto, String nombre, SQLiteDatabase.CursorFactory fabrica, int version){
        super(contexto,nombre,fabrica,version);
    }

    @Override
    public void onCreate (SQLiteDatabase baseDeDatos){
        String sqlCrearTablaJugadores;
        sqlCrearTablaJugadores = "create table jugadores (nombre text, cantJugadas integer, record integer)";
        baseDeDatos.execSQL(sqlCrearTablaJugadores);
    }

    @Override
    public void onUpgrade (SQLiteDatabase baseDeDatos, int versionAnterior, int versionNueva){

    }
}
