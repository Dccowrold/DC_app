package com.assoftek.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btnsignin;
    EditText edemail, edpassword ;
    TextView noAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnsignin = findViewById(R.id.btnsignin);
        edemail = findViewById(R.id.edemailsignin);
        edpassword = findViewById(R.id.edpasswordsignin);
        noAccount = findViewById(R.id.registerLink);


        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edemail.getText().toString())
                        || TextUtils.isEmpty(edpassword.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "All fields required.", Toast.LENGTH_SHORT).show();
                } else {
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setEmail(edemail.getText().toString());
                    loginRequest.setPassword(edpassword.getText().toString());
                    loginuser(loginRequest);
                }
            }
        });

    }

    public void loginuser(LoginRequest loginRequest) {
        Call<LoginResponse> loginResponseCall = RetrofitClient.getInstance().getApi().loginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Login successful.", Toast.LENGTH_SHORT).show();
                   // startActivity(new Intent(LoginActivity.this, Dashboard.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Unable to Login.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }

    });

    }
}
