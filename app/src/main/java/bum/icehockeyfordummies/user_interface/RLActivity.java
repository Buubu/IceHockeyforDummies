/* ©2018-2019, Montaine BURGER
   HES-SO Valais-Wallis, FIG */
package bum.icehockeyfordummies.user_interface;

import android.os.Bundle;
import bum.icehockeyfordummies.R;


public class RLActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set the RL when checked
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_rl, screen);
        setTitle(R.string.regioleague);
        navigation.setCheckedItem(position);
    }
}
