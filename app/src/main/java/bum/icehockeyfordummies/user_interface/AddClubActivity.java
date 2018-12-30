package bum.icehockeyfordummies.user_interface;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import bum.icehockeyfordummies.R;
import bum.icehockeyfordummies.database.BaseApp;
import bum.icehockeyfordummies.database.ClubEntity;
import bum.icehockeyfordummies.database.ClubRepository;


public class AddClubActivity extends AppCompatActivity {
    private Spinner spinner;
    private FloatingActionButton button;
    private EditText name;
    private TextInputLayout layout;
    private ClubRepository repo;
    private ClubEntity club;
    private String idLeague;
    private Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_club);

        // Retrieve all the elements
        repo = ((BaseApp) getApplication()).getClubRepository();
        spinner = findViewById(R.id.addclubinput_league);
        layout = findViewById(R.id.inputlayout_clubname);
        name = findViewById(R.id.addclubinput_name);
        button = findViewById(R.id.addclub_button);


        // Create the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.leagues_array, R.layout.items_spinner);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        // Create the validation button
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!verifyInput()) {
                    return;
                }

                switch (spinner.getSelectedItem().toString()) {
                    case "National League":
                        idLeague = "national";
                        break;
                    case "Swiss League":
                        idLeague = "swiss";
                        break;
                    case "MySports League":
                        idLeague = "mysports";
                        break;
                    case "Regio League / Elite":
                        idLeague = "regio";
                        break;
                }

                // Create the new club
                club = new ClubEntity("club.png", name.getText().toString(), idLeague);
                repo.insert(club, idLeague);

                // Create a toast for the validation
                toast = toast.makeText(getApplicationContext(), getString(R.string.club_created), Toast.LENGTH_LONG);
                TextView toastText = toast.getView().findViewById(android.R.id.message);
                toastText.setTextColor(Color.WHITE);
                toast.getView().getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                toast.show();

                // Close the page and return on the clubs list page
                Intent intent = new Intent(AddClubActivity.this, ClubsActivity.class);

                Thread thread = new Thread() {
                    public void run() {
                        try {
                            Thread.sleep(3500); // As I am using LENGTH_LONG in Toast
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


    // Verify the input
    private boolean verifyInput() {
        if (name.getText().toString().length() < 6) {
            layout.setError(getString(R.string.club_name_error));
            name.requestFocus();
            return false;
        } else {
            layout.setErrorEnabled(false);
        }

        return true;
    }
}
