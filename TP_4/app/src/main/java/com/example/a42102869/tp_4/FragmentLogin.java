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

public class FragmentLogin extends Fragment implements View.OnClickListener {

    Button btnEntrar;
    Button btnCrearNuevoUsuario;

    EditText usuarioIngresado;
    EditText contraseñaIngresado;

    public View onCreateView (LayoutInflater Inflador, ViewGroup GrupoVista, Bundle Datos){

        View vistaDevolver = Inflador.inflate(R.layout.layout_login, GrupoVista, false);

        btnEntrar = (Button)vistaDevolver.findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(this);

        btnCrearNuevoUsuario = (Button)vistaDevolver.findViewById(R.id.btnCrearUsuario);
        btnCrearNuevoUsuario.setOnClickListener(this);

        usuarioIngresado = (EditText)vistaDevolver.findViewById(R.id.usuarioRegistracion);
        contraseñaIngresado = (EditText)vistaDevolver.findViewById(R.id.contraseñaRegistracion);

        return vistaDevolver;
    }

    public void onClick (View vista) {
        ActividadPrincipal AdministradorCentral = (ActividadPrincipal) getActivity();

        switch (vista.getId()){
            case (R.id.btnEntrar):
                String usuario = usuarioIngresado.getText().toString().trim();
                String contraseña = contraseñaIngresado.getText().toString().trim();
                AdministradorCentral.ChequearLogin(usuario, contraseña);
                break;
            case (R.id.btnCrearUsuario):
                AdministradorCentral.irCrearNuevoUsuario();
                break;
        }
    }

}