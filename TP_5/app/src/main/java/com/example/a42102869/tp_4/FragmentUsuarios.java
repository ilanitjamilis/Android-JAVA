package com.example.a42102869.tp_4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class FragmentUsuarios extends Fragment implements View.OnClickListener{

    ListView listaUsuarios;
    Button irInicio;

    public View onCreateView (LayoutInflater Inflador, ViewGroup GrupoVista, Bundle Datos){

        View vistaDevolver = Inflador.inflate(R.layout.layout_usuarios, GrupoVista, false);

        irInicio = (Button)vistaDevolver.findViewById(R.id.irInicio);
        irInicio.setOnClickListener(this);

        ActividadPrincipal AdministradorCentral = (ActividadPrincipal) getActivity();

        //Agarrar de la base de datos lista con todos los usuarios
        AdministradorCentral.CargarListaUsuariosManualParaProbarListView(); //Aca va CargarListaUsuarios()

        //Poner la lista en mi ListView
        listaUsuarios = (ListView) vistaDevolver.findViewById(R.id.listaUsuarios);

        AdaptadorParaUsuarios adaptador = new AdaptadorParaUsuarios(AdministradorCentral.listaUsuarios, AdministradorCentral.getApplicationContext());

        listaUsuarios.setAdapter(adaptador);

        return vistaDevolver;
    }

    public void onClick (View vista) {
        ActividadPrincipal AdministradorCentral = (ActividadPrincipal) getActivity();

        switch (vista.getId()){
            case (R.id.irInicio):
                AdministradorCentral.irInicio();
                break;
        }
    }
}
