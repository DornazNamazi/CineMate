package views.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinemate.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.MovieAdapter;
import model.Movie;
import model.MovieFactory;
import views.admin.fragments.AddMovieActivity;

public class AdminHomePage extends AppCompatActivity {

    private RecyclerView recyclerViewMovies;
    private FloatingActionButton btnAddMovie, btnDeleteMovie;
    private List<Movie> movieList;
    private MovieAdapter adapter;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.admin_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        btnAddMovie = findViewById(R.id.btnAddMovie);
        btnDeleteMovie = findViewById(R.id.btnDeleteMovie);

        db = FirebaseFirestore.getInstance();
        movieList = new ArrayList<>();
        adapter = new MovieAdapter(this, movieList);

        recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewMovies.setAdapter(adapter);

        loadMoviesFromFirebase();

        btnAddMovie.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomePage.this, AddMovieActivity.class);
            startActivity(intent);
        });

        btnDeleteMovie.setOnClickListener(v -> {
            Intent intent = new Intent(AdminHomePage.this, views.admin.fragments.DeleteMovieActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadMoviesFromFirebase();
    }

    private void loadMoviesFromFirebase() {
        db.collection("movies").get().addOnSuccessListener(query -> {
            movieList.clear();
            for (DocumentSnapshot doc : query) {
                Movie movie = doc.toObject(Movie.class);
                movieList.add(movie);
                Log.d("FIREBASE", "Loaded movie: " + movie.getTitle());
            }
            adapter.notifyDataSetChanged();
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Failed to load movies", Toast.LENGTH_SHORT).show();
        });
    }
}