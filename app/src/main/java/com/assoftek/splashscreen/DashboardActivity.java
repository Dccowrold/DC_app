package com.assoftek.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.assoftek.splashscreen.Login.Login;
import com.assoftek.splashscreen.databinding.ActivityDashboardBinding;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {
    FirebaseAuth auth;
    ActivityDashboardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.wealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashboardActivity.this, WealthActivity.class);
                startActivity(intent);
            }
        });

        binding.cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashboardActivity.this, CashScreenActivity.class);
                startActivity(intent);
            }
        });


        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();                                                                  // user logout
                Intent intent=new Intent(DashboardActivity.this, Login.class);        // going back to sign in
                startActivity(intent);
            }
        });
    }
}