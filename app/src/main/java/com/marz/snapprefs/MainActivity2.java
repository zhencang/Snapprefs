package com.marz.snapprefs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.view.MenuItem;

import com.marz.snapprefs.Tabs.BuyTabFragment;
import com.marz.snapprefs.Tabs.DataTabFragment;
import com.marz.snapprefs.Tabs.DeluxeTabFragment;
import com.marz.snapprefs.Tabs.FiltersTabFragment;
import com.marz.snapprefs.Tabs.GeneralTabFragment;
import com.marz.snapprefs.Tabs.MainTabFragment;
import com.marz.snapprefs.Tabs.SavingTabFragment;
import com.marz.snapprefs.Tabs.SharingTabFragment;
import com.marz.snapprefs.Tabs.SpoofingTabFragment;
import com.marz.snapprefs.Tabs.TextTabFragment;

import net.rdrei.android.dirchooser.DirectoryChooserActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity{
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    HashMap<Integer, Fragment> cache = new HashMap<>();
    public static final String PREF_KEY_SAVE_LOCATION = "pref_key_save_location";
    public static final String PREF_KEY_HIDE_LOCATION = "pref_key_hide_location";
    public static final String PREF_KEY_FILTER_LOCATION = "pref_key_filter_location";
    private static final int REQUEST_CHOOSE_DIR = 1;
    private static final int REQUEST_HIDE_DIR = 2;
    private static final int REQUEST_FILTER_DIR = 3;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setContentView(R.layout.activity_main2);

        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.fuckyou);
        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the MainTabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
//        mFragmentTransaction = mFragmentManager.beginTransaction();
//        mFragmentTransaction.replace(R.id.containerView,new MainTabFragment()).commit();
        mFragmentManager.beginTransaction().replace(R.id.containerView, getForId(R.id.nav_item_main)).commit();//it makes no sense ofc it doesnt, its java
        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                mFragmentManager.beginTransaction().replace(R.id.containerView,getForId(menuItem.getItemId())).commit();


//                if (menuItem.getItemId() == R.id.nav_item_main) {
//                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.containerView,new MainTabFragment()).commit();
//                }
//
//
//
//                if (menuItem.getItemId() == R.id.nav_item_buy) {
//                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.containerView,new BuyTabFragment()).commit();
//                }
//                if (menuItem.getItemId() == R.id.nav_item_deluxe) {
//                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.containerView,new DeluxeTabFragment()).commit();
//                }
//
//
//
//                if (menuItem.getItemId() == R.id.nav_item_general) {
//                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
//                    xfragmentTransaction.replace(R.id.containerView,new GeneralTabFragment()).commit();//CACHE EVERYTHING XD
//                }
//                if (menuItem.getItemId() == R.id.nav_item_saving) {
//                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
//                    xfragmentTransaction.replace(R.id.containerView,new SavingTabFragment()).commit();
//                }
//                if (menuItem.getItemId() == R.id.nav_item_text) {
//                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
//                    xfragmentTransaction.replace(R.id.containerView,new TextTabFragment()).commit();
//                }
//                if (menuItem.getItemId() == R.id.nav_item_spoofing) {
//                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
//                    xfragmentTransaction.replace(R.id.containerView,new SpoofingTabFragment()).commit();
//                }
//                if (menuItem.getItemId() == R.id.nav_item_sharing) {
//                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
//                    xfragmentTransaction.replace(R.id.containerView,new SharingTabFragment()).commit();
//                }
//                if (menuItem.getItemId() == R.id.nav_item_data) {
//                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
//                    xfragmentTransaction.replace(R.id.containerView,new DataTabFragment()).commit();
//                }
//                if (menuItem.getItemId() == R.id.nav_item_filters) {
//                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
//                    xfragmentTransaction.replace(R.id.containerView,new FiltersTabFragment()).commit();
//                }

                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }

    public Fragment getForId(int id) {//WTFWTFWTFWTFWTFWTFW no idea... its only gets initalized once..BaseFragmentActivityHoneycomb.java:34)...
        if (cache.get(id) == null) {
            switch (id) {
                case R.id.nav_item_buy:
                    cache.put(id, new BuyTabFragment());
                    break;
                case R.id.nav_item_main:
                    cache.put(id, new MainTabFragment());
                    break;
                case R.id.nav_item_deluxe:
                    cache.put(id, new DeluxeTabFragment());
                    break;
                case R.id.nav_item_general:
                    cache.put(id, new GeneralTabFragment());
                    break;
                case R.id.nav_item_saving:
                    cache.put(id, new SavingTabFragment());
                    break;
                case R.id.nav_item_text:
                    cache.put(id, new TextTabFragment());
                    break;
                case R.id.nav_item_spoofing:
                    cache.put(id, new SpoofingTabFragment());
                    break;
                case R.id.nav_item_sharing:
                    cache.put(id, new SharingTabFragment());
                    break;
                case R.id.nav_item_data:
                    cache.put(id, new DataTabFragment());
                    break;
                case R.id.nav_item_filters:
                    cache.put(id, new FiltersTabFragment());
                    break;
            }
        }
        return cache.get(id);
    }
    // Receives the result of the DirectoryChooserActivity
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CHOOSE_DIR) {
            if (resultCode == DirectoryChooserActivity.RESULT_CODE_DIR_SELECTED) {
                String newLocation = data.getStringExtra(DirectoryChooserActivity.RESULT_SELECTED_DIR);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(PREF_KEY_SAVE_LOCATION, newLocation);
                editor.apply();

                //Preference pref = PreferenceFragmentCompat.findPreference(PREF_KEY_SAVE_LOCATION);
                //pref.setSummary(newLocation);
            }
        }
        if (requestCode == REQUEST_HIDE_DIR) {
            if (resultCode == DirectoryChooserActivity.RESULT_CODE_DIR_SELECTED) {
                String newHiddenLocation = data.getStringExtra(DirectoryChooserActivity.RESULT_SELECTED_DIR);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(PREF_KEY_HIDE_LOCATION, "Last hidden:" + newHiddenLocation);
                editor.apply();

                writeNoMediaFile(newHiddenLocation);
                //Preference pref = findPreference(PREF_KEY_HIDE_LOCATION);
                //pref.setSummary("Last hidden:" + newHiddenLocation);
            }
        }
        if (requestCode == REQUEST_FILTER_DIR) {
            if (resultCode == DirectoryChooserActivity.RESULT_CODE_DIR_SELECTED) {
                String newFilterLocation = data.getStringExtra(DirectoryChooserActivity.RESULT_SELECTED_DIR);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(PREF_KEY_FILTER_LOCATION, newFilterLocation);
                editor.apply();

                writeNoMediaFile(newFilterLocation);
                //Preference pref = findPreference(PREF_KEY_FILTER_LOCATION);
                //pref.setSummary(newFilterLocation);
            }
        }
    }
    /**
     * @param directoryPath The full path to the directory to place the .nomedia file
     * @return Returns true if the file was successfully written or appears to already exist
     */
    public boolean writeNoMediaFile(String directoryPath) {
        String storageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(storageState)) {
            try {
                File noMedia = new File(directoryPath, ".nomedia");
                if (noMedia.exists()) {
                    return true;
                }
                FileOutputStream noMediaOutStream = new FileOutputStream(noMedia);
                noMediaOutStream.write(0);
                noMediaOutStream.close();
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }


}