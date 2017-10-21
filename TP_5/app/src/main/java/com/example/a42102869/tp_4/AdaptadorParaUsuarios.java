package com.example.a42102869.tp_4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorParaUsuarios extends BaseAdapter {

    private ArrayList<Usuario> _MiListaUsuarios;
    private Context _MiContexto;

    public AdaptadorParaUsuarios (ArrayList<Usuario> listaUsuarios, Context contextoAUsar){
        _MiListaUsuarios = listaUsuarios;
        _MiContexto = contextoAUsar;
    }

    public int getCount(){
        return _MiListaUsuarios.size();
    }

    public Usuario getItem(int posicionAObtener){
        Usuario usuarioDevolver;
        usuarioDevolver = _MiListaUsuarios.get(posicionAObtener);
        return usuarioDevolver;
    }

    public long getItemId(int posicionAObtener){
        return posicionAObtener;
    }

    public View getView(int posicionActual, View vistaActual, ViewGroup grupoActual){
        View vistaDevolver;
        vistaDevolver = null;

        LayoutInflater infladorDeLayout;
        infladorDeLayout = (LayoutInflater)_MiContexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vistaDevolver = infladorDeLayout.inflate(R.layout.listview_usuarios_detalle_elemento, grupoActual, false);

        TextView txtUsuario = (TextView)vistaDevolver.findViewById(R.id.usuarioUsuario);
        TextView txtedad = (TextView)vistaDevolver.findViewById(R.id.usuarioEdad);
        CheckBox chkempleado = (CheckBox) vistaDevolver.findViewById(R.id.usuarioEmpleado);

        Usuario usuarioPosicionActual = getItem(posicionActual);
        txtUsuario.setText(usuarioPosicionActual.usuario);
        txtedad.setText(usuarioPosicionActual.edad.toString());
        if(usuarioPosicionActual.empleado){
            chkempleado.setChecked(true);
        }
        else{
            chkempleado.setChecked(false);
        }

        return vistaDevolver;
    }
}
