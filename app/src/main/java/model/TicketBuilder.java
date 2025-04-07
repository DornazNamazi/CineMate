package model;

public class TicketBuilder {
    public Customer customer;
    public Movie movie;
    public Cinema cinema;
    public Movie duration;

    public TicketBuilder setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public TicketBuilder setMovie(Movie movie) {
        this.movie = movie;
        return this;
    }

    public TicketBuilder setCinema(Cinema cinema) {
        this.cinema = cinema;
        return this;
    }

    public TicketBuilder duration(Movie duration) {
        this.duration = duration;
        return this;
    }

    public TicketBuilder build() {
        return new TicketBuilder(this);
    }

}
