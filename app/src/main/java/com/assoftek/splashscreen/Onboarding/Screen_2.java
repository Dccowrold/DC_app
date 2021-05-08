package com.assoftek.splashscreen.Onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.assoftek.splashscreen.Login.Login;
import com.assoftek.splashscreen.R;
import com.assoftek.splashscreen.databinding.ActivityScreen2Binding;
import com.bumptech.glide.Glide;

public class Screen_2 extends AppCompatActivity {

    ActivityScreen2Binding binding;
    boolean callH = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityScreen2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this).load(R.drawable.one).into(binding.image);

        binding.tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Screen_2.this, Login.class);
                startActivity(intent);
                finish();
                callH=false;
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (callH==true) {
                    Intent intent = new Intent(Screen_2.this,Screen_3.class);
                    startActivity(intent);

                }
            }
        },6000);

    }
}