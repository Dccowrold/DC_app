package com.assoftek.splashscreen.homePage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.assoftek.splashscreen.DashboardActivity;
import com.assoftek.splashscreen.LoginActivity;
import com.assoftek.splashscreen.NotificationActivity;
import com.assoftek.splashscreen.R;
import com.assoftek.splashscreen.RegisterActivity;
import com.assoftek.splashscreen.detailsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class HomePage extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private SharedPreferences sharedPref ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Objects.requireNonNull(getSupportActionBar()).hide();
        sharedPref=getSharedPreferences("PREFERENCE", MODE_PRIVATE);

        BottomNavigationView nav=findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_layout,new Wealth_tab()).commit();

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Wealth_Tab_option:
                        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_layout,new Wealth_tab()).commit();
                        break;
                    case R.id.Cash_Tab_option:
                        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_layout,new Cash_tab()).commit();
                        break;
                    case R.id.Pay_Tab_option:
                        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_layout,new Pay_tab()).commit();
                        break;
                }
                return false;
            }
        });

        {
            NavigationView navigationView = findViewById(R.id.navigationDrawer);
            navigationView.setNavigationItemSelectedListener(item -> {
                switch (item.getItemId()) {
                    case R.id.menu_profile:
                        Intent intent1=new Intent(HomePage.this, detailsActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.menu_visibility:
                        Toast.makeText(HomePage.this,"Visibility", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menu_reminder:
                        Intent i=new Intent(HomePage.this, NotificationActivity.class);
                        startActivity(i);
                        break;

                    case R.id.menu_logout:
                        //sharedPref.edit().putBoolean(getString(R.string.isLoggedIn),false).apply();
                        sharedPref.edit().putBoolean(getString(R.string.isLoggedIn), false).apply();
                        Intent intent=new Intent(HomePage.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            });

            Toolbar toolbar = findViewById(R.id.toolBar);
            drawerLayout = findViewById(R.id.drawer_layout);
            toolbar.setTitle("DC");

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

//            View headerView = navigationView.getHeaderView(0);
//            TextView name_nav = headerView.findViewById(R.id.username_nav);
//            name_nav.setText(RegisterActivity.sharedPref.getString("Username","USERNAME"));

        }
    }
}