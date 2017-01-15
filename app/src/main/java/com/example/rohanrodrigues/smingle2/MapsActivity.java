package com.example.rohanrodrigues.smingle2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnMenuTabSelectedListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private CoordinatorLayout coordinatorLayout;
    private BottomBar bottomBar;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

     /*   Toolbar myToolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(myToolbar); */

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.three_buttons_activity);

        bottomBar = BottomBar.attach(this, savedInstanceState);

     /*   bottomBar = BottomBar.bind(getApplicationContext(), R.layout.activity_maps,
                savedInstanceState); */

   /*     bottomBar.setItems(
                new BottomBarTab(R.drawable.map_icon, "Map"),
                new BottomBarTab(R.drawable.message_content, "Favee"),
                new BottomBarTab(R.drawable.contacts_icon, "Contact"),
        ); */

        bottomBar.setItemsFromMenu(R.menu.bottom_bar, new OnMenuTabSelectedListener() {
            @Override
            public void onMenuItemSelected(int itemId) {
                switch (itemId) {
                    case R.id.locations:
                        Snackbar.make(coordinatorLayout, "Recent Item Selected", Snackbar.LENGTH_LONG).show();
                        Intent it = new Intent(getApplicationContext(), MapFragment.class);
                        startActivity(it);
                        break;
                    case R.id.message_content:
                        Snackbar.make(coordinatorLayout, "Favorite Item Selected", Snackbar.LENGTH_LONG).show();
                        Intent n = new Intent(getApplicationContext(), MessageActivity.class);
                        startActivity(n);
                        break;
                    case R.id.contacts:
                        Snackbar.make(coordinatorLayout, "Location Item Selected", Snackbar.LENGTH_LONG).show();
                        break;
                }

            }

        });

        bottomBar.setActiveTabColor("#55a8e5");

        // Use the dark theme. Ignored on mobile when there are more than three tabs.
        bottomBar.setBackgroundColor(Color.parseColor("#55a8e5"));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        bottomBar.onSaveInstanceState(outState);
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng bayArea = new LatLng(37, -122);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.addMarker(new MarkerOptions().position(bayArea).title("Marker in the Bay Area"));
     //   mMap.addMarker(new MarkerOptions().position)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bayArea));
    }


}


