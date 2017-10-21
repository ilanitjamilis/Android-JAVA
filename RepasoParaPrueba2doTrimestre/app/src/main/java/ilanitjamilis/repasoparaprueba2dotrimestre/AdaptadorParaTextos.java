package ilanitjamilis.repasoparaprueba2dotrimestre;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ilanit Jamilis on 15/8/2017.
 */

public class AdaptadorParaTextos extends BaseAdapter {

    private ArrayList<String> _MiListaTextos;
    private Context _MiContexto;

    public AdaptadorParaTextos (ArrayList<String> listaTextos, Context contextoAUsar){
        _MiListaTextos = listaTextos;
        _MiContexto = contextoAUsar;
    }

    public int getCount(){
        return _MiListaTextos.size();
    }

    public String getItem(int posicionAObtener){
        String textoDevolver;
        textoDevolver = _MiListaTextos.get(posicionAObtener);
        return textoDevolver;
    }

    public long getItemId(int posicionAObtener){
        return posicionAObtener;
    }

    public View getView(int posicionActual, View vistaActual, ViewGroup grupoActual){
        View vistaDevolver;
        vistaDevolver = null;

        LayoutInflater infladorDeLayout;
        infladorDeLayout = (LayoutInflater)_MiContexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vistaDevolver = infladorDeLayout.inflate(R.layout.listview_textos_detalle_elemento, grupoActual, false);

        TextView TVtexto = (TextView)vistaDevolver.findViewById(R.id.texto);

        String textoPosicionActual = getItem(posicionActual);
        TVtexto.setText(textoPosicionActual);

        return vistaDevolver;
    }
}
