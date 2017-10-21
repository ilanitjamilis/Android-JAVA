package com.example.ilanitjamilis.evaluacion2trimestre_ilanitjamilis;

import android.content.Context;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorParaPeliculas extends BaseAdapter {

    private ArrayList<String> _MiListaPeliculas;
    private Context _MiContexto;

    public AdaptadorParaPeliculas (ArrayList<String> listaPeliculas, Context contextoAUsar){
        _MiListaPeliculas = listaPeliculas;
        _MiContexto = contextoAUsar;
    }

    public int getCount(){
        return _MiListaPeliculas.size();
    }

    public String getItem(int posicionAObtener){
        String textoDevolver;
        textoDevolver = _MiListaPeliculas.get(posicionAObtener);
        return textoDevolver;
    }

    public long getItemId(int posicionAObtener){
        return posicionAObtener;
    }

    public View getView(int posicionActual, View vistaActual, ViewGroup grupoActual){
        View vistaDevolver = null;

        LayoutInflater infladorDeLayout;
        infladorDeLayout = (LayoutInflater)_MiContexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vistaDevolver = infladorDeLayout.inflate(R.layout.listview_peliculas_detalle_elemento, grupoActual, false);

        TextView TVpelicula = (TextView)vistaDevolver.findViewById(R.id.nombrePelicula);
        TextView TVcaracteres = (TextView)vistaDevolver.findViewById(R.id.cantidadCaracteres);

        String textoPosicionActual = getItem(posicionActual);

        TVpelicula.setText(textoPosicionActual);
        TVcaracteres.setText(String.valueOf(textoPosicionActual.length()));

        return vistaDevolver;
    }
}
