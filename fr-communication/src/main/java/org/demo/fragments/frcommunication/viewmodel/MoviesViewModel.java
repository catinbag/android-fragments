package org.demo.fragments.frcommunication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.demo.fragments.core.model.Movie;

public class MoviesViewModel extends ViewModel {
    private final MutableLiveData<Movie> selected = new MutableLiveData<>();

    public LiveData<Movie> getSelected() {
        return selected;
    }

    public void select(Movie movie) {
        selected.setValue(movie);
    }
}
