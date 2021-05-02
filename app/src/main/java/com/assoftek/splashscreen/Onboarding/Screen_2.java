package com.assoftek.splashscreen.Onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.assoftek.splashscreen.SignUp.SignUp;
import com.assoftek.splashscreen.R;
import com.bumptech.glide.Glide;

public class Screen_2 extends AppCompatActivity {

    private TextView textView;
    boolean callH = true;
    ImageView imageView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_2);
        imageView1 = findViewById(R.id.image);
        textView=findViewById(R.id.tv2);

        Glide.with(this).load(R.drawable.four).into(imageView1);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Screen_2.this, SignUp.class);
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
        },4000);


    }
}