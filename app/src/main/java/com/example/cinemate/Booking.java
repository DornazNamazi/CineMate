package com.example.cinemate;
import java.util.ArrayList;
import java.util.List;

public class Booking {
    private Customer user;
    private Movie movie;
    private Cinema cinema;
    private List<com.example.cinemate.BookingObserver> observers;

    public Booking(Customer user, Movie movie, Cinema cinema) {
        this.user = user;
        this.movie = movie;
        this.cinema = cinema;
        observers = new ArrayList<>();
    }

    public Customer getUser() {
        return user;
    }

    public Movie getMovie() {
        return movie;
    }

    public Cinema getCinema() {
        return cinema;
    }


    public void addObserver(com.example.cinemate.BookingObserver observer) {
        observers.add(observer);
    }


    public void removeObserver(com.example.cinemate.BookingObserver observer) {
        observers.remove(observer);
    }


    public void notifyObservers() {
        for (com.example.cinemate.BookingObserver observer : observers) {
            observer.update(this);
        }
    }

    public void notifyUser() {
        System.out.println("com.example.cinemate.Booking updated for: " + user.getName());
    }

    public String getStatus() {
        return "Booked";
    }
}
