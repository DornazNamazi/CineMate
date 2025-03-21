package com.example.cinemate;


public class Customer extends User implements com.example.cinemate.BookingObserver {
    public Customer(String id, String name, String email, String password) {
        super(id, name, email, password, "CUSTOMER");
    }

    public void bookMovie(Movie movie, com.example.cinemate.Cinema cinema) {


    }

    public void cancelBooking(com.example.cinemate.Booking booking) {

    }

    public void viewBookings() {

    }

    @Override
    public void update(com.example.cinemate.Booking booking) {
        System.out.println("com.example.cinematicket.Booking updated for " + getName());
    }
}
