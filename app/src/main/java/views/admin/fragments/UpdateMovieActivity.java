package views.admin.fragments;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemate.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.List;

import model.Movie;
import model.MovieFactory;

public class UpdateMovieActivity extends AppCompatActivity {

    private EditText etTitle, etGenre, etDuration, etDescription, etShowtimes;
    private Button btnSaveChanges;
    private FirebaseFirestore db;
    private MovieFactory factory;
    private String movieId; // get by intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_update_movie);

        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etDuration = findViewById(R.id.etDuration);
        etDescription = findViewById(R.id.etDescription);
        etShowtimes = findViewById(R.id.etShowtimes);
        btnSaveChanges = findViewById(R.id.btnSave);

        db = FirebaseFirestore.getInstance();
        factory = new MovieFactory();

        movieId = getIntent().getStringExtra("movieId");

        loadMovie();

        btnSaveChanges.setOnClickListener(v -> updateMovie());
    }

    private void loadMovie() {
        DocumentReference docRef = db.collection("movies").document(movieId);
        docRef.get().addOnSuccessListener(snapshot -> {
            Movie movie = snapshot.toObject(Movie.class);
            if (movie != null) {
                etTitle.setText(movie.getTitle());
                etGenre.setText(movie.getGenre());
                etDuration.setText(String.valueOf(movie.getDuration()));
                etDescription.setText(movie.getDescription());
                etShowtimes.setText(String.join(",", movie.getShowtimes()));
            }
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Failed to load movie", Toast.LENGTH_SHORT).show();
        });
    }

    private void updateMovie() {
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
            Toast.makeText(this, "Invalid duration", Toast.LENGTH_SHORT).show();
            return;
        }

        List<String> showtimes = Arrays.asList(showtimesStr.split("\\s*,\\s*"));

        // update only visible fields - dont tpuch the image....
        db.collection("movies").document(movieId).update(
                "title", title,
                "genre", genre,
                "duration", duration,
                "description", description,
                "showtimes", showtimes
        ).addOnSuccessListener(aVoid -> {
            Toast.makeText(this, "Movie updated!", Toast.LENGTH_SHORT).show();
            finish();
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
        });
    }
}