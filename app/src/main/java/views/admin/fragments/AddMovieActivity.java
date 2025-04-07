package views.admin.fragments;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cinemate.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.List;

import model.Movie;
import model.MovieFactory;

public class AddMovieActivity extends AppCompatActivity {
    private EditText etTitle, etGenre, etDuration, etDescription, etShowtimes;
    private Button btnSave;
    private FirebaseFirestore db;
    private MovieFactory movieFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.admin_activity_add_movie);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etDuration = findViewById(R.id.etDuration);
        etDescription = findViewById(R.id.etDescription);
        etShowtimes = findViewById(R.id.etShowtimes);
        btnSave = findViewById(R.id.btnSave);

        db = FirebaseFirestore.getInstance();
        movieFactory = new MovieFactory();

        btnSave.setOnClickListener(v -> saveMovie());
    }

    private void saveMovie() {
        String title = etTitle.getText().toString().trim();
        String genre = etGenre.getText().toString().trim();
        String durationStr = etDuration.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String showtimesStr = etShowtimes.getText().toString().trim();

        if (title.isEmpty() || genre.isEmpty() || durationStr.isEmpty()
                || description.isEmpty() || showtimesStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int duration;
        try {
            duration = Integer.parseInt(durationStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Duration must be a number", Toast.LENGTH_SHORT).show();
            return;
        }

        List<String> showtimes = Arrays.asList(showtimesStr.split("\\s*,\\s*"));

        // Creation of movie with Factory
        Movie movie = movieFactory.createMovie(title, genre, duration, description, showtimes);

        // Save data - firebase
        String movieId = db.collection("movies").document().getId();
        movie.setMovieId(movieId);

        db.collection("movies").document(movieId).set(movie)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Movie added successfully!", Toast.LENGTH_SHORT).show();
                    finish(); // volta para AdminHomePage
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}