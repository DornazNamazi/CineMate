package model;

public class AddMovieAction extends AdminActionTemplate {
    private Admin admin;
    private Movie movie;

    public AddMovieAction(Admin admin, Movie movie) {
        this.admin = admin;
        this.movie = movie;
    }

    @Override
    protected void performAction() {
        admin.addMovie(movie);
        System.out.println("Movie added through template.");
    }
}
