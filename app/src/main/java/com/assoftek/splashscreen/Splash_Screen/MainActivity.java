package com.assoftek.splashscreen.Splash_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;


import com.assoftek.splashscreen.Login.login;
import com.assoftek.splashscreen.SignUp.Otp_verify;
import com.assoftek.splashscreen.SignUp.SignUp;
import com.assoftek.splashscreen.Onboarding.Screen_1;
import com.assoftek.splashscreen.R;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hide actionbar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //check if application is opened for the first time
        SharedPreferences sharedPreferences = getSharedPreferences("PERFERNCE", MODE_PRIVATE);
        String Firsttime = sharedPreferences.getString("FirstTimeInstall", "");


        if (Firsttime.equals("Yes")) {
            //creating thread
           Runnable runnable = new Runnable() {
               @Override
               public synchronized void run() {
                   try {
                       sleep(3000);

                   } catch (Exception e) {
                       e.printStackTrace();
                   } finally {
                       Intent intent = new Intent(MainActivity.this, SignUp.class);
                       startActivity(intent);
                       finish();
                   }
               }
               };

           new Thread(runnable).start();
           }

        else{

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("FirstTimeInstall","Yes");
            editor.apply();


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, Screen_1.class);
                    startActivity(intent);
                }
            },4000);

       }
        }
    }
