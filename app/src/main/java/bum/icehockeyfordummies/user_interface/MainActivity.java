package bum.icehockeyfordummies.user_interface;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import bum.icehockeyfordummies.R;


public class MainActivity extends AppCompatActivity implements
NavigationView.OnNavigationItemSelectedListener {
    // Generic variables
    protected Toolbar toolbar;
    protected FrameLayout screen;
    protected DrawerLayout layout;
    protected NavigationView navigation;
    protected static int position;


    // Creation of the main activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set all the elements
        this.configureToolbar();
        this.configureLayout();
        this.configureNavigationView();
    }


    // Configuration of the toolbar
    private void configureToolbar() {
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    // Configuration of the layout
    private void configureLayout() {
        screen = findViewById(R.id.screen_content);
        this.layout = (DrawerLayout) findViewById(R.id.menu_layout);

        // Move the menu icon (= effect) when open or close the navigation
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, layout, toolbar, R.string.navigation_open, R.string.navigation_close);
        layout.addDrawerListener(toggle);
        toggle.syncState();
    }


    // Configure the navigation view
    private void configureNavigationView() {
        this.navigation = findViewById(R.id.menu_navigation);
        navigation.setNavigationItemSelectedListener(this);
    }


    // Define the behaviour when the back button is pressed
    public void onBackPressed() {
        if (this.layout.isDrawerOpen(GravityCompat.START)) {
            this.layout.closeDrawer(GravityCompat.START);
            return;
        }
        position = 0;
        super.onBackPressed();
    }


    // Set the behaviour of the navigation drawer
    public boolean onNavigationItemSelected(MenuItem item) {
        // Check item as selected
        item.setChecked(true);
        int id = item.getItemId();

        // Check if the item is already selected
        if (id == position) {
            layout.closeDrawer(GravityCompat.START);
            return false;
        }

        // Show the right screen according to the selected item
        position = id;
        Intent intent = null;

        switch(id) {
            case R.id.menu_gamecenter:
                intent = new Intent(this, GameCenterActivity.class);
                break;
            case R.id.menu_nationalleague:
                intent = new Intent(this, NLActivity.class);
                break;
            case R.id.menu_swissleague:
                intent = new Intent(this, SLActivity.class);
                break;
            case R.id.menu_mysportsleague:
                intent = new Intent(this, MSLActivity.class);
                break;
            case R.id.menu_regioleague:
                intent = new Intent(this, RLActivity.class);
                break;
            case R.id.menu_clubs:
                intent = new Intent(this, ClubsActivity.class);
                break;
            case R.id.menu_settings:
                intent = new Intent(this, SettingsActivity.class);
                break;
        }

        // Open the new activity
        if (intent != null) {
            intent.setFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_NO_ANIMATION
            );
            startActivity(intent);
        }
        this.layout.closeDrawer(GravityCompat.START);
        return true;
    }


    // Create the "plus" action button inside the toolbar
    // Generally it's hidden, except for the ClubsActivity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_action, menu);

        MenuItem item = menu.findItem(R.id.action_add);
        item.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;

        if (item.getItemId() == R.id.action_add) {
            intent = new Intent(this, AddClubActivity.class);
            startActivity(intent);
        }

        return true;
    }
}
