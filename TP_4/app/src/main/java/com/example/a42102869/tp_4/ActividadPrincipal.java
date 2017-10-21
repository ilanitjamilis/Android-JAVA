package com.example.a42102869.tp_4;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ActividadPrincipal extends AppCompatActivity {

    FragmentManager AdministradorFragments;
    FragmentTransaction TransaccionesFragments;

    public boolean miBaseDeDatosAbierta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        Log.d("ila","01");

        AdministradorFragments = getSupportFragmentManager();
        Fragment frgBienvenida = new FragmentBienvenida();

        TransaccionesFragments = AdministradorFragments.beginTransaction();
        TransaccionesFragments.replace(R.id.AlojadorFragment, frgBienvenida);
        TransaccionesFragments.commit();

        Log.d("ila","02");
    }

    public void irInicio(){
        AdministradorFragments = getSupportFragmentManager();
        Fragment frgBienvenida = new FragmentBienvenida();

        TransaccionesFragments = AdministradorFragments.beginTransaction();
        TransaccionesFragments.replace(R.id.AlojadorFragment, frgBienvenida);
        TransaccionesFragments.commit();
    }

    public void irLogin (){
        AdministradorFragments = getSupportFragmentManager();
        Fragment frgLogin = new FragmentLogin();

        TransaccionesFragments = AdministradorFragments.beginTransaction();
        TransaccionesFragments.replace(R.id.AlojadorFragment, frgLogin);
        TransaccionesFragments.commit();
    }

    public void ChequearLogin(String usuario, String contraseña){
        boolean usuarioExiste = false;
        //Chequear login con base de datos
        //Si existe ir a pantalla con lista de todos los usuarios que hay registrados

        miBaseDeDatosAbierta = utilidades.baseDeDatosAbierta(this);
        if(miBaseDeDatosAbierta) {
            Cursor registros;
            registros = utilidades.baseDatos.rawQuery("select user, password from usuarios", null);
            if (registros.moveToFirst() == true) {
                do {
                    String userUsuario = registros.getString(0);
                    String passwordUsuario = registros.getString(1);

                    if (usuario.compareTo(userUsuario) == 0 && contraseña.compareTo(passwordUsuario) == 0) {
                        Usuario miUsuario = new Usuario();
                        miUsuario.usuario = userUsuario;
                        miUsuario.contraseña = passwordUsuario;
                        usuarioExiste = true;
                        utilidades.miUsuario = miUsuario;
                    }

                } while (registros.moveToNext() == true);
                utilidades.baseDatos.close();
            }
        }
        if(usuarioExiste){
            AdministradorFragments = getSupportFragmentManager();
            Fragment frgUsuarios = new FragmentUsuarios();

            TransaccionesFragments = AdministradorFragments.beginTransaction();
            TransaccionesFragments.replace(R.id.AlojadorFragment, frgUsuarios);
            TransaccionesFragments.commit();
        }
        else{
            MostrarMensaje("Usuario o contraseña incorrecto");
        }
    }

    public void irCrearNuevoUsuario(){
        AdministradorFragments = getSupportFragmentManager();
        Fragment frgNuevoUsuario = new FragmentCrearNuevoUsuario();

        TransaccionesFragments = AdministradorFragments.beginTransaction();
        TransaccionesFragments.replace(R.id.AlojadorFragment, frgNuevoUsuario);
        TransaccionesFragments.commit();
    }

    public void CrearNuevoUsuario(String usuario, String contraseña){
        //Verificar que el usuario no exista
        Boolean usuarioExiste = false;
        usuarioExiste = UsuarioExiste(usuario);
        if(usuarioExiste){
            MostrarMensaje("Usuario ya existe. Por favor elija otro.");
        }
        else{
            //Registrar usuario en base de datos
            AgregarUsuarioEnBaseDeDatos(usuario, contraseña);

            MostrarMensaje("Registro exitoso");

            //Ir a pantalla de login
            AdministradorFragments = getSupportFragmentManager();
            Fragment frgLogin = new FragmentLogin();
            TransaccionesFragments = AdministradorFragments.beginTransaction();
            TransaccionesFragments.replace(R.id.AlojadorFragment, frgLogin);
            TransaccionesFragments.commit();
        }
    }

    public Boolean UsuarioExiste (String usuario) {
        boolean usuarioExiste = false;
        miBaseDeDatosAbierta = utilidades.baseDeDatosAbierta(this);
        if (miBaseDeDatosAbierta) {
            Cursor registros;
            registros = utilidades.baseDatos.rawQuery("select user from usuarios", null);
            if (registros.moveToFirst() == true) {
                do {
                    String userUsuario = registros.getString(0);

                    if (usuario.compareTo(userUsuario) == 0) {
                        usuarioExiste = true;
                    }

                } while (registros.moveToNext() == true);
                utilidades.baseDatos.close();
            }
        }
        return usuarioExiste;
    }

    public void AgregarUsuarioEnBaseDeDatos(String usuario, String contraseña){
        miBaseDeDatosAbierta = utilidades.baseDeDatosAbierta(this);
        if(miBaseDeDatosAbierta){
            ContentValues nuevoRegistro;
            nuevoRegistro = new ContentValues();
            nuevoRegistro.put("user", usuario);
            nuevoRegistro.put("password", contraseña);
            utilidades.baseDatos.insert("usuarios",null,nuevoRegistro);
            utilidades.baseDatos.close();
        }
    }

    public ArrayList<String> TraerUsuariosNombre() {

        ArrayList<String> miListaUsuarios = new ArrayList<String>();

        miBaseDeDatosAbierta = utilidades.baseDeDatosAbierta(this);
        if (miBaseDeDatosAbierta) {
            Cursor registros;
            registros = utilidades.baseDatos.rawQuery("select user, ultimaConexion from usuarios", null);
            if (registros.moveToFirst() == true) {
                do {
                    String userUsuario = registros.getString(0);
                    Log.d("ila", "usuario: "+userUsuario);
                    miListaUsuarios.add(userUsuario);

                } while (registros.moveToNext() == true);
                utilidades.baseDatos.close();
            }
        }
        return miListaUsuarios;
    }

    public ArrayList<String> TraerUsuariosConexion() {

        ArrayList<String> miListaUsuarios = new ArrayList<String>();

        miBaseDeDatosAbierta = utilidades.baseDeDatosAbierta(this);
        if (miBaseDeDatosAbierta) {
            Cursor registros;
            registros = utilidades.baseDatos.rawQuery("select user, ultimaConexion from usuarios", null);
            if (registros.moveToFirst() == true) {
                do {
                    String ultimaConexion = registros.getString(1);
                    Log.d("ila", "ultimaConexion: "+ultimaConexion);
                    miListaUsuarios.add(ultimaConexion);

                } while (registros.moveToNext() == true);
                utilidades.baseDatos.close();
            }
        }
        return miListaUsuarios;
    }

    public void ChequearUsuarioContraseña (String usuario, String contraseña, String contraseña2){
        String error = "";
        Boolean usuarioValido = true;
        Boolean contraseñaValida = true;

        if(usuario.compareTo("")==0){
            usuarioValido = false;
            error = "Ingrese usuario";
            if(error.compareTo("")!=0){
                MostrarMensaje(error);
            }
        }

        if(contraseña.compareTo("")==0){
            contraseñaValida = false;
            error = "Ingrese contraseña";
            if(error.compareTo("")!=0){
                MostrarMensaje(error);
            }
        }
        else if(contraseña.compareTo("")==0 || contraseña.compareTo(contraseña2)!=0){
            contraseñaValida = false;
            error = "Las contraseñas no son iguales";
            if(error.compareTo("")!=0){
                MostrarMensaje(error);
            }
        }
        else {

            boolean contraseñaTieneMayuscula = !contraseña.equals(contraseña.toLowerCase());
            boolean contraseñaTieneMinuscula = !contraseña.equals(contraseña.toUpperCase());
            boolean contraseñaTieneMas8Caracteres = true;
            boolean contraseñaTieneNumero = false;

            if (contraseña.length() < 8) {
                contraseñaTieneMas8Caracteres = false;
                error = "La contraseña debe poseer mínimo 8 caracteres";
                if(error.compareTo("")!=0){
                    MostrarMensaje(error);
                }
                Log.d("ila","menor 8 caracteres");
            }

            if(contraseña.matches(".*\\d.*")){
                contraseñaTieneNumero = true;
            }

            if (contraseñaTieneMayuscula == false || contraseñaTieneMinuscula == false || contraseñaTieneMas8Caracteres == false || contraseñaTieneNumero == false) {
                contraseñaValida = false;
                error = "La contraseña debe poseer mínimamente 1 número, 1 mayúscula, 1 minúscula";
                if(error.compareTo("")!=0){
                    MostrarMensaje(error);
                }
                Log.d("ila","tiene mayuscula: "+contraseñaTieneMayuscula);
                Log.d("ila","tiene minuscula: "+contraseñaTieneMinuscula);
                Log.d("ila","mas 8 caracteres: "+contraseñaTieneMas8Caracteres);
                Log.d("ila","tiene numero: "+contraseñaTieneNumero);
            }
        }

        if(usuarioValido && contraseñaValida) {
            CrearNuevoUsuario(usuario, contraseña);
        }
        else{
            if(usuarioValido == false) {
                MostrarMensaje("Usuario inválido");
            }
            else{
                MostrarMensaje("Contraseña inválida");
            }
        }
    }

    public void MostrarMensaje(String mensaje){
        Toast alerta = Toast.makeText(this,mensaje,Toast.LENGTH_SHORT);
        alerta.show();
    }

    public String FechaActual(){
        DateFormat df = new SimpleDateFormat("dd MM yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        return date;
    }

    public void ActualizarFechaConexion(){
        boolean miBaseDeDatosAbierta = utilidades.baseDeDatosAbierta(this);
        if(miBaseDeDatosAbierta){
            String fechaActual = FechaActual();
            utilidades.baseDatos.execSQL("UPDATE usuarios SET ultimaConexion='" + fechaActual + "' WHERE user='"+utilidades.miUsuario.usuario+"'");
        }
        utilidades.baseDatos.close();
    }

}
