package bum.icehockeyfordummies.user_interface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import bum.icehockeyfordummies.R;


public class GameCenterFragment extends Fragment {

    // Constructor of the new instance
    public static GameCenterFragment newInstance() {
        return (new GameCenterFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_center, container, false);

    }
}
