package model;

import java.util.List;

public class Cinema {
    private String name;
    private String location;
    private List<Movie> movies;

    public Cinema(String name, String location, List<Movie> movies) {
        this.name = name;
        this.location = location;
        this.movies = movies;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<Movie> getMovies() {
        return movies;
    }

}
