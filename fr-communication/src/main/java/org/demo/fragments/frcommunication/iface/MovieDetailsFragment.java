package org.demo.fragments.frcommunication.iface;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.demo.fragments.core.data.Movies;
import org.demo.fragments.core.model.Movie;
import org.demo.fragments.frcommunication.R;
import org.demo.fragments.frcommunication.rating.RatingBottomSheet;

/** Details for the "Interface" screen: the movie comes from the arguments. */
public class MovieDetailsFragment extends Fragment {
    private static final String ARG_MOVIE_ID = "movie_id";
    private static final String STATE_MY_RATING = "my_rating";

    private long movieId;
    private int myRating;
    private TextView myRatingView;

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
        if (savedInstanceState != null) {
            myRating = savedInstanceState.getInt(STATE_MY_RATING);
        }
        getChildFragmentManager().setFragmentResultListener(
                RatingBottomSheet.REQUEST_KEY, this,
                (requestKey, result) -> showMyRating(result.getInt(RatingBottomSheet.KEY_STARS)));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Movie movie = Movies.find(movieId);
        ((TextView) view.findViewById(R.id.title)).setText(movie.toString());
        ((TextView) view.findViewById(R.id.description)).setText(movie.getDescription());
        myRatingView = view.findViewById(R.id.my_rating);
        view.findViewById(R.id.rate_button).setOnClickListener(v -> openRating());
        showMyRating(myRating);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        myRatingView = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_MY_RATING, myRating);
    }

    private void openRating() {
        RatingBottomSheet.newInstance(movieId).show(getChildFragmentManager(), "rating");
    }

    private void showMyRating(int stars) {
        myRating = stars;
        if (myRatingView != null && stars > 0) {
            myRatingView.setText(getString(R.string.my_rating_format, stars));
        }
    }
}
