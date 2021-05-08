package com.assoftek.splashscreen.SignUp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.assoftek.splashscreen.Login.Login;
import com.assoftek.splashscreen.databinding.ActivitySignupBinding;


public class SignUp extends AppCompatActivity {

    ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i=new Intent(SignUp.this, Login.class);
                        startActivity(i);
                    }
                });


        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(binding.password.getText().toString().isEmpty())
                {
                    binding.password.setError("Enter your password!");
                    return;
                }

                if(binding.email.getText().toString().isEmpty())
                {
                    binding.password.setError("Enter your email!");
                    return;
                }

                if(!binding.checkbox.isChecked())
                {
                    Toast.makeText(SignUp.this, "Accept our terms and conditions!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent i=new Intent(SignUp.this,PhoneActivity.class);
                i.putExtra("email",binding.email.getText().toString());
                i.putExtra("password",binding.password.getText().toString());
                startActivity(i);
            }
        });



    }
}