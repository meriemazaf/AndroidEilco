package fr.tpeilco.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterGeneration.callbackMethods{
    Thread timer;
    private ResultsDB database;
    Adapter adapter;
    AdapterGeneration adapter_generation;
    private RecyclerView rv;
    private RecyclerView rv_generation;
    private PokemonDao pokemonApi;
    private PokemonDao pokemonApiCount;
    private CompositeDisposable disposables = new CompositeDisposable();
    private CountGenerations countGenerations;
    private TextView generation_view;
    private Button reset_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        rv_generation = findViewById(R.id.rv_generation);
        generation_view = findViewById(R.id.generation);
        reset_btn = findViewById(R.id.reset_btn);
        database = ResultsDB.getInstance(this);


//        timer = new Thread(){
//            @Override
//            public void run(){
//                try {
//                    synchronized (this) {
//                        wait(9000);
//                    }
//                }catch(InterruptedException e){
//                        e.printStackTrace();
//                    }finally{
//                        Intent intent = new Intent(MainActivity.this, acceuilActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                }
//            };
//        timer.start();

        Gson gson = new GsonBuilder().serializeNulls().create();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
                pokemonApi = retrofit.create(PokemonDao.class);

       Retrofit retrofitCount = new Retrofit.Builder()
                .baseUrl("https://pokeapi.glitch.me/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        pokemonApiCount = retrofitCount.create(PokemonDao.class);

        getGenerationCount();



        rv.setLayoutManager(new GridLayoutManager(this, 3   ));


        adapter_generation = new  AdapterGeneration(this);
        rv_generation.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        adapter = new Adapter(this);
        rv.setAdapter(adapter);

        rv_generation.setAdapter(adapter_generation);

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.ResultsDao().reset(adapter.list_pokemon);
                adapter.list_pokemon.clear();
//                adapter.list_pokemon.addAll(database.ResultsDao().g)
                adapter.setResults(adapter.list_pokemon);
            }
        });

//        getGenerationObservable()
//                .flatMap(new Function<ResultsGen, Observable<PokemonSpecies>>() {
//                    @Override
//                    public Observable<PokemonSpecies> apply(ResultsGen resultGen) throws Throwable {
//
//                        return getGenerationObservable2(resultGen);
//                    }
//                })
//                .flatMap(new Function<PokemonSpecies, ObservableSource<PokemonSpecies>>() {
//                    @Override
//                    public ObservableSource<PokemonSpecies> apply(PokemonSpecies pokemonSpecie) throws Throwable {
//
//                        return getGenerationObservable3(pokemonSpecie);
//                    }
//                })
////                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Observer<PokemonSpecies>() {
//                               @Override
//                               public void onSubscribe(@NonNull Disposable d) {
//                                   disposables.add(d);
//
//                               }
//
//                               @Override
//                               public void onNext(@NonNull PokemonSpecies pokemonSpecie) {
//                                   Log.d("errr", String.valueOf(pokemonSpecie.getId()));
//                                   adapter.updateResult(pokemonSpecie);
//
//                               }
//
//                               @Override
//                               public void onError(@NonNull Throwable e) {
//                                    Log.d("onError; ", "dd");
//                               }
//
//                               @Override
//                               public void onComplete() {
//                                   Log.d("onError; ", "dd");
//
//                               }
//                           });
//






        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                getPokemonsObservable(151)
