package com.example.cinemate;


public class MovieFactory {

    public com.example.cinemate.Movie createMovie(String title, String genre, int duration, String description) {
        return new com.example.cinemate.Movie(title, genre, duration, description);
    }
}
