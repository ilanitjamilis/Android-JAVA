package com.example.a42102869.tp_4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by 42102869 on 8/6/2017.
 */

public class FragmentUsuarios extends Fragment implements View.OnClickListener{

    ListView listaJugadoresNombres;
    ListView listaJugadoresUltimaConexion;

    public View onCreateView (LayoutInflater Inflador, ViewGroup GrupoVista, Bundle Datos){

        View vistaDevolver = Inflador.inflate(R.layout.layout_usuarios, GrupoVista, false);

        ActividadPrincipal AdministradorCentral = (ActividadPrincipal) getActivity();

        //Agarrar de la base de datos lista con todos los usuarios
        ArrayList<String> userUsuarios = AdministradorCentral.TraerUsuariosNombre();
        ArrayList<String> userConexiones = AdministradorCentral.TraerUsuariosConexion();

        //Poner la lista en mi ListView
        listaJugadoresNombres = (ListView) vistaDevolver.findViewById(R.id.listaJugadoresUsuarios);
        //Probelmas con el parámetro vista
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, userUsuarios);
        listaJugadoresNombres.setAdapter(arrayAdapter);

        listaJugadoresNombres = (ListView) vistaDevolver.findViewById(R.id.listaJugadoresConexiones);
        //Probelmas con el parámetro vista
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, userConexiones);
        listaJugadoresNombres.setAdapter(arrayAdapter2);

        AdministradorCentral.ActualizarFechaConexion();

        return vistaDevolver;
    }

    public void onClick (View vista) {
        ActividadPrincipal AdministradorCentral = (ActividadPrincipal) getActivity();
        AdministradorCentral.irInicio();
    }
}
