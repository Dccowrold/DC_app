package com.example.signin;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity {
	TextView pN;
	TextView pE;
	@SuppressLint("StaticFieldLeak")
	static ImageView img;
	Button btnSignOut;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);


		btnSignOut = findViewById(R.id.btnSignOut);
		pN = findViewById(R.id.pN);
		pE = findViewById(R.id.pE);
		img = findViewById(R.id.img);
		pN.setText(MainActivity.personName);
		pE.setText(MainActivity.personEmail);

		String photoUrl = MainActivity.personPhoto;
		try {
			Glide.with(this).load(photoUrl).into(img);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "User image not available !", Toast.LENGTH_SHORT).show();
		}

		btnSignOut.setOnClickListener(v -> {
			try {
				MainActivity.mAuth.signOut();
				Toast.makeText(this, "Sign Out Successfull !", Toast.LENGTH_SHORT).show();
				startActivity(new Intent(SecondActivity.this, MainActivity.class));
			}catch (Exception e){
				Toast.makeText(this, "Error in sign out !", Toast.LENGTH_SHORT).show();
				
			}
		});


	}


	@Override
	public void onBackPressed()
	{

		finishAffinity();

		return;
	}


}

