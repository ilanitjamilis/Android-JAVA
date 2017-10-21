package ilanitjamilis.repasoparaprueba2dotrimestre;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.app.Fragment;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Ilanit Jamilis on 15/8/2017.
 */

public class ActividadVistaPrincipal extends android.support.v4.app.Fragment implements View.OnClickListener {

    EditText textoIngresado;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    public View onCreateView (LayoutInflater Inflador, ViewGroup GrupoVista, Bundle Datos){

        View vistaDevolver = Inflador.inflate(R.layout.vista_principal, GrupoVista, false);

        textoIngresado = (EditText)vistaDevolver.findViewById(R.id.textoIngresado);

        btn1 = (Button)vistaDevolver.findViewById(R.id.btnIngresar);
        btn1.setOnClickListener(this);

        btn2 = (Button)vistaDevolver.findViewById(R.id.btnBuscar);
        btn2.setOnClickListener(this);

        btn3 = (Button)vistaDevolver.findViewById(R.id.btnBorrar);
        btn3.setOnClickListener(this);

        btn4 = (Button)vistaDevolver.findViewById(R.id.btnVerTodos);
        btn4.setOnClickListener(this);

        return vistaDevolver;
    }

    public void onClick (View vista) {
        ActividadPrincipal AdministradorCentral = (ActividadPrincipal) getActivity();

        String miTextoIngresado = textoIngresado.getText().toString().trim();

        switch (vista.getId()){

            case R.id.btnIngresar:
                if(miTextoIngresado.compareTo("")==0){
                    AdministradorCentral.MostrarMensaje("Ingrese texto");
                }else{
                    AdministradorCentral.IngresarTexto(miTextoIngresado);
                    AdministradorCentral.MostrarMensaje("Texto ingresado");
                    textoIngresado.setText("");
                }
                break;

            case R.id.btnBuscar:
                if(miTextoIngresado.compareTo("")==0){
                    AdministradorCentral.MostrarMensaje("Ingrese texto");
                }else{
                    boolean encontro = AdministradorCentral.BuscarTexto(miTextoIngresado);
                    if(encontro){
                        AdministradorCentral.MostrarMensaje("El texto ingresado existe");
                        textoIngresado.setText("");
                    }
                    else{
                        AdministradorCentral.MostrarMensaje("El texto ingresado no existe");
                    }
                }
                break;

            case R.id.btnBorrar:
                if(miTextoIngresado.compareTo("")==0){
                    AdministradorCentral.MostrarMensaje("Ingrese texto");
                }else{
                    boolean encontro = AdministradorCentral.BorrarTexto(miTextoIngresado);
                    if(encontro){
                        AdministradorCentral.MostrarMensaje("Borrado exitoso");
                        textoIngresado.setText("");
                    }
                    else{
                        AdministradorCentral.MostrarMensaje("El texto ingresado no existe");
                    }
                }
                break;

            case R.id.btnVerTodos:
                AdministradorCentral.irListado();
                break;
        }
    }
}
