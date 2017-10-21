package com.example.ilanitjamilis.evaluacion2trimestre_ilanitjamilis;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Fragment_2 extends android.support.v4.app.Fragment {

    TextView TV;

    public View onCreateView (LayoutInflater Inflador, ViewGroup GrupoVista, Bundle Datos){

        View vistaDevolver = Inflador.inflate(R.layout.fragment_2_vista, GrupoVista, false);

        ActividadCambios AdministradorCentral = (ActividadCambios) getActivity();

        TV = (TextView)vistaDevolver.findViewById(R.id.textoFragment2);
        TV.setText(AdministradorCentral.textoMasLargo.toUpperCase());

        return vistaDevolver;
    }
}
