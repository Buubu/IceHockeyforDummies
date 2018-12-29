package bum.icehockeyfordummies.user_interface;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import bum.icehockeyfordummies.R;
import bum.icehockeyfordummies.adapters.ItemClickListener;
import bum.icehockeyfordummies.adapters.ClubsAdapter;
import bum.icehockeyfordummies.database.ClubEntity;
import bum.icehockeyfordummies.viewmodels.ClubsListViewModel;


public class ClubsActivity extends MainActivity {
    private static final String TAG = "ClubsActivity";
    private List<ClubEntity> clubs;
    private ClubsAdapter adapter;
    private ClubsListViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set the Clubs page when checked
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_clubs, screen);
        setTitle(R.string.clubs);
        navigation.setCheckedItem(position);


        RecyclerView recycler = findViewById(R.id.recycler_clubs);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);


        clubs = new ArrayList<>();
        adapter = new ClubsAdapter(new ItemClickListener() {
            public void onItemClick(View view, int position) {
                Log.d(TAG, "clicked position: " + position);
                Log.d(TAG, "clicked on: " + clubs.get(position).getName());

                Intent intent = new Intent(ClubsActivity.this, ClubActivity.class);
                intent.putExtra("idClub", clubs.get(position).getId());
                startActivity(intent);
            }
        });


        ClubsListViewModel.Factory factory = new ClubsListViewModel.Factory(
                getApplication());

        viewModel = ViewModelProviders.of(this, factory).get(ClubsListViewModel.class);
        viewModel.getAllClubs().observe(this, clubEntities -> {
            if (clubEntities != null) {
                clubs = clubEntities;
                adapter.setData(clubs);
            }
        });

        recycler.setAdapter(adapter);
    }


    // Overrided method
    public boolean onNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == MainActivity.position) {
            layout.closeDrawer(GravityCompat.START);
            return false;
        }

        finish();
        return super.onNavigationItemSelected(item);
    }
}
