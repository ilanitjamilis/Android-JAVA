package anahisaccone.notass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Empieza extends AppCompatActivity {

    public static final long Tiempo = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empieza);

        TimerTask nota = new TimerTask() {
            @Override
            public void run() {
                Intent Loing = new Intent(Empieza.this, Login.class);
                startActivity(Loing);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(nota, Tiempo);
    }
}
