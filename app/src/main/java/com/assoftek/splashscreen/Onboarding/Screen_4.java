package com.assoftek.splashscreen.Onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.assoftek.splashscreen.SignUp.SignUp;
import com.assoftek.splashscreen.R;
import com.bumptech.glide.Glide;

public class Screen_4 extends AppCompatActivity {

    Button button;
    Boolean callH=true;
    ImageView view2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_4);
        view2 = findViewById(R.id.imageView5);
        button=findViewById(R.id.btn);

        Glide.with(this).load(R.drawable.three).into(view2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Screen_4.this, SignUp.class);
                startActivity(intent);
                finish();
                callH=false;
            }
        });

    }
}