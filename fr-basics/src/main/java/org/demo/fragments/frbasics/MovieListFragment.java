package org.demo.fragments.frbasics;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.demo.fragments.core.data.Movies;

public class MovieListFragment extends Fragment {

    public MovieListFragment() {
        super(R.layout.fragment_movie_list);   // the layout goes to the constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView list = view.findViewById(R.id.movie_list);
        list.setAdapter(new MovieAdapter(Movies.sample()));
    }
}
