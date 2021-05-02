package com.assoftek.splashscreen.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.assoftek.splashscreen.Login.login;
import com.assoftek.splashscreen.R;

public class User_Detail extends AppCompatActivity {

    Button button_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__detail);

        button_detail=findViewById(R.id.btn_detail);
        button_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_Detail.this, login.class);
                startActivity(intent);
            }
        });
    }
}