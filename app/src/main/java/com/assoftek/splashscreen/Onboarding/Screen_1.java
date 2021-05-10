package com.assoftek.splashscreen.Onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.assoftek.splashscreen.R;
import com.assoftek.splashscreen.databinding.ActivityScreen1Binding;
import com.bumptech.glide.Glide;
import com.facebook.login.Login;

public class Screen_1 extends AppCompatActivity {

    ActivityScreen1Binding binding;
    boolean callH = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityScreen1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        Glide.with(Screen_1.this).load(R.drawable.four).into(binding.image);

        binding.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Screen_1.this, Login.class);
                startActivity(intent);
                finish();
                callH=false;
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (callH==true) {
                    Intent intent = new Intent(Screen_1.this,Screen_2.class);
                    startActivity(intent);

                }
            }
        },6000);
    }
}