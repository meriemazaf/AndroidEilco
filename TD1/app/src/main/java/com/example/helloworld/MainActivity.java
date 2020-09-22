package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "hello";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // String text = "Bonjour Adrien";
       // Log.i(TAG,text);
        int v = 54;
        int n = v / 9 - 2;
        Log.i(TAG, "n = "+n);
        int f = factorielle(n);
        Log.i(TAG, n+"! = "+f);


        
    }
    private int factorielle(int n){
        int r;
        if (n > 1) {
            int fnm1 = factorielle(n-1);
            r = n * fnm1;
        } else {
            r = 1;
        }
        return r;
    }
}