package model;


public class Admin extends User {

    public Admin(String id, String name, String email, String password) {
        super(id, name, email, password, "ADMIN");
    }

    public void addMovie(Movie movie) {

    }

    public void removeMovie(Movie movie) {

    }

    public void addCinema(Cinema cinema) {

    }

    public void removeCinema(Cinema cinema) {

    }
}
