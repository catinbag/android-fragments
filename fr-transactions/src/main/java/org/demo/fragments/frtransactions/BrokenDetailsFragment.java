package org.demo.fragments.frtransactions;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.demo.fragments.core.data.Movies;
import org.demo.fragments.core.model.Movie;

/**
 * The mistake: a constructor with a parameter and no empty constructor.
 * After rotation FragmentManager cannot create this class: Fragment$InstantiationException.
 */
public class BrokenDetailsFragment extends Fragment {
    private final long movieId;

    public BrokenDetailsFragment(long movieId) {
        super(R.layout.fragment_movie_details);
        this.movieId = movieId;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Movie movie = Movies.find(movieId);
        ((TextView) view.findViewById(R.id.title)).setText(movie.toString());
        ((TextView) view.findViewById(R.id.description)).setText(movie.getDescription());
        ((TextView) view.findViewById(R.id.instance)).setText(
                getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()));
    }
}
