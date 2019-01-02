/* ©2018-2019, Montaine BURGER
   HES-SO Valais-Wallis, FIG */
package bum.icehockeyfordummies.user_interface;

import android.os.Bundle;
import bum.icehockeyfordummies.R;


public class MSLActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set the MSL when checked
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_msl, screen);
        setTitle(R.string.mysportsleague);
        navigation.setCheckedItem(position);
    }
}
