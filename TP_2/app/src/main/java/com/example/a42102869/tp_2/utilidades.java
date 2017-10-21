//ILANIT JAMILIS - TP°2 - 6°IB 2017
package com.example.a42102869.tp_2;

import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class utilidades {

    private static Integer minCantJugadas = 999999;

    public static Integer getMinCantJugadas() {
        return minCantJugadas;
    }

    public static void setMinCantJugadas(Integer num) {
        minCantJugadas = num;
    }


    private static String nombreMinCantJugadas = "";

    public static String getNombreMinCantJugadas() {
        return nombreMinCantJugadas;
    }

    public static void setNombreMinCantJugadas(String nom) {
        nombreMinCantJugadas = nom;
    }


    private static ArrayList<String> jugadoresAnteriores = new ArrayList<>();

    public static ArrayList<String> getListaJugadores(){return jugadoresAnteriores; }

    public static void setJugadorEnLista(String jugador){jugadoresAnteriores.add(jugador);}

}
