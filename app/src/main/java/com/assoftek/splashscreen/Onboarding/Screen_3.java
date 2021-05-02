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

public class Screen_3 extends AppCompatActivity {

    private TextView textView;
    boolean callH=true;
    ImageView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_3);
        view=findViewById(R.id.img);
        textView=findViewById(R.id.tv3);

        Glide.with(this).load(R.drawable.two).into(view);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Screen_3.this, SignUp.class);
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