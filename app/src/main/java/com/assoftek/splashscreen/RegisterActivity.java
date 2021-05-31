package com.assoftek.splashscreen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    Button register;
    EditText name, email, password;
    TextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        register = findViewById(R.id.btnSignup);
        name = findViewById(R.id.edusername);
        email = findViewById(R.id.edemailregister);
        password = findViewById(R.id.edpasswordregister);
        loginLink = findViewById(R.id.loginLink);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userRegister();
            }
        });

    }

    public void userRegister() {

        String userEmail = email.getText().toString();
        String userName = name.getText().toString();
        String userPassword = password.getText().toString();

        if ((userEmail.isEmpty()) || (userName.isEmpty()) || (userPassword.isEmpty())) {
            Toast.makeText(RegisterActivity.this, "All fields required.", Toast.LENGTH_SHORT).show();
        }

        Call<RegisterResponse> call = RetrofitClient.getInstance().getApi().register(userEmail, userName, userPassword);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse( Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse = response.body();
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, registerResponse.getEmail(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RegisterActivity.this, "error", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }
}

