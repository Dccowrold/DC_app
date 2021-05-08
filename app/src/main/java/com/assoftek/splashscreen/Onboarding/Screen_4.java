package com.assoftek.splashscreen.Onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.assoftek.splashscreen.Login.Login;
import com.assoftek.splashscreen.R;
import com.assoftek.splashscreen.databinding.ActivityScreen4Binding;
import com.bumptech.glide.Glide;

public class Screen_4 extends AppCompatActivity {

    ActivityScreen4Binding binding;
    Boolean callH=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityScreen4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Glide.with(this).load(R.drawable.three).into(binding.image);

         binding.tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Screen_4.this, Login.class);
                startActivity(intent);
                finish();
                callH=false;
            }
        });

    }
}