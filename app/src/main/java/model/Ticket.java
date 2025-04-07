package model;

public class Ticket {

    public final Customer customer;
    public final Movie movie;
    public final Cinema cinema;

    public Ticket(TicketBuilder builder) {
        this.customer = builder.customer;
        this.movie = builder.movie;
        this.cinema = builder.cinema;
    }

    // Getters
    public Customer getCustomer() {
        return customer;
    }

    public Movie getMovie() {
        return movie;
    }

    public Cinema getCinema() {
        return cinema;
    }


    @Override
    public String toString() {
        return "🎟️ Ticket\n" +
                "Customer: " + customer.getName() + "\n" +
                "Movie: " + movie.getTitle() + " (" + movie.getGenre() + ")\n" +
                "Cinema: " + cinema.getName() + " - " + cinema.getLocation();
    }
}
