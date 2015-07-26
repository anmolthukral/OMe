package com.ome.akashsachdeva.ome;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class nav_drawer_activity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    Toolbar toolbar;
    MyDBHandler mydb;
    TextView printcredit;
    MyDBHandler_debit mydb2;
    TextView printdebit;
    // ImageButton FAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nav_drawer_activity);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        mydb = new MyDBHandler(this);
        printcredit=(TextView)findViewById(R.id.textView5);
        mydb2 = new MyDBHandler_debit(this);
        printdebit=(TextView)findViewById(R.id.finaldebit);
         printdb_credit();
        printdb_debit();
        // toolbar code
        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        */
        //FAB code
       /* FAB = (ImageButton) findViewById(R.id.imageButton);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getApplicationContext(),"Button clicked",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(nav_drawer_activity.this, SecondActivity.class);
                startActivity(i);


            }
        });*/


    }

    public void whatsappme(View v){
        Intent i= new Intent();
        i.setPackage("com.whatsapp");
        i.setAction(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_TEXT, "Please share this app,i.e:http://www.mediafire.com/download/ybklpqudfqioc0q/ome_final.apk");
        i.setType("text/plain");
        startActivity(i);
    }

    public void fbme(View v){
        String message = "Hi guys, pl share this wonderful app http://www.mediafire.com/download/ybklpqudfqioc0q/ome_final.apk";
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(share, "Facebook Share"));

    }
    public void tweetme(View v){
        String message = "Hi guys, pl share this wonderful app http://www.mediafire.com/download/ybklpqudfqioc0q/ome_final.apk";
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(share, "Twitter Share"));
    }
    public void emame(View v){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "App Share");
        emailIntent.putExtra(Intent.EXTRA_TEXT,"Hi guys, pl share this wonderful app http://www.mediafire.com/download/ybklpqudfqioc0q/ome_final.apk");
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));

    }
    public void gpme(View v){

        String message = "Hi guys, pl share this wonderful app http://www.mediafire.com/download/ybklpqudfqioc0q/ome_final.apk";
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(share, "Google+ Share"));

    }
    public void printdb_credit(){
        Integer dbString = mydb.dbtostr();
        String str = dbString.toString();
        printcredit.setText(str);
    }
    public void printdb_debit(){
        Integer dbString = mydb2.dbtostr();
        String str = dbString.toString();
        printdebit.setText(str);
    }


    public void creditbutton(View v){
        Intent i = new Intent(nav_drawer_activity.this, credit_activity.class);
        startActivity(i);
    }

    public void debitbutton(View v){
        Intent i = new Intent(nav_drawer_activity.this, debit_activity.class);
        startActivity(i);
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment objFragment=null;

        switch(position){
            case 0:
                Intent a = new Intent(nav_drawer_activity.this, credit_activity.class);
                startActivity(a);
                break;
            case 1:
                Intent b = new Intent(nav_drawer_activity.this, debit_activity.class);
                startActivity(b);
                break;

             case 2:
                Intent c = new Intent(nav_drawer_activity.this, about_activity.class);
                startActivity(c);
                break;
        }



        // update the main content by replacing fragments
       /*FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container,objFragment)
                .commit();*/
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
          //  case 3:
              //  mTitle = getString(R.string.title_section3);
               // break;
            case 3:
                mTitle = getString(R.string.title_section4);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.nav_drawer_activity, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_nav_drawer_activity, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((nav_drawer_activity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
