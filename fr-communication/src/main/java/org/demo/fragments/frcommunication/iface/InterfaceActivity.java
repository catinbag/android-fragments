package org.demo.fragments.frcommunication.iface;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.demo.fragments.frcommunication.R;

public class InterfaceActivity extends AppCompatActivity implements OnMovieClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.container, MovieListFragment.class, null, "list")
                    .commit();
        }
    }

    @Override
    public void onMovieClick(long movieId) {
        Log.d("InterfaceDemo", "onMovieClick id=" + movieId);
        showDetails(movieId);
    }

    private void showDetails(long movieId) {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.container, MovieDetailsFragment.newInstance(movieId), "details")
                .addToBackStack("details")
                .commit();
    }
}
