package com.example.a42102869.tp_4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 42102869 on 8/6/2017.
 */

public class FragmentCrearNuevoUsuario extends Fragment implements View.OnClickListener {

    Button btnLoguear;

    EditText usuarioIngresado;
    EditText contraseñaIngresado;
    EditText contraseñaIngresado2;

    public View onCreateView (LayoutInflater Inflador, ViewGroup GrupoVista, Bundle Datos){

        View vistaDevolver = Inflador.inflate(R.layout.layout_crear_nuevo_usuario, GrupoVista, false);

        btnLoguear = (Button)vistaDevolver.findViewById(R.id.btnLoguearUsuario);
        btnLoguear.setOnClickListener(this);

        usuarioIngresado = (EditText)vistaDevolver.findViewById(R.id.usuarioParaRegistracion);
        contraseñaIngresado = (EditText)vistaDevolver.findViewById(R.id.contraseñaParaRegistracion);
        contraseñaIngresado2 = (EditText)vistaDevolver.findViewById(R.id.contraseñaParaRegistracion2);

        return vistaDevolver;
    }

    public void onClick (View vista) {

        ActividadPrincipal AdministradorCentral = (ActividadPrincipal) getActivity();

        String usuario = usuarioIngresado.getText().toString().trim();
        String contraseña = contraseñaIngresado.getText().toString().trim();
        String contraseña2 = contraseñaIngresado2.getText().toString().trim();

        AdministradorCentral.ChequearUsuarioContraseña(usuario, contraseña, contraseña2);
    }

}