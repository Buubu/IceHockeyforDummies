package bum.icehockeyfordummies.user_interface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bum.icehockeyfordummies.R;

public class RLActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set the Game center when checked
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_rl, screen);
        setTitle(R.string.regioleague);
        navigation.setCheckedItem(position);
    }
}
