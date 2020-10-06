package com.example.td4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setTitle(getLocalClassName());

        Button ok = (Button)findViewById(R.id.ok);
        NewsListApplication app = (NewsListApplication) getApplicationContext();
        String login = app.getLogin();

        TextView txt = (TextView)findViewById(R.id.txt1);
        txt.setText("Bonjour "+login);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),NewsActivity.class);
                startActivity(intent);
            }
        });
    }
}