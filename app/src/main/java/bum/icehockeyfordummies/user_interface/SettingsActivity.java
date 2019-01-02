/* ©2018-2019, Montaine BURGER
   HES-SO Valais-Wallis, FIG */
package bum.icehockeyfordummies.user_interface;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import bum.icehockeyfordummies.R;
import bum.icehockeyfordummies.database.BaseApp;
import bum.icehockeyfordummies.database.ClubEntity;
import bum.icehockeyfordummies.database.ClubRepository;
import bum.icehockeyfordummies.database.PlayerEntity;
import bum.icehockeyfordummies.database.PlayerRepository;


public class SettingsActivity extends MainActivity {
    private Button button;
    private List<PlayerEntity> userP;
    private List<ClubEntity> userC;
    private ClubRepository cRepo;
    private PlayerRepository pRepo;
    private Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set the Settings when checked
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_settings, screen);
        setTitle(R.string.settings);
        navigation.setCheckedItem(position);

        // Retrieve the elements
        cRepo = ((BaseApp) getApplication()).getClubRepository();
        pRepo = ((BaseApp) getApplication()).getPlayerRepository();
        button = findViewById(R.id.delete_all);


        // Retrieve the players
        pRepo.getUPlayers().observe(this, playerEntities -> {
            if (playerEntities != null) {
                userP = playerEntities;
            }
        });

        // Retrieve the clubs
        cRepo.getUClubs().observe(this, clubEntities -> {
            if (clubEntities != null) {
                userC = clubEntities;
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Delete all the users' data
                if (userP != null) {
                    pRepo.deleteAll(userP);
                }
                if (userC != null) {
                    cRepo.deleteAll(userC);
                }

                // Toast of confirmation
                toast = toast.makeText(getApplicationContext(), getString(R.string.delete_all_success), Toast.LENGTH_LONG);
                TextView toastText = toast.getView().findViewById(android.R.id.message);
                toastText.setTextColor(Color.WHITE);
                toast.getView().getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                toast.show();
            }
        });
    }
}
