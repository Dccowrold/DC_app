package com.assoftek.splashscreen.Splash_Screen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.assoftek.splashscreen.DashboardActivity;
import com.assoftek.splashscreen.Login.login;
import com.assoftek.splashscreen.Onboarding.OnBoardingScreen;
import com.assoftek.splashscreen.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private SharedPreferences sharedPref ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        sharedPref=getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        //hide actionbar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //check if application is opened for the first time
        boolean isLogin = sharedPref.getBoolean(getString(R.string.isLoggedIn), false);
        boolean firsttimeLogin=sharedPref.getBoolean(getString(R.string.firstTime),true);
        if (isLogin) {
            Intent in = new Intent(MainActivity.this, DashboardActivity.class);
            startActivity(in);
            finish();

        }
        else if(!firsttimeLogin){
            Intent in = new Intent(MainActivity.this, login.class);
            startActivity(in);
            finish();
        } else{
            new Handler(getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, OnBoardingScreen.class);
                    startActivity(intent);
                    finish();
                }
            }, 4000);

        }
    }

    private void checkLogin() {

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.isLoggedIn), false);
        editor.apply();

    }
}
