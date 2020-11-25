package com.example.yukiv3.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.yukiv3.R;
import com.example.yukiv3.fragment.HomeFragment;
import com.example.yukiv3.fragment.PlaylistFragment;
import com.example.yukiv3.fragment.ProfileFragment;
import com.example.yukiv3.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    //default constructor to create the homeFragment class
    //these are all java class files
    private HomeFragment homeFragment = new HomeFragment();
    private PlaylistFragment playlistFragment = new PlaylistFragment();
    private SearchFragment searchFragment = new SearchFragment();
    private ProfileFragment profileFragment = new ProfileFragment();

    private BottomNavigationView menuBar;

//views are xml files

    //so far thie basically switches it between the four different views on click

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting the starting xml to be activity main

        setContentView(R.layout.activity_main);

//sets the menuBar as the BottomNavigation view linking to the xml
        //and this is when we set the menu bar
        menuBar = findViewById(R.id.menu_bar);
//sets the fragment as homeFragment the java file
        //home fragment the java file is linked with home_fragment the xml file
        //so the home fragment frame is on the screen

        setFragment(homeFragment);

        //and the menu bar is set a the bottom
        //here is when they set the menu item in the menu bar to be menu home
        //that was set because the menu item is in the menu bar
        menuBar.setSelectedItemId(R.id.menu_home);

        //we set an on click listner here
        //when the item is selected
        //if its checked return true
        //if not if they click menu playlist itll set the fragment to it
        menuBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            //they're all called menu items so the one selected is the one being used

            //
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //each menu item in the menu bar is a specific made for it
                if (menuItem.isChecked() ){
                    return true;
                } else {
                    switch(menuItem.getItemId()){
//if menu_home is selected it'll set it as the home fragment
                        case R.id.menu_home:
                            setFragment(homeFragment);
                     //       getSupportActionBar().setTitle("Home");
                            return true;
                        case R.id.menu_playlist:
                            setFragment(playlistFragment);
                     //       getSupportActionBar().setTitle("Playlist");
                            return true;
                        case R.id.menu_search:
                            setFragment(searchFragment);
                           // getSupportActionBar().setTitle("Search");
                            return true;
                        case R.id.menu_profile:
                            setFragment(profileFragment);
                            //support action bar doesnt work because i disabled it
                    //        getSupportActionBar().setTitle("Profile");
                            return true;
                        default:
                            setFragment(homeFragment);
                     //       getSupportActionBar().setTitle("Home");
                            return true;


                    }


                }
            }
        });



    }

    //setting the fragment to home fragment
    private void setFragment(Fragment fragment) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame, fragment);
        ft.commit();



    }
}
