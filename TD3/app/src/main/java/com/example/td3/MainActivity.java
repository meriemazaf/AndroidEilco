package com.example.td3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button rollButton = (Button) findViewById(R.id.btn);
        final EditText txt = (EditText) findViewById(R.id.txt);
        final TextView txt1 = (TextView) findViewById(R.id.txt1);
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast toast = Toast.makeText(MainActivity.this, "Dé lancé!", Toast.LENGTH_SHORT);
                //toast.show();
                //int i = getRandomNumber(1,6);
                int j= getRandomNumber(1,new Integer(txt.getText().toString()));
                //txt.setText(String.valueOf(i));
                txt1.setText(String.valueOf(j));

            }
        });

    }
    public int getRandomNumber(int min,int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }
}