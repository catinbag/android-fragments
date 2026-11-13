package org.demo.fragments.frbasics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.demo.fragments.core.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Holder> {
    private final List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Movie movie = movies.get(position);
        holder.title.setText(movie.toString());
        holder.details.setText(movie.getGenre().getTitle() + " · " + movie.getRating());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        final TextView title;
        final TextView details;

        Holder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            details = itemView.findViewById(R.id.details);
        }
    }
}
