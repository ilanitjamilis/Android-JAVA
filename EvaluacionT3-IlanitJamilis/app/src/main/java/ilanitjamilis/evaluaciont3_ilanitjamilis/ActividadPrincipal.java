package ilanitjamilis.evaluaciont3_ilanitjamilis;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import org.cocos2d.opengl.CCGLSurfaceView;

public class ActividadPrincipal extends Activity {

    CCGLSurfaceView vistaPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.actividad_principal);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        vistaPrincipal = new CCGLSurfaceView(this);
        setContentView(vistaPrincipal);
    }

    @Override
    protected void onStart() {
        super.onStart();
        clsJuego miJuego = new clsJuego(vistaPrincipal);
        miJuego.comenzarJuego();
    }
}
