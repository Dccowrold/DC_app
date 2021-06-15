package com.assoftek.splashscreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.assoftek.splashscreen.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {

    ActivityDashboardBinding binding;
    private SharedPreferences sharedPref;
    SharedPreferences mypref;

    public static final String FileName = "login";
    public static final String Username = "username";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDashboardBinding.inflate(getLayoutInflater());
        sharedPref= getSharedPreferences("PREFERENCE", MODE_PRIVATE);

        setContentView(binding.getRoot());

        mypref = getSharedPreferences(FileName , Context.MODE_PRIVATE);
//        if(mypref.contains(Username)) {
//            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
//        }

        binding.profileName.setText(getIntent().getStringExtra("username"));

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


             binding.pay.setOnClickListener(new View.OnClickListener() {
               @Override
             public void onClick(View view) {
                Intent movetoPayments = new Intent(DashboardActivity.this, PaymentsActivity.class);
                startActivity(movetoPayments);
             }
          });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.dash , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.profile:
                Intent intent1=new Intent(DashboardActivity.this, detailsActivity.class);
                startActivity(intent1);
                break;

            case R.id.visibility:
                Toast.makeText(DashboardActivity.this,"Visibility", Toast.LENGTH_SHORT).show();
                break;

            case R.id.notification:
                Intent i=new Intent(DashboardActivity.this, NotificationActivity.class);
                startActivity(i);
                break;

            case R.id.logout:
                //sharedPref.edit().putBoolean(getString(R.string.isLoggedIn),false).apply();
                Intent intent=new Intent(DashboardActivity.this, LoginActivity.class);        // going back to sign in
                startActivity(intent);
                //finish();
                break;
        }
        return true;
    }


}