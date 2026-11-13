package org.demo.fragments.adaptive;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.demo.fragments.core.data.Movies;
import org.demo.fragments.core.model.Movie;

/** The same class on a phone and on a tablet. The movie comes from the arguments. */
public class MovieDetailsFragment extends Fragment {
    static final String ARG_MOVIE_ID = "movie_id";

    private long movieId;

    public MovieDetailsFragment() {
        super(R.layout.fragment_movie_details);
    }

    public static MovieDetailsFragment newInstance(long movieId) {
        Bundle args = new Bundle();
        args.putLong(ARG_MOVIE_ID, movieId);
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieId = requireArguments().getLong(ARG_MOVIE_ID);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Movie movie = Movies.find(movieId);
        ((TextView) view.findViewById(R.id.title)).setText(movie.toString());
        ((TextView) view.findViewById(R.id.description)).setText(movie.getDescription());
    }
}
