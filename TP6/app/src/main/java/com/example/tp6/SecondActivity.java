package com.example.tp6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondActivity extends AppCompatActivity {

    List<Repo> repos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        RecyclerView rvRepos = (RecyclerView) findViewById(R.id.rvRepos);

        SecondAdapter adapter = new SecondAdapter(repos);
        rvRepos.setAdapter(adapter);
        rvRepos.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String nom = "";
        if (intent.hasExtra("nom")) {
            nom = intent.getStringExtra("nom");
            GithubService githubService = new Retrofit.Builder()
                    .baseUrl(GithubService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(GithubService.class);

            githubService.searchRepos(nom).enqueue(new Callback<List<Repo>>() {
                @Override
                public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                    adapter.notifyDataSetChanged();
                }
                @Override
                public void onFailure(Call<List<Repo>> call, Throwable t) {

                }
            });

        }

    }

}