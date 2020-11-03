package com.example.tp6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nom = (EditText) findViewById(R.id.editTextTextPersonName);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SecondActivity.class);
                intent.putExtra("nom", nom.getText().toString());
                startActivity(intent);
            }
        });

        GithubService githubService = new Retrofit.Builder()
                .baseUrl(GithubService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService.class);

        githubService.listRepos("adrienbusin").enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                afficherRepos(response.body());
            }
            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });

    }
    public void afficherRepos(List<Repo> repos) {
        Toast.makeText(this,"nombre de dépots : "+repos.size(), Toast.LENGTH_SHORT).show();
    }

}