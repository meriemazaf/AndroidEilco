package fr.tpeilco.app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
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

public class AdapterGeneration extends RecyclerView.Adapter<AdapterGeneration.ViewHolder> {
//    List<ResultsGen> list_generation = new ArrayList<>();
    List<ResultsGeneration> list_generation = new ArrayList<>();
    List<Results> list_pokemons = new ArrayList<>();
    Context context;
    private ResultsDB database;
//    Results detailsPokemon = new Results("", "");
    Adapter adapter;
    private PokemonDao pokemonApi;
    private PokemonDao pokemonApiCount;
    callbackMethods callbackMethods;
    private CompositeDisposable disposables = new CompositeDisposable();
//    Subscription mSubscription;
    private CountGenerations countGenerations;
    public AdapterGeneration(Context context) {
        this.context = context;
        callbackMethods = (callbackMethods) context;


        Gson gson = new GsonBuilder().serializeNulls().create();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofitCount = new Retrofit.Builder()
                .baseUrl("https://pokeapi.glitch.me/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        pokemonApiCount = retrofitCount.create(PokemonDao.class);
        getGenerationCount();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        pokemonApi = retrofit.create(PokemonDao.class);

        callbackMethods.getGenerationObservable()
                .flatMap(new Function<ResultsGeneration, ObservableSource<ResultsGeneration>>() {
                    @Override
                    public ObservableSource<ResultsGeneration> apply(ResultsGeneration result) throws Throwable {

                        return callbackMethods.getDetailsGenerationObservable(result);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultsGeneration>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull ResultsGeneration result) {
                        Log.d("onNext2", String.valueOf(result.getName()));

                        updateResult(result);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_generation;
        TextView name_generation;
        ProgressBar progress_generation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_generation = itemView.findViewById(R.id.image_generation);
            name_generation = itemView.findViewById(R.id.name_generation);
            progress_generation = itemView.findViewById(R.id.progress_generation);
        }

        private void showProgressBar(boolean showProgressBar){
            if(showProgressBar){
                progress_generation.setVisibility(View.VISIBLE);
            }
            else{
                progress_generation.setVisibility(View.GONE);
            }
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View item_generation = inflater.inflate(R.layout.item_generation, parent, false);
        return new ViewHolder(item_generation);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        database = ResultsDB.getInstance(context);
        ResultsGeneration result = this.list_generation.get(position);
            holder.name_generation.setText(result.getName());

        if(result.getImage() == null){
            holder.showProgressBar(true);
            holder.image_generation.setImageResource(0);
        }else{
            holder.showProgressBar(false);
            Glide.with(holder.image_generation.getContext())
                    .load(result.getImage())
                    .into(holder.image_generation);
        }

        holder.image_generation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultsGeneration gen = list_generation.get(holder.getAdapterPosition());
                list_pokemons = database.ResultsDao().getAll(gen.getName());
                if (list_pokemons.size() == 0){
                    Log.d("sizezero", "zero");
                    int offset;
                int limit;
                Toast.makeText(context, "generation selected" + gen.getName(), Toast.LENGTH_SHORT).show();
                offset = countGenerations.getOffset(gen.getName());
                limit = countGenerations.getLimit(gen.getName());
                if (gen.getName().equals("generation-viii")) limit = 999999;
                callbackMethods.showGenerationName(gen.getName());

                callbackMethods.getPokemonsObservable(offset, limit)
                        .flatMap(new Function<Results, ObservableSource<Results>>() {
                            @Override
                            public ObservableSource<Results> apply(Results result) throws Throwable {

                                return callbackMethods.getDetailsPokemonObservable(result, gen.getName());
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Results>() {


                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(@io.reactivex.rxjava3.annotations.NonNull Results result) {
                                callbackMethods.updateResult(result);
                                database.ResultsDao().insert(result);
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                Log.d("errore", "");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }else{
                    callbackMethods.showGenerationName(gen.getName());
                    callbackMethods.setResults(list_pokemons);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return this.list_generation.size();
    }


    public void setResults(List<ResultsGeneration> results){
        this.list_generation.clear();
        this.list_generation.addAll(results);
        notifyDataSetChanged();
    }

    public void updateResult(ResultsGeneration result){
        list_generation.set(list_generation.indexOf(result), result);
        notifyItemChanged(list_generation.indexOf(result));
        notifyDataSetChanged();
    }


    // get count generation
    private void getGenerationCount(){
        Call<CountGenerations> call = pokemonApiCount.getCountGeneration();

        call.enqueue(new Callback<CountGenerations>() {
            @Override
            public void onResponse(Call<CountGenerations> call, Response<CountGenerations> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, "this page my be deleted, code : "+ response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                countGenerations = response.body();
                Log.d("messageA", String.valueOf(countGenerations.getGen1()));
            }


            @Override
            public void onFailure(Call<CountGenerations> call, Throwable t) {
                Toast.makeText(context, "la requete n'a pas aboutie", Toast.LENGTH_SHORT).show();

            }
        });
    }



    public interface callbackMethods{
        public Observable<Results> getPokemonsObservable(int offset, int limit);
        public Observable<Results> getDetailsPokemonObservable(final Results result, String generationType);
        public Observable<ResultsGeneration> getDetailsGenerationObservable(final ResultsGeneration result);
        public Observable<ResultsGeneration> getGenerationObservable();
        public void updateResult(Results result);
        public void showGenerationName(String generationName);
        public void updateDetailsPokemon(Results detailsPokemon);
        public void setResults(List<Results> results);
    }
}
