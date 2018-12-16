package bum.icehockeyfordummies.user_interface;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import bum.icehockeyfordummies.adapters.ClubsAdapter;
import bum.icehockeyfordummies.R;
import bum.icehockeyfordummies.database.ClubEntity;
import bum.icehockeyfordummies.viewmodels.ClubsListViewModel;


public class ClubsFragment extends Fragment {

    private static final String TAG = "ClubsFragment";
    private List<ClubEntity> clubs;
    private ClubsListViewModel viewModel;


    // Constructor of the new instance
    public static ClubsFragment newInstance() {
        return (new ClubsFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clubs, container, false);
        getActivity().setTitle(R.string.clubs);

        RecyclerView recycler = view.findViewById(R.id.recycler_clubs);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(new ClubsAdapter());

        return view;
    }
}
