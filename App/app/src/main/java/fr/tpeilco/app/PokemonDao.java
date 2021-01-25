package fr.mohamed.app;
import retrofit2.Call;
import retrofit2.http.Path;
import retrofit2.http.Query;
//import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface PokemonDao {

        @GET("api/v2/pokemon")
        Observable<Pokemon> getPokemons(
                @Query("offset") int offset,
                @Query("limit") int limit
        );
        @GET("api/v2/pokemon/{name}")
        Call<Results> getPokemonsDetailsByName(@Path("name") String name);
        @GET
        Observable<Results> getPokemonsDetails(@Url String url);




//        @GET
//        Observable<ResultsGen> getGenerationsDetails(@Url String url);


        @GET("api/v2/pokemon/{name}")
        Observable<Results> getPokemonsDetails3(@Path("name") String name);


        @GET("/v1/pokemon/counts")
        Call<CountGenerations> getCountGeneration();

        @GET("api/v2/generation")
        Observable<Generation> getGeneration();



        @GET
        Observable<ResultsGeneration> getGenerationsDetails(@Url String url);


//        @Insert(onConflict = REPLACE)
//        void insert(Pokemon pokemon);

//        @Query("SELECT * FROM Pokemon")
//        List<Pokemon> getAll();


}
