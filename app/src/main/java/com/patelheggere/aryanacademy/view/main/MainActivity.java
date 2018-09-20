package com.patelheggere.aryanacademy.view.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.patelheggere.aryanacademy.R;
import com.patelheggere.aryanacademy.base.BaseActivity;
import com.patelheggere.aryanacademy.fragments.aboutus.AboutFragment;
import com.patelheggere.aryanacademy.fragments.currentaffairs.CurrentAffairsFragment;
import com.patelheggere.aryanacademy.fragments.jobs.JobUpdatesFragment;
import com.patelheggere.aryanacademy.helper.SharedPrefsHelper;

import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.LANGUAGE;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        CurrentAffairsFragment.OnFragmentInteractionListener,
        JobUpdatesFragment.OnFragmentInteractionListener,
        AboutFragment.OnFragmentInteractionListener {
    private static final String TAG = "MainActivity";

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right);
            Fragment curFrag = fragmentManager.getPrimaryNavigationFragment();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Log.d(TAG, "onNavigationItemSelected: ");
                    if (curFrag != null) {
                        fragmentTransaction.detach(curFrag);
                    }
                    Fragment fragment = fragmentManager.findFragmentByTag("ABOUT");
                    if (fragment == null) {
                        fragment = new AboutFragment();
                        fragmentTransaction.add(R.id.contentFrame, fragment, "ABOUT");
                    } else {
                        fragmentTransaction.attach(fragment);
                    }
                    fragmentTransaction.setPrimaryNavigationFragment(fragment);
                    fragmentTransaction.setReorderingAllowed(true);
                    fragmentTransaction.commitNowAllowingStateLoss();

                    return true;

                case R.id.navigation_work:
                    if (curFrag != null) {
                        fragmentTransaction.detach(curFrag);
                    }
                    fragment = fragmentManager.findFragmentByTag("CURRENT");
                    if (fragment == null) {
                        fragment = new CurrentAffairsFragment();
                        fragmentTransaction.add(R.id.contentFrame, fragment, "CURRENT");
                    } else {
                        fragmentTransaction.attach(fragment);
                    }
                    fragmentTransaction.setPrimaryNavigationFragment(fragment);
                    fragmentTransaction.setReorderingAllowed(true);
                    fragmentTransaction.commitNowAllowingStateLoss();
                    // mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    if (curFrag != null) {
                        fragmentTransaction.detach(curFrag);
                    }
                    fragment = fragmentManager.findFragmentByTag("JOB");
                    if (fragment == null) {
                        fragment = new JobUpdatesFragment();
                        fragmentTransaction.add(R.id.contentFrame, fragment, "JOB");
                    } else {
                        fragmentTransaction.attach(fragment);
                    }
                    fragmentTransaction.setPrimaryNavigationFragment(fragment);
                    fragmentTransaction.setReorderingAllowed(true);
                    fragmentTransaction.commitNowAllowingStateLoss();
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;

            }
            return false;
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_kannada) {
            showConfirmationDialog("ka");
            return true;
        }
        //noinspection SimplifiableIfStatement
        else if (id == R.id.action_english) {
            showConfirmationDialog("en");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_events) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_contact_us) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showConfirmationDialog(final String language) {
        SharedPrefsHelper.getInstance().save(LANGUAGE, language);

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle(R.string.confirmation);
        alertBuilder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPrefsHelper.getInstance().save(LANGUAGE, language);
                //restartActivity();
                recreate();
            }
        });
        alertBuilder.setNegativeButton(getString(R.string.no), null);
        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
