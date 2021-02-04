package fr.tpeilco.app;

import java.util.List;

public class ResultsGen {

    private int cle;
    private String url;
    private int id;
    private String name;
    private String image = null;

    private List<PokemonSpecies> pokemon_species;

    public ResultsGen(int cle, String url, int id, String name, String image, List<PokemonSpecies> pokemon_species) {
        this.cle = cle;
        this.url = url;
        this.id = id;
        this.name = name;
        this.image = image;
        this.pokemon_species = pokemon_species;
    }

    public int getCle() {
        return cle;
    }

    public void setCle(int cle) {
        this.cle = cle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<PokemonSpecies> getPokemon_species() {
        return pokemon_species;
    }

    public void setPokemon_species(List<PokemonSpecies> pokemon_species) {
        this.pokemon_species = pokemon_species;
    }
}


