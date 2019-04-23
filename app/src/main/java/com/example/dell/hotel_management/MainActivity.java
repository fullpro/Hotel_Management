package com.example.dell.hotel_management;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    ViewPager viewPager;
    LinearLayout sliderdotpanel;
    private  int DotsCount;
    private ImageView[] dots;

    List<Room> fisrtRoom;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fisrtRoom = new ArrayList<>();
        fisrtRoom.add(new Room("Suit","A large window with beautiful views of the Plaza de España and María Luisa Park" +
                " is part of the sophisticated charm of this 37 m2 room, " +
                "along with a bathroom with a relaxing whirlpool bathtub separated from the bedroom by a sliding glass door.",R.drawable.hotel_room1));

        fisrtRoom.add(new Room("Villa","Enjoy unlimited experiences in this 51 m2 room with a lounge " +
                "and amazing windows offering beautiful views over the Plaza de España and hotel pool," +
                " plus a bedroom with a double bed or two single beds and a full bathroom.",R.drawable.hotel_room2));

        fisrtRoom.add(new Room("ECONOMY SINGLE ROOM","Spacious, bright and outward facing rooms measuring 19 m2 and totally refurbished." +
                " The room comes with Dreamax bed (manufactured and designed exclusively by Flex for Meliá Hotels International), a modern, fully equipped bathroom finished in top quality bronze coloured ceramics and an independent entrance." +
                " It also has a home automation system which automatically regulates the temperature of the room based on guest presence or absence from the room. They also provide an intelligent temperature management " +
                "system that automatically adjusts the temperature depending on whether anyone is in the room.",R.drawable.hotel_room3));

        fisrtRoom.add(new Room("Premium Room","Spacious, bright and outward facing rooms measuring 27 m2 and totally refurbished. The room comes with double bed or twin beds with Dreamax mattress (manufactured and designed exclusively by Flex for Meliá Hotels International)," +
                " a modern, fully equipped bathroom finished in top quality bronze coloured ceramics and an independent hallway-dressing area. " +
                "It also has a home automation system which automatically regulates the temperature of the room based on guest presence or absence from the room. " +
                "All the rooms have a magnificent hallway-dressing area, as well as a room off the bathroom, which is independent of the room as it has double doors.",R.drawable.hotel_room4));


        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, fisrtRoom);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);






        viewPager = (ViewPager)findViewById(R.id.viewPager);
        sliderdotpanel = (LinearLayout)findViewById(R.id.SliderDots);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);
        DotsCount = viewPagerAdapter.getCount();
        dots = new ImageView[DotsCount];

        for(int i = 0; i < DotsCount; i++){
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);

            sliderdotpanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionoffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< DotsCount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000,3000);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        //Toggle button to open drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);


        } else {
            if(getSupportFragmentManager().getBackStackEntryCount() == 0){
                finish();

            }else {
                getSupportFragmentManager().popBackStackImmediate();

            }

               //super.onBackPressed();

        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left,
                    R.anim.slide_out_right, R.anim.slide_in_right)
                    .add(R.id.fragment_container,new ProfileFragment())
                    .addToBackStack(null).commit();


        } else if (id == R.id.nav_login) {

            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left,
                    R.anim.slide_out_right, R.anim.slide_in_right)
                    .add(R.id.fragment_container,new LoginFragment())
                    .addToBackStack(null).commit();



        } else if (id == R.id.nav_settings) {

            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left,
                    R.anim.slide_out_right, R.anim.slide_in_right)
                    .add(R.id.fragment_container,new SettingsFragment())
                    .addToBackStack(null).commit();


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_about) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    if(viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);

                    }else if(viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }else if(viewPager.getCurrentItem() == 2){
                        viewPager.setCurrentItem(3);
                    }else if(viewPager.getCurrentItem() ==3){
                        viewPager.setCurrentItem(4);
                    }else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

}
