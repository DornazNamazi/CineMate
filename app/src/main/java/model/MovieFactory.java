package model;


import java.util.List;

public class MovieFactory {
/*
    public Movie createMovie(String title, String genre, int duration, String description) {
        return new Movie(title, genre, duration, description);
    }*/

    public Movie createMovie(String title, String genre, int duration, String description, List<String> showtimes) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setDuration(duration);
        movie.setDescription(description);
        movie.setShowtimes(showtimes);
        movie.setImageUrl("moviepicture");
        return movie;
    }
}
