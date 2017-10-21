package com.example.ilanitjamilis.evaluacion2trimestre_ilanitjamilis;

import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class ActividadLista extends AppCompatActivity {

    ArrayList<String> miListaPeliculas;
    ListView miListaPeliculasLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ila", "llega a actividad lista");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_lista);
        Log.d("ila", "pone el layout");

        LlenarLista();
        Log.d("ila", "lleno mi lista");
        miListaPeliculasLV = (ListView) findViewById(R.id.miListaPeliculas);
        Log.d("ila", "declaro el adaptador para mi lista");
        AdaptadorParaPeliculas adaptador = new AdaptadorParaPeliculas(miListaPeliculas, getApplicationContext());
        Log.d("ila", "lleno mi lista con el adaptador que ya tengo");
        miListaPeliculasLV.setAdapter(adaptador);
    }

    public void LlenarLista(){
        miListaPeliculas = new ArrayList<>();
        miListaPeliculas.add("Toy Story 3");
        miListaPeliculas.add("Volver al Futuro");
        miListaPeliculas.add("Buscando a Nemo");
        miListaPeliculas.add("Rocky");
        miListaPeliculas.add("Los Increíbles");
        miListaPeliculas.add("Forest Gump");
        miListaPeliculas.add("Mi Villano Favorito");
        miListaPeliculas.add("Rambo");
        miListaPeliculas.add("Cars");
        miListaPeliculas.add("Indiana Jones");
    }
}
