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
import bum.icehockeyfordummies.database.PlayerEntity;
import bum.icehockeyfordummies.database.PlayerRepository;
import bum.icehockeyfordummies.viewmodels.PlayerViewModel;


public class EditPlayerActivity extends AppCompatActivity {

    // Input values
    private EditText number;
    private EditText firstname;
    private EditText lastname;

    // Update the player
    private PlayerRepository repo;
    private PlayerViewModel viewModel;
    private PlayerEntity player;
    private String id;
    private FloatingActionButton button;
    private Spinner spinner;
    private int pNumber;
    private String pFirstname;
    private String pLastname;

    // Other elements
    private TextView birthdate;
    private TextView license;
    private ImageView portrait;
    private Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_player);

        // Retrieve all the elements
        repo = ((BaseApp) getApplication()).getPlayerRepository();
        id = getIntent().getStringExtra("idPlayer");
        button = findViewById(R.id.editplayer_button);
        number = findViewById(R.id.edit_number);
        firstname = findViewById(R.id.edit_firstname);
        lastname = findViewById(R.id.edit_lastname);
        birthdate = findViewById(R.id.edit_birthdate);
        license = findViewById(R.id.edit_license);
        portrait = findViewById(R.id.editplayer_portrait);
        spinner = findViewById(R.id.edit_position);


        // Retrieve the player
        PlayerViewModel.Factory factory = new PlayerViewModel.Factory(
                getApplication(), id);

        viewModel = ViewModelProviders.of(this, factory).get(PlayerViewModel.class);
        viewModel.getPlayer().observe(this, playerEntity -> {
                    if (playerEntity != null) {
                        player = playerEntity;

                        // Set general elements
                        String picture = player.getPicture();
                        if (!picture.equals("")) {
                            picture = picture.substring(0, picture.indexOf("."));
                            int id = getResources().getIdentifier(picture, "drawable", getApplicationContext().getPackageName());
                            portrait.setImageResource(id);
                        }

                        birthdate.setText(Integer.toString(player.getBirthdate()));
                        license.setText(player.getLicense());

                        // Set the correct values to the three EditText
                        number.setHint(Integer.toString(player.getNumber()));
                        firstname.setHint(player.getFirstname());
                        lastname.setHint(player.getLastname());

                        // Set the spinner
                        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                                R.array.positions_array, R.layout.items_spinner_player);
                        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);

                        String position = player.getPosition();
                        switch (position) {
                            case "Winger":
                                spinner.setSelection(0);
                                break;
                            case "Defense":
                                spinner.setSelection(1);
                                break;
                            case "Goalie":
                                spinner.setSelection(2);
                                break;
                        }


                        // Create the validation button
                        button.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                if (!verifyNumber() || !verifyFirstname() || !verifyLastname()) {
                                    return;
                                }

                                // Create the new entity "Player"
                                Map<String, Boolean> map = player.getClubs();
                                Map.Entry<String, Boolean> entry = map.entrySet().iterator().next();
                                String club = entry.getKey();

                                PlayerEntity upPlayer = new PlayerEntity(player.getBirthdate(), pFirstname,
                                        pLastname, player.getLicense(), pNumber,
                                        player.getPicture(), spinner.getSelectedItem().toString(), club);
                                upPlayer.setId(player.getId());
                                repo.update(upPlayer);

                                // Create a toast for the validation
                                toast = toast.makeText(getApplicationContext(), getString(R.string.player_updated), Toast.LENGTH_LONG);
                                TextView toastText = toast.getView().findViewById(android.R.id.message);
                                toastText.setTextColor(Color.WHITE);
                                toast.getView().getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                                toast.show();

                                // Close the page and return on the player page
                                Intent intent = new Intent(EditPlayerActivity.this, PlayerActivity.class);
                                intent.putExtra("idPlayer", id);

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


    // Back to the player page
    public void back(View view) {
        Intent intent = new Intent(EditPlayerActivity.this, PlayerActivity.class);
        intent.putExtra("idPlayer", id);
        startActivity(intent);
    }


    // Verify the three inputs (text)
    private boolean verifyNumber () {
        if (number.getText().toString().length() < 1) {
            pNumber = player.getNumber();
        } else {
            pNumber = Integer.parseInt(number.getText().toString());
        }

        return true;
    }

    private boolean verifyFirstname () {
        if (firstname.getText().toString().length() == 0) {
            pFirstname = player.getFirstname();
        } else {
            if (firstname.getText().toString().length() == 1) {
                firstname.setError(getString(R.string.player_name_error));
                firstname.requestFocus();
                return false;
            } else {
                pFirstname = firstname.getText().toString();
            }
        }

        return true;
    }

    private boolean verifyLastname () {
        if (lastname.getText().toString().length() == 0) {
            pLastname = player.getLastname();
        } else {
            if (lastname.getText().toString().length() == 1) {
                lastname.setError(getString(R.string.player_name_error));
                lastname.requestFocus();
                return false;
            } else {
                pLastname = lastname.getText().toString();
            }
        }

        return true;
    }
}