//                        .flatMap(new Function<Results, ObservableSource<Results>>() {
//                            @Override
//                            public ObservableSource<Results> apply(Results result) throws Throwable {
//
//                                return getDetailsPokemonObservable(result);
//                            }
//                        })
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Observer<Results>() {
//
//
//                            @Override
//                            public void onSubscribe(@NonNull Disposable d) {
//
//                            }
//
//                            @Override
//                            public void onNext(@NonNull Results result) {
//                                Log.d("result1", String.valueOf(result.getName()));
//                                adapter.updateResult(result);
//                            }
//
//                            @Override
//                            public void onError(@NonNull Throwable e) {
//                                Log.d("errore", "");
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        });
//
//                getGenerationObservable()
//                        .flatMap(new Function<Results, ObservableSource<Results>>() {
//                            @Override
//                            public ObservableSource<Results> apply(Results result) throws Throwable {
//
//                                return getDetailsGenerationObservable(result);
//                            }
//                        })
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Observer<Results>() {
//                            @Override
//                            public void onSubscribe(@NonNull Disposable d) {
//
//                            }
//
//                            @Override
//                            public void onNext(@NonNull Results result) {
//                                Log.d("onNext2", String.valueOf(result.getName()));
//                                adapter_generation.updateResult(result);
//                            }
//
//                            @Override
//                            public void onError(@NonNull Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        });


    }

//
    public void resetDatabase(){

    }
    public void setResults(List<Results> results){
        adapter.setResults(results);
    }

    public void updateDetailsPokemon(Results detailsPokemon) {
        if(adapter != null){
            adapter.updateDetailsPokemon(detailsPokemon);
        }
    }

    public void updateResult(Results result){
        adapter.updateResult(result);
    }

    // get count generation
    private void getGenerationCount(){
        Call<CountGenerations> call = pokemonApiCount.getCountGeneration();

        call.enqueue(new Callback<CountGenerations>() {
            @Override
            public void onResponse(Call<CountGenerations> call, Response<CountGenerations> response) {
                if(!response.isSuccessful()){
                Toast.makeText(MainActivity.this, "this page my be deleted, code : "+ response.code(), Toast.LENGTH_SHORT).show();
                return;
            }
            countGenerations = response.body();
            Log.d("messageA", String.valueOf(countGenerations.getGen1()));
//            response.raw().body().close();

            // set adapters :




//                getGenerationObservable();
            }


            @Override
            public void onFailure(Call<CountGenerations> call, Throwable t) {
            Toast.makeText(MainActivity.this, "la requete n'a pas aboutie", Toast.LENGTH_SHORT).show();

            }
        });
    }



    public void showGenerationName(String generationName){
        generation_view.setText(generationName);

    }


    public @NonNull Observable<Results> getPokemonsObservable(int offset, int limit){

        return  pokemonApi
                .getPokemons(offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<Pokemon, ObservableSource<Results>>() {
                    @Override
                    public ObservableSource<Results> apply(Pokemon pokemon) throws Throwable {
                            // adapter here
                        adapter.setResults(pokemon.getResults());
                            // stockage dans la base de donnees
                        Log.d("pokemonN", String.valueOf(pokemon.getResults().get(2).getName()));
                        return Observable.fromIterable(pokemon.getResults());
                    }
                });


    }

    public Observable<Results> getDetailsPokemonObservable(final Results result, String generationType){
        return pokemonApi
                .getPokemonsDetails(result.getUrl())
                .map(new Function<Results, Results>() {
                    @Override
                    public Results apply(Results result2) throws Throwable {
                        int delay = ((new Random()).nextInt(5) + 1) * 190;
                        Thread.sleep(delay);
                        result.setId(result2.getId());
                        result.setBase_experience(result2.getBase_experience());
                        result.setOrder(result2.getOrder());
                        result.setHeight(result2.getHeight());
                        result.setGenerationType(generationType);
                        result.setImage("https://pokeres.bastionbot.org/images/pokemon/"+ result2.getId() +".png");
                        Log.d("setImage", "ddddd");
                        return result;
                    }
                })
                .subscribeOn(Schedulers.io());
    }



        public Observable<ResultsGeneration> getDetailsGenerationObservable(final ResultsGeneration result){
        return pokemonApi
                .getGenerationsDetails(result.getUrl())
                .map(new Function<ResultsGeneration, ResultsGeneration>() {
                    @Override
                    public ResultsGeneration apply(ResultsGeneration result2) throws Throwable {
//                        int delay = ((new Random()).nextInt(5) + 1) * 400;
//                        Thread.sleep(delay);
//                        Log.e("setImage", String.valueOf(result2.getPokemon_species().get(1).getName()));
                        result.setId(result2.getId());
                        result.setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMQUN42IwmH8Vst7Ftxykw-Z67iEVjl6TbmQ&usqp=CAU");
                        return result;
                    }
                })
                .subscribeOn(Schedulers.io());
    }


    public @NonNull Observable<ResultsGeneration> getGenerationObservable(){

        return  pokemonApi
                .getGeneration()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<Generation, ObservableSource<ResultsGeneration>>() {
                    @Override
                    public ObservableSource<ResultsGeneration> apply(Generation generation) throws Throwable {
                        // adapter here
                        for(ResultsGeneration gen: generation.getResults()){
                            gen.setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMQUN42IwmH8Vst7Ftxykw-Z67iEVjl6TbmQ&usqp=CAU");
                        }
                        adapter_generation.setResults(generation.getResults());
                        // stockage dans la base de donnees
                        Log.d("setImage", "");
                        return Observable.fromIterable(generation.getResults());
                    }
                });


    }


