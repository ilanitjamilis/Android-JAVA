package ilanitjamilis.repasoparaprueba2dotrimestre;

import android.app.admin.DeviceAdminInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Ilanit Jamilis on 15/8/2017.
 */

public class ActivdadVistaListado extends android.support.v4.app.Fragment implements View.OnClickListener  {

    ListView miListaTextos;
    Button btnVolver;

    public View onCreateView (LayoutInflater Inflador, ViewGroup GrupoVista, Bundle Datos){

        View vistaDevolver = Inflador.inflate(R.layout.vista_listado, GrupoVista, false);

        btnVolver = (Button)vistaDevolver.findViewById(R.id.btnVolverAtras);
        btnVolver.setOnClickListener(this);

        ActividadPrincipal AdministradorCentral = (ActividadPrincipal) getActivity();

        //Agarrar de la base de datos lista con todos los textos
        AdministradorCentral.LlenarListaTextos();

        //Poner la lista en mi ListView
        miListaTextos = (ListView) vistaDevolver.findViewById(R.id.listaTextos);

        AdaptadorParaTextos adaptador = new AdaptadorParaTextos(AdministradorCentral.miLista, AdministradorCentral.getApplicationContext());

        miListaTextos.setAdapter(adaptador);

        return vistaDevolver;
    }

    public void onClick (View vista) {
        ActividadPrincipal AdministradorCentral = (ActividadPrincipal) getActivity();
        AdministradorCentral.irPrincipal();
    }
}
