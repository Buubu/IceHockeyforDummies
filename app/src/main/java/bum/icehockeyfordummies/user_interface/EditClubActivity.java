/* ©2018-2019, Montaine BURGER
   HES-SO Valais-Wallis, FIG */
package bum.icehockeyfordummies.user_interface;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Map;
import bum.icehockeyfordummies.R;
import bum.icehockeyfordummies.database.BaseApp;
import bum.icehockeyfordummies.database.ClubEntity;
import bum.icehockeyfordummies.database.ClubRepository;
import bum.icehockeyfordummies.viewmodels.ClubViewModel;


public class EditClubActivity extends AppCompatActivity {

    // Input values
    private EditText inName;
    private Spinner spinner;

    // Update the club
    private ClubRepository repo;
    private ClubViewModel viewModel;
    private ClubEntity club;
    private String id;
    private FloatingActionButton button;
    private String name;
    private String league;

    // Other elements
    private ImageView logo;
    private Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_club);

        // Retrieve all the elements
        repo = ((BaseApp) getApplication()).getClubRepository();
        id = getIntent().getStringExtra("idClub");
        inName = findViewById(R.id.editclub_name);
        spinner = findViewById(R.id.editclub_league);
        button = findViewById(R.id.editclub_button);
        logo = findViewById(R.id.editclub_logo);


        // Retrieve the club
        ClubViewModel.Factory factory = new ClubViewModel.Factory(
                getApplication(), id);

        viewModel = ViewModelProviders.of(this, factory).get(ClubViewModel.class);
        viewModel.getClub().observe(this, clubEntity -> {
            if (clubEntity != null) {
                club = clubEntity;

                // Set general elements
                String picture = club.getLogo();
                picture = picture.substring(0, picture.indexOf("."));
                int idLogo = getResources().getIdentifier(picture, "drawable", getApplicationContext().getPackageName());
                logo.setImageResource(idLogo);

                // Set the correct value to the EditText
                inName.setHint(club.getName());

                // Set the spinner
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.leagues_array, R.layout.items_spinner);
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                Map<String, Boolean> leagues = club.getLeagues();
                Map.Entry<String, Boolean> entry = leagues.entrySet().iterator().next();
                String key = entry.getKey();
                switch(key) {
                    case "national":
                        spinner.setSelection(0);
                        break;
                    case "swiss":
                        spinner.setSelection(1);
                        break;
                    case "mysports":
                        spinner.setSelection(2);
                        break;
                    case "regio":
                        spinner.setSelection(3);
                        break;
                }

                // Create the validation button
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (!verifyName()) {
                            return;
                        }

                        league = retrieveLeague();

                        // Create the new entity "Club"
                        ClubEntity upClub = new ClubEntity(club.getLogo(), name, league);
                        upClub.setId(club.getId());
                        upClub.setFavorite(club.getFavorite());
                        repo.update(upClub, key, league);

                        // Create a toast for the validation
                        toast = toast.makeText(getApplicationContext(), getString(R.string.club_updated), Toast.LENGTH_LONG);
                        TextView toastText = toast.getView().findViewById(android.R.id.message);
                        toastText.setTextColor(Color.WHITE);
                        toast.getView().getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                        toast.show();

                        // Close the page and return on the club page
                        Intent intent = new Intent(EditClubActivity.this, ClubActivity.class);
                        intent.putExtra("idClub", id);

                        Thread thread = new Thread() {
                            public void run() {
                                try {
                                    Thread.sleep(3500);
                                    startActivity(intent);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        };

                        thread.start();
                    }
                });
            }
        });
    }


    // Back to the club page
    public void back(View view) {
        Intent intent = new Intent(EditClubActivity.this, ClubActivity.class);
        intent.putExtra("idClub", id);
        startActivity(intent);
    }


    // Retrieve the right id of the league
    public String retrieveLeague() {
        String selection = spinner.getSelectedItem().toString();

        switch(selection) {
            case "National League":
                selection = "national";
                break;
            case "Swiss League":
                selection = "swiss";
                break;
            case "MySports League":
                selection = "mysports";
                break;
            case "Regio League / Elite":
                selection = "regio";
                break;
        }

        return selection;
    }


    // Verify the input
    private boolean verifyName() {
        if (inName.getText().toString().length() == 0) {
            name = club.getName();
        } else {
            if (inName.getText().toString().length() < 6) {
                inName.setError(getString(R.string.club_name_error));
                inName.requestFocus();
                return false;
            } else {
                name = inName.getText().toString();
            }
        }

        return true;
    }
}
