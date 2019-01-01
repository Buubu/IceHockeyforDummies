package bum.icehockeyfordummies.user_interface;

import android.os.Bundle;
import bum.icehockeyfordummies.R;


public class SettingsActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set the Settings when checked
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_settings, screen);
        setTitle(R.string.settings);
        navigation.setCheckedItem(position);
    }
}
