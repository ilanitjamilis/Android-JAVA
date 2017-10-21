package anahisaccone.notass;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    public EditText Usuario;
    public EditText Contraseña;
    public Button Ingresar;
    public String UsuarioCorrecto="Notas";
    public String ContraseñaCorrecta="1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Usuario=(EditText)findViewById(R.id.Usuario);
        Contraseña=(EditText)findViewById(R.id.Contraseña);
        Ingresar=(Button)findViewById(R.id.Ingresar);

        Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Usuario.getText().toString().equals(UsuarioCorrecto)&& Contraseña.getText().toString().equals(ContraseñaCorrecta))
                {
                    Intent Ingresar =new Intent(Login.this,Notas.class);
                    startActivity(Ingresar);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Contraseña Incorrecta",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
