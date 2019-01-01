package bum.icehockeyfordummies.user_interface;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import bum.icehockeyfordummies.R;
import bum.icehockeyfordummies.adapters.ItemClickListener;
import bum.icehockeyfordummies.adapters.PlayersAdapter;
import bum.icehockeyfordummies.database.BaseApp;
import bum.icehockeyfordummies.database.ClubEntity;
import bum.icehockeyfordummies.database.ClubRepository;
import bum.icehockeyfordummies.database.PlayerEntity;
import bum.icehockeyfordummies.viewmodels.ClubViewModel;
import bum.icehockeyfordummies.viewmodels.PlayersListViewModel;


public class ClubActivity extends AppCompatActivity {
    private static final String TAG = "ClubActivity";

    // RecyclerView
    private List<PlayerEntity> players;
    private PlayersAdapter adapter;
    private PlayersListViewModel viewModel;
    private String idClub;

    // Other elements on the page
    private TextView name;
    private ImageView logo;
    private ImageView favorite;
    private ImageView edit;
    private ImageView delete;
    private ClubEntity club;
    private ClubViewModel clubModel;

    // Club repository
    private ClubRepository repo;
    private Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);

        // Retrieve all the elements related to the club
        repo = ((BaseApp) getApplication()).getClubRepository();
        name = findViewById(R.id.clubpage_name);
        logo = findViewById(R.id.addclub_logo);
        favorite = findViewById(R.id.clubpage_favorite);
        edit = findViewById(R.id.clubpage_modify);
        delete = findViewById(R.id.clubpage_delete);

        // Retrieve the value passed through the intent
        idClub = getIntent().getStringExtra("idClub");


        // Create the right club page
        ClubViewModel.Factory factory = new ClubViewModel.Factory(
                getApplication(), idClub);

        clubModel = ViewModelProviders.of(this, factory).get(ClubViewModel.class);
        clubModel.getClub().observe(this, clubEntity -> {
            if (clubEntity != null) {
                club = clubEntity;

                // Set the right name
                name.setText(club.getName());

                // Set the right logo
                String picture = club.getLogo();
                picture = picture.substring(0, picture.indexOf("."));
                int id = getResources().getIdentifier(picture, "drawable", getApplicationContext().getPackageName());
                logo.setImageResource(id);

                // Set the favorite button
                if (club.getFavorite()) {
                    favorite.setImageResource(R.drawable.ic_clubpage_favorite);
                } else {
                    favorite.setImageResource(R.drawable.ic_clubpage_notfavorite);
                }

                // Set the delete button
                if (club.getSystem()) {
                    edit.setEnabled(false);
                    delete.setEnabled(false);

                } else {
                    edit.setEnabled(true);
                    delete.setEnabled(true);
                }
            }
        });


        // Retrieve the list of players for this club
        RecyclerView recycler = findViewById(R.id.clubpage_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);


        // When the user clicks on a player, his personal page is opened
        players = new ArrayList<>();
        adapter = new PlayersAdapter(new ItemClickListener() {
            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked position: " + position);
                Log.d(TAG, "clicked on: " + players.get(position).toString());

                Intent intent = new Intent(ClubActivity.this, PlayerActivity.class);
                intent.putExtra("idPlayer", players.get(position).getId());
                startActivity(intent);
            }
        });


        // Create the list of players
        PlayersListViewModel.Factory listFactory = new PlayersListViewModel.Factory(
                getApplication(), idClub);

        viewModel = ViewModelProviders.of(this, listFactory).get(PlayersListViewModel.class);
        viewModel.getAllPlayers().observe(this, playerEntities -> {
            if (playerEntities != null) {
                players = playerEntities;
                adapter.setData(players);
            }
        });

        recycler.setAdapter(adapter);
    }


    // Mark a club as favorite
    public void markFavorite(View view) {
        repo.favorite(idClub, club.getFavorite());
    }


    // Edit the club
    public void editClub(View view) {
        Intent intent = new Intent(ClubActivity.this, EditClubActivity.class);
        intent.putExtra("idClub", idClub);
        startActivity(intent);
    }


    // Delete the club
    public void removeClub(View view) {
        // Retrieve the league
        Map.Entry<String, Boolean> entry = club.getLeagues().entrySet().iterator().next();
        String league = entry.getKey();

        // Retrieve the players
        Map<String, Boolean> data = club.getPlayers();
        ArrayList<String> players = new ArrayList<>();

        if (data != null) {
            for (Map.Entry<String, Boolean> set : data.entrySet()) {
                players.add(set.getKey());
            }
        }

        // Create a toast for the confirmation
        toast = toast.makeText(getApplicationContext(), getString(R.string.club_deleted), Toast.LENGTH_LONG);
        TextView toastText = toast.getView().findViewById(android.R.id.message);
        toastText.setTextColor(Color.WHITE);
        toast.getView().getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
        toast.show();

        repo.delete(idClub, league, players);
        Intent intent = new Intent(ClubActivity.this, ClubsActivity.class);
        startActivity(intent);
    }


    // Add a new player
    public void addPlayer(View view) {
        Intent intent = new Intent(ClubActivity.this, AddPlayerActivity.class);
        intent.putExtra("Club", idClub+";"+club.getName());
        startActivity(intent);
    }


    // Back to the list of clubs
    public void back(View view) {
        Intent intent = new Intent(ClubActivity.this, ClubsActivity.class);
        startActivity(intent);
    }
}
