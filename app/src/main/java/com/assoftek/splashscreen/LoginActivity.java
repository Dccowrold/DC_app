package com.assoftek.splashscreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.assoftek.splashscreen.db.AppDatabase;
import com.assoftek.splashscreen.db.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText email, password;
    TextView registerLink;
    String userName, emailID;
    private SharedPreferences sharedPref;
    SharedPreferences mypref;

    public static final String FileName = "login";
    public static final String Username = "username";
    public static final String Pass = "password";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnsignin);
        email = findViewById(R.id.edemailsignin);
        password = findViewById(R.id.edpasswordsignin);
        registerLink = findViewById(R.id.registerLink);
        sharedPref = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        getSupportActionBar().hide();

        mypref = getSharedPreferences(FileName , Context.MODE_PRIVATE);
        if(mypref.contains(Username)) {
            Intent i = new Intent(LoginActivity.this , DashboardActivity.class);
            startActivity(i);
        }


        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Username / Password Required", Toast.LENGTH_LONG).show();
                } else {
                    //proceed to login
                    login();
                }

            }
        });


    }


    public void login() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email.getText().toString());
        loginRequest.setPassword(password.getText().toString());

        Call<LoginResponse> loginResponseCall = RetrofitClient.getService().signin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d("error", response.toString());
                if (response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    LoginResponse loginResponse = response.body();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Storing to Room Database
                            //saveUser(email.getText().toString(), password.getText().toString());

                            Intent intent = new Intent(LoginActivity.this, detailsActivity.class);
                            userName=loginResponse.getName();
                            emailID=loginResponse.getEmail();
                            intent.putExtra("username",userName);
                            intent.putExtra("emailID",emailID);
                            SharedPreferences.Editor editor = mypref.edit();
                            editor.putString(Username , email.getText().toString());
                            editor.putString(Pass , password.getText().toString());
                            editor.commit();

                            sharedPref.edit().putBoolean(getString(R.string.isLoggedIn), true).apply();
                            sharedPref.edit().putBoolean(getString(R.string.firstTime), false).apply();
                            startActivity(intent);
                            finish();
                        }
                    }, 700);

                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

    private void saveUser(String email, String password) {

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        User user = new User();
        user.email = email;
        user.password = password;
        user.username = userName;

        db.userDao().insertUser(user);

    }

}