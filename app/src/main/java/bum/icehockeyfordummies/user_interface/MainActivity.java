package bum.icehockeyfordummies.user_interface;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import bum.icehockeyfordummies.R;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout layout;
    protected static int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Set the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);


        // Set the layout
        layout = findViewById(R.id.menu_layout);
        layout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );


        // Move the menu icon (= effect) when open or close the navigation
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, layout, toolbar, R.string.navigation_open, R.string.navigation_close);
        layout.setDrawerListener(toggle);
        toggle.syncState();


        // Set the behaviour of the navigation drawer
        NavigationView navigation = findViewById(R.id.menu_navigation);
        navigation.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Check item as selected
                        menuItem.setChecked(true);
                        int id = menuItem.getItemId();




                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        Intent intent = null;

                        if (id == R.id.menu_gamecenter) {
                            intent = new Intent(getApplicationContext(), GameCenterActivity.class);
                        }

                        startActivity(intent);



                        // Close the menu
                        layout.closeDrawers();
                        return true;
                    }
                }
        );
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                layout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
