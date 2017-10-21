package com.example.a42102869.tp_4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewGroupCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by 42102869 on 8/6/2017.
 */

public class FragmentBienvenida extends Fragment implements View.OnClickListener {

    Button btnIngresar;

    public View onCreateView (LayoutInflater Inflador, ViewGroup GrupoVista, Bundle Datos){

        View vistaDevolver = Inflador.inflate(R.layout.layout_bienvenida, GrupoVista, false);

        btnIngresar = (Button)vistaDevolver.findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(this);

        return vistaDevolver;
    }

    public void onClick (View vista){
        ActividadPrincipal AdministradorCentral = (ActividadPrincipal) getActivity();
        AdministradorCentral.irLogin();
    }

}
