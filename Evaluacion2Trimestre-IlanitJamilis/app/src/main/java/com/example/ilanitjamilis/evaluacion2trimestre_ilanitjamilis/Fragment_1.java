package com.example.ilanitjamilis.evaluacion2trimestre_ilanitjamilis;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Fragment_1 extends android.support.v4.app.Fragment implements View.OnClickListener {

    EditText ET1;
    EditText ET2;
    Button BTN;
    String texto1;
    String texto2;

    public View onCreateView (LayoutInflater Inflador, ViewGroup GrupoVista, Bundle Datos){

        View vistaDevolver = Inflador.inflate(R.layout.fragment_1_vista, GrupoVista, false);

        ET1 = (EditText)vistaDevolver.findViewById(R.id.miEditText1);
        ET2 = (EditText)vistaDevolver.findViewById(R.id.miEditText2);

        BTN = (Button)vistaDevolver.findViewById(R.id.btnAceptar);
        BTN.setOnClickListener(this);

        return vistaDevolver;
    }

    public void onClick (View vista) {

        texto1 = ET1.getText().toString().trim();
        texto2 = ET2.getText().toString().trim();

        ActividadCambios AdministradorCentral = (ActividadCambios) getActivity();

        if(texto1.compareTo("")!=0 && texto2.compareTo("")!=0){
            AdministradorCentral.CompararTextos(texto1, texto2);
        }
        else{
            AdministradorCentral.MostrarMensaje("Complete ambos campos!");
        }

    }
}
