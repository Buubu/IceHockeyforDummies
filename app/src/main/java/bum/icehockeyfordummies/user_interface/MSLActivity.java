package bum.icehockeyfordummies.user_interface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bum.icehockeyfordummies.R;

public class MSLActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set the Game center when checked
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_msl, screen);
        setTitle(R.string.mysportsleague);
        navigation.setCheckedItem(position);
    }
}
