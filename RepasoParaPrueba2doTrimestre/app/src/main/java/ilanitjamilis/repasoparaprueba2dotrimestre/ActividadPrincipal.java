package ilanitjamilis.repasoparaprueba2dotrimestre;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ActividadPrincipal extends AppCompatActivity {

    FragmentManager AdministradorFragments;
    FragmentTransaction TransaccionesFragments;
    ArrayList<String> miLista;

    public boolean miBaseDeDatosAbierta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);

        irPrincipal();
    }

    public void irPrincipal(){
        AdministradorFragments = getSupportFragmentManager();
        Fragment frgVistaPrincipal = new ActividadVistaPrincipal();

        TransaccionesFragments = AdministradorFragments.beginTransaction();
        TransaccionesFragments.replace(R.id.AlojadorFragment, frgVistaPrincipal);
        TransaccionesFragments.commit();
    }

    public void IngresarTexto(String miTexto){
        miBaseDeDatosAbierta = utilidades.baseDeDatosAbierta(this);
        if(miBaseDeDatosAbierta){
            ContentValues nuevoRegistro;
            nuevoRegistro = new ContentValues();
            nuevoRegistro.put("texto", miTexto);
            utilidades.baseDatos.insert("textos",null,nuevoRegistro);
            utilidades.baseDatos.close();
        }
    }

    public boolean BuscarTexto(String miTexto){
        boolean existeTexto = VerificarExistenciaTexto(miTexto);
        return existeTexto;
    }

    public boolean VerificarExistenciaTexto(String texto){
        boolean existe = false;
        //Buscar si existe
        miBaseDeDatosAbierta = utilidades.baseDeDatosAbierta(this);
        if (miBaseDeDatosAbierta) {
            Cursor registros;
            registros = utilidades.baseDatos.rawQuery("select texto from textos", null);
            if (registros.moveToFirst() == true) {
                do {
                    String registroTexto = registros.getString(0);

                    if (registroTexto.compareTo(texto) == 0) {
                        existe = true;
                    }

                } while (registros.moveToNext() == true || !existe);
                utilidades.baseDatos.close();
            }
        }
        return existe;
    }

    public boolean BorrarTexto(String miTexto){
        boolean existeTexto = VerificarExistenciaTexto(miTexto);
        if(existeTexto) {
            //Eliminar registro de la tabla
            miBaseDeDatosAbierta = utilidades.baseDeDatosAbierta(this);
            if (miBaseDeDatosAbierta) {
                utilidades.baseDatos.delete("textos", "texto='" + miTexto + "'", null);
                utilidades.baseDatos.close();
            }
        }
        return existeTexto;
    }

    public void irListado(){
        AdministradorFragments = getSupportFragmentManager();
        Fragment frgLista = new ActivdadVistaListado();

        TransaccionesFragments = AdministradorFragments.beginTransaction();
        TransaccionesFragments.replace(R.id.AlojadorFragment, frgLista);
        TransaccionesFragments.commit();
    }

    public void LlenarListaTextos(){
        miLista = new ArrayList<>();
        //Buscar si existe
        miBaseDeDatosAbierta = utilidades.baseDeDatosAbierta(this);
        if (miBaseDeDatosAbierta) {
            Cursor registros;
            registros = utilidades.baseDatos.rawQuery("select texto from textos", null);
            if (registros.moveToFirst() == true) {
                do {
                    String registroTexto = registros.getString(0);
                    miLista.add(registroTexto);

                } while (registros.moveToNext() == true);
                utilidades.baseDatos.close();
            }
        }
    }

    public void MostrarMensaje(String mensaje){
        Toast alerta = Toast.makeText(this,mensaje,Toast.LENGTH_SHORT);
        alerta.show();
    }
}
