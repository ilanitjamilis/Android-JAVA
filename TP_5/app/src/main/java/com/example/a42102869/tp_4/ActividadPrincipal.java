package com.example.a42102869.tp_4;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ActividadPrincipal extends AppCompatActivity {

    FragmentManager AdministradorFragments;
    FragmentTransaction TransaccionesFragments;
    Boolean usuarioExiste;
    String usuarioIngresado;
    String contrasenaIngresado;
    String usuarioInsertar;
    String contraseñaInsertar;
    Integer edadInsertar;
    Integer empleadoInsertar;
    boolean funciono;
    Boolean salio;
    ArrayList<Usuario> listaUsuarios;
    Connection conexion;

    String rutaServidorMySQL, nombreBaseDeDatos, nombreUsuario, contrasenaUsuario, cadenaCompletaConexion;
    int puertoServidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        rutaServidorMySQL = "localhost";
        puertoServidor = 3306;
        nombreBaseDeDatos = "db";
        nombreUsuario = "root";
        contrasenaUsuario = "root";
        cadenaCompletaConexion = "jdbc:mysql://"+rutaServidorMySQL+":"+puertoServidor+"/"+nombreBaseDeDatos;

        //Descomentar lo comentado y comentar las lineas anteiores a cada una para probar el uso del ListView

        AdministradorFragments = getSupportFragmentManager();
        Fragment frgBienvenida = new FragmentBienvenida();
        //Fragment frgUsuarios = new FragmentUsuarios();

        TransaccionesFragments = AdministradorFragments.beginTransaction();
        TransaccionesFragments.replace(R.id.AlojadorFragment, frgBienvenida);
        //TransaccionesFragments.replace(R.id.AlojadorFragment, frgUsuarios);
        TransaccionesFragments.commit();
    }

    public boolean conexion (){
        boolean conexionAbierta = false;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            conexion = DriverManager.getConnection(cadenaCompletaConexion, nombreUsuario, contrasenaUsuario);

            Log.d("Principal","Conexion todo ok");
            conexionAbierta = true;
        }
        catch (ClassNotFoundException error){
            Log.d("Principal","Error: "+error.getMessage());
            conexionAbierta = false;
        }
        catch(SQLException error){
            Log.d("Principal","Error: "+error.getMessage());
            conexionAbierta = false;
        }

        return conexionAbierta;
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
        salio = false;
        usuarioExiste = false;
        usuarioIngresado = usuario;
        contrasenaIngresado = contraseña;
        //Chequear login con db
        //Si existe ir a pantalla con lista de todos los usuarios que hay registrados
        HiloMYSQLChequearLogin.start();
        while(salio==false) {
            //Espera a que haya terminado de chequear el login
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

    Thread HiloMYSQLChequearLogin = new Thread(){
        @Override
        public void run(){
            try {
                boolean conexionAbierta = conexion();
                if(conexionAbierta) {
                    Statement instruccion = conexion.createStatement();
                    String consulta = "select usuario, contrasena from usuarios";
                    ResultSet resultados = instruccion.executeQuery(consulta);
                    if (resultados.first()) {
                        do {
                            String userUsuario = resultados.getString(0);
                            String passwordUsuario = resultados.getString(1);
                            if (usuarioIngresado.compareTo(userUsuario) == 0 && contrasenaIngresado.compareTo(passwordUsuario) == 0) {
                                usuarioExiste = true;
                            }
                        } while (resultados.next());
                    }
                }
            }
            catch(SQLException error){
                Log.d("Principal","Error: "+error.getMessage());
            }
            salio = true;
        }
    };

    public void irCrearNuevoUsuario(){
        AdministradorFragments = getSupportFragmentManager();
        Fragment frgNuevoUsuario = new FragmentCrearNuevoUsuario();

        TransaccionesFragments = AdministradorFragments.beginTransaction();
        TransaccionesFragments.replace(R.id.AlojadorFragment, frgNuevoUsuario);
        TransaccionesFragments.commit();
    }

    public void CrearNuevoUsuario(String usuario, String contraseña, Integer edad, Integer empleado){
        //Verificar que el usuario no exista
        usuarioExiste = false;
        salio = false;
        HiloMYSQLChequearUsuarioExiste.start();
        while(salio==false){

        }
        if(usuarioExiste){
            MostrarMensaje("Usuario ya existe. Por favor elija otro.");
        }
        else{
            //Registrar usuario en base de datos
            AgregarUsuarioEnBaseDeDatos(usuario, contraseña, edad, empleado);
            if(funciono) {
                MostrarMensaje("Registro exitoso");

                //Ir a pantalla de login
                AdministradorFragments = getSupportFragmentManager();
                Fragment frgLogin = new FragmentLogin();
                TransaccionesFragments = AdministradorFragments.beginTransaction();
                TransaccionesFragments.replace(R.id.AlojadorFragment, frgLogin);
                TransaccionesFragments.commit();
            }
            else{
                MostrarMensaje("Hubo un error, intente nuevamente");
            }
        }
    }

    Thread HiloMYSQLChequearUsuarioExiste = new Thread(){
        @Override
        public void run(){
            try {
                boolean conexionAbierta = conexion();
                if(conexionAbierta) {
                    Statement instruccion = conexion.createStatement();
                    String consulta = "select usuario from usuarios";
                    ResultSet resultados = instruccion.executeQuery(consulta);
                    if (resultados.first()) {
                        while (resultados.next()) {
                            String userUsuario = resultados.getString(0);
                            if (usuarioIngresado.compareTo(userUsuario) == 0) {
                                usuarioExiste = true;
                            }
                        }
                    } else {
                        Log.d("Principal", "No hay resultados");
                    }
                }
            }
            catch(SQLException error){
                Log.d("Principal","Error: "+error.getMessage());
            }
            salio = true;
        }
    };

    public void AgregarUsuarioEnBaseDeDatos(String usuario, String contraseña, Integer edad, Integer empleado){
        usuarioInsertar = usuario;
        contraseñaInsertar = contraseña;
        edadInsertar = edad;
        empleadoInsertar = empleado;
        salio = false;
        funciono = false;

        HiloMYSQLAgregarUsuario.start();

        while(salio==false){
            //Espera a que termine de ejecutarse el hilo
        }
    }

    Thread HiloMYSQLAgregarUsuario = new Thread(){
        @Override
        public void run(){
            funciono = false;
            try {
                boolean conexionAbierta = conexion();
                if(conexionAbierta) {

                    Log.d("ila", "llega conexion");

                    Statement instruccion = conexion.createStatement();

                    Log.d("ila", "usuarioInsertar: " + usuarioInsertar);
                    Log.d("ila", "contraseñaInsertar: " + contraseñaInsertar);
                    Log.d("ila", "edadInsertar: " + edadInsertar);
                    Log.d("ila", "empleadoInsertar: " + empleadoInsertar);


                    String consulta = "INSERT INTO `usuarios`(`usuario`, `contrasena`, `edad`, `empleado`) VALUES (" + usuarioInsertar + "," + contraseñaInsertar + "," + edadInsertar + "," + empleadoInsertar + ")";
                    Log.d("ila", "insertar hecho");
                    ResultSet resultados = instruccion.executeQuery(consulta);
                    funciono = true;
                }
            }
            catch(SQLException error){
                Log.d("Principal","Error: "+error.getMessage());
            }
            salio = true;
        }
    };

    public void CargarListaUsuariosManualParaProbarListView(){
        listaUsuarios = new ArrayList<Usuario>();
        Usuario unUsuario = new Usuario();
        unUsuario.usuario = "Ilanit";
        unUsuario.contraseña = "Hola1234";
        unUsuario.edad = 22;
        unUsuario.empleado = true;
        listaUsuarios.add(unUsuario);

        unUsuario = new Usuario();
        unUsuario.usuario = "Guillermo";
        unUsuario.contraseña = "Hola1234";
        unUsuario.edad = 52;
        unUsuario.empleado = false;
        listaUsuarios.add(unUsuario);

        unUsuario = new Usuario();
        unUsuario.usuario = "Leo";
        unUsuario.contraseña = "Hola1234";
        unUsuario.edad = 45;
        unUsuario.empleado = true;
        listaUsuarios.add(unUsuario);

        unUsuario = new Usuario();
        unUsuario.usuario = "Pepe";
        unUsuario.contraseña = "Hola1234";
        unUsuario.edad = 87;
        unUsuario.empleado = false;
        listaUsuarios.add(unUsuario);

        unUsuario = new Usuario();
        unUsuario.usuario = "Martin";
        unUsuario.contraseña = "Hola1234";
        unUsuario.edad = 23;
        unUsuario.empleado = true;
        listaUsuarios.add(unUsuario);

        unUsuario = new Usuario();
        unUsuario.usuario = "Isabel";
        unUsuario.contraseña = "Hola1234";
        unUsuario.edad = 18;
        unUsuario.empleado = true;
        listaUsuarios.add(unUsuario);
    }

    public void CargarListaUsuarios(){
        listaUsuarios = new ArrayList<Usuario>();
        HiloMYSQLTraerUsuarios.start();
    }

    Thread HiloMYSQLTraerUsuarios = new Thread(){
        @Override
        public void run(){
            try {
                boolean conexionAbierta = conexion();
                if(conexionAbierta) {
                    Statement instruccion = conexion.createStatement();
                    String consulta = "SELECT * FROM `usuarios`";
                    ResultSet resultados = instruccion.executeQuery(consulta);
                    Usuario unUsuario = new Usuario();
                    if (resultados.first()) {
                        do {
                            unUsuario.usuario = resultados.getString(0);
                            unUsuario.edad = resultados.getInt(2);
                            if (resultados.getInt(3) == 1) {
                                unUsuario.empleado = true;
                            } else {
                                unUsuario.empleado = false;
                            }
                            listaUsuarios.add(unUsuario);
                        } while (resultados.next() == true);
                    }
                }
            }
            catch(SQLException error){
                Log.d("Principal","Error: "+error.getMessage());
            }
        }
    };

    public void ChequearUsuarioContraseña (String usuario, String contraseña, String contraseña2, Integer edad, Integer empleado){
        String error = "";
        Boolean usuarioValido = true;
        Boolean contraseñaValida = true;
        Boolean edadValida = true;

        if(usuario.compareTo("")==0){
            usuarioValido = false;
            error = "Ingrese usuario";
            if(error.compareTo("")!=0){
                MostrarMensaje(error);
            }
        }
        else if(contraseña.compareTo("")==0){
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
        else if(edad<18 || edad>100){
            edadValida = false;
            error = "Edad inválida";
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
            }
        }

        if(usuarioValido && contraseñaValida && edadValida) {
            CrearNuevoUsuario(usuario, contraseña, edad, empleado);
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
}
