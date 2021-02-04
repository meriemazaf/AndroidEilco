package fr.tpeilco.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class acceuilActivity extends AppCompatActivity {
    Thread timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        timer = new Thread() {
            @Override
            public void run(){
                try {
                    synchronized (this) {
                        wait(9000);
                    }
                }catch(InterruptedException e){
                        e.printStackTrace();
                    }finally{
                        Intent intent = new Intent(acceuilActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            };
        timer.start();
        }

}