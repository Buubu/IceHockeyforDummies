/* ©2018-2019, Montaine BURGER
   HES-SO Valais-Wallis, FIG */
package bum.icehockeyfordummies.user_interface;

import android.os.Bundle;
import bum.icehockeyfordummies.R;


public class SLActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set the SL when checked
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_sl, screen);
        setTitle(R.string.swissleague);
        navigation.setCheckedItem(position);
    }
}
