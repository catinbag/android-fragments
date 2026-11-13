package org.demo.fragments.core.repository;

import org.demo.fragments.core.model.Movie;

import java.util.List;

public interface MovieRepository {

    /** Returns a copy. Later changes of the repository do not change it. */
    List<Movie> getAll();

    /** @throws java.util.NoSuchElementException if there is no movie with this id */
    Movie getById(long id);

    void add(Movie movie);

    /** @return true if a movie was removed */
    boolean remove(long id);
}
