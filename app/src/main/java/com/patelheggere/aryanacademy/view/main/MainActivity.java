package com.patelheggere.aryanacademy.view.main;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.patelheggere.aryanacademy.R;
import com.patelheggere.aryanacademy.base.BaseActivity;
import com.patelheggere.aryanacademy.fragments.aboutus.AboutFragment;
import com.patelheggere.aryanacademy.fragments.currentaffairs.CurrentAffairsFragment;
import com.patelheggere.aryanacademy.fragments.jobs.JobUpdatesFragment;
import com.patelheggere.aryanacademy.helper.SharedPrefsHelper;
import com.patelheggere.aryanacademy.view.events.EventsActivity;
import com.patelheggere.aryanacademy.view.exam.ExamActivity;
import com.patelheggere.aryanacademy.view.gallery.GalleryActivity;

import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.EMAIL;
import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.LANGUAGE;
import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.MOBILE;
import static com.patelheggere.aryanacademy.helper.AppUtils.Constants.NAME;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        CurrentAffairsFragment.OnFragmentInteractionListener,
        JobUpdatesFragment.OnFragmentInteractionListener,
        AboutFragment.OnFragmentInteractionListener {
    private static final String TAG = "MainActivity";
    private static final int CALL_REQ_CODE = 1 ;
    private android.support.v7.app.AlertDialog alertDialog;
    private TextView phone;

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
        View header = navigationView.getHeaderView(0);
        TextView name = (TextView) header.findViewById(R.id.tv_name);
        TextView email = (TextView) header.findViewById(R.id.tv_email);
        if (SharedPrefsHelper.getInstance().get(NAME, null) != null) {
            name.setText(SharedPrefsHelper.getInstance().get(NAME).toString());
        }
        if (SharedPrefsHelper.getInstance().get(EMAIL, null) != null) {
            email.setText(SharedPrefsHelper.getInstance().get(EMAIL).toString());
        } else if (SharedPrefsHelper.getInstance().get(EMAIL, null) == null) {
            email.setText(SharedPrefsHelper.getInstance().get(MOBILE).toString());
        }
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

    /*
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
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right);
            Fragment curFrag = fragmentManager.getPrimaryNavigationFragment();
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
        } else if (id == R.id.nav_events) {
            Intent intent = new Intent(context, EventsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(context, GalleryActivity.class);
            startActivity(intent);
            //overridePendingTransition();
        } else if (id == R.id.nav_feedback) {
            sendEmail();
        } else if (id == R.id.nav_share) {
            shareApp();
        } else if (id == R.id.nav_contact_us) {
            contactUS();
        } else if (id == R.id.nav_language) {
            showAlert();
        }
        else if(id==R.id.nav_exam)
        {
            showExamScreen();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showExamScreen() {
        Intent intent = new Intent(context, ExamActivity.class);
        startActivity(intent);
    }

    private void sendEmail()
    {
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{getString(R.string.email_adds)});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "FeedBack");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Add Message here");

        emailIntent.setType("message/rfc822");

        try {
            startActivity(Intent.createChooser(emailIntent,
                    "Send email using..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "No email clients installed.",
                    Toast.LENGTH_SHORT).show();
        }

    }
    private void requestCallPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CALL_PHONE},
                CALL_REQ_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CALL_REQ_CODE) {
            if (resultCode == RESULT_OK) {
                callPhoneNumber(phone.getText().toString());
            }
        } 
    }
    private boolean isCallPermissionRPermitted() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }
    private void showAlert() {
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_alert_layout, null);
        builder.setView(view);

        TextView textViewTitle = view.findViewById(R.id.tv_cant_cashout);
        // textViewTitle.setText(title);
        RadioGroup radioGroup;
        RadioButton radioButtonKannada, radioButtonEnglish;

        radioGroup = view.findViewById(R.id.rg_language);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_kannada) {
                    alertDialog.dismiss();
                    showConfirmationDialog("ka");

                } else if (i == R.id.rb_english) {
                    alertDialog.dismiss();
                    showConfirmationDialog("en");
                }

            }

        });

        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

    }


    private void contactUS() {
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.contact_us, null);
        builder.setView(view);

        TextView textViewTitle = view.findViewById(R.id.tv_cant_cashout);
        Button btn = view.findViewById(R.id.btn_ok);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
       phone = view.findViewById(R.id.phone_number);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                if(isCallPermissionRPermitted()) {
                    callPhoneNumber(phone.getText().toString());
                }
                else {
                    requestCallPermission();
                }
            }
        });
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

    }

    private void callPhoneNumber(String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }


    private void shareApp() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_message));
        //shareIntent.putExtra(Intent.EXTRA_STREAM, R.drawable.finaled);
        shareIntent.setType("text/plain");
        //shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            startActivity(Intent.createChooser(shareIntent, getString(R.string.share_us_via)));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(activity, getString(R.string.no_app_to_share), Toast.LENGTH_LONG).show();
        }
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
