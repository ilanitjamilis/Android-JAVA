package anahisaccone.notass;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.security.KeyStore;

public class Notas extends AppCompatActivity {

    public Anotaciones[] Anotaciones = new Anotaciones[10];
    ViewPager miViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        for(int i=0; i<10; i++)
        {
            Anotaciones[i] = new Anotaciones();
        }

        Anotaciones[0].setAnotacion("Nota 1");
        Anotaciones[0].setFecha("sfasv");
        Anotaciones[0].setRealizado(false);

        Anotaciones[1].setAnotacion("Nota 2");
        Anotaciones[1].setFecha("17/6/17");
        Anotaciones[1].setRealizado(true);

        Anotaciones[2].setAnotacion("Nota 3");
        Anotaciones[2].setFecha("sfasv");
        Anotaciones[2].setRealizado(false);

        Anotaciones[3].setAnotacion("Nota 4");
        Anotaciones[3].setFecha("sfasv");
        Anotaciones[3].setRealizado(false);

        Anotaciones[4].setAnotacion("Nota 5");
        Anotaciones[4].setFecha("sfasv");
        Anotaciones[4].setRealizado(false);

        Anotaciones[5].setAnotacion("Nota 6");
        Anotaciones[5].setFecha("sfasv");
        Anotaciones[5].setRealizado(false);

        Anotaciones[6].setAnotacion("Nota 7");
        Anotaciones[6].setFecha("sfasv");
        Anotaciones[6].setRealizado(false);

        Anotaciones[7].setAnotacion("Nota 8");
        Anotaciones[7].setFecha("sfasv");
        Anotaciones[7].setRealizado(false);

        Anotaciones[8].setAnotacion("Nota 9");
        Anotaciones[8].setFecha("sfasv");
        Anotaciones[8].setRealizado(false);

        Anotaciones[9].setAnotacion("Nota 10");
        Anotaciones[9].setFecha("sfasv");
        Anotaciones[9].setRealizado(false);


        miViewPager = (ViewPager) findViewById(R.id.pager);
        AdaptadorNotas miAdaptador = new AdaptadorNotas(this, Anotaciones);
        miViewPager.setAdapter(miAdaptador);
    }

    public void notaSiguiente(View vista) {
        Log.d("ila", "entro a nota siguiente");
        int currentPage = miViewPager.getCurrentItem();
        Log.d("ila", "pagina actual: "+currentPage);
        int totalPages = miViewPager.getAdapter().getCount();
        Log.d("ila", "paginas totales: "+totalPages);

        int nextPage = currentPage+1;
        if (nextPage >= totalPages) {
            // We can't go forward anymore.
            // Loop to the first page. If you don't want looping just
            // return here.
            nextPage = 0;
        }

        Log.d("ila", "antes de cambiar de pagina");
        miViewPager.setCurrentItem(nextPage, true);
        Log.d("ila", "paso de pagina");
    }

    public void notaAnterior(View vista) {
        int currentPage = miViewPager.getCurrentItem();
        int totalPages = miViewPager.getAdapter().getCount();

        int previousPage = currentPage-1;
        if (previousPage < 0) {
            // We can't go back anymore.
            // Loop to the last page. If you don't want looping just
            // return here.
            previousPage = totalPages - 1;
        }

        miViewPager.setCurrentItem(previousPage, true);
    }
}