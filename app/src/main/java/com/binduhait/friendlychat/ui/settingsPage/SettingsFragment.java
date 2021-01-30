package com.binduhait.friendlychat.ui.settingsPage;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;
import androidx.preference.SwitchPreferenceCompat;
import com.binduhait.friendlychat.R;

public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        SwitchPreferenceCompat switchPreference = findPreference("theme_toggle");
        switchPreference.setOnPreferenceChangeListener(this);
        findPreference("notification_enable").setOnPreferenceChangeListener(this);

        //to set switchPreference default value
        switchPreference.setChecked(currentSystemTheme() == 2);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        if (preference instanceof SwitchPreferenceCompat) {
            if (preference.getKey().equals("theme_toggle")) {
                if (((SwitchPreferenceCompat) preference).isChecked()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
            }
        }
        else if(preference instanceof CheckBoxPreference){
            if(preference.getKey().equals("notification_enable")){
                if (((CheckBoxPreference)preference).isChecked()) {
                    Toast.makeText(getContext(),"Notifications Disabled",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(),"Notifications Enabled",Toast.LENGTH_SHORT).show();
                }
            }
        }
        return true;
    }

    private int currentSystemTheme(){
        switch (getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_NO: return 1; //1 for light mode
            case Configuration.UI_MODE_NIGHT_YES: return 2; //2 for dark mode
        }
        return 0;
    }
}