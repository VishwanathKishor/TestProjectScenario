package com.example.vishwanath.testprojecthttpscenario;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.vishwanath.Fragments.FragmentScenarioFirst;
import com.example.vishwanath.Fragments.FragmentScenarioSecond;

/**
 * Created by android-linux-mv on 8/11/17.
 */

public class MainActivity extends FragmentActivity {
    private DrawerLayout homeDrawerLayout;
    private ListView homeDrawerList;
    private ActionBarDrawerToggle homeDrawerToggle;
    private String[] drawerTitle = {"Scenario 1", "Scenario 2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_main);
            if (getActionBar() != null) {
                getActionBar().setHomeButtonEnabled(true);
                getActionBar().setIcon(R.drawable.drawer_icon);
                getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#da4336")));
            }

            homeDrawerLayout = (DrawerLayout) findViewById(R.id.homeDrawerLayout);
            homeDrawerList = (ListView) findViewById(R.id.homeDrawerList);

            String[] items = {"Scenario 1", "Scenario 2"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, items);
            homeDrawerList.setAdapter(adapter);

            homeDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    callToChangeFragment(position);
                }
            });

            homeDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,
                    homeDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
                public void onDrawerClosed(View view) {
                    super.onDrawerClosed(view);
                    invalidateOptionsMenu();
                }

                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    invalidateOptionsMenu();
                }

                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {
                    super.onDrawerSlide(drawerView, slideOffset);
                }

                @Override
                public void onDrawerStateChanged(int newState) {
                    super.onDrawerStateChanged(newState);
                }
            };
            callToChangeFragment(0);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (homeDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        homeDrawerToggle.syncState();
    }

    private void callToChangeFragment(int position) {
        try {
            Fragment newFragment = null;
            switch (position) {
                case 0:
                    newFragment = new FragmentScenarioFirst();
                    break;
                case 1:
                    newFragment = new FragmentScenarioSecond();
                    break;
            }
            FragmentTransaction transaction = MainActivity.this.getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.homeContentFrame, newFragment);
            transaction.commit();

            homeDrawerList.setItemChecked(position, true);
            setTitle(drawerTitle[position]);
            homeDrawerLayout.closeDrawer(homeDrawerList);
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

}