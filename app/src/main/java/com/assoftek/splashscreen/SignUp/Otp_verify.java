package com.assoftek.splashscreen.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.assoftek.splashscreen.R;

public class Otp_verify extends AppCompatActivity {

    Button button_verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);

        button_verify = findViewById(R.id.btn_verify);

        button_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Otp_verify.this,User_Detail.class);
                startActivity(intent);
            }
        });

    }
}