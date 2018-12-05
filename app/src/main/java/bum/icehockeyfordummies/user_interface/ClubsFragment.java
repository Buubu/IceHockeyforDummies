package bum.icehockeyfordummies.user_interface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import bum.icehockeyfordummies.R;


public class ClubsFragment extends Fragment {

    // Constructor of the new instance
    public static ClubsFragment newInstance() {
        return (new ClubsFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_clubs, container, false);
    }

}
