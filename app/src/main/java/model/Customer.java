package model;


public class Customer extends User implements BookingObserver {
    public Customer(String id, String name, String email, String password) {
        super(id, name, email, password, "CUSTOMER");
    }

    public void bookMovie(Movie movie, Cinema cinema) {


    }

    public void cancelBooking(Booking booking) {

    }

    public void viewBookings() {

    }

    @Override
    public void update(Booking booking) {
        System.out.println("com.example.cinematicket.Booking updated for " + getName());
    }
}
