package bum.icehockeyfordummies.user_interface;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.GravityCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import bum.icehockeyfordummies.R;
import bum.icehockeyfordummies.adapters.ClubsAdapter;
import bum.icehockeyfordummies.adapters.ItemClickListener;
import bum.icehockeyfordummies.database.ClubEntity;
import bum.icehockeyfordummies.viewmodels.FavoritesViewModel;


public class GameCenterActivity extends MainActivity {

    // General values
    private static final String TAG = "GameCenterActivity";
    private List<ClubEntity> favorites;
    private ClubsAdapter adapter;
    private FavoritesViewModel viewModel;

    // Elements
    private ConstraintLayout favLayout;
    private TextView anyFav;
    private RecyclerView recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set the Game center when checked
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_game_center, screen);
        setTitle(R.string.app_name);
        navigation.setCheckedItem(position);

        recycler = findViewById(R.id.recycler_favorites);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        favLayout = findViewById(R.id.anyfav_layout);
        anyFav = findViewById(R.id.anyfav_text);


        favorites = new ArrayList<>();
        adapter = new ClubsAdapter(new ItemClickListener() {
            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked position: " + position);
                Log.d(TAG, "clicked on: " + favorites.get(position).getName());

                Intent intent = new Intent(GameCenterActivity.this, ClubActivity.class);
                intent.putExtra("idClub", favorites.get(position).getId());
                startActivity(intent);
            }
        });


        FavoritesViewModel.Factory factory = new FavoritesViewModel.Factory(
                getApplication());

        viewModel = ViewModelProviders.of(this, factory).get(FavoritesViewModel.class);
        viewModel.getAllFavorites().observe(this, favEntities -> {
            if (favEntities != null && favEntities.size() != 0) {
                favorites = favEntities;
                adapter.setData(favorites);

                favLayout.setVisibility(View.INVISIBLE);
                anyFav.setVisibility(View.INVISIBLE);
                recycler.setVisibility(View.VISIBLE);
                recycler.setAdapter(adapter);
            } else {
                recycler.setVisibility(View.INVISIBLE);
                favLayout.setVisibility(View.VISIBLE);
                anyFav.setVisibility(View.VISIBLE);
            }
        });
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
