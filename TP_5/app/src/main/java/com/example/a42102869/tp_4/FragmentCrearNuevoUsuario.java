package com.example.a42102869.tp_4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class FragmentCrearNuevoUsuario extends Fragment implements View.OnClickListener {

    Button btnLoguear;

    EditText usuarioIngresado;
    EditText contraseñaIngresado;
    EditText contraseñaIngresado2;
    EditText edadIngresado;
    CheckBox empleadoIngresado;

    public View onCreateView (LayoutInflater Inflador, ViewGroup GrupoVista, Bundle Datos){

        View vistaDevolver = Inflador.inflate(R.layout.layout_crear_nuevo_usuario, GrupoVista, false);

        btnLoguear = (Button)vistaDevolver.findViewById(R.id.btnLoguearUsuario);
        btnLoguear.setOnClickListener(this);

        usuarioIngresado = (EditText)vistaDevolver.findViewById(R.id.usuarioParaRegistracion);
        contraseñaIngresado = (EditText)vistaDevolver.findViewById(R.id.contraseñaParaRegistracion);
        contraseñaIngresado2 = (EditText)vistaDevolver.findViewById(R.id.contraseñaParaRegistracion2);
        edadIngresado = (EditText)vistaDevolver.findViewById(R.id.edadParaRegistracion);
        empleadoIngresado = (CheckBox) vistaDevolver.findViewById(R.id.empleadoParaRegistracion);

        return vistaDevolver;
    }

    public void onClick (View vista) {

        ActividadPrincipal AdministradorCentral = (ActividadPrincipal) getActivity();

        String usuario = usuarioIngresado.getText().toString().trim();
        String contraseña = contraseñaIngresado.getText().toString().trim();
        String contraseña2 = contraseñaIngresado2.getText().toString().trim();
        Integer edad;
        if(edadIngresado.getText().toString().trim().compareTo("")==0){
            edad = 0;
        }
        else {
            edad = Integer.parseInt(edadIngresado.getText().toString().trim());
        }
        Integer empleado;
        if(empleadoIngresado.isPressed()){
            empleado = 1;
        }
        else{
            empleado = 0;
        }

        AdministradorCentral.ChequearUsuarioContraseña(usuario, contraseña, contraseña2, edad, empleado);
    }

}