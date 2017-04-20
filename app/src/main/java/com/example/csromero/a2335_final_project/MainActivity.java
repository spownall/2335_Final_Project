package com.example.csromero.a2335_final_project;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements KitchenControl.OnFragmentListItemSelectedListener, OnItemSelectedListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    
    // reference to the kitchen control fragment
    private KitchenControl kitchenControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
     * By implementing the FragmentListItemSelected listener, the class
     * must implement this function.  The parameter is the position of the list
     * item that was selected in the fragment.
     * @param position
     */
 
    @Override
    public void onFragmentListItemSelected(int position) {
        String newName = "";
        String newType = "";
        
        switch (position){
            // TODO Create these UI classes
            case 0:
                Intent intent = new Intent(this, MicrowaveControl.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent1 = new Intent(this, FridgeControl.class);
                startActivity(intent1);
                break;
            case 2:
                Intent intent2 = new Intent(this, LightControl.class);
                startActivity(intent2);
                break;
            case 3:
                // This is the "Add new option" case
                // creates a custom dialog to demand the user for a new entry
                final Dialog dialog = new Dialog(context);
			    dialog.setContentView(R.layout.custom);
			    dialog.setTitle("Add New Item");

			    // initialize the custom dialog components
			    TextView text = (TextView) dialog.findViewById(R.id.text);
			    text.setText("Choose an item to add to the list: ");
                
                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.kitchen_items_list, android.R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(this);
                
                EditText edit = (EditText) findViewById(R.id.edit);

			    Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
			    // if button is clicked, close the custom dialog and add any new items to the list
			    dialogButton.setOnClickListener(new OnClickListener() {
				    @Override
				    public void onClick(View v) {
                        // update the listview with the new entry
                        kitchenControl.kitchenItemNameList.add(newType);
                        kitchenControl.kitchenAdapter.notifyDataSetChanged();
				    	dialog.dismiss();
				    }
			    });
                dialog.show();
                break;
        }
        
    public void onItemSelected(AdapterView<?> parent, View view,
            int pos, long id) {
        // An item in the dialog list was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        newName = dialog.edit.getText();
        newType = parent.getItemAtPosition(pos).getText();
    }

    public void onNothingSelected(AdapterView<?> parent) {  
        
    }
        
}
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

        public PlaceholderFragment() {
        }

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

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        // getItem is called to instantiate the fragment for the given page.
        public Fragment getItem(int position) {
            if (position >= 1){
                // Return a PlaceholderFragment (defined as a static inner class below).
                return PlaceholderFragment.newInstance(position + 1);
            } else {
                // return a kitchen control activity fragment and keep the reference for later use
                kitchenControl = KitchenControl.newInstance();
                return kitchenControl;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Kitchen";
                case 1:
                    return "Automobile";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
