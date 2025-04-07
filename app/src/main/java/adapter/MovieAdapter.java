package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinemate.R;

import java.util.List;

import model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_card_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);

        holder.tvTitle.setText(movie.getTitle());
        holder.tvGenre.setText("Genre: " + movie.getGenre());
        holder.tvDuration.setText("Duration: " + movie.getDuration() + " min");
        holder.tvDescription.setText(movie.getDescription());

        if (movie.getShowtimes() != null) {
            holder.tvShowtimes.setText("Showtimes: " + String.join(", ", movie.getShowtimes()));
        } else {
            holder.tvShowtimes.setText("Showtimes: N/A");
        }

        String imageUrl = movie.getImageUrl();

        if (imageUrl != null && imageUrl.startsWith("http")) {
            // if url from database
            Glide.with(context).load(imageUrl).into(holder.ivPoster);
        } else {
            // if drawable
            int imageRes = context.getResources().getIdentifier(
                    imageUrl, "drawable", context.getPackageName());

            if (imageRes == 0) {
                imageRes = R.drawable.moviepicture;
            }

            Glide.with(context).load(imageRes).into(holder.ivPoster);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, views.admin.fragments.UpdateMovieActivity.class);
            intent.putExtra("movieId", movie.getMovieId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster;
        TextView tvTitle, tvGenre, tvDuration, tvDescription, tvShowtimes;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvGenre = itemView.findViewById(R.id.tvGenre);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvShowtimes = itemView.findViewById(R.id.tvShowtimes);
        }
    }
}
