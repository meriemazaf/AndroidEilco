package fr.tpeilco.app;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ResultsPokemonDao {

    @Insert(onConflict = REPLACE)
    void insert(Results mainData);

    @Delete
    void delete(Results  mainData);
    @Delete
    void reset(List<Results> mainData);

    @Query("SELECT * FROM Results where generationType = :generationType")
    List<Results> getAll(String generationType);

}
