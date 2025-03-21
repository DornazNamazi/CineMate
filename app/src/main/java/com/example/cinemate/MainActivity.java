package com.example.cinemate;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textViewOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable Edge-to-Edge UI
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Apply WindowInsets to adjust padding around system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find the TextView where output will be displayed
        textViewOutput = findViewById(R.id.textViewOutput);

        // Run the app logic and update the UI with the results
        runAppLogic();
    }

    private void runAppLogic() {
        // Step 1: Create Movie objects using the MovieFactory
        MovieFactory movieFactory = new MovieFactory();
        Movie movie1 = movieFactory.createMovie("Inception", "Sci-Fi", 148, "A mind-bending thriller by Christopher Nolan.");
        Movie movie2 = movieFactory.createMovie("Titanic", "Romance", 195, "A tragic love story on the doomed ship.");
        Movie movie3 = movieFactory.createMovie("Avatar", "Action/Sci-Fi", 162, "A visually stunning film set on the planet Pandora.");

        // Step 2: Create Cinema objects
        Cinema cinema1 = new Cinema("Cineplex Downtown", "123 Main St", List.of(movie1, movie2, movie3));
        Cinema cinema2 = new Cinema("Vue Cinema", "456 Elm St", List.of(movie1, movie3));

        // Step 3: Create Admin object and add/remove movies or cinemas
        Admin admin = new Admin("1", "Admin", "admin@example.com", "admin123");
        admin.addMovie(movie2); // Admin adds a movie
        admin.removeMovie(movie2); // Admin removes a movie
        admin.addCinema(cinema1); // Admin adds a cinema
        admin.removeCinema(cinema2); // Admin removes a cinema

        // Step 4: Create Customer object
        Customer customer = new Customer("2", "John Doe", "johndoe@example.com", "password123");

        // Step 5: Create Booking object and assign an observer (customer)
        Booking booking1 = new Booking(customer, movie1, cinema1);
        booking1.addObserver(customer); // Customer subscribes to booking updates

        // Step 6: Customer books a movie
        customer.bookMovie(movie1, cinema1);
        booking1.notifyObservers(); // Notify observers (customer) about the booking update

        // Step 7: Customer cancels the booking
        customer.cancelBooking(booking1);
        booking1.notifyObservers(); // Notify observers (customer) about the booking cancellation

        // Step 8: Generate the output with the movie and cinema information
        StringBuilder output = new StringBuilder("Movies and cinemas processed:\n\n");

        output.append("Movies available:\n");
        output.append("1. " + movie1.getTitle() + " (" + movie1.getGenre() + ")\n");
        output.append("2. " + movie2.getTitle() + " (" + movie2.getGenre() + ")\n");
        output.append("3. " + movie3.getTitle() + " (" + movie3.getGenre() + ")\n\n");

        output.append("Cinemas available:\n");
        output.append("1. " + cinema1.getName() + " (" + cinema1.getLocation() + ")\n");
        output.append("2. " + cinema2.getName() + " (" + cinema2.getLocation() + ")\n\n");

        output.append("Booked Movie: " + movie1.getTitle() + " at " + cinema1.getName() + "\n");
        output.append("Booking Status: " + booking1.getStatus() + "\n");

        // Display the results in the TextView
        textViewOutput.setText(output.toString());
    }
}