//    private @NonNull Observable<ResultsGen> getGenerationObservable(){
//
//        return  pokemonApi
//                .getGeneration()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new Function<Generation, ObservableSource<ResultsGen>>() {
//                    @Override
//                    public ObservableSource<ResultsGen> apply(Generation generation) throws Throwable {
//                        // adapter here
//                        for(ResultsGen gen: generation.getResults()){
//                            gen.setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMQUN42IwmH8Vst7Ftxykw-Z67iEVjl6TbmQ&usqp=CAU");
//                        }
//                        adapter_generation.setResults(generation.getResults());
//                        // stockage dans la base de donnees
//                        Log.d("setImage", "");
//                        return Observable.fromIterable(generation.getResults());
//                    }
//                });
//
//
//    }
//
//






//    private @NonNull Observable<PokemonSpecies> getGenerationObservable2(@NotNull ResultsGen result){
//
//        return  pokemonApi
//                .getGenerationsDetails(result.getUrl())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new Function<ResultsGen, ObservableSource<PokemonSpecies>>() {
//                    @Override
//                    public ObservableSource<PokemonSpecies> apply(ResultsGen result) throws Throwable {
//                        // adapter here
////                        adapter_generation.setResults(generation.getResults());
//                        // stockage dans la base de donnees
////                        result.setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMQUN42IwmH8Vst7Ftxykw-Z67iEVjl6TbmQ&usqp=CAU");
//                        Log.e("setImage", String.valueOf(result.getPokemon_species().get(33).getName()));
//                        adapter.setResults(result.getPokemon_species());
//                        return Observable.fromIterable(result.getPokemon_species());
//                    }
//                });
//
//
//    }

//    private @NonNull Observable<PokemonSpecies> getGenerationObservable3(PokemonSpecies PokemonSpecie){
//        return  pokemonApi
//                .getPokemonsDetails(PokemonSpecie.getName())
//                .map(new Function<PokemonSpecies, PokemonSpecies>() {
//                    @Override
//                    public PokemonSpecies apply(PokemonSpecies pokemonSpecieNouveau) throws Throwable {
//                        Log.d("errr3", String.valueOf(PokemonSpecie.getName()));
//                        PokemonSpecie.setImage(pokemonSpecieNouveau.getImage());
//                        return PokemonSpecie;
//                    }
//                })
//                .subscribeOn(Schedulers.io());
//
//    }

//    private Observable<Results> getDetailsGenerationObservable4(final Results result){
//        return pokemonApi
//                .getGenerationsDetails(result.getName())
//                .map(new Function<Results, Results>() {
//                    @Override
//                    public Results apply(Results result2) throws Throwable {
//                        int delay = ((new Random()).nextInt(5) + 1) * 1000;
//                        Thread.sleep(delay);
//                        result.setImage("https://pokeres.bastionbot.org/images/pokemon/"+ result2.getId() +".png");
//                        return result;
//                    }
//                })
//                .subscribeOn(Schedulers.io());
//    }



    }
