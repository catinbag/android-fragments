package org.demo.fragments.core.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.demo.fragments.core.model.Movie;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class MoviesTest {

    @Test
    public void sampleHasTwentyMoviesWithUniqueIds() {
        List<Movie> movies = Movies.sample();
        Set<Long> ids = new HashSet<>();
        for (Movie movie : movies) {
            ids.add(movie.getId());
        }
        assertEquals(20, movies.size());
        assertEquals(20, ids.size());
    }

    @Test
    public void sampleReturnsNewListEachTime() {
        assertNotSame(Movies.sample(), Movies.sample());
    }

    @Test
    public void findReturnsMovieById() {
        assertEquals("Inception", Movies.find(6).getTitle());
    }

    @Test(expected = NoSuchElementException.class)
    public void findThrowsForUnknownId() {
        Movies.find(999);
    }

    @Test
    public void everyMovieHasTitleAndDescription() {
        for (Movie movie : Movies.sample()) {
            assertTrue(movie.getTitle().length() > 0);
            assertTrue(movie.getDescription().length() > 0);
        }
    }
}
