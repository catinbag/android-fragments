package org.demo.fragments.frtransactions;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.demo.fragments.core.data.Movies;

public class MovieListFragment extends Fragment {
    private OnMovieClickListener listener;
    private TextView hint;

    public MovieListFragment() {
        super(R.layout.fragment_movie_list);
    }

    // Stage 9 explains onAttach and onDetach. Here the list only needs the Activity.
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (OnMovieClickListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        hint = view.findViewById(R.id.hint);
        ((TextView) view.findViewById(R.id.instance)).setText(
                getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()));
        RecyclerView list = view.findViewById(R.id.movie_list);
        list.setAdapter(new MovieAdapter(Movies.sample(),
                movie -> listener.onMovieClick(movie.getId())));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hint = null;
    }

    /** Works only when the View exists. Before onViewCreated the field is null. */
    public void showHint(String text) {
        hint.setText(text);
    }
}
