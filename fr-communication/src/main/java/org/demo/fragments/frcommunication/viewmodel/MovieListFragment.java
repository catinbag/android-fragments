package org.demo.fragments.frcommunication.viewmodel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import org.demo.fragments.core.data.Movies;
import org.demo.fragments.core.model.Movie;
import org.demo.fragments.frcommunication.BugSettings;
import org.demo.fragments.frcommunication.MovieAdapter;
import org.demo.fragments.frcommunication.R;

public class MovieListFragment extends Fragment {
    private static final String TAG = "ViewModelDemo";

    public MovieListFragment() {
        super(R.layout.fragment_movie_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        MovieAdapter adapter = new MovieAdapter(Movies.sample());
        ((RecyclerView) view.findViewById(R.id.movie_list)).setAdapter(adapter);

        MoviesViewModel viewModel =
                new ViewModelProvider(requireActivity()).get(MoviesViewModel.class);
        adapter.setOnItemClick(movie -> viewModel.select(movie));
        observeSelected(viewModel, adapter);
    }

    private void observeSelected(MoviesViewModel viewModel, MovieAdapter adapter) {
        Observer<Movie> observer = movie -> {
            Log.d(TAG, "list observer called");
            adapter.setSelected(movie);
        };
        if (BugSettings.isOn(requireContext(), BugSettings.OBSERVE_THIS)) {
            viewModel.getSelected().observe(this, observer);   // mode "observe(this)": the mistake
        } else {
            viewModel.getSelected().observe(getViewLifecycleOwner(), observer);
        }
    }
}
