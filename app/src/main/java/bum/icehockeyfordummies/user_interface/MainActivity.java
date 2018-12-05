package bum.icehockeyfordummies.user_interface;

import android.support.design.widget.NavigationView;
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
    private Toolbar toolbar;
    private DrawerLayout layout;
    private NavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set all the elements
        this.configureToolbar();
        this.configureDrawerLayout();
        this.configureNavigationView();
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


                break;
            case R.id.menu_nationalleague:


                break;
            case R.id.menu_swissleague:


                break;
            case R.id.menu_mysportsleague:


                break;
            case R.id.menu_regioleague:


                break;
            case R.id.menu_clubs:


                break;
            case R.id.menu_settings:


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

//        ActionBar actionbar = getSupportActionBar();
//        actionbar.setDisplayHomeAsUpEnabled(true);
//        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
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
}
