package org.demo.fragments.adaptive;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.demo.fragments.core.data.Movies;

/** The same class on a phone and on a tablet. It reports a click to the host and nothing else. */
public class MovieListFragment extends Fragment {
    private OnMovieClickListener listener;

    public MovieListFragment() {
        super(R.layout.fragment_movie_list);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (!(context instanceof OnMovieClickListener)) {
            throw new IllegalStateException(context + " must implement OnMovieClickListener");
        }
        listener = (OnMovieClickListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView list = view.findViewById(R.id.movie_list);
        list.setAdapter(new MovieAdapter(Movies.sample(),
                movie -> listener.onMovieClick(movie.getId())));
    }
}
