package com.binduhait.friendlychat.ui.settingsPage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.binduhait.friendlychat.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
    }
}