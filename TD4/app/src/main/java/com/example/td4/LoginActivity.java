package com.example.td4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(getLocalClassName());

        // récupérer le contexte d'application et la donnée qu'elle contient
        NewsListApplication app = (NewsListApplication) getApplicationContext();
        String login1 = app.getLogin();

        final EditText login = (EditText) findViewById(R.id.login);
        EditText password = (EditText) findViewById(R.id.password);

        login1=login.getText().toString();
        app.setLogin(login1);

        Button btnlogin = (Button)findViewById(R.id.btnLogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NewsActivity.class);
                intent.putExtra("login", login.getText().toString());
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}