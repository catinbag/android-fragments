package org.demo.fragments.frbasics;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/** Writes its callbacks with the same tag as the fragments. The log line comes before super. */
public class LifecycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LifecycleLog.d(this, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.container, LifecycleListFragment.class, null, "list")
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        LifecycleLog.d(this, "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        LifecycleLog.d(this, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        LifecycleLog.d(this, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        LifecycleLog.d(this, "onStop");
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        LifecycleLog.d(this, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        LifecycleLog.d(this, "onDestroy");
        super.onDestroy();
    }
}
