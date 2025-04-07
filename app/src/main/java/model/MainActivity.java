package model;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cinemate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textViewOutput;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable Edge-to-Edge UI
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_testing_page);

        // Apply WindowInsets to adjust padding around system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();

        // Find the TextView where output will be displayed
        textViewOutput = findViewById(R.id.textViewOutput);

        mAuth.signInWithEmailAndPassword("client1@example.com", "123456")
                .addOnCompleteListener(this, new OnCompleteListener<com.google.firebase.auth.AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            textViewOutput.setText("Login successful! UID: " + user.getUid() + "\n\n");
                            runAppLogic(); // só roda depois de login
                        } else {
                            textViewOutput.setText("Login failed: " + task.getException().getMessage());
                        }
                    }
                });


    }

    private void runAppLogic() {
        textViewOutput.append("Starting app logic...\n\n");

        // Step 1: Create Movie objects using the MovieFactory

        // creating showtimes to testing:

        List<String> showtimes = Arrays.asList("14:00", "16:30", "19:00");

        MovieFactory movieFactory = new MovieFactory();
        Movie movie1 = movieFactory.createMovie("Inception", "Sci-Fi", 148, "A mind-bending thriller by Christopher Nolan.", showtimes);
        Movie movie2 = movieFactory.createMovie("Titanic", "Romance", 195, "A tragic love story on the doomed ship.", showtimes);
        Movie movie3 = movieFactory.createMovie("Avatar", "Action/Sci-Fi", 162, "A visually stunning film set on the planet Pandora.", showtimes);
        textViewOutput.append("Movies created successfully.\n");

        // Step 2: Create Cinema objects
        Cinema cinema1 = new Cinema("Cineplex Downtown", "123 Main St", List.of(movie1, movie2, movie3));
        Cinema cinema2 = new Cinema("Vue Cinema", "456 Elm St", List.of(movie1, movie3));
        textViewOutput.append("Cinemas created successfully.\n");

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
        textViewOutput.append("Customer created and registered as observer.\n");

        // Step 6: Customer books a movie
        customer.bookMovie(movie1, cinema1);
        booking1.notifyObservers(); // Notify observers (customer) about the booking update
        textViewOutput.append("Customer booked a movie and was notified.\n");

        // Step 7: Customer cancels the booking
        customer.cancelBooking(booking1);
        booking1.notifyObservers(); // Notify observers (customer) about the booking cancellation
        textViewOutput.append("Customer canceled the booking and was notified.\n\n");

        // Step 8: Generate the output with the movie and cinema information
        StringBuilder output = new StringBuilder("Final summary:\n\n");

        output.append("Available movies:\n");
        output.append("1. " + movie1.getTitle() + " (" + movie1.getGenre() + ")\n");
        output.append("2. " + movie2.getTitle() + " (" + movie2.getGenre() + ")\n");
        output.append("3. " + movie3.getTitle() + " (" + movie3.getGenre() + ")\n\n");

        output.append("Available cinemas\n");
        output.append("1. " + cinema1.getName() + " (" + cinema1.getLocation() + ")\n");
        output.append("2. " + cinema2.getName() + " (" + cinema2.getLocation() + ")\n\n");

        output.append("Booked Movie: " + movie1.getTitle() + " at " + cinema1.getName() + "\n");
        output.append("Booking Status: " + booking1.getStatus() + "\n");

        // Display the results in the TextView
        textViewOutput.append(output.toString());
    }
}
