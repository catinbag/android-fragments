package org.demo.fragments.frcommunication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.demo.fragments.frcommunication.iface.InterfaceActivity;
import org.demo.fragments.frcommunication.viewmodel.ViewModelActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViewById(R.id.open_interface).setOnClickListener(
                v -> startActivity(new Intent(this, InterfaceActivity.class)));
        findViewById(R.id.open_viewmodel).setOnClickListener(
                v -> startActivity(new Intent(this, ViewModelActivity.class)));
    }
}
