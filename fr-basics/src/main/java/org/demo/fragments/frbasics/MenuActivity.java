package org.demo.fragments.frbasics;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViewById(R.id.open_static).setOnClickListener(
                v -> startActivity(new Intent(this, StaticActivity.class)));
        findViewById(R.id.open_lifecycle).setOnClickListener(
                v -> startActivity(new Intent(this, LifecycleActivity.class)));
    }
}
