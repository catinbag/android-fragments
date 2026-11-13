package org.demo.fragments.adaptive;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements OnMovieClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.container, MovieListFragment.class, null, "list")
                    .commit();
        } else {
            adaptRestoredDetails();
        }
    }

    private boolean isTwoPane() {
        return findViewById(R.id.details_pane) != null;   // landscape and tablet layouts only
    }

    @Override
    public void onMovieClick(long movieId) {
        if (isTwoPane()) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.details_pane, MovieDetailsFragment.newInstance(movieId), "details")
                    .commit();                       // no back stack: the details are beside the list
        } else {
            showDetails(movieId);                    // replace + addToBackStack, stage 5
        }
    }

    private void showDetails(long movieId) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.container, MovieDetailsFragment.newInstance(movieId), "details")
                .addToBackStack("details")
                .commit();
    }

    /**
     * Rotation changes the layout, but FragmentManager restores fragments with their old container id.
     * The list keeps "container" in every layout. The details do not: move them to the right place.
     */
    private void adaptRestoredDetails() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment details = fm.findFragmentByTag("details");
        if (details == null) {
            return;
        }
        long movieId = details.requireArguments().getLong(MovieDetailsFragment.ARG_MOVIE_ID);
        boolean onBackStack = fm.getBackStackEntryCount() > 0;
        if (isTwoPane() && onBackStack) {            // portrait -> landscape: details were full screen
            fm.popBackStackImmediate();
            onMovieClick(movieId);
        } else if (!isTwoPane() && !onBackStack) {   // landscape -> portrait: details were in the pane
            fm.beginTransaction().remove(details).commitNow();
            onMovieClick(movieId);
        }
    }
}
