package fr.tpeilco.app;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "Pokemon")
public class Pokemon {
    @PrimaryKey(autoGenerate = true)
    private int ID;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int id;
    private String image;
    @ColumnInfo(name = "count")
   private int count;
    @ColumnInfo(name = "next")
    private String next;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @ColumnInfo(name = "previous")
    private String previous;
    @ColumnInfo(name = "results")
    private List<Results> results = new ArrayList<>();
    private int base_experience;
    //    @ColumnInfo(name = "height")/
    private int height;
    //
    private int weight;

    public Pokemon(int ID, int count, String next, String previous, List<Results> results, int base_experience, int height, int weight) {
        this.ID = ID;
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
        this.base_experience = base_experience;
        this.height = height;
        this.weight = weight;
    }

    public int getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }



    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }
}
