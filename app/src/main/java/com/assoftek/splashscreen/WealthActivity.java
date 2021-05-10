package com.assoftek.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.assoftek.splashscreen.databinding.ActivityScreen1Binding;
import com.assoftek.splashscreen.databinding.ActivityWealth2Binding;

public class WealthActivity extends AppCompatActivity {

   ActivityWealth2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityWealth2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(WealthActivity.this, DashboardActivity.class);
                startActivity(i);
            }
        });

    }
}