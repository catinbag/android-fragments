package org.demo.fragments.frcommunication.viewmodel;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.demo.fragments.frcommunication.R;
import org.demo.fragments.frcommunication.rating.RatingBottomSheet;

/** Details for the "ViewModel" screen: the movie comes from the shared MoviesViewModel. */
public class MovieDetailsFragment extends Fragment {
    private static final String STATE_MY_RATING = "my_rating";

    private TextView title;
    private TextView description;
    private TextView myRatingView;
    private long movieId = -1;
    private int myRating;

    public MovieDetailsFragment() {
        super(R.layout.fragment_movie_details);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            myRating = savedInstanceState.getInt(STATE_MY_RATING);
        }
        getChildFragmentManager().setFragmentResultListener(
                RatingBottomSheet.REQUEST_KEY, this,
                (requestKey, result) -> showMyRating(result.getInt(RatingBottomSheet.KEY_STARS)));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        myRatingView = view.findViewById(R.id.my_rating);
        view.findViewById(R.id.rate_button).setOnClickListener(v -> openRating());
        showMyRating(myRating);

        MoviesViewModel viewModel =
                new ViewModelProvider(requireActivity()).get(MoviesViewModel.class);
        viewModel.getSelected().observe(getViewLifecycleOwner(), movie -> {
            movieId = movie.getId();
            title.setText(movie.getTitle());
            description.setText(movie.getDescription());
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        title = null;
        description = null;
        myRatingView = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_MY_RATING, myRating);
    }

    private void openRating() {
        if (movieId >= 0) {
            RatingBottomSheet.newInstance(movieId).show(getChildFragmentManager(), "rating");
        }
    }

    private void showMyRating(int stars) {
        myRating = stars;
        if (myRatingView != null && stars > 0) {
            myRatingView.setText(getString(R.string.my_rating_format, stars));
        }
    }
}
