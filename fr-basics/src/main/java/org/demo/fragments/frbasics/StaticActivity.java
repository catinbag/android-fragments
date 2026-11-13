package org.demo.fragments.frbasics;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/** The fragment comes from activity_static.xml (android:name). The Activity has no fragment code. */
public class StaticActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static);
    }
}
