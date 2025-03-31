package model;


public class MovieFactory {

    public Movie createMovie(String title, String genre, int duration, String description) {
        return new Movie(title, genre, duration, description);
    }
}
