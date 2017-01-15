package com.example.rohanrodrigues.smingle2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import com.google.android.gms.maps.MapFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabSelectedListener;

/**
 * Created by rohanrodrigues on 1/14/17.
 */

public class MessageActivity extends Activity {
    private CoordinatorLayout coordinatorLayout;
    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_activity);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.three_buttons_activity);

        bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.setItemsFromMenu(R.menu.bottom_bar, new OnMenuTabSelectedListener() {
            Fragment objFragment = null;

            @Override
            public void onMenuItemSelected(int itemId) {
                switch (itemId) {
                    case R.id.locations:
                        Snackbar.make(coordinatorLayout, "Map Activity selected", Snackbar.LENGTH_LONG).show();
                /*        Intent map = new Intent(this, MapsActivity.class);
                        startActivity(map); */
                        Intent it = new Intent(getParent(), MapFragment.class);
                        startActivity(it);

                        break;
                    case R.id.message_content:
                        Snackbar.make(coordinatorLayout, "Favorite Item Selected", Snackbar.LENGTH_LONG).show();
                        Intent n = new Intent(getParent(), MessageActivity.class);
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


}
