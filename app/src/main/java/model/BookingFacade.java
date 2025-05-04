
package model;

import java.util.ArrayList;
import java.util.List;


public class BookingFacade {

    public Booking bookMovie(Customer customer, Movie movie, Cinema cinema) {

        Booking booking = new Booking(customer, movie, cinema);


        booking.addObserver(customer);


        booking.notifyObservers();

        // Build ticket (optional but recommended)
        Ticket ticket = new TicketBuilder()
                .setCustomer(customer)
                .setMovie(movie)
                .setCinema(cinema)
                .buildTicket();


        System.out.println("Booking successful!");
        System.out.println(ticket);

        return booking;
    }

    public void cancelBooking(Booking booking) {

        for (BookingObserver observer : new ArrayList<>(booking.getObservers())) {
            booking.removeObserver(observer);
        }


        booking.notifyObservers();
        System.out.println("Booking cancelled for " + booking.getUser().getName());
    }
}
