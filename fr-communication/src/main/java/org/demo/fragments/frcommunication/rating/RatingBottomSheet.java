package org.demo.fragments.frcommunication.rating;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.demo.fragments.core.data.Movies;
import org.demo.fragments.frcommunication.R;

/** A bottom sheet with five buttons. It returns the stars through the Fragment Result API. */
public class RatingBottomSheet extends BottomSheetDialogFragment {
    public static final String REQUEST_KEY = "rating_request";
    public static final String KEY_STARS = "stars";

    private static final String ARG_MOVIE_ID = "movie_id";
    private static final int[] STAR_IDS = {
            R.id.star_1, R.id.star_2, R.id.star_3, R.id.star_4, R.id.star_5
    };

    public static RatingBottomSheet newInstance(long movieId) {
        Bundle args = new Bundle();
        args.putLong(ARG_MOVIE_ID, movieId);
        RatingBottomSheet sheet = new RatingBottomSheet();
        sheet.setArguments(args);
        return sheet;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sheet_rating, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        long movieId = requireArguments().getLong(ARG_MOVIE_ID);
        ((TextView) view.findViewById(R.id.sheet_title)).setText(
                getString(R.string.sheet_title_format, Movies.find(movieId).getTitle()));
        for (int i = 0; i < STAR_IDS.length; i++) {
            int stars = i + 1;
            view.findViewById(STAR_IDS[i]).setOnClickListener(v -> sendRating(stars));
        }
    }

    private void sendRating(int stars) {
        Bundle result = new Bundle();
        result.putInt(KEY_STARS, stars);
        getParentFragmentManager().setFragmentResult(REQUEST_KEY, result);
        dismiss();
    }
}
