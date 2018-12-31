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
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import bum.icehockeyfordummies.R;
import bum.icehockeyfordummies.database.BaseApp;
import bum.icehockeyfordummies.database.PlayerEntity;
import bum.icehockeyfordummies.database.PlayerRepository;


public class AddPlayerActivity extends AppCompatActivity {
    // General attributes
    private PlayerRepository repo;
    private PlayerEntity player;
    private String message;
    private String idClub;
    private String nameClub;
    private TextView club;
    private Toast toast;

    // Inputs
    private TextInputLayout firstnameLayout;
    private EditText firstname;
    private TextInputLayout lastnameLayout;
    private EditText lastname;
    private NumberPicker number;
    private NumberPicker birthdate;
    private Spinner position;
    private Spinner license;
    private FloatingActionButton button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        // Retrieve all the general elements
        repo = ((BaseApp) getApplication()).getPlayerRepository();
        message = getIntent().getStringExtra("Club");
        idClub = message.substring(0, message.indexOf(";"));
        nameClub = message.substring(message.indexOf(";") + 1, message.length());
        club = findViewById(R.id.addplayer_club);
        club.setText(nameClub);

        // Retrieve the inputs
        firstnameLayout = findViewById(R.id.inputlayout_pfirstname);
        firstname = findViewById(R.id.addplayer_firstname);
        lastnameLayout = findViewById(R.id.inputlayout_plastname);
        lastname = findViewById(R.id.addplayer_lastname);
        number = findViewById(R.id.addplayer_number);
        birthdate = findViewById(R.id.addplayer_birthdate);
        position = findViewById(R.id.addplayer_position);
        license = findViewById(R.id.addplayer_license);
        button = findViewById(R.id.addplayer_button);

        // Set the values of the both number pickers
        number.setMinValue(1);
        number.setMaxValue(99);

        birthdate.setMinValue(1940);
        birthdate.setMaxValue(2009);
        birthdate.setValue(1990);

        // Create the both spinners
        ArrayAdapter<CharSequence> padapter = ArrayAdapter.createFromResource(this,
                R.array.positions_array, R.layout.items_spinner_player);
        padapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        position.setAdapter(padapter);

        ArrayAdapter<CharSequence> ladapter = ArrayAdapter.createFromResource(this,
                R.array.licenses_array, R.layout.items_spinner_player);
        ladapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        license.setAdapter(ladapter);


        // Create the validation button
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!verifyFirstname() || !verifyLastname()) {
                    return;
                }

                // Create the new player
                player = new PlayerEntity(birthdate.getValue(), firstname.getText().toString(),
                        lastname.getText().toString(), license.getSelectedItem().toString(),
                        number.getValue(), "player.jpg", position.getSelectedItem().toString(), idClub);
                repo.insert(player, idClub);

                // Create a toast for the validation
                toast = toast.makeText(getApplicationContext(), getString(R.string.player_created), Toast.LENGTH_LONG);
                TextView toastText = toast.getView().findViewById(android.R.id.message);
                toastText.setTextColor(Color.WHITE);
                toast.getView().getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                toast.show();

                // Close the page and return on the club page
                Intent intent = new Intent(AddPlayerActivity.this, ClubActivity.class);
                intent.putExtra("idClub", idClub);

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


    // Verify the both inputs (text)
    private boolean verifyFirstname() {
        if (firstname.getText().toString().length() < 2) {
            firstnameLayout.setError(getString(R.string.player_name_error));
            firstname.requestFocus();
            return false;
        } else {
            firstnameLayout.setErrorEnabled(false);
        }

        return true;
    }

    private boolean verifyLastname() {
        if (lastname.getText().toString().length() < 2) {
            lastnameLayout.setError(getString(R.string.player_name_error));
            lastname.requestFocus();
            return false;
        } else {
            lastnameLayout.setErrorEnabled(false);
        }

        return true;
    }
}
