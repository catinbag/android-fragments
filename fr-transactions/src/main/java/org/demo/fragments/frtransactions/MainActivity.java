package org.demo.fragments.frtransactions;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements OnMovieClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        watchFragmentCount();
        // Mode "no check": the condition is always true, a restored fragment gets a twin.
        if (savedInstanceState == null || BugSettings.isOn(this, Bug.NO_STATE_CHECK)) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.container, MovieListFragment.class, null, "list")
                    .commit();
            if (BugSettings.isOn(this, Bug.CALL_AFTER_COMMIT)) {
                callAfterCommit();
            }
        }
    }

    @Override
    public void onMovieClick(long movieId) {
        showDetails(movieId);
    }

    void showDetails(long movieId) {
        Fragment details = BugSettings.isOn(this, Bug.CONSTRUCTOR_ARG)
                ? new BrokenDetailsFragment(movieId)           // mode "constructor"
                : MovieDetailsFragment.newInstance(movieId);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.container, details, "details")
                .addToBackStack("details")
                .commit();
    }

    /** Mode "call after commit()": the transaction is only queued, the fragment is not there yet. */
    private void callAfterCommit() {
        BugSettings.set(this, Bug.CALL_AFTER_COMMIT, false);   // one shot: the next start is safe
        MovieListFragment list =
                (MovieListFragment) getSupportFragmentManager().findFragmentByTag("list");
        list.showHint("Select a movie");   // list == null -> NullPointerException
    }

    // ---- menu with the error modes ----

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        for (Bug bug : Bug.values()) {
            menu.findItem(menuId(bug)).setChecked(BugSettings.isOn(this, bug));
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        for (Bug bug : Bug.values()) {
            if (item.getItemId() == menuId(bug)) {
                BugSettings.set(this, bug, !BugSettings.isOn(this, bug));
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private static int menuId(Bug bug) {
        switch (bug) {
            case NO_STATE_CHECK:
                return R.id.bug_no_state_check;
            case CONSTRUCTOR_ARG:
                return R.id.bug_constructor;
            default:
                return R.id.bug_call_after_commit;
        }
    }

    // ---- the counter in the subtitle: how many fragments the FragmentManager holds ----

    private void watchFragmentCount() {
        getSupportFragmentManager().registerFragmentLifecycleCallbacks(
                new FragmentManager.FragmentLifecycleCallbacks() {
                    @Override
                    public void onFragmentAttached(@NonNull FragmentManager fm,
                                                   @NonNull Fragment f, @NonNull Context context) {
                        scheduleCountUpdate();
                    }

                    @Override
                    public void onFragmentDetached(@NonNull FragmentManager fm, @NonNull Fragment f) {
                        scheduleCountUpdate();
                    }

                    @Override
                    public void onFragmentViewCreated(@NonNull FragmentManager fm,
                                                      @NonNull Fragment f, @NonNull View v,
                                                      Bundle savedInstanceState) {
                        scheduleCountUpdate();
                    }

                    @Override
                    public void onFragmentViewDestroyed(@NonNull FragmentManager fm,
                                                        @NonNull Fragment f) {
                        scheduleCountUpdate();
                    }
                }, false);
        scheduleCountUpdate();
    }

    // post: the list of fragments is final only after the transaction ends
    private void scheduleCountUpdate() {
        getWindow().getDecorView().post(() -> {
            ActionBar bar = getSupportActionBar();
            if (bar != null) {
                bar.setSubtitle("Fragments: " + getSupportFragmentManager().getFragments().size());
            }
        });
    }
}
