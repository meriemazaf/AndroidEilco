package com.example.td4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        setTitle(getLocalClassName());

        // récupérer le contexte d'application et la donnée qu'elle contient
        NewsListApplication app = (NewsListApplication) getApplicationContext();
        String login1 = app.getLogin();

        Button details = (Button)findViewById(R.id.details);
        Button logout = (Button)findViewById(R.id.logout);
        Button about = (Button)findViewById(R.id.about);
        final TextView txt = (TextView)findViewById(R.id.txt);

        Intent intent = getIntent();
        String login = "";
        if (intent.hasExtra("login")) {
            login = intent.getStringExtra("login");
            txt.setText("Bonjour "+login);
        }
        final String finalLogin = login;
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),DetailsActivity.class);
                intent.putExtra("login", finalLogin);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://news.google.com/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}