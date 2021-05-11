package com.assoftek.splashscreen.Onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.assoftek.splashscreen.Login.login;
import com.assoftek.splashscreen.R;
import com.assoftek.splashscreen.databinding.ActivityScreen3Binding;
import com.bumptech.glide.Glide;
import com.facebook.login.Login;

public class Screen_3 extends AppCompatActivity {

    ActivityScreen3Binding binding;
    boolean callH=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityScreen3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        Glide.with(this).load(R.drawable.two).into(binding.image);

        binding.tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Screen_3.this, login.class);
                startActivity(intent);
                finish();
                callH=false;
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (callH==true) {
                    Intent intent = new Intent(Screen_3.this,Screen_4.class);
                    startActivity(intent);

                }
            }
        },6000);


    }
}