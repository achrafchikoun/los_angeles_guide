package com.achraf.sport.cote.los_angles_guide;

import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.achraf.sport.cote.los_angles_guide.Fragments.HotelsFragment;
import com.achraf.sport.cote.los_angles_guide.Fragments.PopularFragment;
import com.achraf.sport.cote.los_angles_guide.Fragments.RestaurantsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HotelsFragment.OnFragmentInteractionListener,
        RestaurantsFragment.OnFragmentInteractionListener
{

    Toolbar toolbar;
    DrawerLayout drawer;
    Fragment fragment;
    FragmentTransaction transaction;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDrawerNavigation();
        setTitle(this.getResources().getString(R.string.popular));
        fragment = new PopularFragment();
        setFragmentManager(fragment);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        //super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
       /* int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    /****
     * Drawer navigation start
     ****/
    public void initDrawerNavigation() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_hotel);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        switch (item.getItemId()) {
            case R.id.nav_popular:
                setTitle(this.getResources().getString(R.string.popular));
                fragment = new PopularFragment();
                setFragmentManager(fragment);
                break;
            case R.id.nav_hotel:
                setTitle(this.getResources().getString(R.string.hotels));
                fragment = new HotelsFragment();
                setFragmentManager(fragment);
                break;
            case R.id.nav_restaurant:
                setTitle(this.getResources().getString(R.string.restaurants));
                fragment = new RestaurantsFragment();
                setFragmentManager(fragment);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /**** Drawer navigation end ****/

    /****
     * Starting fragments manager
     *****/
    public void setFragmentManager(Fragment fragment) {

        if (!isFinishing()) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
    /**** Ending fragment manager ****/
}
