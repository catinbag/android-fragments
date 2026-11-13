package org.demo.fragments.frcommunication.viewmodel;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import org.demo.fragments.frcommunication.BugSettings;
import org.demo.fragments.frcommunication.R;

/** The list only selects a movie. The Activity opens the details when the selection changes. */
public class ViewModelActivity extends AppCompatActivity {
    private boolean skipReplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        MoviesViewModel viewModel = new ViewModelProvider(this).get(MoviesViewModel.class);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.container, MovieListFragment.class, null, "list")
                    .commit();
        }
        // LiveData repeats its last value after rotation. A transition is an event: skip the repeat.
        skipReplay = savedInstanceState != null && viewModel.getSelected().getValue() != null;
        viewModel.getSelected().observe(this, movie -> {
            if (skipReplay) {
                skipReplay = false;
                return;
            }
            showDetails();
        });
    }

    private void showDetails() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.container, MovieDetailsFragment.class, null, "details")
                .addToBackStack("details")
                .commit();
    }

    // ---- menu with the error mode ----

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.viewmodel_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.bug_observe_this)
                .setChecked(BugSettings.isOn(this, BugSettings.OBSERVE_THIS));
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.bug_observe_this) {
            BugSettings.set(this, BugSettings.OBSERVE_THIS,
                    !BugSettings.isOn(this, BugSettings.OBSERVE_THIS));
            recreate();   // the list reads the mode in onViewCreated: rebuild the View
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
