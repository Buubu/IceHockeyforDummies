package bum.icehockeyfordummies.user_interface;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Map;
import bum.icehockeyfordummies.R;
import bum.icehockeyfordummies.database.BaseApp;
import bum.icehockeyfordummies.database.PlayerEntity;
import bum.icehockeyfordummies.database.PlayerRepository;
import bum.icehockeyfordummies.viewmodels.PlayerViewModel;


public class PlayerActivity extends AppCompatActivity {
    private static final String TAG = "PlayerActivity";
    private PlayerEntity player;
    private String idPlayer;

    // Elements on the page
    private ImageView portrait;
    private TextView name;
    private TextView birthdate;
    private TextView position;
    private TextView license;
    private PlayerViewModel viewModel;

    // Actions of the page
    private ImageView edit;
    private ImageView delete;
    private Toast toast;
    private PlayerRepository repo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // Retrieve all the elements related to the player
        portrait = findViewById(R.id.editplayer_portrait);
        name = findViewById(R.id.editplayer_background);
        birthdate = findViewById(R.id.edit_birthdate);
        position = findViewById(R.id.edit_position);
        license = findViewById(R.id.edit_license);
        edit = findViewById(R.id.playerpage_modify);
        delete = findViewById(R.id.playerpage_delete);
        repo = ((BaseApp) getApplication()).getPlayerRepository();

        // Retrieve the value passed through the intent
        idPlayer = getIntent().getStringExtra("idPlayer");


        // Create the right player page
        PlayerViewModel.Factory factory = new PlayerViewModel.Factory(
                getApplication(), idPlayer);

        viewModel = ViewModelProviders.of(this, factory).get(PlayerViewModel.class);
        viewModel.getPlayer().observe(this, playerEntity -> {
            if (playerEntity != null) {
                player = playerEntity;

                // Set the right name
                String number = "#" + player.getNumber();
                String label = number + " " + player.toString();
                name.setText(label);

                // Set the right logo
                String picture = player.getPicture();

                if (!picture.equals("")) {
                    picture = picture.substring(0, picture.indexOf("."));
                    int id = getResources().getIdentifier(picture, "drawable", getApplicationContext().getPackageName());
                    portrait.setImageResource(id);
                }

                // Set the player details
                birthdate.setText(Integer.toString(player.getBirthdate()));
                position.setText(player.getPosition());
                license.setText(player.getLicense());

                // Set the edit/delete buttons
                if (player.getSystem()) {
                    edit.setEnabled(false);
                    delete.setEnabled(false);
                } else {
                    edit.setEnabled(true);
                    delete.setEnabled(true);
                }
            }
        });
    }


    // Edit the player
    public void editPlayer(View view) {
        Intent intent = new Intent(PlayerActivity.this, EditPlayerActivity.class);
        intent.putExtra("idPlayer", idPlayer);
        startActivity(intent);
    }


    // Delete the player
    public void removePlayer(View view) {

        // Retrieve the club
        Map.Entry<String, Boolean> entry = player.getClubs().entrySet().iterator().next();
        String idClub = entry.getKey();

        // Create a toast for the confirmation
        toast = toast.makeText(getApplicationContext(), getString(R.string.player_deleted), Toast.LENGTH_LONG);
        TextView toastText = toast.getView().findViewById(android.R.id.message);
        toastText.setTextColor(Color.WHITE);
        toast.getView().getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
        toast.show();

        repo.delete(idPlayer, idClub);
        Intent intent = new Intent(PlayerActivity.this, ClubActivity.class);
        intent.putExtra("idClub", idClub);
        startActivity(intent);
    }
}
