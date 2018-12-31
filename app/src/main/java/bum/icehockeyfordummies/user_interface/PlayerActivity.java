package bum.icehockeyfordummies.user_interface;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import bum.icehockeyfordummies.R;
import bum.icehockeyfordummies.database.PlayerEntity;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // Retrieve all the elements related to the player
        portrait = findViewById(R.id.addplayer_portrait);
        name = findViewById(R.id.playerpage_name);
        birthdate = findViewById(R.id.playerpage_birthdate);
        position = findViewById(R.id.playerpage_position);
        license = findViewById(R.id.playerpage_license);
        edit = findViewById(R.id.playerpage_modify);
        delete = findViewById(R.id.playerpage_delete);

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
}
