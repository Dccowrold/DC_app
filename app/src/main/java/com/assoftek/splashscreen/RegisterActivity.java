package com.assoftek.splashscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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

public class RegisterActivity extends AppCompatActivity {
    Button register;
    EditText name, email, password;
    TextView loginLink;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        register = findViewById(R.id.btnSignUp);
        name = findViewById(R.id.etName);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        //loginLink = findViewById(R.id.loginLink);
        sharedPref = getSharedPreferences("PREFERENCE", MODE_PRIVATE);

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
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(userEmail);
        registerRequest.setName(userName);
        registerRequest.setPassword(userPassword);
        Call<RegisterResponse> call = RetrofitClient.getService().register(registerRequest);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse = response.body();
                Log.d("response", response.toString());
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "SignUp Successful", Toast.LENGTH_LONG).show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Storing to Room Database
                            //saveUser(userEmail, userPassword, userName);

                            Intent intent = new Intent(RegisterActivity.this, detailsActivity.class);
                            intent.putExtra("username", registerResponse.getName());
                            sharedPref.edit().putBoolean(getString(R.string.isLoggedIn), true).apply();
                            sharedPref.edit().putBoolean(getString(R.string.firstTime), false).apply();
                            startActivity(intent);
                            finish();
                        }
                    }, 700);

                } else {
                    Toast.makeText(RegisterActivity.this, "SignUp Failed", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }

    private void saveUser(String email, String password, String username) {

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        User user = new User();
        user.email = email;
        user.password = password;
        user.username = username;

        db.userDao().insertUser(user);

    }

}
