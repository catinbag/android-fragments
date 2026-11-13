package org.demo.fragments.core.data;

import org.demo.fragments.core.model.Genre;
import org.demo.fragments.core.model.Movie;
import org.demo.fragments.core.repository.MovieRepository;

import java.util.Arrays;
import java.util.List;

/** Local data for the demos: 20 movies, ids 1..20. */
public final class Movies {
    private static final List<Movie> SAMPLE = Arrays.asList(
            new Movie(1, "The Shawshank Redemption", 1994, Genre.DRAMA, 9.3,
                    "A banker is sent to prison for a crime he did not commit. He makes a friend and plans his way out."),
            new Movie(2, "The Godfather", 1972, Genre.CRIME, 9.2,
                    "The head of a crime family passes his power to his reluctant youngest son."),
            new Movie(3, "The Dark Knight", 2008, Genre.CRIME, 9.0,
                    "Batman faces the Joker, a criminal who wants to push Gotham into chaos."),
            new Movie(4, "Pulp Fiction", 1994, Genre.CRIME, 8.9,
                    "Several stories of Los Angeles criminals cross in a non-linear plot."),
            new Movie(5, "Forrest Gump", 1994, Genre.DRAMA, 8.8,
                    "A kind man with a low IQ is present at many key events of American history."),
            new Movie(6, "Inception", 2010, Genre.SCI_FI, 8.8,
                    "A thief enters dreams to steal secrets. His last job is to plant an idea."),
            new Movie(7, "Fight Club", 1999, Genre.DRAMA, 8.8,
                    "An office worker and a soap maker start an underground fight club."),
            new Movie(8, "The Matrix", 1999, Genre.SCI_FI, 8.7,
                    "A hacker learns that his world is a simulation and joins a rebellion."),
            new Movie(9, "Goodfellas", 1990, Genre.CRIME, 8.7,
                    "The rise and fall of a young man in the New York mafia."),
            new Movie(10, "Interstellar", 2014, Genre.SCI_FI, 8.7,
                    "Astronauts travel through a wormhole to find a new home for humanity."),
            new Movie(11, "Spirited Away", 2001, Genre.ANIMATION, 8.6,
                    "A girl enters a world of spirits and must save her parents."),
            new Movie(12, "Se7en", 1995, Genre.CRIME, 8.6,
                    "Two detectives hunt a killer who uses the seven deadly sins as a pattern."),
            new Movie(13, "The Lion King", 1994, Genre.ANIMATION, 8.5,
                    "A young lion prince runs away after his father's death and later returns."),
            new Movie(14, "Back to the Future", 1985, Genre.SCI_FI, 8.5,
                    "A teenager travels to 1955 and must make sure his parents fall in love."),
            new Movie(15, "Whiplash", 2014, Genre.DRAMA, 8.5,
                    "A young drummer meets a teacher who pushes him to the limit."),
            new Movie(16, "WALL-E", 2008, Genre.ANIMATION, 8.4,
                    "A small robot on an empty Earth finds a plant and follows a probe into space."),
            new Movie(17, "The Grand Budapest Hotel", 2014, Genre.COMEDY, 8.1,
                    "A hotel concierge and his lobby boy are drawn into the theft of a painting."),
            new Movie(18, "Groundhog Day", 1993, Genre.COMEDY, 8.0,
                    "A weatherman lives the same day again and again."),
            new Movie(19, "Some Like It Hot", 1959, Genre.COMEDY, 8.2,
                    "Two musicians disguise themselves as women to hide from gangsters."),
            new Movie(20, "Blade Runner", 1982, Genre.SCI_FI, 8.1,
                    "A detective hunts artificial humans in a rainy future Los Angeles.")
    );

    private static final MovieRepository REPOSITORY = new InMemoryMovieRepository(SAMPLE);

    private Movies() {
    }

    /** Returns a new list each time. Movies are immutable. */
    public static List<Movie> sample() {
        return REPOSITORY.getAll();
    }

    /** @throws java.util.NoSuchElementException if there is no movie with this id */
    public static Movie find(long id) {
        return REPOSITORY.getById(id);
    }
}
