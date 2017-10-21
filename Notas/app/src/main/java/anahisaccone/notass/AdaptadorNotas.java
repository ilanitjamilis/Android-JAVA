package anahisaccone.notass;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import anahisaccone.notass.Anotaciones;

public class AdaptadorNotas extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private Anotaciones[] mResources;

    public AdaptadorNotas(Context context, Anotaciones[] resources) {
        mContext = context;
        mResources = resources;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View miNotaVista = mLayoutInflater.inflate(R.layout.descripcion_notas, container, false);

        TextView descripcion = (TextView)miNotaVista.findViewById(R.id.anotacion);
        descripcion.setText(mResources[position].getAnotacion());

        TextView fecha = (TextView)miNotaVista.findViewById(R.id.fecha);
        fecha.setText(mResources[position].getFecha());

        CheckBox realizado = (CheckBox) miNotaVista.findViewById(R.id.realizado);
        if(mResources[position].getRealizado()==true){
            realizado.setChecked(true);
        }
        else{
            realizado.setChecked(false);
        }

        container.addView(miNotaVista);

        return miNotaVista;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

}