package views.admin.fragments;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemate.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeleteMovieActivity extends AppCompatActivity {

    private Spinner spinnerMovies;
    private Button btnDelete;
    private FirebaseFirestore db;
    private ArrayList<String> movieTitles = new ArrayList<>();
    private Map<String, String> titleToIdMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_delete_movie);

        spinnerMovies = findViewById(R.id.spinnerMovies);
        btnDelete = findViewById(R.id.btnDeleteMovie);
        db = FirebaseFirestore.getInstance();

        loadMoviesIntoSpinner();

        btnDelete.setOnClickListener(v -> deleteSelectedMovie());
    }

    private void loadMoviesIntoSpinner() {
        db.collection("movies").get().addOnSuccessListener(query -> {
            movieTitles.clear();
            titleToIdMap.clear();
            for (com.google.firebase.firestore.DocumentSnapshot doc : query) {
                String title = doc.getString("title");
                String id = doc.getId();
                movieTitles.add(title);
                titleToIdMap.put(title, id);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_spinner_item, movieTitles);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerMovies.setAdapter(adapter);
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Failed to load movies", Toast.LENGTH_SHORT).show();
        });
    }

    private void deleteSelectedMovie() {
        String selectedTitle = (String) spinnerMovies.getSelectedItem();
        if (selectedTitle == null) {
            Toast.makeText(this, "No movie selected", Toast.LENGTH_SHORT).show();
            return;
        }

        String movieId = titleToIdMap.get(selectedTitle);
        if (movieId == null) {
            Toast.makeText(this, "Invalid selection", Toast.LENGTH_SHORT).show();
            return;
        }

        db.collection("movies").document(movieId).delete()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Movie deleted!", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Delete failed", Toast.LENGTH_SHORT).show();
                });
    }
}