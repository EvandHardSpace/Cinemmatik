package com.drammatik.cinema;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.drammatik.cinema.ui.about.AboutFragment;
import com.drammatik.cinema.ui.detailMovie.DetailFragment;
import com.drammatik.cinema.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.app.ShareCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, homeFragment)
                .addToBackStack("home fragment")
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.share_option) {
            shareButton();
        } else if (item.getItemId() == R.id.action_add_movie) {
            Snackbar snackbar = Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content),
                    "This feature will be available later :(", BaseTransientBottomBar.LENGTH_LONG);
            View snackBarView = snackbar.getView();
            snackBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            snackbar.show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void shareButton() {
        ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setText(getString(R.string.share_text))
                .setChooserTitle(R.string.share_with)
                .startChooser();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_home) {
            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.fragment_open_enter, R.anim.fragment_close_exit)
                    .replace(R.id.content_main, homeFragment, "findThisFragment")
                    .addToBackStack(null)
                    .commit();

        } else if (item.getItemId() == R.id.nav_about) {
            AboutFragment aboutFragment = new AboutFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.fragment_open_enter, R.anim.fragment_close_exit)
                    .replace(R.id.content_main, aboutFragment, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}