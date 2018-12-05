package bum.icehockeyfordummies.user_interface;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import bum.icehockeyfordummies.R;


public class MainActivity extends AppCompatActivity implements
NavigationView.OnNavigationItemSelectedListener {

    // Generic variables
    private Toolbar toolbar;
    private DrawerLayout layout;
    private NavigationView navigation;

    // Declare all fragments
    private Fragment fragGameCenter;
    private Fragment fragNL;
    private Fragment fragSL;
    private Fragment fragMSL;
    private Fragment fragRL;
    private Fragment fragClubs;
    private Fragment fragSettings;

    // Identify each fragment with a number
    private static final int FRAG_GAMECENTER = 0;
    private static final int FRAG_NL = 1;
    private static final int FRAG_SL = 2;
    private static final int FRAG_MSL = 3;
    private static final int FRAG_RL = 4;
    private static final int FRAG_CLUBS = 5;
    private static final int FRAG_SETTINGS = 6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set all the elements
        this.configureToolbar();
        this.configureDrawerLayout();
        this.configureNavigationView();

        // Show a default fragment on load
        this.showDefaultFragment();
    }


    // Define the behaviour when the back button is pressed
    @Override
    public void onBackPressed() {
        if (this.layout.isDrawerOpen(GravityCompat.START)) {
            this.layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    // Set the behaviour of the navigation drawer
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Check item as selected
        item.setChecked(true);
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_gamecenter:
                this.showFragment(FRAG_GAMECENTER);
                break;
            case R.id.menu_nationalleague:
                this.showFragment(FRAG_NL);
                break;
            case R.id.menu_swissleague:
                this.showFragment(FRAG_SL);
                break;
            case R.id.menu_mysportsleague:
                this.showFragment(FRAG_MSL);
                break;
            case R.id.menu_regioleague:
                this.showFragment(FRAG_RL);
                break;
            case R.id.menu_clubs:
                this.showFragment(FRAG_CLUBS);
                break;
            case R.id.menu_settings:
                this.showFragment(FRAG_SETTINGS);
                break;
            default:
                break;
        }

        this.layout.closeDrawer(GravityCompat.START);
        return true;
    }


    // Configuration of the toolbar
    private void configureToolbar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    // Configuration of the drawer layout
    private void configureDrawerLayout() {
        this.layout = (DrawerLayout) findViewById(R.id.menu_layout);

        // Move the menu icon (= effect) when open or close the navigation
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, layout, toolbar, R.string.navigation_open, R.string.navigation_close);
        layout.addDrawerListener(toggle);
        toggle.syncState();
    }


    // Configure the navigation view
    private void configureNavigationView() {
        this.navigation = (NavigationView) findViewById(R.id.menu_navigation);
        navigation.setNavigationItemSelectedListener(this);
    }


    // Show the right fragment according to the number
    private void showFragment(int idFrag){

        switch(idFrag) {
            case FRAG_GAMECENTER:
                this.showGameCenterFragment();
                break;
            case FRAG_NL:
                this.showNLFragment();
                break;
            case FRAG_SL:
                this.showSLFragment();
                break;
            case FRAG_MSL:
                this.showMSLFragment();
                break;
            case FRAG_RL:
                this.showRLFragment();
                break;
            case FRAG_CLUBS:
                this.showClubsFragment();
                break;
            case FRAG_SETTINGS:
                this.showSettingsFragment();
                break;
            default:
                break;
        }
    }


    // Methods for create each fragment
    private void showGameCenterFragment() {
        if (this.fragGameCenter == null) {
            this.fragGameCenter = GameCenterFragment.newInstance();
        }

        this.changeFragment(this.fragGameCenter);
    }

    private void showNLFragment() {
        if (this.fragNL == null) {
            this.fragNL = NLFragment.newInstance();
        }

        this.changeFragment(this.fragNL);
    }

    private void showSLFragment() {
        if (this.fragSL == null) {
            this.fragSL = SLFragment.newInstance();
        }

        this.changeFragment(this.fragSL);
    }

    private void showMSLFragment() {
        if (this.fragMSL == null) {
            this.fragMSL = MSLFragment.newInstance();
        }

        this.changeFragment(this.fragMSL);
    }

    private void showRLFragment() {
        if (this.fragRL == null) {
            this.fragRL = RLFragment.newInstance();
        }

        this.changeFragment(this.fragRL);
    }

    private void showClubsFragment() {
        if (this.fragClubs == null) {
            this.fragClubs = ClubsFragment.newInstance();
        }

        this.changeFragment(this.fragClubs);
    }

    private void showSettingsFragment() {
        if (this.fragSettings == null) {
            this.fragSettings = SettingsFragment.newInstance();
        }

        this.changeFragment(this.fragSettings);
    }


    // Change the visible fragment when user clicks
    private void changeFragment(Fragment fragment) {
        if (!fragment.isVisible()) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.screen_content, fragment).commit();
        }
    }


    // On load, show the default fragment if no one is already showing
    private void showDefaultFragment() {
        Fragment visible = getSupportFragmentManager().findFragmentById(R.id.screen_content);

        if (visible == null) {
            this.showFragment(FRAG_GAMECENTER);
            this.navigation.getMenu().getItem(0).setChecked(true);
        }
    }
}
