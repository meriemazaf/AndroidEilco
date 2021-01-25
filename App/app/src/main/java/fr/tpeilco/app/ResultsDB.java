package fr.mohamed.app;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Results.class}, version = 2, exportSchema = false)
public abstract class ResultsDB extends RoomDatabase {
    // Create database instance
    private static ResultsDB database;
    private static String DATABASE_NAME = "database";

    public synchronized static ResultsDB getInstance(Context context){
        if(database == null){

            // when database is null
            // Initialize database
            database = Room.databaseBuilder(context.getApplicationContext(),
                    ResultsDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        }
        // Return database
        return database;

    }

    public abstract ResultsPokemonDao ResultsDao();

}
